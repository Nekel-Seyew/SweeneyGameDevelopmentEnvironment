/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import Advance.RadixSortable;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


/*Copyright 2011 Kyle Dieter Sweeney
 * This file is part of the Sweeney Game Development Environment.

    Sweeney Game Development Environment is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Sweeney Game Development Environment is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Sweeney Game Development Environment.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 *
 * @author Kyle Maximilian Dieter Sweeney
 */

/**
 * A Class which holds a sprite, and can draw it.
 */
public class Image2D extends Image implements Serializable, RadixSortable{
    protected BufferedImage bf;
    protected BufferedImage tinted;
    protected AffineTransform rotation;
    protected AffineTransform move;
    protected AffineTransform transform;
    protected Vector2 position;
    protected int depth;
    protected float scalex=1f;
    protected float scaley=1f;
    protected float theta=0f;
    protected String s="";
    protected Vector2 rotationAnchor;
    protected Rectangle drawnArea;
    private Shading shading;
    protected int thisID;
    protected static int IDcount=0;
    protected HashMap subImageCache;
    protected static Hashtable<String, Image> images = new Hashtable<String, Image>();
    //protected static Hashtable<String, HashMap> subimages= new Hashtable<String, HashMap>();
    protected static Hashtable<String, Hashtable<Rectangle, HashMap>> subimages=new Hashtable<String, Hashtable<Rectangle, HashMap>>();
    
    
    /**
     * Takes a string of the location in the project the images resides, and image name.
     * 
     * By default, this will try to optimize the image to best fit with local graphics configurations. If you do not want this,
     * use the contructor "Image2D(String s, boolean optimize)" with the second value as "false".
     *
     * Example: the Image called ball in folder Sprites: "Sprites/ball.png"
     * @param s -the string of the image name and location.
     */
    public Image2D(String s){
        subImageCache=new HashMap();
        try{
            
            if(images.containsKey(s)){
                bf=(BufferedImage)images.get(s);
            }else{
                bf=toCompatibleImage(ImageIO.read(new File(s)));
            }
            position= new Vector2();
            drawnArea=new Rect(0,0,bf.getWidth(),bf.getHeight());
            tinted=bf;
            bf.setAccelerationPriority(1f);
        }catch(Exception e){
            e.printStackTrace();
        }
        this.s=new String(s);
        depth=0;
        thisID=IDcount++;
        shading= new Shading(Color.white,0f);
    }
    
