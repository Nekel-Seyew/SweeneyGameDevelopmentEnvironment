/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Advance;

import Utilities.Image2D;
import Utilities.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RomulusAaron
 */
public class AMath {
    
//    static{
//        System.loadLibrary("hello");
//    }
    
    /**
     * Tau, the relationship of the circumference of the circle over the radius. Also equal to 2PI.
     */
    public static final double TAU=Math.PI*2;
    /**
     * The Natural Log. Equal to Math.E.
     */
    public static final double E=Math.E;
    /**
     * Pi, the relationship of the circumference of the circle over the diameter. Equal to Math.PI.
     */
    public static final double PI=Math.PI;
    
    public static final double RT2=Math.sqrt(2.0);
    
    /**
     * Useful for comparing numbers which are within rounding error of each other. Use when comparing values given by this Package.
     * @param a the first value
     * @param b the second value
     * @return whether or not the two numbers are within rounding error of one another(0.00001), and thus the same number.
     */
    public static boolean equals(double a, double b){
        return Math.abs(a-b)<=0.00001;
    }
    
    /**
     * A Faster square root function than Math.Sqrt, and within a rounding error of Math.sqrt.
     * @param x the number to be square rooted
     * @return the square root of x
     */
    public static double sqrt(double x){
        return Math.pow(Math.E, 0.5*Math.log(x));
//        return AMath.dist((int)(x/RT2), (int)(x/RT2));
    }
    
    public static double newtonSqrt(double x){
        double A = x;
        double xn = x;
        double fx = x*x - A;
        double fpx = 2*x;
        double xn1 = xn - (fx*fpx)/(fpx*fpx - fx*2);
        while(Math.abs(xn - xn1) > 0.00001 ){
            xn=xn1;
            fx = xn*xn - A;
            fpx = 2*xn;
            xn1 = xn - (fx*fpx)/(fpx*fpx - fx*2);
        }
        return xn1;
    }
    
