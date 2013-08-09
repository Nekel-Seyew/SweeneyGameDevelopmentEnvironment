/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StressTest;

import java.io.Serializable;

/**
 *
 * @author RomulusAaron
 */
public class MyObject implements Serializable{
    double firstValue;
    double secondValue;
    int[] myArray;
    
    public MyObject(){
        firstValue=Math.random();
        secondValue=Math.random();
        myArray=new int[(int)(Math.random()*10)];
        for(int i=myArray.length-1; i>=0 ; i--){
            myArray[i]=(int)(Math.random()*100);
        }
    }
    
    @Override
    public String toString(){
        String s="{";
        s=s.concat("FirstValue: "+firstValue);
        s=s.concat(" SecondValue: "+secondValue);
        s=s.concat(" My Array: "+printArray(myArray));
        s=s.concat("}");
        return s;
    }
    
    public String printArray(int[] a){
        String s="[";
        if(a.length!=0)
            s=s.concat(""+a[0]);
        for(int i=1; i<a.length; i++){
            s=s.concat(","+a[i]);
        }
        s=s.concat("]");
        return s;
    }
}
