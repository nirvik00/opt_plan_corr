package gtpw.revopt;

import gtpw.data.DataClass;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import package_table.Panel_AdjMatrix;
import package_table.Panel_FuncTbl;

/**
 *
 * @author Nirvik Saha
 */
public class GTPW_revOpt extends JFrame{
    
    GTPW_revOpt_Pnl pnl;
    Panel_AdjMatrix adj_pnl;
    Panel_FuncTbl func_pnl;    
    JButton jbtnRunIte, jbtnInit;
    JLabel jlblPath, jlblCorrDepth;
    JTextField jtfPath, jtfCorrDepth;
    
    ArrayList<String>funcObjList;
    ArrayList<String>adjDataList;
    ArrayList<String>adjList;
    
    GTPW_revOpt(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Optimization of Double-loaded corridor");
        setSize(900,900);
        setLocation(0,0);
        setVisible(true);        
        
        pnl=new GTPW_revOpt_Pnl();
        pnl.setBounds(0,0,1200,700);
    
        jlblPath=new JLabel("Enter the PATH OF THE EXCEL FILE :");
        jlblPath.setBounds(20,720,300,40);

        jtfPath=new JTextField("C:\\nir_dev\\Book1.xlsx");
        jtfPath.setBounds(20,770,250,40);
        
        jlblCorrDepth=new JLabel("CORRIDOR DEPTH [ min = 300 ] [ default = 500] [max = 700] ");
        jlblCorrDepth.setBounds(350,710,400,40);
        
        jtfCorrDepth=new JTextField("500");
        jtfCorrDepth.setBounds(700,710,50,40);
        
        jbtnInit=new JButton("GET DATA & INITIATE ALGO");
        jbtnInit.setBounds(350,770,200,50);
    
        jbtnRunIte=new JButton("RUN ITERATION");
        jbtnRunIte.setBounds(600,770,200,50);
        

        setLayout(null);
        add(pnl);
        add(jtfPath);
        add(jlblPath);
        add(jbtnRunIte);
        add(jbtnInit);
        add(jlblCorrDepth);
        add(jtfCorrDepth);
        
        
        funcObjList=new ArrayList<String>();
        adjDataList=new ArrayList<String>();
        adjList=new ArrayList<String>();

        jbtnInit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){                 
                String path=jtfPath.getText();                
                double corr=Double.parseDouble(jtfCorrDepth.getText());
                if(corr<300){
                    corr=300;
                }else if(corr>700){
                    corr=700;
                }
                pnl.clear();
                pnl.initData(path,corr);
                pnl.initSimulatedAnnealing(path);
                
            }
        });
        
        jbtnRunIte.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                String path=jtfPath.getText();            
                pnl.runIter(path);                
                repaint();
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new GTPW_revOpt();
            }
        });
    }    
}
