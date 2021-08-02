package mmstest;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

class ProcessPanel extends JPanel{
    JTextField process_id;
    JTextField name;
    JTextField meal;
    JTextField expanditure;
    JTable processTable;
    DefaultTableModel processTableModel ;
    Management management = new Management();
    String mem_idString[];
    public ProcessPanel(){
        setLayout(new BorderLayout(0,0));
        
        JLabel lblprocess = new JLabel("Process");
        lblprocess.setFont(new Font("Gabriola",Font.BOLD,30));
        lblprocess.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblprocess,BorderLayout.NORTH);
        
        JPanel processCenter = new JPanel();
        processCenter.setLayout(new GridLayout(1,2,0,0));
        add(processCenter,BorderLayout.CENTER);
        
        JPanel processLeft = new JPanel();
        processLeft.setLayout(null);
        processCenter.add(processLeft);        
        
        JLabel lblmember_id = new JLabel("Member ID: ");
        lblmember_id.setBounds(30,10,90,30);
        lblmember_id.setFont(new Font("Dialog",Font.BOLD,12));
        processLeft.add(lblmember_id);
        
        try{
            int i=0;
            String query="select * from informations";           
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/mmstest","root","");
            Statement statement = connection.createStatement();
            ResultSet resultcount = statement.executeQuery(query);
            while(resultcount.next()){
                i++;
            }
            mem_idString = new String[i];
            ResultSet result = statement.executeQuery(query);
            i=0;
            while(result.next()){                
                mem_idString[i] = result.getString("member_id");
                i++;
            }
            i=0;
            connection.close();
            statement.close();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }       
//        management.infoList.add(new Informations("S","s","S"));
//        String mem_idString[] = new String[management.infoList.size()];
//        for (int m = 0; m < management.infoList.size(); m++) {
//                mem_idString[m] = management.infoList.get(m).getMember_id();
//                System.out.println("Sasdf");
//        }       
        final JComboBox member_id = new JComboBox<>(mem_idString);
        member_id.setBounds(105,15,90,17);
        processLeft.add(member_id);

        JLabel lblprocess_id = new JLabel("Process ID :");
        lblprocess_id.setBounds(30,85,90,30);
        lblprocess_id.setFont(new Font("Dialog",Font.BOLD,12));
        processLeft.add(lblprocess_id);
        
        process_id = new JTextField();
        process_id.setText("26001");
        process_id.setFont(new Font("Dialog",Font.BOLD,14));
        process_id.setBounds(105,85,170,30);
        processLeft.add(process_id);
        
        JLabel lblmeal = new JLabel("Total Meal");
        lblmeal.setBounds(30,130,100,30);
        processLeft.add(lblmeal);
        
        meal = new JTextField();
        meal.setBounds(105,130,170,30);
        meal.setFont(new Font("Dialog",Font.BOLD,14));
        processLeft.add(meal);
        
        JLabel lblexpanditure= new JLabel("Expanditure");
        lblexpanditure.setBounds(30,180,100,30);
        processLeft.add(lblexpanditure);
        
        expanditure = new JTextField();
        expanditure.setBounds(105,180,170,30);
        expanditure.setFont(new Font("Dialog",Font.BOLD,14));
        processLeft.add(expanditure);       
        
        JPanel processRight = new JPanel();
        processRight.setLayout(new BorderLayout(0,0));
        processCenter.add(processRight);        
        
        JScrollPane processScroll = new JScrollPane();
        processRight.add(processScroll);
        
        processTable = new JTable();
        processScroll.setViewportView(processTable);
        loadTable();
        JPanel processSouth = new JPanel();
        add(processSouth,BorderLayout.SOUTH);
        
        JButton processToinfo = new JButton("<< Back");
         
        processSouth.add(processToinfo);

