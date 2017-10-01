
package gtpw.ga;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Nirvik Saha
 */
public class Population {
    ArrayList<DNA> dnaPool;
    ArrayList<DNA> selectedDna;
    Random rnd;
    double targetFitness;
    Population(double targetFitness_){
        rnd=new Random();
        targetFitness=targetFitness_;
        dnaPool=new ArrayList<DNA>();
        selectedDna=new ArrayList<DNA>();
    }
    public void addMatingPool(){
        for(int i=0; i<10; i++){
            DNA dna=new DNA(10);
            double f=dna.getFitness();
            dnaPool.add(dna);
        }
        naturalSelection();
    }
    public void naturalSelection(){
        for(int i=0; i<dnaPool.size(); i++){
            double f=dnaPool.get(i).getFitness();
            if(f>targetFitness/2){
                for(int j=0; j<10; j++){
                    selectedDna.add(dnaPool.get(i));
                }
            }else{
                addMatingPool();
            }
        }
        dnaPool.clear();
        for(int i=0; i<selectedDna.size(); i++){
            int r0=rnd.nextInt(selectedDna.size());
            int r1=rnd.nextInt(selectedDna.size());
            DNA dna0=selectedDna.get(r0);
            DNA dna1=selectedDna.get(r1);
            DNA child=dna0.crossOver(dna1);
            double f=child.getFitness();
            if(f>targetFitness*0.67){
                for(int j=0; j<10; j++){
                    selectedDna.add(child);
                }
            }
        }
    }
    public void getBestFitness(){
        double f0=0;
        int idx=0;
        for(int i=0; i<selectedDna.size(); i++){
            DNA dna=selectedDna.get(i);
            double f=dna.getFitness();
            if(f0<f){
                f0=f;
                idx=i;
            }
        }
        System.out.println("best fitness= "+f0);
        DNA fDna=selectedDna.get(idx);
        String s=fDna.printDNA();
        System.out.println(s);
    }
}
