/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleGame;

import Utilities.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Kyle Maximilian Dieter Sweeney
 */
public class gameObject {

    private Vector2 pos;
    public Rect boundingRect;
    private Image2D sprite=new Image2D("Sprites/zeBall.png");
    double plusX = Math.random();
    double plusY = Math.random();
    private Vector2 velocity;

    public gameObject(Vector2 p) {
        pos = p;
        boundingRect = new Rect((int) pos.getX() - sprite.getWidth() / 2,
                (int) pos.getY() - sprite.getHeight() / 2,
                sprite.getWidth(),
                sprite.getHeight(),0f);
        if (plusX < 0.5) {
            plusX = -1;
        } else {
            plusX = 1;
        }
        if (plusY < 0.5) {
            plusY = -1;
        } else {
            plusY = 1;
        }
        Random rand = new Random();
        velocity = new Vector2(rand.nextInt(5) * plusX, rand.nextInt(5) * plusY);
    }

    public void Draw(ImageCollection batch) {
        batch.Draw(sprite, pos,3);
    }

    public void Update(ArrayList<gameObject> s) {
        boundingRect = new Rect((int) pos.getX() - sprite.getWidth() / 2,
                (int) pos.getY() - sprite.getHeight() / 2,
                sprite.getWidth(),
                sprite.getHeight(),0f);
        for(gameObject p : s){
            if(this.boundingRect.intersects(p.boundingRect) && this!=p){
                if(p.pos.getX()>this.pos.getX()){
                    this.velocity.setX(this.velocity.getX()*-1);
                }else if(p.pos.getX()<this.pos.getX()){
                    this.velocity.setX(this.velocity.getX()*-1);
                }else if(p.pos.getY()>this.pos.getY()){
                    this.velocity.setY(this.velocity.getY()*-1);
                }else if(p.pos.getY()<this.pos.getY()){
                    this.velocity.setY(this.velocity.getY()*-1);
                }
            }
        }
        pos.add(velocity);
    }
}
