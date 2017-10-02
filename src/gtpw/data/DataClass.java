package gtpw.data;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Nirvik Saha
 */
public class DataClass {
    String filePath;
    ArrayList<String>objList;
    ArrayList<String>adjDataList;
    ArrayList<String>adjList;
    
    public DataClass(String path){
        filePath=path;
        objList=new ArrayList<String>();
        adjDataList=new ArrayList<String>();
        adjList=new ArrayList<String>();
        
        /*
        *   ADD FUNCTION OBJECTS
        */
        objList.addAll(getObjData());
        if(objList.size()<1){
            objList.add("0;Entr;5000;1;120-120-255;70;100");
            objList.add("1;Eval;2500;9;50-225-50;100;25");
            objList.add("2;Nurse;3000;2;200-200-0;50;60");
            objList.add("3;Bath;2000;2;255-155-55;100;20");
            objList.add("4;Stair;3500;2;255-64-64;70;50");
        }
        /*
        *   ADD ADJACENT DATA LIST
        */
        adjDataList.addAll(getAdjData());
        if(adjDataList.size()<1){
            adjDataList.add("10;35;10;10;10");
            adjDataList.add("0;10;5;0;0");
            adjDataList.add("0;0;-50;0;0");
            adjDataList.add("0;0;0;-50;10");
            adjDataList.add("0;0;0;0;-50");
        }
        
    }

    public ArrayList<String> getObjList(){
        return objList;
    }
    public void setObjList(ArrayList<String> objLi_){
        objList=objLi_;
    }
    public ArrayList<String> getAdjDataList(){
        return adjDataList;
    }
    
    public ArrayList<String> getAdjList(){
        adjList.clear();
        ArrayList<String>names=new ArrayList<String>();
        for(int i=0; i<objList.size(); i++){
            String a=objList.get(i).split(";")[1];
            names.add(a);
        }
        for(int i=0; i<adjDataList.size(); i++){
            String rowName=names.get(i);
            String[] colArr=adjDataList.get(i).split(";");
            for(int j=0; j<colArr.length; j++){
                String colName=names.get(j);
                double val=Double.parseDouble(colArr[j]);
                adjList.add(rowName+","+colName+","+val);
            }
        }
        return adjList;
    }
    public void setAdjList(ArrayList<String> adjLi_){
        adjList=adjLi_;
    }
        
    public ArrayList<String> getAdjData(){
        ArrayList<String>tempAdjDataList=new ArrayList<String>();
        ArrayList<String>objDataList=new ArrayList<String>();
        try{
            //FileInputStream file=new FileInputStream(new File("C:\\Users\\Nirvik Saha\\Documents\\NetBeansProjects\\GTPW_revOpt\\Book1.xlsx"));
            FileInputStream file=new FileInputStream(new File(filePath));
            XSSFWorkbook wb1=new XSSFWorkbook(file);
            XSSFSheet sheet1=wb1.getSheet("Adjacency");
            Iterator<Row> rowIterator=sheet1.iterator();
            int row_counter=0;
            while(rowIterator.hasNext()){//row of the sheet : start from 2nd
                Row row=rowIterator.next();
                Iterator<Cell> cellIterator=row.cellIterator();
                if(row_counter>0){
                    String s="";
                    int col_counter=0;
                    while(cellIterator.hasNext()){//column of the row : start from 2nd
                        Cell cell=cellIterator.next();
                        if(col_counter>0){
                            s+=cell.toString()+";";                        
                        }
                        col_counter++;                   
                    }
                    tempAdjDataList.add(s);
                }
                row_counter++;
            }
            wb1.close();
            file.close();
        }catch(Exception e){       
            System.out.println("file not found");
        }
        for(int i=0; i<tempAdjDataList.size(); i++){
            //System.out.println(tempAdjDataList.get(i));
        }
        return tempAdjDataList;
    }
    
    
    public ArrayList<String> getObjData(){
        ArrayList<String>tempObjDataList=new ArrayList<String>();
        try{
            //FileInputStream file=new FileInputStream(new File("C:\\Users\\Nirvik Saha\\Documents\\NetBeansProjects\\GTPW_revOpt\\Book1.xlsx"));
            FileInputStream file=new FileInputStream(new File(filePath));
            XSSFWorkbook wb1=new XSSFWorkbook(file);
            XSSFSheet sheet1=wb1.getSheet("Object");
            Iterator<Row> rowIterator=sheet1.iterator();
            int row_counter=0;
            while(rowIterator.hasNext()){//row of the sheet : start from 2nd
                Row row=rowIterator.next();
                Iterator<Cell> cellIterator=row.cellIterator();
                if(row_counter>0){
                    String s="";
                    int col_counter=0;
                    while(cellIterator.hasNext()){//column of the row : start from 2nd
                        Cell cell=cellIterator.next();
                        if(col_counter>-1){
                            s+=cell.toString()+";";                        
                        }
                        col_counter++;                   
                    }
                    tempObjDataList.add(s);
                }
                row_counter++;
            }
            wb1.close();
            file.close();
        }catch(Exception e){       
            System.out.println("file not found");
        }
        for(int i=0; i<tempObjDataList.size(); i++){
            //System.out.println(tempObjDataList.get(i));
        }
        return tempObjDataList;
    }
}
