/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
 * Basic version of the Input class. Pure Abstract class. Needs to be extended. Handles Keyboard, Mouse, and Mouse wheel inputs.
 * @author Kyle Maximilian Dieter Sweeney
 */
public abstract class InputBasic implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener,Serializable{

    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
    public void keyTyped(KeyEvent e){
        //Blargoh
    }

    public void mouseClicked(MouseEvent e) {
        //blarghoh
    }

    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public void mouseEntered(MouseEvent e) {
        //blarghoh
    }

    public void mouseExited(MouseEvent e) {
        //blarghoh
    }

    public abstract void mouseDragged(MouseEvent e);

    public void mouseMoved(MouseEvent e) {
        //barghoh
    }

    public abstract void mouseWheelMoved(MouseWheelEvent e);
    

}
