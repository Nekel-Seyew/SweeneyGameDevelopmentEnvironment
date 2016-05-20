/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game3D;

import Utilities.Vector2;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author Nekel
 */
public class PolygonTexture implements Renderable{
    static ConcurrentHashMap<String, BufferedImage> imageCache;//for performance, might want regular HashMap
    BufferedImage tex;
    boolean drawOutline;
    Vector2 texturePoints[];
    Vector3 worldPoints[];
    Vector3 normal;
    
    /**
     * Constructs a textured polygon.
     * @param texture file of texture
     * @param texturePoints list of points in the image map, all points must be [0,1] bottom left is (0,0) top right is (1,1)
     * @param worldPoints actual points in the world
     * @param normal pre-generated normal of texture
     */
    public PolygonTexture(String texture, Vector2[] texturePoints, Vector3[] worldPoints, Vector3 normal){
        if(imageCache.containsKey(texture)){
            this.tex = imageCache.get(texture);
        }else{
            try {
                tex = toCompatibleImage(ImageIO.read(new File(texture)));
                imageCache.put(texture, tex);
            } catch (IOException ex) {
                Logger.getLogger(PolygonTexture.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.texturePoints = texturePoints;
        for(Vector2 texPoint : this.texturePoints){
            texPoint.setX(texPoint.getX()*this.tex.getWidth());
            texPoint.setY((1-texPoint.getY())*this.tex.getHeight());
        }
        //this.texturePoints = texturePoints;
        this.worldPoints = worldPoints;
        this.normal = normal;
    }
    /**
     * Constructs a textured polygon. Will also calculate the normal by Crossing
     * the vector going from the first point to the second point, and the vector
     * going from the first point to the third point.
     * @param texture file of texture
     * @param texturePoints list of points in the image map, all points must be [0,1] bottom left is (0,0) top right is (1,1)
     * @param worldPoints actual points in the world
     */
    public PolygonTexture(String texture, Vector2[] texturePoints, Vector3[] worldPoints){
        if(imageCache.containsKey(texture)){
            this.tex = imageCache.get(texture);
        }else{
            try {
                tex = toCompatibleImage(ImageIO.read(new File(texture)));
                imageCache.put(texture, tex);
            } catch (IOException ex) {
                Logger.getLogger(PolygonTexture.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.texturePoints = texturePoints;
        for(Vector2 texPoint : this.texturePoints){
            texPoint.setX(Math.round(texPoint.getX()*this.tex.getWidth()));
            texPoint.setY(Math.round((1-texPoint.getY())*this.tex.getHeight()));
        }
        this.worldPoints = worldPoints;
        this.generateNormal();
    }
    /**
     * Creates a Colored Polygon.
     * @param color Color of the Polygon
     * @param worldPoints actual points in the world
     */
    public PolygonTexture(int color, Vector3[] worldPoints){
        
    }
    /**
     * Creates a Colored Polygon with a color set to each point.
     * @param colors Colors of the textures
     * @param worldPoints actual points in the world
     */
    public PolygonTexture(int[] colors, Vector3[] worldPoints){
        
    }
    
    public void translate(Vector3 v){
        for(Vector3 vec : worldPoints){
            vec.add(v);
        }
        this.generateNormal();
    }
    public void rotate(Quaternion qr){
        
    }
    
    public void setNormal(Vector3 v){
        this.normal = v;
    }
    
    @Override
    public void render(Graphics2D g, Camera c) {
        
        
        Polygon tri = new Polygon();
        Graphics2D imageGraphics = this.tex.createGraphics();
        imageGraphics.clip(tri);
        
        g.drawImage(tex, null, null);//end step
    }
    
    
    
    private void generateNormal(){
        Vector3 a = worldPoints[1].clone();
        Vector3 b = worldPoints[2].clone();
        a.subtract(worldPoints[0]);
        b.subtract(worldPoints[0]);
        normal = a.crossProduct(b);
        normal.normalize();
    }
    private BufferedImage toCompatibleImage(BufferedImage img){
        GraphicsConfiguration gfxcfg=GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getDefaultScreenDevice().
                getDefaultConfiguration();
        if(img.getColorModel().equals(gfxcfg.getColorModel())){
            return img;
        }else{
            BufferedImage new_img=gfxcfg.createCompatibleImage(img.getWidth(), img.getHeight(), img.getTransparency());
            
            Graphics2D gfx = (Graphics2D) new_img.getGraphics();
            gfx.drawImage(img, 0,0, null);
            gfx.dispose();
            
            return new_img;
        }
    }
    
    
    public static AffineTransform createTransform(Vector2[] source, Vector2[] dest) {
        float x11 = (float) source[0].getX();
        float x12 = (float)source[0].getY();
        float x21 = (float)source[1].getX();
        float x22 = (float)source[1].getY();
        float x31 = (float)source[2].getX();
        float x32 = (float)source[2].getY();
        float x12mx32 = x12 - x32;
        float x12mx22 = x12 - x22;
        float x11mx21 = x11 - x21;
        float x11mx31 = x11 - x31;
        float y11 = (float)dest[0].getX();
        float y12 = (float)dest[0].getY();
        float y21 = (float)dest[1].getX();
        float y22 = (float)dest[1].getY();
        float y31 = (float)dest[2].getX();
        float y32 = (float)dest[2].getY();
        float y12my21 = y11 - y21;
        float y12my22 = y12 - y22;
        float y12my32 = y12 - y32;
        float y11my31 = y11 - y31;

        float a1 = ((y12my21) * (x12mx32) - (y11my31) * (x12mx22))
                 / ((x11mx21) * (x12mx32) - (x11mx31) * (x12mx22));
        float a2 = ((y12my21) * (x11mx31) - (y11my31) * (x11mx21))
                 / ((x12mx22) * (x11mx31) - (x12mx32) * (x11mx21));
        float a3 = y11 - a1 * x11 - a2 * x12;
        float a4 = ((y12my22) * (x12mx32) - (y12my32) * (x12mx22))
                 / ((x11mx21) * (x12mx32) - (x11mx31) * (x12mx22));
        float a5 = ((y12my22) * (x11mx31) - (y12my32) * (x11mx21))
                 / ((x12mx22) * (x11mx31) - (x12mx32) * (x11mx21));
        float a6 = y12 - a4 * x11 - a5 * x12;
        return new AffineTransform(a1, a4, a2, a5, a3, a6);
    }

}
