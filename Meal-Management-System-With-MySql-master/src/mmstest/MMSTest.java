package mmstest;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
public class MMSTest extends JFrame{    
    
    CardLayout appLayout;
    InformationPanel infoPanel = new InformationPanel();
    ProcessPanel processPanel = new ProcessPanel(); 
    AboutPanel aboutPanel = new AboutPanel();
   
    public MMSTest(){  
        setTitle("Meal Management System");        
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setSize(3*dimension.width/5,3*dimension.height/5);
        setLocation(dimension.width/2-getWidth()/2,dimension.height/2-getHeight()/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        getContentPane().setLayout(new CardLayout(0,0));
        
        JMenuBar menubar = new JMenuBar();         
        JMenu info = new JMenu("Informations");
        JMenu process = new JMenu("Process");
        JMenu about = new JMenu("About");

	info.addMouseListener(new MouseAdapter() {
            @Override
		public void mouseClicked(MouseEvent e) {
                    appLayout = (CardLayout) getContentPane().getLayout();
                    appLayout.show(getContentPane(),"infoPanel");
                }
        });
        
	process.addMouseListener(new MouseAdapter() {
            @Override
		public void mouseClicked(MouseEvent e) {
                appLayout = (CardLayout) getContentPane().getLayout();
                appLayout.show(getContentPane(), "processPanel");
            }
        });
        about.addMouseListener(new MouseAdapter() {
            @Override
		public void mouseClicked(MouseEvent e) {
                    appLayout = (CardLayout) getContentPane().getLayout();
                    appLayout.show(getContentPane(),"aboutPanel");
            }
        });
        menubar.add(info);
        menubar.add(process);
        menubar.add(about);
        setJMenuBar(menubar);

        getContentPane().add(infoPanel,"infoPanel");
        getContentPane().add(processPanel,"processPanel"); 
        getContentPane().add(aboutPanel,"aboutPanel");
    }
    public static void main(String[] args) {
        
        MMSTest mmstest = new MMSTest();
        mmstest.setVisible(true);
    }    
}