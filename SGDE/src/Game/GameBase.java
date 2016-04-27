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

package Game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

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
