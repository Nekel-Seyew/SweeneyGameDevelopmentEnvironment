/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ExampleGame;

import Hardware_Accelerated.AGame;
import Hardware_Accelerated.AccelGame;
import Networking.UDP.NetworkThread;
import Networking.UDP.UDPServerListener;
import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Mouse;
import Utilities.Rect;
import Utilities.Vector2;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Kyle Maximilian Dieter Sweeney
 */
public class ExampleGame extends AGame implements UDPServerListener{
    //Where instance variables go
    MyPlayer player;
    gameObject go;
    ArrayList<gameObject> gos;
    Rect box;
    Image2D pixel;
    int previousTurns=0;
    boolean resized=false;
    
    boolean first=true;
    
    NetworkThread server;
    NetworkThread client;
    Image2D myGif;
    
    Robot robot;

    @Override
    public void Update(){
        if(first){
            AccelGame.frame.addKeyListener(new keyListener());
            AccelGame.gui.addKeyListener(new keyListener());
            first=false;
            Toolkit tk=Toolkit.getDefaultToolkit();
            AccelGame.gui.setCursor(tk.createCustomCursor(tk.createImage("Sprites/theDot.png"), new Point(AccelGame.gui.getX(),AccelGame.gui.getY()), "img"));
            
        }
        
        //player.Update();
        ArrayList<gameObject> d=gos;
        for(gameObject g: gos){
            g.Update(d);
        }
        if(keyboard.isKeyDown('w')||keyboard.isKeyDown('8')){
            player.move(new Vector2(0,-5));
        }
        if(keyboard.isKeyDown('d')||keyboard.isKeyDown('6')){
            player.move(new Vector2(5,0));
        }
        if(keyboard.isKeyDown('s')||keyboard.isKeyDown('2')){
            player.move(new Vector2(0,5));
        }
        if(keyboard.isKeyDown('a')||keyboard.isKeyDown('4')){
            player.move(new Vector2(-5,0));
        }
        if(Mouse.isPressed(Mouse.Buttons.Left)){
            player.changePosition(mouse.location());
        }
        if(keyboard.isKeyDown('q')){
            player.rotateLeft();
        }
        if(keyboard.isKeyDown('e')){
            player.rotateRight();
        }
        if(keyboard.isKeyDown('p')){
            player.grow();
        }
        if(keyboard.isKeyDown('o')){
            player.shrink();
        }
        if(keyboard.isKeyDown('h') && !resized){
            this.resize(1000, 1500);
            System.out.println("Resize!!!!!!!!!!!!!");
            resized=true;
        }
        if(keyboard.isKeyDown('g') && resized){
            this.resize(800,600);
            System.out.println("Smaller!");
            resized=false;
        }
        if(previousTurns>mouse.getWheelScroll()){
            player.shrink();
            previousTurns=mouse.getWheelScroll();
        }
        if(previousTurns<mouse.getWheelScroll()){
            player.grow();
            previousTurns=mouse.getWheelScroll();
        }
        
        
        //System.out.println("Mouse's wheel Scroll: "+mouse.getWheelScroll());
        player.Update(d);
//        if(player.boundingRect.intersects(pixel.getRectangle()))
//            System.out.println("Collision with box!!");
//        System.out.println("Player Position: ("+player.getPosition().getX()+","+player.getPosition().getY()+")");
//        System.out.println("Player angle: "+player.angle%(2*Math.PI));
    }

    @Override
    public void Draw(Graphics2D g, ImageCollection batch) {
        //batch.fillRect(new Vector2(box.x, box.y), box.width, box.height, Color.blue, 1000);
        batch.drawRect(pixel.getRectangle(), Color.GREEN, 200);
        batch.Draw(pixel, new Vector2(100,50), 0, Color.blue, 1, 500, 100, null, 20);
        player.Draw(batch);
        go.Draw(batch);
        for (gameObject t : gos) {
            t.Draw(batch);
        }
        
//        batch.Draw(myGif, new Vector2(200,200), 1000);
        
        this.setBackgroundColor(Color.red);
    }

    @Override
    public void InitializeAndLoad() {
        System.setProperty("sun.java2d.d3d","false");
        System.setProperty("sun.java2d.opengl","True");
        player=new MyPlayer("Sprites/Wyvern.jpg", new Vector2(200,300), new Vector2(10,10));
        go=new gameObject(new Vector2(400,300));
        gos= new ArrayList<gameObject>();
        Random rand= new Random();
        for(int i=0; i<500; i++){
            gos.add(new gameObject(new Vector2(rand.nextInt(4001), rand.nextInt(3001))));
            System.out.println("Still Building....");
        }
        box=new Rect(100,100,500,100);
        pixel= new Image2D("Sprites/theDot.png");
//        try {
//            AudioStream as= new AudioStream(new FileInputStream("Sounds/Unreel.wav"));
//            AudioPlayer.player.start(as);
//        } catch (IOException ex) {
//            Logger.getLogger(ExampleGame.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            robot= new Robot();
    //        server = new NetworkThread(new UDPServer("192.168.0.11",4446),this, null, null);
    //        client = new NetworkThread(new UDPClient("192.168.0.11",4446),this, null, this);
    //        myGif=new Image2D("Sprites/mygif.gif");
    //        server.start();
    //        client.start();
        } catch (AWTException ex) {
            Logger.getLogger(ExampleGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void playSound(){
        System.out.println("PLAYING SOUND");
        AudioStream as = null;
        try {
            as = new AudioStream(new FileInputStream("Sounds/s_206.wav"));
            AudioPlayer.player.start(as);
        } catch (Exception ex) {
            Logger.getLogger(ExampleGame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                as.close();
            } catch (IOException ex) {
                Logger.getLogger(ExampleGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void UnloadContent() {
        
    }

    @Override
    public void recieveData(byte[] data) {
        System.out.println(new String(data));
    }
    
    
    private class keyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_I) {
                System.out.println("EVENT!");
                try {
                    Image2D.saveImage(getGuiImage(), "Sprites/Test.png");
                } catch (Exception ex) {
                    Logger.getLogger(ExampleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(e.getKeyCode()==KeyEvent.VK_M){
                System.out.println(AccelGame.gui.getLocationOnScreen());
                robot.mouseMove(300+(int)AccelGame.gui.getLocationOnScreen().getX(), 400+(int)AccelGame.gui.getLocationOnScreen().getY());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
    
}