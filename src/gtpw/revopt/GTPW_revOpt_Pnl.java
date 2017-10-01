package gtpw.revopt;

import gtpw.data.CellObj;
import gtpw.data.DataClass;
import gtpw.data.ProductObj;
import gtpw.utility.Fitness;
import gtpw.utility.MakeString;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Nirvik Saha
 */
public class GTPW_revOpt_Pnl extends JPanel implements MouseListener{
    double Depth_Max=500;
    DataClass dataClass;
    MakeString makeStringClass;
    Fitness fitnessClass;
    ProductObj productObj;
    
    ArrayList<String>objList;
    ArrayList<String>adjDataList;
    ArrayList<String>adjList;
    ArrayList<String>cellList;
    
    ArrayList<CellObj>cellObjList;
    ArrayList<ProductObj>prodObjList;
    
    String currentString;
    double currentFitness=0;
    int ClickCounter=0;
    int numOfiterations=0;
    
    GTPW_revOpt_Pnl(){
        addMouseListener(this);
        
        
        cellList=new ArrayList<String>();
        cellObjList=new ArrayList<CellObj>();
                
        objList=new ArrayList<String>();
        adjList=new ArrayList<String>();
        adjDataList=new ArrayList<String>();
        
        dataClass=new DataClass();
        objList.addAll(dataClass.getObjList());
        adjDataList.addAll(dataClass.getAdjDataList());
        adjList.addAll(dataClass.getAdjList());
        
        
        prodObjList=new ArrayList<ProductObj>();
        
        initData();
        initSimulatedAnnealing();
    }
    
    public void initData(){
        /*
        *   CONSTRUCT INITIAL CELLS 
        */
        
        double sum=0;
        for(int i=0; i<objList.size(); i++){
            double x=Double.parseDouble(objList.get(i).split(";")[3]);//num of cells for board
            sum+=x;
        }
        int ite=(int)(400/(sum/2));
        System.out.println(ite);
        int k=0;
        
        for(int i=0; i<(int)(sum/2); i++){
            int[] colr={255,255,255};
            int diff=(400/((int)sum/2));
            CellObj celltop=new CellObj(k,i*diff,50,diff,50,colr,"top");
            CellObj cellbottom=new CellObj(k+1,i*diff,100,diff,50,colr,"bottom");
            cellObjList.add(celltop);
            cellObjList.add(cellbottom);
            k+=2;
        }        
        /*
        *   END INITIALIZATION
        */
    }
    
    public void initSimulatedAnnealing(){
        //makeStringClass=new MakeString(cellObjList,objList,adjList);
        makeStringClass=new MakeString(cellObjList);
        String u=makeStringClass.initProcess();
        currentString=u;
        cellObjList.clear();
        cellObjList.addAll(makeStringClass.constructBoard(u));
        double f=makeStringClass.getFitness();
        currentFitness=f;
        if(currentFitness!=0){
            ProductObj obj=new ProductObj(currentString,currentFitness);
            prodObjList.add(obj);
        }
    }
    
    public void runIter(){
        String prevString=currentString;
        String tempString=currentString;
        boolean t=false;
        int ite=0;
        while(t==false && ite<100){
            String v=makeStringClass.swapString(currentString);
            revertCells(v);
            currentString = v;
            double f=makeStringClass.getFitness();
            currentFitness=f;
            t=betterIte(f);
            if(t==true){
                currentString=v;
                System.out.println("this was better (lower) : "+f );
            }else{
                currentString=prevString;
                System.out.println("this was worse (higher) : "+f );
            }   
            ite++;
        }
        numOfiterations=ite;
        revertCells(currentString);
        ProductObj obj=new ProductObj(currentString,currentFitness);
        prodObjList.add(obj);
        ClickCounter++;
    }
    
