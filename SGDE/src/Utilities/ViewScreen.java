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

import java.io.Serializable;

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
