package gtpw.data;

/**
 *
 * @author Nirvik Saha
 */
public class CellObj {

    int id;
    double x;
    double y;
    double l;
    double d;
    int num;
    double cell_val;
    int[] colr;
    String name;
    CellObj cell;
    String loc;
    public CellObj(int id_, double x_, double y_, double l_, double d_, int[] colr_, String loc_){
        id=id_;
        x=x_;
        y=y_;
        l=l_;
        d=d_;
        colr=colr_;
        loc=loc_;
    }
    public String getLoc(){
        return loc;
    }
    
    public CellObj getObj(){
        return cell;
    }    
    public int getId(){
        return id;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getL(){
        return l;
    }
    public double getD(){
        return d;
    }
    public String getName(){
        return name;
    }
    public int[] getColr(){
        return colr;
    }
    public double getCellVal(){
        return cell_val;
    }
    public int getNum(){
        return num;
    }
    public void setCellObj(CellObj cell_){
        cell=cell_;
    }
    public void setNum(int n_){
        num=n_;
    }
    public void setId(int id_){
        id=id_;
    }
    public void setX(double x_){
        x=x_;
    }
    public void setY(double y_){
        y=y_;
    }
    public void setL(double l_){
        l=l_;
    }
    public void setD(double w_){
        d=w_;
    }
    public void setName(String name_){
        name=name_;
    }
    public void setColr(int[] colr_){
        colr=colr_;
    }
    public void setCellVal(double v_){
        cell_val=v_;
    }
    public void setLoc(String loc_){
        loc=loc_;
    }
}
