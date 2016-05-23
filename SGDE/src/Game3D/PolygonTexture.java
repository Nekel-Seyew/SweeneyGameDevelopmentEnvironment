/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game3D;

import Utilities.Vector2;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.TexturePaint;
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
    //for performance, might want regular HashMap
    static ConcurrentHashMap<String, BufferedImage> imageCache = new ConcurrentHashMap<String, BufferedImage>();
    BufferedImage tex;
    boolean drawOutline;
    Vector2 texturePoints[];
    Vector3 worldPoints[];
    Vector3 normal;
    int[] xpos = new int[3];
    int[] ypos = new int[3];
    double zdepth=0;
    
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
        this.texturePoints = new Vector2[3];
        System.arraycopy(texturePoints, 0, this.texturePoints, 0, texturePoints.length);
        for(Vector2 texPoint : this.texturePoints){
            texPoint.setX(Math.round(texPoint.getX()*(this.tex.getWidth()-1)));
            texPoint.setY(Math.round((1-texPoint.getY())*(this.tex.getHeight()-1)));
        }
        //this.texturePoints = texturePoints;
        this.worldPoints = new Vector3[3];
        System.arraycopy(worldPoints, 0, this.worldPoints, 0, worldPoints.length);
        //this.worldPoints = worldPoints;
        this.normal = normal;
        drawOutline = false;
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
        this.texturePoints = new Vector2[3];
        System.arraycopy(texturePoints, 0, this.texturePoints, 0, texturePoints.length);
        for(Vector2 texPoint : this.texturePoints){
            texPoint.setX(Math.round(texPoint.getX()*(this.tex.getWidth()-1)));
            texPoint.setY(Math.round((1-texPoint.getY())*(this.tex.getHeight()-1)));
        }
        this.worldPoints = new Vector3[3];
        System.arraycopy(worldPoints, 0, this.worldPoints, 0, worldPoints.length);
        this.generateNormal();
        drawOutline = false;
    }
    /**
     * Creates a Colored Polygon.
     * @param color Color of the Polygon
     * @param worldPoints actual points in the world
     */
    public PolygonTexture(int color, Vector3[] worldPoints){
        this.worldPoints = new Vector3[3];
        System.arraycopy(worldPoints, 0, this.worldPoints, 0, worldPoints.length);
        this.generateNormal();
        drawOutline = false;
    }
    /**
     * Creates a Colored Polygon with a color set to each point.
     * @param colors Colors of the textures
     * @param worldPoints actual points in the world
     */
    public PolygonTexture(int[] colors, Vector3[] worldPoints){
        this.worldPoints = new Vector3[3];
        System.arraycopy(worldPoints, 0, this.worldPoints, 0, worldPoints.length);
        this.generateNormal();
        drawOutline = false;
    }
    
    public void translate(Vector3 v){
        for(Vector3 vec : worldPoints){
            vec.add(v);
        }
        this.generateNormal();
    }
    
    public void setNormal(Vector3 v){
        this.normal = v;
    }
    
    @Override
    public void prerender(Camera c) {
        double cx = c.getPos().getX();
        double cy = c.getPos().getY();
        double cz = c.getPos().getZ();
        double n = -1.0/cz;
        Camera.projection projection = c.getProjection();
        if(projection == Camera.projection.orthographic){
            
        }else if(projection == Camera.projection.perspective){
            zdepth = 0;
            for (int i = 0; i < 3; i++) {
                double x = this.worldPoints[i].getX();
                double y = this.worldPoints[i].getY();
                double z = this.worldPoints[i].getZ();
                double w = 1.0;
                double near = c.getNear();
                double far = c.getFar();
                
                Vector3 s = c.getSideways();
                Vector3 u = c.getUp();
                Vector3 f = c.getForward();
                
                //double right = near*Math.tan(0.5*c.getFov());
                //double top = right / c.getAspectRatio();
                //double m = -(far+near)/(far-near);
                //double k = (2 * far * near) / (far - near);
                //double nf = near/far;
                //double nt = near/top;
                
//                double dx = nf*(s.getX()*x + s.getY()*y + s.getZ()*z - cx*w);
//                double dy = nt*(u.getX()*x + u.getY()*y + u.getZ()*z - cy*w);
//                double dz = m*(f.getX()*x + f.getY()*y + f.getZ()*z - (cz-(k/m))*w);
//                double dw = -f.getX()*x -f.getY()*y -f.getZ()*z + cz*w;
                
//                double dx = s.getX()*x + s.getY()*y + (s.getZ()+n*cx)*z - cx*w;
//                double dy = u.getX()*x + u.getY()*y + (u.getZ()+n*cy)*z - cy*w;
//                double dz = f.getX()*x + f.getY()*y + (f.getZ()+n*cz)*z - cz*w;
//                double dw = n*z + 1*w;
                
//                double dx = s.getX()*x + s.getY()*y + (s.getZ())*z - cx*w;
//                double dy = u.getX()*x + u.getY()*y + (u.getZ())*z - cy*w;
//                double dz = f.getX()*x + f.getY()*y + (f.getZ())*z - cz*w;
//                double dw = n*(f.getX()*x + f.getY()*y+f.getZ()*z -(cz+1/n)*w);
                
//                int vx = c.getWidth()/8;
//                int vy = c.getHeight()/8;
//                int width = 3*c.getWidth()/4;
//                int height = 3*c.getHeight()/4;
//                double d = 255;
//                
//                double dx = (0.5*width*s.getX() + n*f.getX()*(vx+0.5*width))*x + (0.5*width*s.getY() + n*f.getY()*(vx+0.5*width))*y + (0.5*width*s.getZ() + n*f.getZ()*(vx+0.5*width))*z - (cx*0.5*width+(vx+0.5*width)*(-cz*n+1))*w;
//                double dy = (0.5*height*s.getX() + n*f.getX()*(vy+0.5*height))*x + (0.5*height*s.getY() + n*f.getY()*(vy+0.5*height))*y + (0.5*height*s.getZ() + n*f.getZ()*(vy+0.5*height))*z - (cy*0.5*height+(vy+0.5*height)*(-cz*n+1))*w;
//                double dz = (0.5*d*f.getX()+0.5*d*n*f.getX())*x + (0.5*d*f.getY()+0.5*d*n*f.getY())*y + (0.5*d*f.getZ()+0.5*d*n*f.getZ())*z + (cz*0.5*d + 0.5*d*(-cz*n+1))*w;
//                double dw = n*(f.getX()*x + f.getY()*y+f.getZ()*z -(cz+1/n)*w);
                
                double k = Math.tan(c.getFov()/2);
                double r = c.getAspectRatio();
//                double dx = 1/(r*k) * (s.getX()*x + s.getY()*y + s.getZ()*z - cx*w);
//                double dy = 1/k * (u.getX()*x + u.getY()*y + u.getZ()*z -cy*w);
//                double dz = far/(far-near)*(f.getX()*x + f.getY()*y + f.getZ()*z) + (-cz*(far/(far-near))+1)*w;
//                double dw = -(near*far)/(far-near) * (f.getX()*x + f.getY()*y + f.getZ()*z - cz*w);
                
                double j = far+near;
                double h = far-near;
                double dx = 1/(r*k)*(s.getX()*x + s.getY()*y + s.getZ()*z - cx*w);
                double dy = 1/k * (u.getX()*x + u.getY()*y + u.getZ()*z -cy*w);
                double dz = -j/h * (f.getX()*x + f.getY()*y + f.getZ()*z) + (cz*j/h - 2*far*near/h)*w;
                double dw = -f.getX()*x - f.getY()*y - f.getZ()*z + cz*w;
                
                double vecx = dx/dw;
                double vecy = dy/dw;
                double vecz = dz/dw;
                
                double viewpointx = 0;
                double viewpointy = 0;
                
                xpos[i] = (int)((vecx+2)/2*c.getWidth() + viewpointx);
                ypos[i] = (int)((1-(vecy+2)/2) * c.getHeight() + viewpointy);
                //xpos[i] = (int)((vecx + 1) * (c.getWidth()/2.0) + viewpointx);
                //ypos[i] = (int)((vecy + 1) * (c.getHeight()/2.0) + viewpointy);
                if(i == 0){ zdepth = vecz;}
                else if(vecz > zdepth){ zdepth = vecz;}
                else if(vecz < 0){ 
                    zdepth = vecz;
                
                }
            }
        }
    }
    
    @Override
    public void render(Graphics2D g){
        if (this.zdepth >= 0) {
            Polygon tri = new Polygon();
            tri.addPoint(xpos[0], ypos[0]);
            tri.addPoint(xpos[1], ypos[1]);
            tri.addPoint(xpos[2], ypos[2]);

            if (this.drawOutline) {
                g.setColor(Color.black);
                g.drawPolygon(tri);
            }

            Vector2[] newshape = new Vector2[3];
            for (int i = 0; i < 3; i++) {
                newshape[i] = new Vector2(xpos[i], ypos[i]);
            }

            AffineTransform at = createTransform(texturePoints, newshape);

            g.setClip(tri);
            g.drawImage(tex, at, null);//end step
            g.setClip(null);
        }
    }
    
    public void setOutline(boolean tf){
        this.drawOutline=tf;
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
        float x11 = (float)source[0].getX();
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

    @Override
    public double zdepth() {
        return zdepth;
    }

}
