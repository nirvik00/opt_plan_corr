package package_table;

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
public class Panel_FuncTbl extends JPanel implements ActionListener{
    DefaultTableModel model;
    JTable jtbl;
    JScrollPane jsp;
    public ArrayList<String>funcList;
    Timer timer;
    
    public Panel_FuncTbl(){
        timer=new Timer(150,this);
        timer.start();
        funcList=new ArrayList<String>();
        funcList.clear();
        model=new DefaultTableModel();
        model.addColumn("UID");
        model.addColumn("Name");
        model.addColumn("Area");
        model.addColumn("Number");
        model.addColumn("Color");
        String[] s0Arr={"0","Entr","500","1","128,128,255"};
        model.addRow(s0Arr);
        String[] s1Arr={"1","Eval","120","5","50,255,50"};
        model.addRow(s1Arr);
        String[] s2Arr={"2","Nurse","80","2","200,200,0"};
        model.addRow(s2Arr);
        String[] s3Arr={"3","Bath","200","2","255,155,55"};
        model.addRow(s3Arr);
        String[] s4Arr={"4","Stairs","100","2","255,64,64"};
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
        funcList.clear();
        int r=model.getRowCount();
        int c=model.getColumnCount();       
        for(int i=0; i<r; i++){
            String list="";
            for(int j=0; j<c; j++){
                list+=jtbl.getValueAt(i,j)+";";
            }
            funcList.add(list);
        }     
        return funcList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }    
}
