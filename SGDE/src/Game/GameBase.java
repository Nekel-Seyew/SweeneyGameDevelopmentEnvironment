/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

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
 * This creates a GUI for an instance of the Game class. It runs and sets the game visible.
 * It also calls the background music to play.
 * @author Kyle Maximilian Dieter Sweeney
 */
public class GameBase{
    public JFrame theGUI=new JFrame();
    public int WindowWidth=800;
    public int WindowHeight=600;
    public Container pane=theGUI.getContentPane();
    public Game game;
    public String name;
    public BufferStrategy buffers;

    /**
     * Creates a new Game Window with a Game, and the Name of the Game
     * @param game Game to be run in the window
     * @param name Name of the Game to put at the top of the GUI
     */

    public GameBase(Game game, String name){
        this.game=game;
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theGUI.setSize(WindowWidth, WindowHeight);
        pane.add(game);
        theGUI.setTitle(name);
//        theGUI.createBufferStrategy(2);
//        buffers=theGUI.getBufferStrategy();
    }

    /**
     * Runs the game. The Game will not run until this method is called.
     */
    public void Run(){
        theGUI.setVisible(true);
        game.base=this;
        game.requestFocusInWindow();
        Thread d = new Thread(game);
        d.start();
    }
    /**
     * Sets the GUI frame's height and width
     * @param WindowWidth Width of the GUI
     * @param WindowHeight Height of the GUI
     */

    public void setWindowWidthAndHeight(int WindowWidth, int WindowHeight){
        theGUI.setSize(WindowWidth, WindowHeight);
    }
}
