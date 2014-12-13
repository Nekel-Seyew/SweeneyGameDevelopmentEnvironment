/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