    public void paint(Graphics g){
        Graphics2D g2d=(Graphics2D) g.create();
        g2d.setColor(new Color(255,255,255));
        g2d.fill(new Rectangle2D.Double(0,0,1200,700));
        double tX_t=50,tY_t=30,tX_b=50,tY_b=0;
                
        g2d.translate(tX_t,tY_t);
        g2d.setColor(new Color(0,0,0));
        for(int i=0; i<cellObjList.size(); i++){
            CellObj cell=cellObjList.get(i);
            double x=cell.getX();
            double y=cell.getY();
            double l=cell.getL();
            double d=cell.getD();
            String name=cell.getName();
            int[] c=cell.getColr();
            for(int j=0; j<objList.size(); j++){
                String n_=objList.get(j).split(";")[1];
                if(n_.equals(name)){
                    String s=objList.get(j).split(";")[4];
                    int re=Integer.parseInt(s.split("-")[0]);
                    int gr=Integer.parseInt(s.split("-")[1]);
                    int bl=Integer.parseInt(s.split("-")[2]);
                    c[0]=re;
                    c[1]=gr;
                    c[2]=bl;
                }
            }            
            int id=cell.getId();
            g2d.setColor(new Color(c[0],c[1],c[2]));
            g2d.fill(new Rectangle2D.Double(x,y,l,d));
            g2d.setColor(new Color(0,0,0));
            g2d.draw(new Rectangle2D.Double(x,y,l,d));
            try{
                g2d.drawString(name, (int) x+17, (int)y+30);
            }catch(Exception e){                
            }
        }
        g2d.translate(-tX_t,-tY_t);
        g2d.drawString("Click Counter : " + ClickCounter, 10,670);
        g2d.drawString("Current Fitness (minimize) : " + currentFitness, 150,670);
        g2d.drawString("Number of Iterations(max=100) : " + numOfiterations, 450,670);
        
        g2d.translate(tX_b,tY_b);
        double topX=0;
        double botX=0;
        double iteY=300;
        //System.out.println("--------------------------");
        for(int i=0; i<cellObjList.size(); i++){
            CellObj obj=cellObjList.get(i);
            String name=obj.getName();
            String loc=obj.getLoc();
            //System.out.println(loc);
            for(int j=0; j<objList.size(); j++){
                String s=objList.get(j);
                double ar=Double.parseDouble(s.split(";")[2]);
                double L=Double.parseDouble(s.split(";")[5]);
                double D=Double.parseDouble(s.split(";")[6]);
                String name_=s.split(";")[1];
                double x0=0;
                double y0=iteY;
                int re=Integer.parseInt(s.split(";")[4].split("-")[0]);
                int gr=Integer.parseInt(s.split(";")[4].split("-")[1]);
                int bl=Integer.parseInt(s.split(";")[4].split("-")[2]);
                double d;
                double l=L;
                if(name.equals(name_) && loc.equals("bottom")){
                    x0=botX;
                    y0=Depth_Max-D;
                    d=D;
                    g2d.setColor(new Color(re,gr,bl));
                    g2d.fill(new Rectangle2D.Double(x0,y0,l,d));
                    g2d.setColor(new Color(0,0,0));
                    g2d.draw(new Rectangle2D.Double(x0,y0,l,d));
                    g2d.drawString(name_, (int)(x0)+10, (int)(y0+20));
                    botX+=l;
                }else if(name.equals(name_) && loc.equals("top")){
                    x0=topX;
                    iteY=300;
                    d=D;
                    g2d.setColor(new Color(re,gr,bl));
                    g2d.fill(new Rectangle2D.Double(x0,y0,l,d));
                    g2d.setColor(new Color(0,0,0));
                    g2d.draw(new Rectangle2D.Double(x0,y0,l,d));
                    g2d.drawString(name_, (int)(x0)+10, (int)(y0+20));
                    topX+=l;
                }
            }
        }
        g2d.translate(-tX_b,-tY_b);
        g2d.drawString("OPTIMIZING ADJACENCY", 50,200);
        g2d.drawString("OPTIMIZING AREA (OVERALL SOLUTION)", 50,550);
        
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {        
        String prevString=currentString;
        String tempString=currentString;
        boolean t=false;
        int ite=0;
        while(t==false && ite<100){
            String v=makeStringClass.swapString(currentString);
            revertCells(v);
            currentString = v;
            double f=makeStringClass.getFitness();
            currentFitness=f;
            t=betterIte(f);
            if(t==true){
                currentString=v;
                System.out.println("this was better (lower) : "+f );
            }else{
                currentString=prevString;
                System.out.println("this was worse (higher) : "+f );
            }   
            ite++;
        }
        numOfiterations=ite;
        revertCells(currentString);
        ProductObj obj=new ProductObj(currentString,currentFitness);
        prodObjList.add(obj);
        
        ClickCounter++;
        repaint();
    }
    
    public void revertCells(String u){
        cellObjList.clear();
        cellObjList.addAll(makeStringClass.constructBoard(u));
    }
    
    public boolean betterIte(double f){
        double min=10000000;
        for(int i=0; i<prodObjList.size(); i++){
            double f_=prodObjList.get(i).getFitness();
            if(f_<min && f_!=0){
                min=f_;
            }
        }
        System.out.println("min "+(int)min + " , "+(int)f);
        if(f<min){
           return true;
        }
        return false;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
