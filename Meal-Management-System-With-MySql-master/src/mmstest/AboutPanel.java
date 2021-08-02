package mmstest;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

class AboutPanel extends JPanel{
    public AboutPanel(){
        setLayout(new BorderLayout(0,0));
        JLabel lblaboutNorth = new JLabel();
        lblaboutNorth.setText("This software is developed by");
        lblaboutNorth.setFont(new Font("Gabriola",Font.BOLD,26));
        lblaboutNorth.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblaboutNorth,BorderLayout.NORTH);
        
        JPanel aboutCenter = new JPanel();
        aboutCenter.setLayout(null);
        add(aboutCenter,BorderLayout.CENTER);
        
        JLabel lblmuhtadir = new JLabel();
        lblmuhtadir.setText("~ Md. Muhtadir Rahman");
        lblmuhtadir.setBounds(80,15,600,30);
        lblmuhtadir.setFont(new Font("MV Boli",Font.PLAIN,18));
        aboutCenter.add(lblmuhtadir);
        
        JLabel lblFoysal = new JLabel();
        lblFoysal.setText("~ Foysal Hossain");
        lblFoysal.setBounds(80,48,600,30);
        lblFoysal.setFont(new Font("MV Boli",Font.PLAIN,18));
        aboutCenter.add(lblFoysal);
        
        JLabel lblKyachinghla = new JLabel();
        lblKyachinghla.setText("~ Kyachinghla Marma");
        lblKyachinghla.setBounds(80,80,600,30);
        lblKyachinghla.setFont(new Font("MV Boli",Font.PLAIN,18));
        aboutCenter.add(lblKyachinghla);
        
        JLabel lblAkash = new JLabel();
        lblAkash.setText("~ Manoranjan Shikder");
        lblAkash.setBounds(80,110,600,30);
        lblAkash.setFont(new Font("MV Boli",Font.PLAIN,18));
        aboutCenter.add(lblAkash);
        
        JLabel lbldept = new JLabel();
        lbldept.setText("Department of Computer Science and Engineering,");
        lbldept.setBounds(70,165,600,30);
        lbldept.setFont(new Font("MV Boli",Font.PLAIN,20));
        aboutCenter.add(lbldept);

        JLabel lblbatch = new JLabel();
        lblbatch.setText("16-th batch(Session 2015-16),");
        lblbatch.setBounds(70,190,600,30);
        lblbatch.setFont(new Font("MV Boli",Font.PLAIN,20));
        aboutCenter.add(lblbatch);

        
        JLabel lbluni = new JLabel();
        lbluni.setText("University of Chittagong.");
        lbluni.setBounds(70,215,600,30);
        lbluni.setFont(new Font("MV Boli",Font.PLAIN,20));
        aboutCenter.add(lbluni);
        
        JPanel aboutSouth = new JPanel();
        add(aboutSouth,BorderLayout.SOUTH);
        
        JButton exit = new JButton("Exit");
        aboutSouth.add(exit);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });

    }
}