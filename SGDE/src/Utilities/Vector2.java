/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import Advance.AMath;
import Advance.Matrix;
import java.awt.Point;
import java.io.Serializable;

/*Copyright 2011 Kyle Dieter Sweeney
 * This file is part of the Sweeney Game Development Environment.

    Sweeney Game Development Environment is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Sweeney Game Development Environment is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Sweeney Game Development Environment.  If not, see <http://www.gnu.org/licenses/>.
 */

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
    
    public double getTheta(Vector2 v){
       return Math.acos(dotProduct(v)/(this.length()*v.length()));
    }
}
