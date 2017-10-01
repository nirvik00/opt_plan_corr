package gtpw.utility;

import gtpw.data.CellObj;
import gtpw.data.DataClass;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Nirvik Saha
 */
public class MakeString {
    
    DataClass dataClass;
    ArrayList<String>objList;
    ArrayList<String>adjDataList;
    ArrayList<String>adjList;
    ArrayList<CellObj>cellObjList;
    
    Random rnd;
    
    public MakeString(ArrayList<CellObj>cellobjli_){
    //public MakeString(ArrayList<CellObj>cellobjli_,ArrayList<String>objLi_, ArrayList<String>adjLi_ ){
        rnd=new Random();
        dataClass=new DataClass();
        objList=new ArrayList<String>();
        adjDataList=new ArrayList<String>();
        adjList=new ArrayList<String>();
        cellObjList=new ArrayList<CellObj>();
        cellObjList.addAll(cellobjli_);
        
        //objList.addAll(objLi_);
        //adjList.addAll(adjLi_);
        
        
        objList.addAll(dataClass.getObjList());        
        adjDataList.addAll(dataClass.getAdjDataList());        
        adjList.addAll(dataClass.getAdjList());  
        
        
        Collections.sort(adjList, new CompareIndex2());
    }
    
    public String initProcess(){
        String iniString="";//iniString is the string with names;
        for(int i=0; i<objList.size(); i++){
            String name=objList.get(i).split(";")[1];
            double x=Double.parseDouble(objList.get(i).split(";")[3]);//num of cells for board
            int num=(int)x;
            for(int j=0; j<num; j++){
                iniString+=name+",";
                
            }
        }        
        
        return iniString;
    }

    public String swapString(String str){
        String[] arr=str.split(",");
        int r1=rnd.nextInt(arr.length-1);
        int r2=rnd.nextInt(arr.length-1);
        for(int i=0; i<arr.length; i++){
            arr[i]=str.split(",")[i];//populate the array to retrieve string position
        }
        String a=arr[r1];
        String b=arr[r2];
        double d0=retAdjVal(a,b);
        
        arr[r2]=a;
        arr[r1]=b;
        String finalStr=makeStringFromArray(arr);
        return finalStr;
    }
    
    public ArrayList<CellObj> constructBoard(String iniString){
        for(int i=0; i<iniString.split(",").length; i++){
            String name=iniString.split(",")[i];
            cellObjList.get(i).setName(name);
        }
        return cellObjList;
    }

    public double getFitness(){
        double sum=0;
        for(int i=0; i<cellObjList.size(); i++){
            CellObj cell1=cellObjList.get(i);
            for(int j=0; j<cellObjList.size(); j++){
                CellObj cell2=cellObjList.get(j);    
                if( Math.abs(i-j)>0){
                    String u=cell1.getName();
                    String v=cell2.getName();
                    double w=retAdjVal(u,v);
                    double d=dis(cell1, cell2);
                    sum+=w*d;
                }
            }
        }
        return sum;
    }
    
    public double retAdjVal(String u, String v){
        double d=0;
        for(int i=0; i<adjList.size(); i++){
            String a=adjList.get(i).split(",")[0];
            String b=adjList.get(i).split(",")[1];
            double c=Double.parseDouble(adjList.get(i).split(",")[2]);
            if((u.equals(a) && v.equals(b))||(u.equals(b) && v.equals(a))){
                d=c;
            }
        }
        return d;
    }
    
    public String makeStringFromArray(String[] arr){
        String str="";
        for(int i=0; i<arr.length; i++){
            str+=arr[i]+",";
        }
        return str;
    }
    
    public double dis(CellObj a, CellObj b){
        double x0=a.getX();
        double y0=a.getY();
        double x1=b.getX();
        double y1=b.getY();
        double d=Math.sqrt((x0-x1)*(x0-x1) + (y0-y1)*(y0-y1));
        return d;
    }
}