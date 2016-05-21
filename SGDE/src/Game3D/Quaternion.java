/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game3D;

/**
 *
 * @author Nekel
 */
public class Quaternion {
    double i,j,k,r;
    
    public Quaternion(double x, double y, double z, double theta){
        double length = Math.sqrt(x*x + y*y + z*z);
        i = x/length * Math.sin(theta/2);
        j = y/length * Math.sin(theta/2);
        k = z/length * Math.sin(theta/2);
        r = Math.cos(theta/2);
    }
    /**
     * Creates quaternion based on axis and theta
     * @param in normalized axis
     * @param theta amount to turn
     */
    public Quaternion(Vector3 in, double theta){
        i = in.getX() * Math.sin(theta/2);
        j = in.getY() * Math.sin(theta/2);
        k = in.getZ() * Math.sin(theta/2);
        r = Math.cos(theta/2);
    }
    
    public Vector3 getAxis(){
        return new Vector3(i,j,k);
    }
    
    public double getScalar(){
        return r;
    }
    
    public Vector3 rotate(Vector3 vector){
        Vector3 axis = this.getAxis();
        
        Vector3 a = axis.clone();
        a.scalarMultiply(2.0 * axis.dotProduct(vector));
        
        Vector3 v = vector.clone();
        v.scalarMultiply(r*r - axis.dotProduct(vector));
        
        Vector3 b = axis.crossProduct(vector);
        b.scalarMultiply(2.0 * r);
        
        a.add(v);
        a.add(b);
        
        return a;
    }
    
}
