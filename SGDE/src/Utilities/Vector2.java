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

package Utilities;

import Game3D.Vector3;
import Advance.AMath;
import Advance.Matrix;
import java.awt.Point;
import java.io.Serializable;


/**
 * A useful class with an X, Y and length. Also has many other useful methods for physics.
 * @author Kyle Maximilian Dieter Sweeney
 */
public class Vector2 implements Cloneable, Serializable{
    protected double X;
    protected double Y;

    /**
     * Creates a new Vector2 out of an X and Y
     * @param x X of the Vector
     * @param y Y of the Vector
     */
    public Vector2(double x, double y){
        X=x;
        Y=y;
    }
    /**
     * creates a new Vector2 out of an old Vector2
     * @param v the old Vector2 to duplicate
     */
    public Vector2(Vector2 v){
        this.X=v.X;
        this.Y=v.Y;
    }
    /**
     * Creates a Vector2 out of a point
     * @param p a point on a Cartesian plane
     * @see Point
     */
    public Vector2(Point p){
        this.X=p.x;
        this.Y=p.y;
    }

    /**
     * Default Constructor. The X and Y will both be 0
     */
    public Vector2(){
        X=0;
        Y=0;
    }
    /**
     * A constructor which uses a double array, to represent a vertical matrix.
     * @param v 
     */
    public Vector2(double[][] v){
        X=v[0][0];
        Y=v[1][0];
    }
    /**
     * A constructor which uses a matrix holding a vertical matrix vector.
     * @param v 
     */
    public Vector2(Matrix v){
        X=v.returnCol(0)[0];
        Y=v.returnCol(0)[1];
    }

    /**
     * Returns the X value of the Vector
     * @return the X value of the Vector
     */
    public double getX(){
        return X;
    }
    /**
     * Returns the Y value of the Vector
     * @return the Y value of the Vector
     */
    public double getY(){
        return Y;
    }
    /**
     * Sets the X of the Vector to this new value
     * @param x the new Value of the X coordinate
     */
    public void setX(double x){
        X=x;
    }
    /**
     * Sets the Y of the Vector to this new value
     * @param y the new Value of the Y coordinate
     */
    public void setY(double y){
        Y=y;
    }
    /**
     * Adds the passed in value to the old value
     * @param x the amount to be added to the X value
     */
    public void dX(double x){
        X+=x;
    }
    /**
     * Adds the passed in value to the old value
     * @param y the amount to be added to the Y value
     */
    public void dY(double y){
        Y+=y;
    }
    /**
     * Resets the Vector using the new double values. Its like a new Constructor without creating a different instance
     * @param x the new X value
     * @param y the new Y value
     */
    public void reset(double x, double y){
        X=x;
        Y=y;
    }
    /**
     * Gives an exact copy of this Vector.
     * @return copy of this vector
     */
    @Override
     public Vector2 clone(){
         return new Vector2(X,Y);
     }
    /**
     * the Length of the Vector. Imagine the X and Y as legs of a Right Triangle. Length is the Hypotenuse
     * @return the Length of the Vector
     */
    public double length(){
        return lengthMaker(X,Y);
    }

    /**
     * Calculates the distance between this vector, and another
     * @param other the other point to calculate the distance between
     * @return the distance between the two vectors
     */
    public double distance(Vector2 other){
        return lengthMaker((X-other.getX()),(Y-other.getY()));
    }
    /**
     * Calculates the distance between this vector and a point
     * @param p other the other point to calculate the distance between
     * @return the distance between the vector and point
     */
    public double distance(Point p){
        return lengthMaker((X-p.x),(Y-p.y));
    }
    
    /**
     * Calculates the square of the distance between this vector and another
     * @param other the other vector to calculate the distance
     * @return the distance squared
     */
    public double distanceSquare(Vector2 other){
        double x = this.X - other.X;
        double y = this.Y - other.Y;
        return (x*x) + (y*y);
    }
    /**
     * Calculates the square of the distance between this vector and a point
     * @param p the other point to calculate the distance
     * @return the distance squared
     */
    public double distanceSquare(Point p){
        double x = this.X - p.x;
        double y = this.Y - p.y;
        return (x*x) + (y*y);
    }
    
    /**
     * adds the passed in vector's values to this vector's values
     * @param other the vector who's values will be added to this instance's variables
     */
    public void add(Vector2 other){
        this.X+=other.X;
        this.Y+=other.Y;
    }
    /**
     * subtracts the passed in vector's values from this vector's values
     * @param other the vector who's values will be subtracted from this instance's variables
     */
    public void subtract(Vector2 other){
        this.X-=other.X;
        this.Y-=other.Y;
    }
    /**
     * Returns a string which represents this object.
     * @return A string which contains the X, Y, and Length
     */
    @Override
    public String toString(){
        return "X: "+X+", Y: "+Y+", Length: "+length();
    }
    
    @Override
    public boolean equals(Object e){
        if(!(e instanceof Vector2)){
            return false;
        }
        Vector2 d=(Vector2)e;
        return this.X==d.X&&this.Y==d.Y;
    }
    /**
     * This method will return to the user the Scalar Multiple which turns this Vector into the passed in Vector.
     * If no scalar multiple exists, then returns a 0.
     * @param o Vector this vector becomes when multiplied by a scalar multiple.
     * @return The scalar multiple, or 0 if no scalar multiple exists.
     */
    public double getScalarMultiple(Vector2 o){
        if(this.X/o.X!=this.Y/o.Y){
            return 0;
        }else{
            return o.X/this.X;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.X) ^ (Double.doubleToLongBits(this.X) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.Y) ^ (Double.doubleToLongBits(this.Y) >>> 32));
        return hash;
    }

    protected double lengthMaker(double x, double y){
        return Math.sqrt((x*x)+ (y*y));
//        return AMath.dist((int)x, (int)y);
    }
    
    public double dotProduct(Vector2 o){
        return X*o.X+Y*o.Y;
    }
    
    public Vector3 crossProduct(Vector2 v){
        return new Vector3(this).crossProduct(v);
    }
    
    /**
     * Calculates the angle between this vector and another
     * @param v the other vector
     * @return the angle theta between the two
     */
    public double getTheta(Vector2 v){
       return Math.acos(dotProduct(v)/(this.length()*v.length()));
    }
    
    /**
     * A very fast approximation for obtaining theta, on average about 30x faster, and with an average error of about 0.1068
     * @param v vector to get the theta from
     * @return an approximation for theta between this vector and the passed in
     */
    public double getThetaFast(Vector2 v){
//        return AMath.acos(dotProduct(v) * AMath.invSqrtd(X*X + Y*Y) * AMath.invSqrtd(v.X * v.X + v.Y*v.Y));
        return AMath.acos(dotProduct(v)/(v.length() * this.length()));
    }
    
    public void scalarMultiply(double x){
        this.X *= x;
        this.Y *= x;
    }
    
    /**
     * Rotates this vector by the passed in angle.
     * @param angle 
     */
    public void rotate(double angle){
        double newX = X*Math.cos(angle) - Y*Math.sin(angle);
        this.Y = X*Math.sin(angle) + Y*Math.cos(angle);
        this.X = newX;
    }
    
    public void normalize(){
        double length = this.length();
        this.X /= length;
        this.Y /= length;
    }
    
    public void normalizeFast(){
        double invlength = AMath.invSqrtf((float)(X*X+Y*Y));
        this.X *= invlength;
        this.Y *= invlength;
    }
}
