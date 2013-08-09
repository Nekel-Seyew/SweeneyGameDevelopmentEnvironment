/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hardware_Accelerated;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.image.VolatileImage;

/**
 * IGNORE-NOT USED
 * @author KyleSweeney
 */
public class GameCanvas extends Canvas{
    private VolatileImage vol_img;
    
    @Override
    public void paint(Graphics g){
        createBackBuffer();
        
        do{
            GraphicsConfiguration gc= this.getGraphicsConfiguration();
            int valCode=vol_img.validate(gc);
            
            if(valCode==VolatileImage.IMAGE_INCOMPATIBLE){
                createBackBuffer();
            }
            
            Graphics offscreen=vol_img.getGraphics();
            doPaint(offscreen);
            
            g.drawImage(vol_img, 0, 0, this);
        }while(vol_img.contentsLost());
        
    }
    
    private void doPaint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(), getHeight());
        g.setColor(Color.BLACK);
        //g.drawLine(AccelGame.lineX,0,AccelGame.lineX+100,0);
    }
    
    private void createBackBuffer(){
        GraphicsConfiguration gc= this.getGraphicsConfiguration();
        vol_img=gc.createCompatibleVolatileImage(this.getWidth(), this.getHeight());
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
}
