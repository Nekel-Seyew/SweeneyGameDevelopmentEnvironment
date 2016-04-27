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
package Hardware_Accelerated;

import java.awt.Canvas;
import javax.swing.JFrame;

/**
 * The game basis which holds the game.
 * @author KyleSweeney
 */
public class AccelGame {
    static{
        if(System.getProperty("os.name").contains("Win")||System.getProperty("os.name").contains("win")){
            System.setProperty("sun.java2d.d3d","True");
        }else{
            System.setProperty("sun.java2d.opengl","True");
        }
    }
    //private static final int THREAD_DELAY=16;
    /**
     * The game's canvas
     */
    public static Canvas gui;
    /**
     * The physical game window
     */
    public static JFrame frame;
    private AGame game;
    private Thread gameThread;
    
    public AccelGame(AGame game, String title){
        this.game=game;
        frame= new JFrame(title);
        gui= new Canvas();
        frame.setSize(game.GAME_WIDTH, game.GAME_HEIGHT);
        frame.add(gui);
        frame.setVisible(true);
        gui.createBufferStrategy(2);
        gameThread=new Thread(game);
        gameThread.setPriority(Thread.MAX_PRIORITY);
        
        //adding listeners to the jframe
        frame.addKeyListener(game.getInputs());
        frame.addMouseListener(game.getInputs());
        frame.addMouseMotionListener(game.getInputs());
        frame.addMouseWheelListener(game.getInputs());
        //close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //adding listeners for the canvas
        gui.addKeyListener(game.getInputs());
        gui.addMouseListener(game.getInputs());
        gui.addMouseMotionListener(game.getInputs());
        gui.addMouseWheelListener(game.getInputs());
    }
    /**
     * Starts the game.
     */
    public void run(){
        game.giveGraphics(gui.getBufferStrategy());
        gameThread.start();
    }
    
    
    
    
    /*
    public static int lineX=0, lineY=0;
    
    public static void main(String[] args){
        JFrame frame= new JFrame();
        gui= new Canvas();
        frame.getContentPane().add(gui);
        frame.setSize(800,600);
        frame.setVisible(true);
        Thread gameThread= new Thread(new GameLoop());
        gameThread.setPriority(Thread.MIN_PRIORITY);
        gameThread.start();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private static void updateGameState(){
        lineX++;
    }
    
    private static void updateGUI(BufferStrategy strategy){
        Graphics g = strategy.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,gui.getWidth(), gui.getHeight());
        g.setColor(Color.BLACK);
        
        g.drawLine(lineX, 0, lineX+10, 0);
        g.dispose();
        strategy.show();
    }
    
    private static class GameLoop implements Runnable{
        boolean isRunning = true;
        
        public void run(){
            long cycleTime=System.currentTimeMillis();
            gui.createBufferStrategy(2);
            BufferStrategy strategy=gui.getBufferStrategy();
            
            while(isRunning){
                updateGameState();
                
                updateGUI(strategy);
                
                cycleTime+=AccelGame.THREAD_DELAY;
                long difference = cycleTime-System.currentTimeMillis();
                try{
                    Thread.sleep(Math.max(0, difference));
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
            
        }
    }
    * 
    * */
    
}
