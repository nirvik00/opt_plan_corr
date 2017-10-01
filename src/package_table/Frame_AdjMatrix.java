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
public class Frame_AdjMatrix extends JFrame{
    
    public Panel_AdjMatrix pnl;
    public ArrayList<String>adjList;
    JButton jbtnUpdate, jbtnDef;
    
    public Frame_AdjMatrix(){
        setSize(500,350);
        setLocation(200,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Adjacency Table");
        
        adjList=new ArrayList<String>();
        
        jbtnUpdate=new JButton("Update Values");
        jbtnUpdate.setBounds(50,240,160,40);
        
        jbtnDef=new JButton("Default Values");
        jbtnDef.setBounds(270,240,160,40);
        
        pnl=new Panel_AdjMatrix();
        pnl.setBounds(0,0,500,230);
        
        setLayout(null);
        add(pnl);
        add(jbtnUpdate);
        add(jbtnDef);
        
        jbtnUpdate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                adjList.clear();
                adjList.addAll(pnl.update());
                //System.out.println(adjList);
            }
        });
    }
    
}
