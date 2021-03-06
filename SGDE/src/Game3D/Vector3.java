/*
Copyright (c) 2011, Kyle D. Sweeney
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package Game3D;

import Advance.AMath;
import Advance.Matrix;
import Utilities.Vector2;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author HAL-9000
 */
public class Vector3 extends Vector2 implements Serializable{
    static final public Vector3 I = new Vector3(1,0,0);
    static final public Vector3 J = new Vector3(0,1,0);
    static final public Vector3 K = new Vector3(0,0,1);
    
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
            X=v.getX();
            Y=v.getY();
            Z=((Vector3)v).Z;
        }else{
            X=v.getX();
            Y=v.getY();
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
        return Math.sqrt(X*X+Y*Y+Z*Z);
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
        return Math.sqrt(((X-v.X)*(X-v.X)) + ((Y-v.Y)*(Y-v.Y)) + ((Z-v.Z)*(Z-v.Z)));
    }
    public double distanceSquare(Vector3 v){
        return ((X-v.X)*(X-v.X)) + ((Y-v.Y)*(Y-v.Y)) + ((Z-v.Z)*(Z-v.Z));
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
    
    @Override
    public void scalarMultiply(double x){
        this.X *= x;
        this.Y *= x;
        this.Z *= x;
    }
    
    @Override
    public void normalize(){
        double length = 1.0/this.length();
        this.X *= length;
        this.Y *= length;
        this.Z *= length;
    }
    
    @Override
    public void normalizeFast(){
        double invlength = AMath.invSqrtd((X*X+Y*Y+Z*Z));
        this.X *= invlength;
        this.Y *= invlength;
        this.Z *= invlength;
    }
    
    public static Vector3 crossProduct(Vector3 v1, Vector3 v2){
        return v1.crossProduct(v2);
    }
    
    public double getTheta(Vector3 v){
       return Math.acos(dotProduct(v)/(this.length()*v.length()));
    }
}
