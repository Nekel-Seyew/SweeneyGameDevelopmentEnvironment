/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hardware_Accelerated;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 * The game basis which holds the game.
 * @author KyleSweeney
 */
public class AccelGame {
    static{
        System.setProperty("sun.java2d.opengl","True");
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
        frame= new JFrame();
        gui= new Canvas();
        frame.setSize(game.GAME_WIDTH, game.GAME_HEIGHT);
        frame.add(gui);
        frame.setVisible(true);
        gui.createBufferStrategy(2);
        gameThread=new Thread(game);
        
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
