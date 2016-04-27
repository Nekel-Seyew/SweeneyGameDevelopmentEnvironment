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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.Serializable;


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
