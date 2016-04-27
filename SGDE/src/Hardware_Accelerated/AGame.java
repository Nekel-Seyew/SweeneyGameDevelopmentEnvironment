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
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE U
 */
package Hardware_Accelerated;

import Utilities.ImageCollection;
import Utilities.InputAdvance;
import Utilities.KeyBoard;
import Utilities.Mouse;
import Utilities.Vector2;
import Utilities.ViewScreen;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * A Hardware Accelerated game, much better than the normal Game Class.
 * @author KyleSweeney
 */
public abstract class AGame implements Runnable{
    /**
     * The Keyboard object belonging to the game object.
     */
    public KeyBoard keyboard;
    /**
     * The Mouse object belonging to the game object.
     */
    public Mouse mouse;
    private ImageCollection batch;
    private boolean isRunning;
    private InputAdvance inputs;
    /**
     * The Amount of milliseconds between the runtime of thread.
     */
    public static int FPS_DELAY=16;
    /**
     * The width of the game window. If you change this, you need to do it in the resize method.
     */
    public int GAME_WIDTH=800;
    /**
     * The height of the game window. If you change this, you need to do it in the resize method.
     */
    public int GAME_HEIGHT=600;
    /**
     * The Viewscreen. This is the game window into the game world. If you move this, you change the entire view.
     */
    public ViewScreen v;
    
    private Color bgc=Color.white;
    
    private BufferStrategy gfx;
    
    private long internalClock;
    
    /**
     * Default constructor for AGame. Do Not Overide.
     */
    public AGame(){
        keyboard=new KeyBoard();
        mouse=Mouse.getCurrentInstance();
        internalClock=System.currentTimeMillis();
        inputs= new Inputs();
        v=new ViewScreen(new Vector2(GAME_WIDTH, GAME_HEIGHT));
        batch=new ImageCollection(v);
        isRunning=true;
        InitializeAndLoad();
        
    }
    /**
     * Standard InitializeAndLoad function for setting up the game logic.
     */
    public abstract void InitializeAndLoad();
    /**
     * Seriously, does anyone know what this does? It will be called when you call exit()
     *@deprecated
     */
    public abstract void UnloadContent();
    /**
     * Where the game logic should be updated. Will happen 60 times a second.
     */
    public abstract void Update();
    /**
     * Where you draw your sprites in the game. Will happen 60 times a second.
     * @param g The graphics object used to draw
     * @param batch the batch to which things can be drawn.
     */
    public abstract void Draw(Graphics2D g, ImageCollection batch);
    
    public void exit(){
        UnloadContent();
        isRunning=false;
        System.exit(0);
    }
    /**
     * Internal system function to allow for proper drawing. IGNORE.
     * @param gfx 
     */
    public void giveGraphics(BufferStrategy gfx){
        this.gfx=gfx;
    }
    /**
     * Another system function which grabs the interface between event-based actions and state-based actions.
     * @return 
     */
    public InputAdvance getInputs(){
        return inputs;
    }
    /**
     * Sets the background color.
     * @param c the color of the background.
     */
    public void setBackgroundColor(Color c){
        bgc=c;
    }
    /**
     * Resizes the game
     * @param width new width of the window
     * @param height new height of the window
     */
    public void resize(int width, int height){
        this.GAME_HEIGHT=height;
        this.GAME_WIDTH=width;
        this.v.setHeight(height);
        this.v.setWidth(width);
        AccelGame.frame.setVisible(false);
        AccelGame.frame.setSize(width, height);
        AccelGame.frame.setVisible(true);
    }
    
    private void updateGUI(BufferStrategy strategy){
        Graphics2D gfx = (Graphics2D) strategy.getDrawGraphics();
        gfx.setColor(bgc);
        gfx.fillRect(0,0,AccelGame.gui.getWidth(), AccelGame.gui.getHeight());
        Draw(gfx,batch);
        batch.Render(gfx, AccelGame.gui);
        gfx.dispose();
        strategy.show();
    }
    
    public BufferedImage getGuiImage(){
        BufferedImage img = new BufferedImage(AccelGame.gui.getWidth(), AccelGame.gui.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g=img.createGraphics();
        g.setColor(bgc);
        g.fillRect(0,0,AccelGame.gui.getWidth(), AccelGame.gui.getHeight());
        Draw(g,batch);
        batch.Render(g, AccelGame.gui);
        return img;
    }
    
    /**
     * Internal run function which makes the game run. IGNORE and DO NOT OVERRIDE.
     */
    @Override
    public void run(){
        internalClock=System.currentTimeMillis();
        synchronized(this){
            while(isRunning){
                internalClock=System.currentTimeMillis();
                //game logic
                Update();
                //graphics
                updateGUI(AccelGame.gui.getBufferStrategy());
                
                //inputfix?
                //AccelGame.gui.setFocusable(true);
                
                //sleeping
                //internalClock+=FPS_DELAY;
                long difference =System.currentTimeMillis()-internalClock;
                try{
//                    Thread.sleep(Math.max(0, difference));
                    //System.out.println("Difference: "+difference);
                    if(difference > FPS_DELAY){
                        continue;
                    }else{
                        long sleep=FPS_DELAY-difference;
                        Thread.sleep(sleep);
                    }
                    //Thread.sleep(Math.min(difference, FPS_DELAY));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * A function to see if the game is still running.
     * @return whether or not the game is running.
     */
    public boolean isAlive(){
        return isRunning;
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
}
