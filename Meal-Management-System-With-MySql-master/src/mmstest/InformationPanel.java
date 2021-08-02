package mmstest;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;

class InformationPanel extends JPanel{
    JTextField mem_id;
    JTextField name;
    JTextField phone_no;   
    JTable infoTable;
    DefaultTableModel infoTableModel;
    Management management = new Management();
    public InformationPanel(){
        setLayout(new BorderLayout(0,0));
        
        JLabel lblinfo = new JLabel("Informations");
        lblinfo.setFont(new Font("Gabriola",Font.BOLD,30));
        lblinfo.setHorizontalAlignment(SwingConstants.CENTER);        
        add(lblinfo,BorderLayout.NORTH); 
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1,2,0,0));
        add(centerPanel,BorderLayout.CENTER);
        
        JPanel infoLeft = new JPanel();
        infoLeft.setLayout(null);
        centerPanel.add(infoLeft);    
        
        JLabel lblmem_id = new JLabel("Member ID  :");
        lblmem_id.setBounds(30,10,140,30);
        lblmem_id.setFont(new Font("Dialog",Font.BOLD,12));
        infoLeft.add(lblmem_id);
        
        mem_id = new JTextField();
        mem_id.setText("1001");
        mem_id.setBounds(105,10,170,30);
        mem_id.setFont(new Font("Dialog",Font.BOLD,14));
        infoLeft.add(mem_id);  
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30,60,60,30);
        infoLeft.add(lblname);
        
        name = new JTextField();
        name.setBounds(105,60,170,30);
        name.setFont(new Font("Dialog",Font.BOLD,14));
        infoLeft.add(name);
        
        JLabel lblphone_no = new JLabel("Phone N0");
        lblphone_no.setBounds(30,110,100,30);
        infoLeft.add(lblphone_no);
        
        phone_no = new JTextField();
        phone_no.setBounds(105,110,170,30);
        phone_no.setText("+8801");
        phone_no.setFont(new Font("Dialog",Font.BOLD,14));
        infoLeft.add(phone_no);
        
        JPanel infoRight = new JPanel();
        infoRight.setLayout(new BorderLayout(0,0));
        centerPanel.add(infoRight);
        
        JScrollPane infoScroll = new JScrollPane();
        infoRight.add(infoScroll,BorderLayout.CENTER);
        
        infoTable = new JTable();
        infoScroll.setViewportView(infoTable);
        loadTable();        
        
        JPanel infoSouth = new JPanel(); 
        
        JButton btnsaveInfo = new JButton("Save");
        btnsaveInfo.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent event){
               try{
                    String m_id = mem_id.getText();
                    String n = name.getText();
                    String p = phone_no.getText().toString(); 
                    String query = "insert into informations (member_id,name,phone_no) values ('"+m_id+"','"+n+"','"+p+"')";
                    if(m_id.length()>0&&n.length()>0&&p.length()>0){
                        System.out.println(query);
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/mmstest","root","");
                        Statement statement = connection.createStatement();  
                        statement.executeUpdate(query);
                        connection.close();
                        statement.close();
                        loadTable();
                    }
                    int m = Integer.parseInt(m_id);
                    m++;
                    m_id= Integer.toString(m);
                    mem_id.setText(m_id);
                    name.setText("");
                    phone_no.setText("+8801");
               }
               catch(ClassNotFoundException e){
                   e.printStackTrace();
               }
               catch(SQLException e){
                   e.printStackTrace();
               }                
//                    management.SaveInfoList(m_id, n,p);
//                    management.showInfo();/*Showing info list into console*/
//                    loadTable();
//                    int m = Integer.parseInt(m_id);
//                    m++;
//                    m_id= Integer.toString(m);
//                    mem_id.setText(m_id);
//                    phone_no.setText("+88");
//                }
//                else{
//                    JOptionPane.showMessageDialog(null,"Invalid!");
//                }
           } 
        });
        infoSouth.add(btnsaveInfo);
        add(infoSouth,BorderLayout.SOUTH);    
        
        JButton btndeleteInfo = new JButton("Delete");
        btndeleteInfo.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent event){
                try{
                    int count=0;
                    int index = infoTable.getSelectedRow()+1;
                    String QuerySelect = "select * from informations";
                    String selected=null;  
                    if(index>=0){
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/mmstest","root","");
                        Statement statement = connection.createStatement();
                        ResultSet result = statement.executeQuery(QuerySelect);
                        while(result.next()){
                            count+=1;
                            if(index==count){
                                selected = result.getString("member_id");
                            }
                        }
                        ResultSet result2 = result;
                        while(result2.next()){
                            management.SaveInfoList(result.getString("member_id"),result.getString("name"), 
                                result.getString("phone_no"));
                        }
                        System.out.println(selected);//
                        String QueryDelete = "delete from informations where member_id='"+selected+"'";
                        System.out.println(QueryDelete);//
                        statement.executeUpdate(QueryDelete);
                        loadTable();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Please select a row first");
                    }
                }
                catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }               
//                   management.infoList.remove(index);
//                    management.showInfo();/*Showing info list into Console*/
//                    loadTable();
//                }                
           } 
        });
        infoSouth.add(btndeleteInfo);
        add(infoSouth,BorderLayout.SOUTH);
        
        JButton infoToProcess = new JButton("Next >>");
        infoToProcess.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent event){
           } 
        });
        infoSouth.add(infoToProcess);     
        
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });
        infoSouth.add(exit);
    }
    public void loadTable(){
        String heading[] = {"Member ID","Name","Phone Number"};
        infoTableModel = new DefaultTableModel(heading,0);
        try{
            management.infoList.clear();
            String Query = "select * from informations";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/mmstest","root","");
            Statement statement = connection.createStatement(); 
                ResultSet result = statement.executeQuery(Query);
            
            while(result.next()){
                management.SaveInfoList(result.getString("member_id"),result.getString("name"), result.getString("phone_no"));
            }
            for(Informations info: management.infoList){
                Object[] objectInfo ={info.getMember_id(),info.getName(),info.getPhone_no()};
                infoTableModel.addRow(objectInfo);
            }

        }        
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }        
//        for(Informations info: management.infoList){
//            Object[] ObjInfo = {info.getMember_id(),info.getName(),info.getPhone_no()};
//            infoTableModel.addRow(ObjInfo);
//        }
        infoTable.setModel(infoTableModel);
    }
}

