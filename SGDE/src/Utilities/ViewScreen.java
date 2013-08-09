/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

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
 * A "window" into the game world. Allows for camera control, and a larger game world than just the GUI frame.
 * @author RomulusAaron
 */
public class ViewScreen implements Serializable{

    private Vector2 position;
    private Vector2 dimensions;
    /**
     * The default value of view screen
     */
    public static final Vector2 DEFAULT=new Vector2(0,0);

    /**
     * Creates a View Screen with an upper corner of 0,0
     */
    public ViewScreen(){
        position=new Vector2(0,0);
        dimensions=new Vector2(800,600);
    }
    
    public ViewScreen(Vector2 dimensions){
        position=new Vector2();
        this.dimensions=dimensions;
        
    }

    /**
     * moves the upper corner of the view screen's X value by the amount passed in
     * @param x the amount to move the upper corner's view screen.
     */
    public void moveX(int x){
        position.dX(-x);
    }
    /**
     * moves the upper corner of the view screen's Y value by the amount passed in
     * @param y the amount to move the Y value
     */
    public void moveY(int y){
        position.dY(y);
    }
    /**
     * returns the X coordinate of the upper corner
     * @return the Y coordinate of the upper corner
     */
    public int GetX(){
        return (int)position.getX();
    }
    /**
     * returns the Y coordinate of the upper corner
     * @return the Y coordinate of the upper corner
     */
    public int GetY(){
        return (int)position.getY();
    }
    /**
     * places the position of the upper corner to the passed in Vector2
     * @param b the vector of the new upper corner
     */
    public void set(Vector2 b){
        Vector2 d=new Vector2();
        d.subtract(b);
        position=(d);
    }
    protected Vector2 loc(){
        return position;
    }
    
    public double getWidth(){
        return this.dimensions.getX();
    }
    
    public double getHeight(){
        return this.dimensions.getY();
    }
    
    public void setWidth(double width){
        this.dimensions.setX(width);
    }
    
    public void setHeight(double height){
        this.dimensions.setY(height);
    }
}