    /**
     * A faster Sine function compared to Math.sin(x). On average, about a 
     * difference of 0.3 degrees, with max error of 5 degrees. 1.3 times faster
     * for x in range [0,2PI], up to 10 times faster for x in range[0,100000000]
     * @param x the value to be inputed
     * @return the sin(x)
     */
    public static double sin(double x){
        x%=TAU;
        double x2 = x*x;
        double x3= x2*x;
        double x5= x3*x2;
        double x7= x5*x2;
        double x9= x7*x2;
        double x11=x9*x2;
        double x13 = x11 * x2;
        double x15 = x13 * x2;
        //return x*(1-x2*(1./6 + x2*(1./120 -x2*(1.0/5040 + x2*(1.0/362880 - x2/39916800.)))));
        return ((x) - (x3/6) + (x5/120) - (x7/5040) + (x9/362880) - (x11/39916800) + (x13/6227020800l) - (x15/1307674368000l));
    }
    /**
     * A faster cosine function compared to Math.cos(x). Within rounding error as well.
     * @param x the value to be inputed
     * @return the cos(x)
     */
    public static double cos(double x){
        x%=TAU;
        double x2= x*x;
        double x4= x2*x2;
        double x6= x4*x2;
        double x8= x6*x2;
        double x10=x8*x2;
        double x12=x10*x2;
        double x14 =x12*x2;
        return (1 - (x2/2) + (x4/24) - (x6/720) + (x8/40320) - (x10/3628800) +(x12/479001600) - (x14/87178291200l));
    }
    /**
     * A faster cosecant function compared to Math.csc(x). Within rounding error as well.
     * @param x the value to be inputed
     * @return the csc(x)
     */
    public static double csc(double x){
        return 1/sin(x);
    }
    /**
     * A faster secant function compared to Math.sec(x). Within rounding error as well.
     * @param x the value to be inputed
     * @return the sec(x)
     */
    public static double sec(double x){
        return 1/cos(x);
    }
    /**
     * A faster tangent function compared to Math.tan(x). Behaves well [-PI/4,PI/4].
     * Starts to misbehave between [PI/4, PI/2]. I think this is because of too many multiplications.
     * Note, this needs to be replaced with a Lagrange Polynomial.
     * @param x 
     * @return the tan(x)
     */
    public static double tan(double x){
        double x2 = x*x;
       // double x3 = x2*x;
       // double x5 = x3*x2;
        //double x7 = x5*x2;
        //double x9 = x7*x2;
        //double x11= x9*x2;
        //double x13= x11*x2;
        //double x15= x13*x2;
        return x*(1 + x2*((1/3) + (x2)*((2/15) + (x2)*((17/315) 
                + (x2)*((62/2835)+(x2)*((1382/155925)+(x2)*((21844/6081075)
                +(x2)*(929569/638512875))))))));
        //x+x^3/3+2x^5/15+17x^7/315+62x^9/2835
        //return sin(x)/cos(x);
    }
    /**
     * A faster cotangent function compared to Math.cot(x). Within rounding error as well.
     * @param x the value to be inputed
     * @return the cot(x)
     */
    public static double cot(double x){
        return cos(x)/sin(x);
    }
    /**
     * Determines the maximum of two numbers, including if the numbers are within rounding error. 
     * <b>Note</b>, if numbers are within rounding error as determined by this package, then the absolute larger number may not always be returned.
     * @param a first parameter
     * @param b second parameter
     * @return the larger of the two numbers, within rounding error
     */
    public static double max(double a, double b){
        return (a>b || equals(a,b)) ? a : b;
    }
    /**
     * Determines the minimum of two numbers, including if the numbers are within rounding error. 
     * <b>Note</b>, if numbers are within rounding error as determined by this package, then the absolute smaller number may not always be returned.
     * @param a first parameter
     * @param b second parameter
     * @return the smaller of the two numbers, within rounding error
     */
    public static double min(double a, double b){
        return (a<b || equals(a,b)) ? a : b;
    }
    /**
     * A Lagrange Polynomial approximation for arccos, with max error of 0.18 radians.
     * Do note however that this function obeys the limitations of the arccos function.
     * @param x A number in radians in range [0,PI]
     * @return a number in domain [-1,1]
     */
    public static double acos( double x) {
        return (-0.69813170079773212 * x * x - 0.87266462599716477) * x + 1.5707963267948966;
    }
    /**
     * Do not use this function. The Lagrange Polynomial has not been calculated yet.
     * @param x
     * @return 
     */
    public static double asin(double x){
        return PI/2 + acos(x);
    }
    /**
     * A faster arctangent function compared to Math.atan(x). Really not good.
     * @param x the value to be inputed
     * @return the arctan(x)
     */
    public static double arctan(double x){
        double x2 = x*x;
        double x3= x2*x;
        double x5= x3*x2;
        double x7= x5*x2;
        double x9= x7*x2;
        double x11=x9*x2;
        double x13=x11*x2;
        
        return x - x3/3 + x5/5 - x7/7 + x9/9 - x11/11 + x13/13;
    }
    /**
     * A faster arccotangent function compared to Math.acot(x). Within rounding error as well.
     * @param x the value to be inputed
     * @return the arccot(x)
     */
    public static double arccot(double x){
        return ((PI/2)-arctan(x));
    }
    /**
     * A faster arcsecant function compared to Math.asec(x). Within rounding error as well.
     * @param x the value to be inputed
     * @return the arcsec(x)
     */
    public static double arcsec(double x){
        return acos((1/x));
    }
    /**
     * A faster arccosecant function compared to Math.acsc(x). Within rounding error as well.
     * @param x the value to be inputed
     * @return the arccsc(x)
     */
    public static double arccsc(double x){
        return asin((1/x));
    }
    
    public static double distance(Vector2 a, Vector2 b){
        return dist((int)(a.getX()-b.getX()),(int)(a.getY()-b.getY()));
    }
    
    private static double dist(int dx, int dy){
        int max, min, approx;
        if(dx<0){
            dx=-dx;
        }
        if(dy<0){
            dy=-dy;
        }
        
        if( dx < dy ){
            min=dx;
            max=dy;
        }else{
            min=dy;
            max=dx;
        }
        
        approx = (max*1007) +(min*441);
        if(max < (min <<4)){
            approx-=(max*40);
        }
        
        return (double)(((approx+512) >> 10));
    }
    // Code recieved from: http://stackoverflow.com/questions/11513344/how-to-implement-the-fast-inverse-square-root-in-java 
    public static float invSqrtf(float x) {
        int i;
        float x2, y;

        x2 = x * 0.5f;
        y = x;
        i = Float.floatToIntBits(y);
        i = 0x5f3759df - (i >> 1);
        y = Float.intBitsToFloat(i);
        y = y * (1.5f - (x2 * y * y));

        return y;
    }

    public static double invSqrtd(double x) {
        long i;
        double x2, y;
        x2 = x * 0.5f;
        y = x;
        i = Double.doubleToLongBits(y);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        y = Double.longBitsToDouble(i);
        y = y * (1.5 - (x2 * y * y));
        return y;
    }
    
    public static int[][] transpose(int[][] array){
        int[][] arrayT = new int[array[0].length][array.length];
        for(int i=0; i<arrayT.length; i++){
            for(int j=0; j<arrayT[i].length; j++){
                arrayT[i][j]=array[j][i];
            }
        }
        return arrayT;
    }
    
}
