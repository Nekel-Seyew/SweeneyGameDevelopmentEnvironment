/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Advance;

import Utilities.Vector2;

/**
 *
 * @author KyleSweeney
 */
public class Test {
    public static void main(String[] args) throws Exception{
//        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
//        System.out.println(m);
//        m.multiply(4);
//        System.out.println(m);
//        Matrix q=m.multiply(new Matrix(new double[][]{{1,0,0},{0,1,0},{0,0,1}}));
//        System.out.println(q);
//        m.transpose();
//        System.out.println(m);
//        m.transpose();
//        System.out.println(m);
//        m=new Matrix(new double[][]{{1},{1},{1}});
//        double[] c=m.returnCol(0);
//        System.out.println(m);
//        for(int i=0; i<c.length; i++){
//            System.out.println(c[i]);
//        }
//        Matrix z=new Matrix(new double[][]{{1,0,0},{0,1,0},{0,0,1}});
//        Matrix p=new Matrix(new double[][]{{1},{1},{1}});
//        z.multiplyts(p);
//        System.out.println(z);
//        System.out.println(p);
        
//        double nsqrt=0;
//        double fsqrt=0;
//        double diff=0;
//        
//        double fav=0;
//        double sav=0;
//        double di=0;
//        
//        for(int i=0; i<1000; i++){
//            for(float j=1; j<1000000; j+=0.25){
//                double start= System.nanoTime();
//                double res=Math.sqrt(j);
//                nsqrt+=System.nanoTime()-start;
//                start=System.nanoTime();
//                double fres=1/AMath.invSqrtf(j);
//                fsqrt+=System.nanoTime()-start;
//                diff+=Math.abs((res-fres));
//            }
//            nsqrt/=((1000000)*4);
//            fsqrt/=((1000000)*4);
//            diff/=((1000000)*4);
//            
//            fav+=fsqrt;
//            sav+=nsqrt;
//            di+=diff;
//            System.out.println("Completed test: "+i+"!");
//        }
//        
//        fav/=(1000);
//        sav/=(1000);
//        di/=(1000);
//        
//        System.out.println("Average normal sqrt time: "+sav);
//        System.out.println("Average fast sqrt time: "+fav);
//        System.out.println("Average difference: "+di);
        
//        double start=System.nanoTime();
//        double tow=AMath.InvSqrtf(4.0f);
//        System.out.println("Fast inv: "+(System.nanoTime()-start));
//        start=System.nanoTime();
//        tow=1/Math.sqrt(4.0f);
//        System.out.println("Normal inv: "+(System.nanoTime()-start));
        
//        double res=Math.sqrt(200*200 + 1234*1234);
//        System.out.println("Normal distance: Time: "+(System.nanoTime()-start)+" Result: "+res);
//        start=System.currentTimeMillis();
//        res=AMath.dist(200, 1234);
//        System.out.println("AMath distance: Time: "+(System.nanoTime()-start)+" Result: "+res);
        
        exp[] e=new exp[100];
//        for(int i=0; i<e.length; i++){
//            e[i]=new exp((int)(Math.random()*1000));
//        }
////        RadixSort.sort(e, 1000);
//        for(int i=0; i<e.length; i++){
//            System.out.print(e[i]+" ");
//        }
        
//        Object hello=e;
//        System.out.println((hello instanceof exp[]));
//        
//        Vector2 a = new Vector2(100,200);
//        Vector2 b = new Vector2(300,-400);
//        
//        System.out.println(a.distance(b));
        
        double difference=0;
        long theta=0, fast=0;
        
        for(int i=0; i<100000000; i++){
            Vector2 first = new Vector2(Math.random()*10000, Math.random()*10000);
            Vector2 second = new Vector2(Math.random()*10000,Math.random()*10000);
            
            long start = System.currentTimeMillis();
            double theta1 = first.getTheta(second);
            long end = System.currentTimeMillis();
            
            theta += (end-start);
            
            start = System.currentTimeMillis();
            double theta2 = first.getThetaFast(second);
            end = System.currentTimeMillis();
            fast +=(end-start);
            
            difference += Math.abs(theta1 - theta2);
        }
        difference/=100000000;
        System.out.println("getTheta took: "+theta+"ms");
        System.out.println("getThetaFast took: "+fast+"ms");
        System.out.println("Average value difference: "+difference);
        
    }
    
    public static class exp implements RadixSortable{
        int i;
        public exp(int i){
            this.i=i;
        }
        
        public String toString(){
            return ""+i;
        }

        @Override
        public int getSortNum() {
            return i;
        }
        
    }
}
