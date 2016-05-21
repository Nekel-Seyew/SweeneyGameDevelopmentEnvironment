
import Utilities.Vector2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nekel
 */
public class Test {
    public static void main(String[] args){
        long end, start;
        double testA=0, testB=0, testC=0;
        double yes = 0;
        long iterations = 1000000;
        for(long i=0; i<iterations; ++i){
            double x = Math.random()*10000000.0 * (Math.random() > 0.5 ? 1 : -1);
            double y = Math.random()*10000000.0 * (Math.random() > 0.5 ? 1 : -1);
            
            Vector2 hi = new Vector2(x,y);
            
            start = System.nanoTime();
            yes += hi.length();
            end = System.nanoTime();
            testA += end-start;
            
            start = System.nanoTime();
            //yes += hi.lengthFast();
            end = System.nanoTime();
            testB += end - start;
        }
        System.out.println("Normal Length took: "+testA/iterations+" ns.");
        System.out.println("FastLength took: "+testB/iterations+" ns.");
        System.out.println("Yes: "+yes);
    }
}
