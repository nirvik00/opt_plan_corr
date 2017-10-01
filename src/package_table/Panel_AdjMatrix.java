package package_table;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nirvik Saha
 */
public class Panel_AdjMatrix extends JPanel implements ActionListener{
    DefaultTableModel model;
    JTable jtbl;
    JScrollPane jsp;
    public ArrayList<String>adjDataList;
    public ArrayList<String>adjList;
    Timer timer;
    
    public Panel_AdjMatrix(){
        timer=new Timer(150,this);
        timer.start();
        adjDataList=new ArrayList<String>();
        adjList=new ArrayList<String>();
        model=new DefaultTableModel();
        model.addColumn("Sl.No");
        model.addColumn("Entr");
        model.addColumn("Eval");
        model.addColumn("Nurse");
        model.addColumn("Bath");
        model.addColumn("Stairs");
        String[] s0Arr={"Entr","1","05","05","05","05"};
        model.addRow(s0Arr);
        String[] s1Arr={"Eval","00","10","05","00","00"};
        model.addRow(s1Arr);
        String[] s2Arr={"Nurse","00","00","-50","00","00"};
        model.addRow(s2Arr);
        String[] s3Arr={"Bath","00","00","00","-50","1000"};
        model.addRow(s3Arr);
        String[] s4Arr={"Stairs","00","00","00","00","-50"};
        model.addRow(s4Arr);
        
        jtbl=new JTable(model);
        DefaultTableCellRenderer dtcr= new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0; i<s0Arr.length; i++){
            if(i==0){
                jtbl.getColumnModel().getColumn(i).setPreferredWidth(70);
            }else{
                jtbl.getColumnModel().getColumn(i).setPreferredWidth(40);
            }
            jtbl.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
        jtbl.setRowHeight(40);
        jsp=new JScrollPane(jtbl,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(0,0,485,460);
        setLayout(null);
        add(jsp);
    }
    
    public ArrayList<String> update(){
        adjDataList.clear();
        int r=model.getRowCount();
        int c=model.getColumnCount();
        for(int i=1; i<r; i++){
            String list="";
            for(int j=1; j<i+1; j++){               
                jtbl.setValueAt("X",i,j);
            }
        }        
        for(int i=0; i<r; i++){
            String list="";
            for(int j=0; j<c; j++){
                list+=jtbl.getValueAt(i,j)+",";
            }
            adjDataList.add(list);
        }        
        
        
        //System.out.println(adjDataList);
        return adjDataList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }    
}
