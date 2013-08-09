/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleGame;

import Utilities.*;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

/**
 *
 * @author Kyle Maximilian Dieter Sweeney
 */
public class MyPlayer {

    private Vector2 position;
    private Vector2 velocity;
    private Image2D sprite;
    public Rect boundingRect;
    public float angle=0;
    private float scale=1f;

    public MyPlayer(String spriteFileName, Vector2 pos, Vector2 velocity) {
        this.position = pos;
        this.velocity = velocity;
        sprite = new Image2D(spriteFileName);
        boundingRect = new Rect(pos,
                sprite.getWidth(), sprite.getHeight(),0f);
    }

    public void changeVelocity(Vector2 newVelocity) {
        velocity = newVelocity;
    }

    public Vector2 getVelocity() {
        return this.velocity;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void Draw(ImageCollection batch) {
        batch.Draw(sprite, position,angle,Color.blue,scale,scale,10);
        int[] xPoints=new int[]{(int)boundingRect.UpperLeftCorner().getX(), (int)boundingRect.UpperRightCorner().getX(), (int)boundingRect.LowerRightCorner().getX(), (int)boundingRect.LowerLeftCorner().getX()};
        int[] yPoints=new int[]{(int)boundingRect.UpperLeftCorner().getY(), (int)boundingRect.UpperRightCorner().getY(), (int)boundingRect.LowerRightCorner().getY(), (int)boundingRect.LowerLeftCorner().getY()};
        batch.drawPolyline(xPoints, yPoints, 4, Color.blue, 10);
    }

    public void Update(ArrayList<gameObject> g) {
        boundingRect = sprite.getRectangle();
//        for(gameObject s: g){
//            if(this.boundingRect.intersects(s.boundingRect)){
//                System.out.println("Collision!!");
//            }
//        }
    }

    public void changePosition(Vector2 pos) {
        this.position = pos;
    }

    public void move(Vector2 pos) {
        this.position.add(pos);
    }
    
    public void rotateRight(){
        angle+=0.1f;
    }
    public void rotateLeft(){
        angle-=0.1f;
    }
    public void grow(){
        scale+=0.05f;
    }
    public void shrink(){
        scale-=0.05f;
    }

    public void KeyInput(KeyEvent e) {
        if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) {
            position.dY(velocity.getY() * -1);
        }
        if (e.getKeyChar() == keyChars.s) {
            position.dY(velocity.getY());
        }
        if (String.valueOf(e.getKeyChar()).equals("a")
                || String.valueOf(e.getKeyChar()).equals("A")) {
            position.dX(velocity.getX() * -1);
        }
        if (e.getKeyText(e.getKeyCode()).equals("D")) {
            position.dX(velocity.getX());
        }

    }
}
