package gtpw.data;

import java.util.ArrayList;

/**
 *
 * @author Nirvik Saha
 */
public class ProductObj {
    String str;
    double fitness;
    
    public ProductObj(String s_, double f_){
        str=s_;
        fitness=f_;
    }
    public String getStr(){
        return str;
    }
    public void setString(String s_){
        str=s_;
    }
    public void setFitness(double f_){
        fitness=f_;
    }
    public double getFitness(){
        return fitness;
    }
}
