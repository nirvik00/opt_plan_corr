package package_table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Nirvik Saha
 */
public class Frame_FuncTbl extends JFrame{
    
    public Panel_FuncTbl pnl;
    public ArrayList<String>funcList;
    JButton jbtnUpdate, jbtnDef;
    
    public Frame_FuncTbl(){
        setSize(500,350);
        setLocation(200,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Functional Element Table");
        
        funcList=new ArrayList<String>();
        
        jbtnUpdate=new JButton("Update Values");
        jbtnUpdate.setBounds(50,240,160,40);
        
        jbtnDef=new JButton("Default Values");
        jbtnDef.setBounds(270,240,160,40);
        
        pnl=new Panel_FuncTbl();
        pnl.setBounds(0,0,500,230);
        
        setLayout(null);
        add(pnl);
        add(jbtnUpdate);
        add(jbtnDef);
        
        jbtnUpdate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                funcList.clear();
                funcList.addAll(pnl.update());
                //System.out.println(funcList);
            }
        });
    }
}
