/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

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
