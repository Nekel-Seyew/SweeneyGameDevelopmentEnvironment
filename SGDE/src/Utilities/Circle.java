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

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author Kyle Maximilian Dieter Sweeney
 */

/**
 * A useful class for collision detection. Holds a virtual circle in memory
 */

public class Circle implements Cloneable, Serializable{
    private Vector2 center;
    private double radius;
    /**
     * Takes a Vector2, and a Radius, and constructs a circle
     * @param pos -the position of the center of the circle
     * @param radius -the radius of the circle
     */
    public Circle(Vector2 pos, double radius){
        this.center=pos;
        this.radius=radius;
    }
    /**
     * Constructs a Circle from two doubles of position and a double of radius
     * @param X -a double representing the X position of a Cartesian coordinate
     * @param Y -a double representing the Y position of a Cartesian coordinate
     * @param radius -a double representing the radius of the circle
     */
    public Circle(double X, double Y, double radius){
        this.center=new Vector2(X,Y);
        this.radius=radius;
    }
    /**
     * Constructs a Circle from a Point and a double
     * @param p -a Point of the position of the circle on a Cartesian grid
     * @param radius - a double representing the radius of the circle
     * @see Point
     */
    public Circle(Point p, double radius){
        this.center=new Vector2(p);
        this.radius=radius;
    }
    /**
     * Constructs a Circle from another circle
     * @param c -this passed in circle will be cloned into the new circle.
     */
    public Circle(Circle c){
        this.center=c.center;
        this.radius=c.radius;
    }
    /**
     * moves the circle by the amount passed into it
     * @param changeX -this double represents the amount the X coordinate will change
     * @param changeY -this double represents the amount the Y coordinate will change
     */
    public void moveCenter(double changeX, double changeY){
        center.add(new Vector2(changeX, changeY));
    }
    /**
     * moves the circle by the amount passed into it
     * @param change -this Vector2 represents the amount the center will move
     */
    public void moveCenter(Vector2 change){
        center.add(change);
    }
    /**
     * checks to see if the passed in point resides inside the circle
     * @param x -the x coordinate of the passed in point
     * @param y -the y coordinate of the passed in point
     * @return -whether or not that point resides in the circle
     */
    public boolean containsPoint(double x, double y){
        return center.distance(new Vector2(x,y)) <= this.radius;
    }
    /**
     * checks to see if the passed in point resides inside the circle
     * @param point -a Vector2 representing the point passed in
     * @return -whether or not that point resides in the circle
     */
    public boolean containsPoint(Vector2 point){
        return center.distance(point) <= this.radius;
    }
    /**
     * checks to see if the passed in point resides inside the circle
     * @param point -a Point representing the point passed in
     * @return -whether or not that point resides in the circle
     */
    public boolean containsPoint(Point point){
        return center.distance(point) <= this.radius;
    }
    /**
     * checks to see if the passed in circle collides with this circle
     * @param other -the other circle that is colliding with this circle
     * @return -whether or not the two circles collide
     */
    public boolean intersects(Circle other){
        return (center.distance(other.center)) <= (radius+other.radius);
    }

    /**
     * returns an exact copy of this circle
     * @return -an exact copy of this circle
     */
    @Override
    public Circle clone(){
        return new Circle(center, radius);
    }
}
