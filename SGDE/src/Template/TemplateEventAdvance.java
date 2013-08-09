/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Template;

import Game.Game;
import Utilities.InputAdvance;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

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
public class TemplateEventAdvance extends Game{

    public TemplateEventAdvance(){
        super();
        InputAdvance KyleMaximilianDieterSweeney=new input();
        this.addKeyListener(KyleMaximilianDieterSweeney);
        this.addMouseListener(KyleMaximilianDieterSweeney);
        this.addMouseMotionListener(KyleMaximilianDieterSweeney);
        this.addMouseWheelListener(KyleMaximilianDieterSweeney);
    }

    
    @Override
    public void InitializeAndLoad() {
        
    }
    
    @Override
    public void UnloadContent() {
        
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {

    }

    @Override
    public void run() {
        
    }
    
    private class input extends InputAdvance{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }

    }
}
