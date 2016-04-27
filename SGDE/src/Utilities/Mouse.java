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

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;


/**
 * A class which holds the state of the mouse at any instant.
 * @author Kyle Maximilian Dieter Sweeney
 */
public class Mouse implements Cloneable,Serializable{

    private Vector2 location;
    private boolean[] buttons;
    private int mouseScroll;
    private static Mouse inst=new Mouse();
    private Robot robot;
    private boolean captureMouse;
    private Vector2 center;
    private Vector2 wh;
    private double dx=0;
    private double dy=0;
    private Component gui;

    /**
     * Represents the Left Button for methods in this class.
     * @see MouseEvent
     */
    public static final int LEFT_BUTTON=MouseEvent.BUTTON1;
    /**
     *Represents the Right Button for methods in this class.
     * @see MouseEvent
     */
    public static final int RIGHT_BUTTON=MouseEvent.BUTTON2;
    /**
     *Represents some other Button on the mouse for methods in this class.
     * @see MouseEvent
     */
    public static final int OTHER_BUTTON=MouseEvent.BUTTON3;

    /**
     * Constructs an instance of the state of the mouse
     */
    public Mouse(){
        location=new Vector2();
        buttons=new boolean[3];
        mouseScroll=0;
        try {
            robot=new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Mouse.class.getName()).log(Level.SEVERE, null, ex);
        }
        captureMouse=false;
    }

    /**
     * Takes in the mouse event, and checks to see which button was pressed, and sets that button to true.
     * Also sets the location of the mouse.
     * @param e mouse event
     */
    public void setButton(MouseEvent e){
        location=new Vector2(e.getX(),e.getY());
        if(e.getButton()==MouseEvent.BUTTON1){
            buttons[0]=true;
        }
        if(e.getButton()==MouseEvent.BUTTON2){
            buttons[1]=true;
        }
        if(e.getButton()==MouseEvent.BUTTON3){
            buttons[2]=true;
        }
    }

    /**
     * Sets the button specified by the mouse event to false
     * @param e mouse event
     */
    public void setRelease(MouseEvent e){
        location=new Vector2(e.getX(),e.getY());
        if(e.getButton()==MouseEvent.BUTTON1){
            buttons[0]=false;
        }
        if(e.getButton()==MouseEvent.BUTTON2){
            buttons[1]=false;
        }
        if(e.getButton()==MouseEvent.BUTTON3){
            buttons[2]=false;
        }
    }

    /**
     * Checks to see if that button is currently pressed or not
     * @param e the int value to specify the button on the mouse
     * @return the current state of being pressed
     */
    public boolean isPressed(int e){
        if(e==MouseEvent.BUTTON1){
            return buttons[0];
        }
        if(e==MouseEvent.BUTTON2){
            return buttons[1];
        }
        if(e==MouseEvent.BUTTON3){
            return buttons[2];
        }
            return false;
    }
    /**
     * Checks to see if that button is not being pressed
     * @param e int value of the button
     * @return whether or not the button is not being pressed
     */
    public boolean isNotPressed(int e){
        if(e==MouseEvent.BUTTON1){
            return !buttons[0];
        }
        if(e==MouseEvent.BUTTON2){
            return !buttons[1];
        }
        if(e==MouseEvent.BUTTON3){
            return !buttons[2];
        }
            return false;
        
    }

    /**
     * Gives the place this mouse currently is
     * @return location of the mouse
     */
    public Vector2 location(){
        return this.location;
    }

    /**
     * sets the location of the mouse
     * @param e MouseEvent of being moved
     */
    public void mouseMoved(MouseEvent e){
        location=new Vector2(e.getX(),e.getY());
        if(this.captureMouse){
            dx=(location.getX()-center.getX())/wh.getX();
            dy=(location.getY()-center.getY())/wh.getY();
            robot.mouseMove((int)((center.getX()-location.getX())+gui.getLocationOnScreen().getX()),
                    (int)((center.getY()-location.getY())+gui.getLocationOnScreen().getY()));
        }
    }

    /**
     * Sets the number of rotations the mouse wheel has made
     * @param e the mouse Wheel event
     */
    public void mouseScroll(MouseWheelEvent e){
        location=new Vector2(e.getX(),e.getY());
        mouseScroll+=e.getWheelRotation();
    }

    /**
     * Returns the number of rotations the mouse wheel has made
     * @return the number of rotations the mouse wheel has made
     */
    public int getWheelScroll(){
        return mouseScroll;
    }

    /**
     * Returns the clone of this instance of the class
     * @return the clone of this instance of the class
     */

    @Override
    public Mouse clone(){
        Mouse returningMouse = new Mouse();
        returningMouse.buttons=this.buttons.clone();
        returningMouse.location=this.location.clone();
        returningMouse.mouseScroll=this.mouseScroll;
        returningMouse.captureMouse=this.captureMouse;
        returningMouse.center=this.center.clone();
        returningMouse.wh=this.wh.clone();
        returningMouse.gui=this.gui;
        return returningMouse;
    }
    
    public void captureMouse(boolean set, Vector2 mouseCenter, Vector2 widthHeight, Component guiApp){
        this.captureMouse=set;
        this.center=mouseCenter;
        this.wh=widthHeight;
        this.gui=guiApp;
    }
    public void setCursorImage(String ImgPath, JComponent jc){
        Toolkit.getDefaultToolkit().createCustomCursor(
                Toolkit.getDefaultToolkit().createImage(ImgPath), 
                new Point(jc.getX(), jc.getY()), "img");
        
        //done
    }
    
    public double dx(){
        double r=dx;
        dx=0;
        return r;
    }
    public double dy(){
        double r=dy;
        dy=0;
        return r;
    }
    
    /**
     * Buttons the Mouse has. Left, Right, and Other
     */
    public enum Buttons{
        Left,
        Right,
        Other;
    }
    
    /**
     * Sees if the button is pressed
     * @param i the button to be checked
     * @return whether or not the button is down, or, if the button is unrecognized: false
     */
    public static boolean isPressed(Buttons i){
        if(i.equals(Buttons.Left)){
            return inst.isPressed(LEFT_BUTTON);
        }else if(i.equals(Buttons.Right)){
            return inst.isPressed(RIGHT_BUTTON);
        }else if(i.equals(Buttons.Other)){
            return inst.isPressed(OTHER_BUTTON);
        }
        return false;
    }
    /**
     * Sees if the button is not pressed
     * @param i the button to be checked
     * @return whether or not the button is up, or, if the button is unrecognized: false
     */
    public static boolean isNotPressed(Buttons i){
        if(i.equals(Buttons.Left)){
            return inst.isNotPressed(LEFT_BUTTON);
        }else if(i.equals(Buttons.Right)){
            return inst.isNotPressed(RIGHT_BUTTON);
        }else if(i.equals(Buttons.Other)){
            return inst.isNotPressed(OTHER_BUTTON);
        }
        return false;
    }
    /**
     * Returns the reference to the Class's instance of the Mouse;
     * @return the Class's mouse
     */
    public static Mouse getCurrentInstance(){
        return inst;
    }
    /**
     * The value type for the Scrolling event.
     */
    public enum WheelType{
        Approx;
    }
    /**
     * Depening on the wheel type passed in, returns the number of times the mouse wheel has clicked
     * @param w the Wheel type to be used
     * @return the number of times the wheel has clicked
     */
    public static double getScroll(WheelType w){
            return inst.getWheelScroll();
    }
}