        JButton btnSaveProcess = new JButton("Save");
        btnSaveProcess.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                try{
                    int mi,ei;
                    boolean bool=true;
                    float totalMeal = 0.0f;
                    float totalExpanditure = 0.0f;
                    int count = 0;  
                    String m_id = member_id.getSelectedItem().toString();
                    String pro_id = process_id.getText().toString();
                    String m = meal.getText().toString();
                    String e = expanditure.getText().toString();
                    String Query = "select * from process";
                    String queryInsert = 
                    "insert into process (member_id,process_id,meal,expenditure,due,back) values ('"+m_id+"','"+pro_id+"','"+m+"','"+e+"','"+"0"+"','"+"0"+"')";
                    String d="",b="";
                    System.out.println(queryInsert);
                    try{					
                        mi=Integer.parseInt(m);
                        ei=Integer.parseInt(e);
                    }
                    catch(Exception a){
                        bool=false;
                        JOptionPane.showMessageDialog(null, "Invalid!");
                    }
                    if(pro_id.length()>0&&m.length()>0&&e.length()>0&&bool){
                        
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/mmstest","root","");
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(queryInsert);
                        ResultSet result = statement.executeQuery(Query);
                        ResultSet result2 = result;          
                        while(result.next()){
                            totalMeal += Float.parseFloat(result.getString("meal"));
                            System.out.println(totalMeal);
                            totalExpanditure += Float.parseFloat(result.getString("expenditure"));
                            count++;
                        }
                        float mealRate = totalExpanditure/totalMeal;
                        if(count==1){
                            
                        }
                        else{
                            float mdue = (mealRate * Float.parseFloat(m)- Float.parseFloat(e));
                            d = mdue > 0 ? (mdue + "") : "0";
                            b = mdue < 0 ? (Math.abs(mdue) + "") : "0";
                            float floatD=0.0f;
                            float floatB=0.0f;
                            floatD=Float.parseFloat(d);
                            floatB=Float.parseFloat(b);
                            String stringD = String.format("%.02f",floatD);
                            String stringB = String.format("%.02f",floatB);
                            String q = "update process set due = '"+stringD+"',back = '"+stringB+"' where member_id = '"+m_id+"'";
                            System.out.println(q);
                            statement.executeUpdate(q);
                        }
                        management.SaveProcessList(m_id,pro_id,m,e,d,b);
                        connection.close();
                        statement.close();
                        int p = Integer.parseInt(pro_id);
                        p++;
                        pro_id = Integer.toString(p);
                        process_id.setText(pro_id);
                        meal.setText("");
                        expanditure.setText("");
                        
                    }
                }
                catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }    
                loadTable();
            }
        });
        processSouth.add(btnSaveProcess);
        JButton processDelete = new JButton("Delete");
        processDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                try{
                    int count=0;
                    int index = processTable.getSelectedRow()+1;
                    String selected;
                    String querySelect = "select * from process";  
                    if(index>=0){
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/mmstest","root","");
                        Statement statement = connection.createStatement(); 
                        ResultSet result= statement.executeQuery(querySelect);
                        ResultSet result2;
                        while(result.next()){
                            count+=1;
                            if(count==index){
                                selected = result.getString("member_id");
                                System.out.println(selected);
                            }
                        }
                    }
                }
                catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
//                int index = processTable.getSelectedRow();
//                
//                if(index>=0){
//                    management.processList.remove(index);
//                    management.showProcess();/*Showing process list into Console*/
//                    loadTable();
//                }
//                management.showProcess();
//                if(index<0){
//                    JOptionPane.showMessageDialog(null,"Select a row first!");
//                }
            }
        });
        processSouth.add(processDelete);
        
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });
        processSouth.add(exit);        
    }
    public void loadTable(){
        String heading[] = {"Member ID","Process ID","Meal","Expanditure","Due","Back"};
        processTableModel = new DefaultTableModel(heading,0);
            try{
                management.processList.clear();
                String Query = "select * from process";
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/mmstest","root","");
                Statement statement = connection.createStatement(); 
                ResultSet result = statement.executeQuery(Query);
                ResultSet result2 = result;
                while(result.next()){
                    management.SaveProcessList(result.getString("member_id"),result.getString("process_id"), 
                    result.getString("meal"),result.getString("expenditure"),result.getString("due"),result.getString("back"));
                }

                for(Process pro: management.processList){
                    Object[] objectInfo ={pro.getMem_id(),pro.getProcess_id(),pro.getMeal(),pro.getExpanditure(),pro.getDue(),pro.getBack()};
                    processTableModel.addRow(objectInfo);
                }
//                System.out.println((processTableModel.getValueAt(0, 0)));
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            processTable.setModel(processTableModel);
        }
    }












//            float totalMeal = 0.0f;
//            float totalExpanditure = 0.0f;
//            int count = 0;
//            try {
//            for (Process pro : management.processList) {
//                totalMeal += Float.parseFloat(pro.getMeal());
//                totalExpanditure += Float.parseFloat(pro.getExpanditure());
//                count++;
//            }
//            float mealRate = totalExpanditure/totalMeal;
//            for (Process pro : management.processList) {
//                if (count == 1) {
//                        Object[] memberData = { pro.getProcess_id(), pro.getMeal(), pro.getExpanditure(), "-", "-" };
//                        processTableModel.addRow(memberData);
//                } else {
//                        float mdue = (mealRate * Float.parseFloat(pro.getMeal())
//                                        - Float.parseFloat(pro.getExpanditure()));
//
//                        Object[] memberData = { pro.getProcess_id(), pro.getMeal(), pro.getExpanditure(),
//                                        mdue > 0 ? (mdue + "") : "-", mdue < 0 ? (Math.abs(mdue) + "") : "-" };
//                        processTableModel.addRow(memberData);
//                }
//            }
//        }catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"Invalid input types!");
//        }

//    public void count(){
//        int c=0;
//       for(Informations info : management.infoList){
//           c = info.getName().length();
//
//       }
//       System.out.println(c);           
//   }

//                int mi,ei;
//                boolean b=true;
//                String p_id = process_id.getText().toString();
//                String m = meal.getText().toString();
//                String e = expanditure.getText().toString();
//                try{					
//                        mi=Integer.parseInt(m);
//                        ei=Integer.parseInt(e);
//                }
//                catch(Exception a){
//                        b=false;
//                }
//                if(p_id.length()>0&&m.length()>0&&e.length()>0&&b){
//                    management.SaveProcessList(p_id, m, e);
//                    management.showProcess();/*Showing process list into Console*/
//                    int pro_id = Integer.parseInt(p_id);
//                    pro_id++;
//                    p_id = Integer.toString(pro_id);
//                    process_id.setText(p_id);
//                    meal.setText("");
//                    expanditure.setText("");
//                    loadTable();
//                }
//                else{
//                    JOptionPane.showMessageDialog(null, "Invalid!");
//                }