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
    JButton jbtnRunIte, jbtnInitIte;
    
    ArrayList<String>funcObjList;
    ArrayList<String>adjDataList;
    ArrayList<String>adjList;
    
    GTPW_revOpt(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Optimization of Double-loaded corridor");
        setSize(1250,900);
        setLocation(0,0);
        setVisible(true);        
        
        pnl=new GTPW_revOpt_Pnl();
        pnl.setBounds(0,0,1200,700);
        
        jbtnRunIte=new JButton("RUN ITERATION");
        jbtnRunIte.setBounds(70,720,200,40);
        
        jbtnInitIte=new JButton("INITIATE ITERATION");
        jbtnInitIte.setBounds(260,720,200,40);
        
        setLayout(null);
        add(pnl);
        //add(adj_pnl);
        //add(func_pnl);
        add(jbtnRunIte);
        //add(jbtnInitIte);
        
        
        funcObjList=new ArrayList<String>();
        adjDataList=new ArrayList<String>();
        adjList=new ArrayList<String>();
        
        jbtnRunIte.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                pnl.runIter();
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
