
package gtpw.ga;

import java.util.Random;

/**
 *
 * @author Nirvik Saha
 */
public class DNA {
    int[] genes;
    int num;
    Random rnd;
    
    DNA(int num_){
        rnd=new Random();
        num=num_;
        genes=new int[num];
        for(int i=0; i<num; i++){
            genes[i]=rnd.nextInt(100);
        }        
    }
    public DNA crossOver(DNA partnerDNA){
        DNA childDNA=new DNA(num);
        for(int i=0; i<genes.length; i++){
            if(i<genes.length/2){
                childDNA.genes[i]=genes[i];
            }else{
                childDNA.genes[i]=partnerDNA.genes[i];
            }
        }
        return childDNA;
    }
    public double getFitness(){
        int sum=0;
        for(int i=0; i<num; i++){
            sum+=genes[i];
        }
        double avgFitness=sum/num;
        return avgFitness;
    }
    public String printDNA(){
        double sum=0;
        String strDna="";
        for(int i=0; i<num; i++){
            strDna+=genes[i]+",";
            sum+=genes[i];
        }
        double fitness=sum/num;
        strDna+=";"+fitness;
        System.out.println(strDna);
        return strDna;
    }
}
