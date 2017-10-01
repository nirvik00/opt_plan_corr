package gtpw.utility;

import gtpw.data.CellObj;
import java.util.ArrayList;

/**
 *
 * @author Nirvik Saha
 */
public class Fitness {
    
    ArrayList<CellObj>cellObjList;
    ArrayList<String>adjList;
    ArrayList<String>fitnessRelList;
    
    public Fitness(ArrayList<CellObj>cell_, ArrayList<String>adjList_){
        fitnessRelList=new ArrayList<String>();
        cellObjList=new ArrayList<CellObj>();
        cellObjList=cell_;
        adjList=new ArrayList<String>();
        adjList.addAll(adjList_);
    }
    
    public double getFitness(){
        fitnessRelList.clear();
        for(int i=0; i<cellObjList.size(); i++){
            CellObj cellA=cellObjList.get(i);
            int aI=cellA.getId();
            double aX=cellA.getX();
            double aY=cellA.getY();
            double cell_val_A=cellA.getCellVal();
            String aName=cellA.getName();
            for(int j=0; j<cellObjList.size(); j++){
                CellObj cellB=cellObjList.get(j);
                int bI=cellB.getId();
                double bX=cellB.getX();
                double bY=cellB.getY();
                double cell_val_B=cellB.getCellVal();
                String bName=cellB.getName();
                if(aI != bI){
                    //double distval=dis(aX,aY,bX,bY);
                    double distval=Math.abs(cell_val_A-cell_val_B);
                    double adjval=retAdjVal(aName, bName);
                    double fit=distval*adjval*(-1);
                    String f=aName+","+bName+","+distval+","+adjval+","+fit;
                    fitnessRelList.add(f);
                    //System.out.println(f);
                }
            }
        }
        int sum=0;
        for(int i=0; i<fitnessRelList.size(); i++){
            double f=Double.parseDouble(fitnessRelList.get(i).split(",")[4]);
            sum+=f;
        }
        return sum;
    }
    
    public double dis(double x,double y,double a,double b){
        double d=Math.sqrt((x-a)*(x-a) + (y-b)*(y-b));
        return d;
    }
    
    public double retAdjVal(String a, String b){
        double val=0;
        for(int i=0; i<adjList.size(); i++){
            String a_=adjList.get(i).split(",")[0];
            String b_=adjList.get(i).split(",")[1];
            double v=Double.parseDouble(adjList.get(i).split(",")[2]);
            if(a.equals(a_) && b.equals(b_)){
                val=v;
                break;
            }
            if(a.equals(b_)||b.equals(a_)){
                val=v;
                break;
            }
        }
        return val;
    }
}
