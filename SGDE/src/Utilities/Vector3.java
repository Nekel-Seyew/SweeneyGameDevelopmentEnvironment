/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Advance.Matrix;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author HAL-9000
 */
public class Vector3 extends Vector2 implements Serializable{
    protected double Z;
    
    public Vector3(double x, double y, double z){
        super(x,y);
        Z=z;
    }
    
    public Vector3(Vector2 xy, double z){
        super(xy);
        Z=z;
    }
    
    public Vector3(Point p, double z){
        super(p);
        Z=z;
    }
    
    public Vector3(Vector3 v){
        super(v.X, v.Y);
        Z=v.Z;
    }
    
    public Vector3(Vector2 v){
        if(v instanceof Vector3){
            X=v.X;
            Y=v.Y;
            Z=((Vector3)v).Z;
        }else{
            X=v.X;
            Y=v.Y;
            Z=0;
        }
    }
    
    public Vector3(){
        super();
        Z=0;
    }
    
    public Vector3(double[][] v){
        X=v[0][0];
        Y=v[0][1];
        Z=v[0][2];
    }
    public Vector3(Matrix v){
        X=v.returnCol(0)[0];
        Y=v.returnCol(0)[1];
        Z=v.returnCol(0)[2];
    }
    
    @Override
    public double length(){
        return Math.sqrt(Math.pow(X, 2)+Math.pow(Y, 2)+Math.pow(Z, 2));
    }
    
    @Override
    public Vector3 clone(){
        return new Vector3(this);
    }
    
    public Vector2 vectorXY(){
        return new Vector2(X,Y);
    }
    public Vector2 vectorXZ(){
        return new Vector2(X,Z);
    }
    public Vector2 vectorYZ(){
        return new Vector2(Y,Z);
    }
    
    public void dZ(double dz){
        Z+=dz;
    }

    public double getZ() {
        return Z;
    }

    public void setZ(double Z) {
        this.Z = Z;
    }
    public double distance(Vector3 v){
        return Math.sqrt(Math.pow(X-v.X, 2)+Math.pow(Y-v.Y, 2)+Math.pow(Z-v.Z, 2));
    }
    public void add(Vector3 v){
        X+=v.X;
        Y+=v.Y;
        Z+=v.Z;
    }
    public void subtract(Vector3 v){
        X-=v.X;
        Y-=v.Y;
        Z-=v.Z;
    }
    
    @Override
    public String toString(){
        return "X: "+X+", Y: "+Y+", Z: "+Z+", Length: "+length();
    }
    
    @Override
    public boolean equals(Object e){
        if(!(e instanceof Vector3)){
            return false;
        }
        Vector3 d=(Vector3)e;
        return this.X==d.X&&this.Y==d.Y && this.Z==d.Z;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.Z) ^ (Double.doubleToLongBits(this.Z) >>> 32));
        return hash;
    }
    
    public double dotProduct(Vector3 o){
        return X*o.X+Y*o.Y+Z*o.Z;
    }
    public Vector3 crossProduct(Vector3 v){
        double x,y,z;
        x=Y*v.Z-Z*v.Y;
        y=-1*(X*v.Z-Z*v.X);
        z=X*v.Y-Y*v.X;
        return new Vector3(x,y,z);
    }
    @Override
    public Vector3 crossProduct(Vector2 v) {
        if (v instanceof Vector2) {
            return crossProduct(new Vector3(v, 0));
        } else {
            return crossProduct(new Vector3(v));
        }
    }
    
    public static Vector3 crossProduct(Vector3 v1, Vector3 v2){
        return v1.crossProduct(v2);
    }
    
    public double getTheta(Vector3 v){
       return Math.acos(dotProduct(v)/(this.length()*v.length()));
    }
}
