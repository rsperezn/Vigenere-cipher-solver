/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenere;

import java.util.Comparator;

/**
 *
 * @author Santiago Perez
 */
public class IndexCoincidence implements Comparable<IndexCoincidence>,Comparator<IndexCoincidence>{
    public int position; public double value;
   
    //constructor
    public IndexCoincidence(int inposition, double invalue){
        position=inposition;
        value=invalue;
    }
    //default constructor
    IndexCoincidence() {
    }
    public void setPos(int inpos){
        position=inpos;
    }
    public void setVal(double inval){
        value=inval;
    }
    
    public int getPos(){
        return position;
    }
    public double getVal(){
        return value;
    }  
 
    @Override
    public int compareTo(IndexCoincidence compareVal) {
        double compareQuantity=((IndexCoincidence)compareVal).getVal();
        double result= value-compareQuantity;
        if(result>0){
            return 1;}
        else if (result<0){
            return -1;}
        else{ return 0;}
    }

    @Override
    public int compare(IndexCoincidence inIC1, IndexCoincidence inIC2) {
        double val1=inIC1.getVal();
        double val2=inIC2.getVal();
        double result= val1-val2;
        if(result>0){
            return 1;}
        else if (result<0){
            return -1;}
        else{ return 0;}
    
    }
}
