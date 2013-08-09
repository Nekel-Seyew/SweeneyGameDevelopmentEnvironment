/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hardware_Accelerated;

import java.awt.Image;

/**
 * IGNORE-NOT USED
 * @author KyleSweeney
 */
public class Sprite {
    private Image sprite;
    
    public Sprite(Image image){
        this.sprite=image;
    }
    
    public int getWidth(){
        return sprite.getWidth(null);
    }
    
    public int getHeight(){
        return sprite.getHeight(null);
    }
}