    /**
     * Takes a string of the location in the project the images resides, and image name.
     * 
     * This gives you the option to have non-optimized images for your local graphics environment.
     * @param s the string of the image name and location
     * @param optimize whether or not optimize the image for local environments.
     */
    public Image2D(String s, boolean optimize) {
        subImageCache = new HashMap();
        if (optimize) {
            try {
                if (images.containsKey(s)) {
                    bf = (BufferedImage) images.get(s);
                } else {
                    bf = toCompatibleImage(ImageIO.read(new File(s)));
                }
                position = new Vector2();
                drawnArea = new Rect(0, 0, bf.getWidth(), bf.getHeight());
                tinted = bf;
                bf.setAccelerationPriority(1f);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.s=new String(s);
            depth = 0;
            thisID = IDcount++;
        } else {
            try {
                if (images.containsKey(s)) {
                    bf = (BufferedImage) images.get(s);
                } else {
                    bf = ImageIO.read(new File(s));
                }
                position = new Vector2();
                drawnArea = new Rect(0, 0, bf.getWidth(), bf.getHeight());
                tinted = bf;
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.s=new String(s);
            depth = 0;
            thisID = IDcount++;
        }
        shading= new Shading(Color.white,0f);
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
    /**
     * a default constructor, using the super default constructor
     */
    public Image2D(){
        subImageCache=new HashMap();
        depth=0;
        thisID=IDcount++;
        shading= new Shading(Color.white,0f);
    }
    /**
     * Sets the depth of the item
     * @param x -the depth
     */
    public void setDepth(int x){
        depth=x;
    }
    /**
     * returns the depth of the item.
     * @return -the depth
     */
    public int getDepth(){
        return depth;
    }
    /**
     * sets the position of the image
     * @param pos -the position in game world
     */
    public void setVector2(Vector2 pos){
        position=pos;
    }
    /**
     * sets the position using an X and Y
     * @param x -X coordinate
     * @param y -Y coordinate
     */
    public void setVector2(double x, double y){
        position=new Vector2(x,y);
    }
    /**
     * returns the Vector2 position
     * @return -the position
     */
    public Vector2 getPosition(){
        return position;
    }
 
    /**
     * Returns the Height of the original image
     * @return the Height of the original image
     */
    public int getHeight(){
        return bf.getHeight();
    }
    /**
     * Returns the Width of the original image
     * @return the width of the original image
     */
    public int getWidth(){
        return bf.getWidth();
    }
    /**
     * Obtains the scaled height of the image
     * @return the scaled height
     */
    public float getScaledHeight(){
        return getHeight()*scaley;
    }
    /**
     * Obtains the scaled width of the image
     * @return the scaled width
     */
    public float getScaledWidth(){
        return getWidth()*scalex;
    }
    /**
     * Sets the rotation of the sprite in radians
     * @param theta rotation in radians
     */
    public void setRotation(float theta){
        this.theta=(float)(theta%(2*Math.PI));
    }
    /**
     * Sets the Width scaling factor for the image
     * @param scalex the amount to scale width wise
     */
    public void setScaleX(float scalex){
        if(scalex<0f){
            this.scalex=0;
        }else{
            this.scalex=scalex;
        }
    }
    /**
     * Sets the Height scaling factor for the image
     * @param scaley the amount to scale height wise
     */
    public void setScaleY(float scaley){
        if(scaley<0f){
            this.scaley=0;
        }else{
            this.scaley=scaley;
        }
    }
    /**
     * Sets the area of the image to be drawn. Image space starts with the upper-left corner of the image being 0,0
     * @param r the area to be drawn
     */
    public void setDrawnArea(Rectangle r){
        drawnArea=r;
    }
    /**
     * Returns an ImageIcon of the original image
     * @return a new ImageIcon
     */
    public ImageIcon getIcon(){
        return new ImageIcon(bf);
    }
    
    /**
     * Returns the Rect which bounds this image.
     * @return Rect surrounding this image
     */
    public Rect getRectangle(){
        return new Rect((int)(position.getX()-(this.getScaledWidth()/2)), 
                (int)(position.getY()-(this.getScaledHeight()/2)), (int)(this.getScaledWidth()), (int)(this.getScaledHeight()),theta);
    }
    
    /**
     * While could be used independently, it is suggested that only the ImageCollection use this method.
     * @param g -the graphics used by the JFrame/JPanel
     * @param c -the JPanel this will be drawn in
     */
    public void Draw(Graphics2D g, ImageObserver c){
        //setup
        Vector2 s=new Vector2((int)position.getX()-(this.getScaledWidth()/2), (int)position.getY()-(this.getScaledHeight()/2));
        move=new AffineTransform();
        rotation= new AffineTransform();
        transform = new AffineTransform();
        //move the image
        transform.setToScale(scalex, scaley);
        rotation.setToRotation(theta, this.getWidth()/2, this.getHeight()/2);
        move.setToTranslation((int)s.getX(), (int)s.getY());
        move.concatenate(transform);
        move.concatenate(rotation);
        
        //getCache
        Image img=null;
        Hashtable<Rectangle, HashMap> source=null;
        HashMap tint=null;
        try{
        if (subimages.containsKey(this.s)) {
            source = subimages.get(this.s);
            if (source.containsKey(drawnArea)) {
                tint = source.get(drawnArea);
                if (tint.containsKey(shading)) {
                    img = (BufferedImage) tint.get(shading);
                } else {
                    img=toCompatibleImage(ImageIO.read(new File(this.s))).getSubimage(drawnArea.x, drawnArea.y, drawnArea.width, drawnArea.height);
                    //img = tinted.getSubimage(drawnArea.x, drawnArea.y, drawnArea.width, drawnArea.height);
                    tintImage((BufferedImage) img, shading.getColor(), shading.getPercent());
                    tint.put(shading, ((BufferedImage) img));
                }
            }else{
                tint=new HashMap();
                img=toCompatibleImage(ImageIO.read(new File(this.s))).getSubimage(drawnArea.x, drawnArea.y, drawnArea.width, drawnArea.height);
                //img = tinted.getSubimage(drawnArea.x, drawnArea.y, drawnArea.width, drawnArea.height);
                tintImage((BufferedImage) img, shading.getColor(), shading.getPercent());
                tint.put(shading, ((BufferedImage) img));
                source.put(drawnArea, tint);
                
            }
        }else{
            source=new Hashtable<Rectangle, HashMap>();
            tint=new HashMap();
            img=toCompatibleImage(ImageIO.read(new File(this.s))).getSubimage(drawnArea.x, drawnArea.y, drawnArea.width, drawnArea.height);
            //img = tinted.getSubimage(drawnArea.x, drawnArea.y, drawnArea.width, drawnArea.height);
            tintImage((BufferedImage) img, shading.getColor(), shading.getPercent());
            tint.put(shading, ((BufferedImage) img));
            source.put(drawnArea, tint);
            subimages.put(this.s, source);
//            subimages.put(this.s, cache);
//            if (cache.containsKey(drawnArea)) {
//                img = (Image) cache.get(drawnArea);
//            } else {
//                img = tinted.getSubimage(drawnArea.x, drawnArea.y, drawnArea.width, drawnArea.height);
//                cache.put(drawnArea, img);
//            }
        }
        }catch(Exception e){
            
        }
        
        //g.drawImage(tinted.getSubimage(drawnArea.x, drawnArea.y, drawnArea.width, drawnArea.height), move, c); 
        g.drawImage(img, move, c);
    }
    
    public void giveShading(Color c, float percent){
        percent*=100;
        percent=(int)percent;
        percent=(float)(percent/100f);
        if(this.shading.getColor().getRGB()==c.getRGB() && this.shading.percent==percent){
            return;
        }
        this.shading=new Shading(c,percent);
    }
    
    private static Color tint(Color start, Color target, float percent){
        if(start.getAlpha()==0){
            return start;
        }
        return new Color((int) (start.getRed() + percent * (target.getRed() - start.getRed())),
                (int) (start.getGreen() + percent * (target.getGreen() - start.getGreen())),
                (int) (start.getBlue() + percent * (target.getBlue() - start.getBlue())),
                (int) (start.getAlpha() + percent * (target.getAlpha() - start.getAlpha())));
    }
    
    public static void tintImage(BufferedImage img, Color target, float percent){
        if(percent <=0f){
            return;
        }
        for(int x=0; x<img.getWidth(); x++){
            for(int y=0; y<img.getHeight(); y++){
                img.setRGB(x, y, tint(new Color(img.getRGB(x, y)),target,percent).getRGB());
            }
        }
    }
    
//    /**
//     * Tints the image by 25% to the color passed in
//     * @param target the color the image will be tinted
//     */
//    public void tint(Color target){
//        if(target ==null){
//            tinted=bf;
//            return;
//        }
//        if (tint == null || !(tint.getRGB() == target.getRGB())) {
//            try {
//                tinted=ImageIO.read(new File(s));
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            tint = new Color(target.getRGB());
//            for (int x = 0; x < bf.getWidth(); x++) {
//                for (int y = 0; y < bf.getHeight(); y++) {
//                    tinted.setRGB(x, y, this.tint(new Color(bf.getRGB(x, y)), target, .250).getRGB());
//                }
//            }
//        } else {
//            return;
//        }
//    }
//    /**
//     * Tints the image by the percentage passed in to the color passed in. 1=100%
//     * @param target color to tint to
//     * @param percent percentage of tinting
//     */
//    public void tint(Color target, float percent){
//        if(target ==null){
//            tinted=bf;
//            return;
//        }
//        if (tint == null || !(tint.getRGB() == target.getRGB())) {
//            try {
//                tinted=ImageIO.read(new File(s));
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            tint = new Color(target.getRGB());
//            for (int x = 0; x < bf.getWidth(); x++) {
//                for (int y = 0; y < bf.getHeight(); y++) {
//                    tinted.setRGB(x, y, this.tint(new Color(bf.getRGB(x, y)), target, percent).getRGB());
//                }
//            }
//        } else {
//            return;
//        }
//    }
//    
//    public void tintAnyway(Color target, float percent) {
//        try {
//            tinted = new BufferedImage(bf.getWidth(), bf.getHeight(), bf.getType());
//            Graphics g=tinted.createGraphics();
//            g.drawImage(bf, 0, 0, null);
//            g.dispose();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        tint = new Color(target.getRGB());
//        for (int x = 0; x < bf.getWidth(); x++) {
//            for (int y = 0; y < bf.getHeight(); y++) {
//                tinted.setRGB(x, y, this.tint(new Color(bf.getRGB(x, y)), target, percent).getRGB());
//            }
//        }
//    }
    
    @Override
    public String toString(){
        return ""+s;
    }

    @Override
    public int getWidth(ImageObserver observer) {
        return bf.getWidth(observer);
    }

    @Override
    public int getHeight(ImageObserver observer) {
        return bf.getHeight(observer);
    }

    @Override
    public ImageProducer getSource() {
        return bf.getSource();
    }

    @Override
    public Graphics getGraphics() {
        return this.getGraphics();
    }

    @Override
    public Object getProperty(String name, ImageObserver observer) {
        return this.getProperty(name, observer);
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Image2D){
            return thisID==((Image2D)o).thisID;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.position != null ? this.position.hashCode() : 0);
        hash = 79 * hash + this.depth;
        hash = 79 * hash + Float.floatToIntBits(this.scalex);
        hash = 79 * hash + Float.floatToIntBits(this.scaley);
        hash = 79 * hash + Float.floatToIntBits(this.theta);
        hash = 79 * hash + (this.s != null ? this.s.hashCode() : 0);
        hash = 79 * hash + (this.rotationAnchor != null ? this.rotationAnchor.hashCode() : 0);
        hash = 79 * hash + (this.drawnArea != null ? this.drawnArea.hashCode() : 0);
        hash = 79 * hash + (this.shading != null ? this.shading.hashCode() : 0);
        hash = 79 * hash + this.thisID;
        return hash;
    }

    @Override
    public int getSortNum() {
        return this.depth;
    }


    
    
    private class Shading implements Serializable{
        private Color color;
        private float percent;
        
        public Shading(Color c, float p){
            this.color=c;
            this.percent=p;
        }

        public Color getColor() {
            return color;
        }

        public float getPercent() {
            return percent;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o instanceof Shading) {
                return this.hashCode() == ((Shading) o).hashCode();
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 97 * hash + (this.color != null ? this.color.hashCode() : 0);
            hash = 97 * hash + Float.floatToIntBits(this.percent);
            return hash;
        }
        
    }
}
