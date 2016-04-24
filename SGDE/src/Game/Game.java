/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Utilities.ImageCollection;
import Utilities.InputAdvance;
import Utilities.KeyBoard;
import Utilities.Mouse;
import Utilities.SoundBatch;
import Utilities.ViewScreen;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

/*Copyright (c) 2011, Kyle D. Sweeney
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
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE U
 */

/**
 * This is the base class for all subsequent games. It will update and draw everything.
 * It also has methods built in for playing sound clips and music.
 * This class also automatically converts all key, Mouse, Mouse Motion, and Mouse Wheel events into state driven.
 * All subsequent games should be extensions of this.
 * @author Kyle Maximilian Dieter Sweeney
 */
public abstract class Game extends JPanel implements ActionListener, Runnable{

    // <editor-fold defaultstate="collapsed" desc="Necessary Instance Variables">
    private double startimeNano=System.nanoTime();
    /**
     * The View Screen, that is, the visible area of the game world
     */
    protected ViewScreen viewScreen=new ViewScreen();
    /**
     * The collection into which Images are placed, and from where they are drawn
     */
    protected ImageCollection batch = new ImageCollection(viewScreen,this);
    private Timer timer;
    private Timer updateTimer;
    /**
     * The game speed, or how many milliseconds waited between updates
     */
    protected int gameSpeed=16;
    protected boolean backgroundMusicFinished = false;
    /**
     * Represents the current state of the Keyboard
     */
    protected KeyBoard keyboard;
    /**
     * Represents the current state of the Mouse
     */
    protected Mouse mouse;
    /**
     * A sound handler for the game
     */
    protected SoundBatch soundbatch=new SoundBatch();;
    /**
     * The window of the game itself. Only access in update or drawing methods
     */
    public GameBase base=null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor Method">
    /**
     * Constructs the game
     */
    public Game() {
        InitializeAndLoad();
        UnloadContent();
        keyboard=new KeyBoard();
        mouse=Mouse.getCurrentInstance();
        InputAdvance input=new Inputs();
        addMouseListener(input);
        addMouseMotionListener(input);
        addMouseWheelListener(input);
        addKeyListener(input);
        timer = new Timer(gameSpeed, this);
        updateTimer=new Timer(gameSpeed, new updateClass());
//        imageTimer= new Timer(gameSpeed, new ActionListener(){
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                synchronized(new Object()){
//                    repaint();
//                }
//            }
// });
//        imageTimer.start();
        timer.start();
        updateTimer.start();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor Methods -to be implemented in child class">

    /**
     * Where items can be Initialized during the construction of the game
     */
    public abstract void InitializeAndLoad();

    /**
     * Where content once needed by the constructor for making the game, but no longer needed, can be deleted.
     *
     * Also could be used to simply unload any other content not needed.
     */
    public abstract void UnloadContent();
    
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Update and Draw Methods">

    /**
     * Where all objects needing to be updated can be update at the rate of the game speed.
     *
     * Default game speed is 60 Hz or 60 times per second
     */
    public abstract void Update();
    /**
     * Where all images are loaded into the batch
     * @param g the graphics from the Game
     */
    public abstract void Draw(Graphics g);
    
    /**
     * Adds another handy thread from which items can run
     */
    @Override
    public abstract void run();

    // </editor-fold>
    
    /**
     * Returns the ImageColection used by the game
     * @return the Image Collection used by the game
     */
    public ImageCollection getImageCollection() {
        return batch;
    }

    /**
     * Runs the LoadContent(), Initialize() and UnloadContent() again.
     */
    protected void reset(){
        InitializeAndLoad();
        UnloadContent();
    }

    /**
     * Returns the amount of time elapsed since the start of the game. Result is in nanoseconds
     * @return the amount of time, in nanoSeconds, since the start of the game
     */
    protected double ElapsedGameSystemTimeNano(){
        double currentTime=System.nanoTime();
        return currentTime-startimeNano;
    }

    // <editor-fold defaultstate="collapsed" desc="InputClass">
    /**
     * the class which handles all input events
     */
    protected class Inputs extends InputAdvance{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyboard.setPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keyboard.setRelease(e);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouse.setButton(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouse.setRelease(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            mouse.mouseMoved(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            mouse.mouseMoved(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouse.mouseMoved(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouse.mouseMoved(e);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            mouse.mouseScroll(e);
        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Untouchable Uethods">
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage buffer=new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gr=buffer.createGraphics();
        Draw(gr);
        Drawer(gr);
        g.drawImage(buffer, 0, 0, this);
        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            //Update();
            RepaintCaller();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void RepaintCaller() {
        soundbatch.Update();
        repaint();
    }

    private void Drawer(Graphics g) {
        batch.Render(g, base.theGUI);
    }
    // </editor-fold>
    
    private class updateClass implements ActionListener{

        public void actionPerformed(ActionEvent ae) {
            try {
                synchronized (this) {
                    Update();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
}
