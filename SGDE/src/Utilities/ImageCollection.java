/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Advance.AMath;
import Advance.RadixSortable;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.List;

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
 * Holds images for a game. It will keep them orderly, if enabled. Also contains methods for GUI drawing.
 * @author Kyle Maximilian Dieter Sweeney
 */
public class ImageCollection implements Serializable{

    private ArrayList<Image2D> Batch;
    //private Image2D[] buffer;
    private ViewScreen v;
    private boolean depthSorting; //implemented because Dolan Murvihill thought that sorting was not necessary
    //on server side, and that client code should stop being given the opportunity to be lazy and do work for themselves.
    //He is wrong, of course.
    private int largestSize=150;
    private Node bufferStart;
    ArrayList<Node> nodes;

    /**
     * The constructor for the ImageCollection class. It needs a ViewScreen to shift everything over.
     * It also sets the depthSorting to true.
     * @param v
     */
    public ImageCollection(ViewScreen v, JPanel c) {
        Batch = new ArrayList<Image2D>();
        Batch.ensureCapacity(150);
        depthSorting = true;
        this.v = v;
        bufferStart=new Node(null, 0, null);
        nodes= new ArrayList<Node>();
        //buffer= new Image2D[1000];
    }
    
    public ImageCollection(ViewScreen v){
        Batch = new ArrayList<Image2D>();
        Batch.ensureCapacity(150);
        depthSorting = true;
        this.v = v;
        bufferStart=new Node(null, 0, null);
        nodes= new ArrayList<Node>();
        //buffer= new Image2D[1000];
    }
    
    private void addOne(Image2D hold, int depth, Vector2 position){
        Vector2 pos = position.clone();
        pos.add(v.loc());
        hold.setDepth(depth);
        hold.setVector2(pos);
        if(depth>largestSize){
            largestSize=depth;
        }
    }
    private void addTwo(Image2D hold, int depth, Vector2 position){
        if(bufferStart.getNext()==null){
            Node newNode=new Node(hold, depth, bufferStart);
            bufferStart.setNext(newNode);
        }
        Node search = bufferStart.getNext();
        while (search != null) {
            if (search.depth > depth) {
                Node newNode = new Node(hold, depth, search.getPrevious());
                break;
            } else if (search.getNext() == null) {
                Node newNode = new Node(hold, depth, search);
                break;
            } else {
                search = search.getNext();
            }
        }
    }
    
    private void addThree(Image2D hold, int depth){
        nodes.add(new Node(hold, depth, null));
    }
    
    private void addToColl(Image2D hold, int depth, Vector2 position){
        addOne(hold, depth, position);
        addThree(hold, depth);
        
//        addTwo(hold, depth, position);

//        boolean searching = true;
//        int place = 0;
//        while (searching) {
//            if (Batch.isEmpty()) {
//                searching = false;
//                Batch.add(hold);
//            } else if (Batch.size() <= place) {
//                searching = false;
//                Batch.add(hold);
//            } else if (Batch.get(place).getDepth() > depth) {
//                Batch.add(place, hold);
//                searching = false;
//            } else {
//                place++;
//            }
//        }
        
        
    }
    
    private void add(int place, Image2D i){
        
    }
    /**
     * Draws an Image passed into it. Sets depth to 0. Useful if the user does not wish to use depth-sorting. The position passed in will be the center of the image.
     * @param i the Image2D to be drawn
     * @param pos the Position at which the Image2D will be drawn
     */
    public void Draw(Image2D i, Vector2 pos){
        addToColl(i,0,pos);
    }
    /**
     * Draws an Image passed into it at the depth provided. The Higher depth means it will be drawn later, or on top of others. The position passed in will be the center of the image.
     * @param i the Image2D to be drawn
     * @param pos the Position at which the Image2D will be drawn
     * @param depth the depth at which the image will be drawn
     */
    public void Draw(Image2D i, Vector2 pos, int depth){
        addToColl(i, depth, pos);
    }
    public void Draw(Image2D i, Vector2 pos,float angle, int depth){
        i.setRotation(angle);
        addToColl(i, depth, pos);
    }
    public void Draw(Image2D i, Vector2 pos, float angle, Color tint, int depth){
        i.giveShading(tint, 0.25f);
        addToColl(i,depth, pos);
    }
    public void Draw(Image2D i, Vector2 pos, float angle, Color tint, float scaleX,float scaleY, int depth){
        i.setRotation(angle);
        i.setScaleX(scaleX);
        i.setScaleY(scaleY);
        i.giveShading(tint,0.25f);
        addToColl(i, depth, pos);
    }
    public void Draw(Image2D i, Vector2 pos, float angle,float scale, int depth){
        i.setRotation(angle);
        i.setScaleX(scale);
        i.setScaleY(scale);
        addToColl(i, depth, pos);
    }
    public void Draw(Image2D i, Vector2 pos, float angle, float scaleX, float scaleY, int depth){
        i.setRotation(angle);
        i.setScaleX(scaleX);
        i.setScaleY(scaleY);
        addToColl(i, depth, pos);
    }
    public void Draw(Image2D i, Vector2 pos, Rectangle sourceDrawnArea, int depth){
        if(sourceDrawnArea!=null){
            i.setDrawnArea(sourceDrawnArea);
        };
        addToColl(i,depth, pos);
    }
    public void Draw(Image2D i, Vector2 pos, float angle, Rectangle sourceDrawnArea, int depth){
        i.setRotation(angle);
        if(sourceDrawnArea!=null){
            i.setDrawnArea(sourceDrawnArea);
        }
        addToColl(i,depth,pos);
    }
    public void Draw(Image2D i, Vector2 pos, float angle, float scaleX,float scaleY, Rectangle sourceDrawnArea, int depth){
        i.setRotation(angle);
        i.setScaleX(scaleX);
        i.setScaleY(scaleY);
        if(sourceDrawnArea!=null){
            i.setDrawnArea(sourceDrawnArea);
        }
        addToColl(i, depth, pos);
    }
    public void Draw(Image2D i, Vector2 pos, float angle, Color tint, float scaleX, float scaleY, Rectangle sourceDrawnArea, int depth){
        i.setRotation(angle);
        i.setScaleX(scaleX);
        i.setScaleY(scaleY);
        if(sourceDrawnArea!=null){
            i.setDrawnArea(sourceDrawnArea);
        }
        i.giveShading(tint, 0.25f);
        addToColl(i, depth, pos);
    }
    public void Draw(Image2D i, Vector2 pos, float angle, Color tint,float percentTint, float scaleX, float scaleY, Rectangle sourceDrawnArea, int depth){
        i.setRotation(angle);
        i.setScaleX(scaleX);
        i.setScaleY(scaleY);
        if(sourceDrawnArea!=null){
            i.setDrawnArea(sourceDrawnArea);
        }
        i.giveShading(tint, percentTint);
        addToColl(i, depth, pos);
    }

    /**
     * This disables Depth sorting.
     */
    public void DisableDepthSorting() {
        depthSorting = false;
    }

    /**
     * This Enables Depth Sorting
     */
    public void EnableDepthSorting() {
        depthSorting = true;
    }
    
    private void sort(){
        Collections.sort(nodes);
//        ImageCollection.RadixSort(nodes, largestSize);
    }
    

    /**
     * This method draws all of the Images from the collection onto the JPanel, using the Graphics.
     *
     * If depth sorting is enabled, then everything will be drawn in the order of least to greatest, in terms of depth. For each item with the same depth, they are drawn on a first in, last out basis. Otherwise, everything is drawn on a first in, first out basis.
     * @param g -graphics to be used
     * @param c -JPanel or Game the Items will be drawn in. NOTE: the JPanel can be a child class of the Game Class.
     */
    public void Render(Graphics g, ImageObserver c) {
        int initSize = Batch.size();
        Rect r= new Rect(-50,-50, (int)v.getWidth()+50, (int)v.getHeight()+50,0f);
        try {
//            Node draw=bufferStart.getNext();
//            while(draw!=null){
//                draw.draw((Graphics2D)g, c, r);
//                draw=draw.getNext();
//            }
//            bufferStart.setNext(null);
//            System.out.println("LargestSize: "+this.largestSize);
            this.sort();
            for(Node n: nodes){
                n.draw((Graphics2D)g, c, r);
            }
            int size=nodes.size();
            nodes= new ArrayList<Node>(size);
        } catch (Exception e) {
//            if (Batch.isEmpty()) {
//                return;
//            }
            e.printStackTrace();
        }
    }

    /**
     * This will draw a string onto the screen
     * @param pos -position for the string to start being drawn
     * @param string -the string to be drawn
     * @param c -the color the string will be drawn in
     * @param depth -the depth at which the string will be drawn
     */
    public void DrawString(Vector2 pos, String string, Color c, int depth) {
        Command p= new Command();
        p.c=c;
        p.whatToSay=new String(string);
        p.command=commands.DrawString;
        
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
        
    }
    public void DrawString(Vector2 pos, String string, Color c, int depth, int fontType, int fontStyle, int size){
        Command p= new Command();
        p.c=c;
        p.whatToSay=new String(string);
        p.command=commands.drawStringFont;
        p.fontsize=size;
        p.Fontstyle=fontStyle;
        p.fonttype=fontType;
        
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
    }
    /**
     * Draws an Arc inside of the rectangle, given by the upper left hand origin, width, and height.
     * The width and height are the major and minor axis, depending on the size. 
     * Given these will determine whether or not the arc will be circular or ellipsoidal.
     * 
     * The measurement of angle is at 90 degrees (pi/2 radians) going clockwise.
     * @param pos upper left hand corner of the rectangle surrounding the arc
     * @param width width of the rectangle surrounding the arc
     * @param height height of the rectangle surrounding the arc
     * @param startAngle starting angle of the arc
     * @param arcAngle how far the arc will go (final angle is this angle plus start angle
     * @param c Color of the arc
     * @param depth Image depth the arc will be drawn at
     */
    public void drawArc(Vector2 pos, int width, int height, int startAngle, int arcAngle, Color c, int depth){
        Command p = new Command();
        p.width = width;
        p.height = height;
        p.startAngle = startAngle;
        p.arcAngle = arcAngle;
        p.c = c;
        p.command = commands.drawArc;
        
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
        
    } 
    /**
     * Draws a line which will be drawn between those two points
     * @param pos1 one of the positions
     * @param pos2 the other position
     * @param c the color of the line
     * @param depth the depth at which the line will be drawn
     */
    public void drawLine(Vector2 pos1, Vector2 pos2, Color c, int depth){//when implementing, the x and y inside pos2 will be placed into width and height
        Command p = new Command();
        p.c=c;
        p.width=(int)pos2.getX();
        p.height=(int)pos2.getY();
        p.command=commands.drawLine;
        
        if(depthSorting){
            addToColl(p, depth, pos1);
        }else{
            pos1.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos1;
            p.depth=depth;
            Batch.add(p);
        }
    } 
    /**
     * Draws an Oval inside of the rectangle made from the position, width and height
     * @param pos upper left hand corner of the rectangle
     * @param width width of the rectangle for the oval
     * @param height height of the rectangle for the oval
     * @param c color of the oval
     * @param depth image depth the oval will be drawn at
     */
    public void drawOval(Vector2 pos, int width, int height, Color c, int depth){//upper left, x and y
        Command p= new Command();
        p.c=c;
        p.width=width;
        p.height=height;
        p.command=commands.drawOval;
        
         if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
    }
    /**
     * Similar to a line, but has many points together, instead of two.
     * @param xPoints array of the x-coordinates of the points
     * @param yPoints array of the y-coordinates of the points
     * @param nPoints number of points to be made
     * @param c color of the line
     * @param depth image depth of the polyline
     */
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints, Color c, int depth) {
        Command p= new Command();
        p.c=c;
        p.xPoints=xPoints;
        p.yPoints=yPoints;
        p.nPoints=nPoints;
        p.command=commands.drawPolyline;
        
        Vector2 pos= new Vector2();
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
        
    }
    /**
     * Similar to a line, but has many points together, instead of two.
     * @param points Vector2 array of all of the points
     * @param nPoints the number of points to be made
     * @param c color of the line
     * @param depth image depth of the polyline
     */
    public void drawPolyline(Vector2[] points, int nPoints, Color c, int depth){
        Command p= new Command();
        p.c=c;
        p.nPoints=nPoints;
        
        int[] xPoints= new int[points.length];
        int[] yPoints= new int[points.length];
        for(int i=0; i<points.length; i++){
            xPoints[i]=(int)points[i].getX();
            yPoints[i]=(int)points[i].getY();
        }
        p.xPoints=xPoints;
        p.yPoints=yPoints;
        p.command=commands.drawPolyline;
        Vector2 pos= new Vector2();
        
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
    }
    /**
     * Draws the outline of a rectangle with the given Vector2 in the upper left hand corner
     * @param pos upper left hand corner of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param c color of the rectangle
     * @param depth image depth of the rectangle
     */
    public void drawRect(Vector2 pos, int width, int height, Color c, int depth) {
        Command p= new Command();
        p.c=c;
        p.width=width;
        p.height=height;
        p.command=commands.drawRect;
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
        
    }
    /**
     * Creates a rectangle based off of the given rectangle
     * @param r rectangle to be drawn
     * @param c color of the rectangle
     * @param depth image depth of the rectangle
     */
    public void drawRect(Rect r, Color c, int depth){
        Command p= new Command();
        p.c=c;
        p.width=r.width;
        p.height=r.height;
        p.command=commands.drawRect;
        
        Vector2 pos= new Vector2(r.x, r.y);
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
    }
    /**
     * Draws a rectangle with round edges. Note, non-angular
     * @param pos upper left hand corner of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param arcWidth width of the arcs in the corners
     * @param arcHeight height of the arcs in the corner
     * @param c color of the rectangle
     * @param depth image depth of the rounded rectangle
     */
    public void drawRoundRect(Vector2 pos, int width, int height, int arcWidth, int arcHeight, Color c, int depth) {
        Command p= new Command();
        p.width=width;
        p.height=height;
        p.arcWidth=arcWidth;
        p.arcHeight=arcHeight;
        p.c=c;
        p.command=commands.drawRoundRect;
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
        
    }
    /**
     * Draws a rectangle with round edges.
     * @param r the rectangle which will become round with edges. Note, non-angular.
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param arcWidth width of the arcs in the corners
     * @param arcHeight height of the arcs in the corner
     * @param c color of the rectangle
     * @param depth image depth of the rounded rectangle
     */
    public void drawRoundRect(Rect r, int arcWidth, int arcHeight, Color c, int depth) {
        Command p= new Command();
        p.width=r.width;
        p.height=r.height;
        p.arcWidth=arcWidth;
        p.arcHeight=arcHeight;
        p.c=c;
        p.command=commands.drawRoundRect;
        Vector2 pos = new Vector2(r.x, r.y);
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
        
    }
    /**
     * Draws a filled in arc. Equivalent to getting a section of the disk inscribed inside of the given rectangle.
     * The start angle with be the angle the arc is drawn at, measured clockwise from 90 Degrees (pi/2 radians).
     * The arc angle with be the measure of the angle, going from the start angle, and ending at the startAngle plus arcAngle, going 
     * clockwise.
     * @param pos upper left hand corner of the rectangle arc is inscribed in.
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param startAngle starting angle 
     * @param arcAngle extent of the arc
     * @param c color the filled in arc will be
     * @param depth image depth of the filled in arc
     */
    public void fillArc(Vector2 pos, int width, int height, int startAngle, int arcAngle, Color c, int depth) {
        Command p= new Command();
        p.width=width;
        p.height=height;
        p.startAngle=startAngle;
        p.arcAngle=arcAngle;
        p.c=c;
        p.command=commands.fillArc;
        
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
    }
    /**
     * Draws a filled in oval, also known as a disk. The given position is the upper left hand corner of the rectangle 
     * the oval is inscribed in.
     * @param pos upper left hand corner of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param c color of the filled in oval
     * @param depth image depth of the oval
     */
    public void fillOval(Vector2 pos, int width, int height, Color c, int depth){
        Command p= new Command();
        p.width=width;
        p.height=height;
        p.c=c;
        p.command=commands.fillOval;
        
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
    }
    /**
     * Draws a filled in rectangle based upon the passed in arguments
     * @param pos upper left hand corner of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param c color of the rectangle
     * @param depth image depth of the rectangle
     */
    public void fillRect(Vector2 pos, int width, int height, Color c, int depth) {
        Command p= new Command();
        p.width=width;
        p.height=height;
        p.c=c;
        p.command=commands.fillRect;
        
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
        
    }
    /**
     * Draws a filled in rectangle based upon the passed in arguments
     * @param r the rectangle to be filled and drawn.
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param c color of the rectangle
     * @param depth image depth of the rectangle
     */
    public void fillRect(Rect r, Color c, int depth) {
        Command p= new Command();
        p.width=r.width;
        p.height=r.height;
        p.c=c;
        p.command=commands.fillRect;
        Vector2 pos=new Vector2(r.x,r.y);
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
        
    }
    /**
     * Draws a filled in rectangle with rounded corners
     * @param pos upper left hand corner of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param arcWidth width of the arcs in the corners
     * @param arcHeight height of the arcs in the corners
     * @param c color of the filled in rectangle
     * @param depth image depth of the filled in rectangle
     */
    public void fillRoundRect(Vector2 pos, int width, int height, int arcWidth, int arcHeight, Color c, int depth) {
        Command p= new Command();
        p.width=width;
        p.height=height;
        p.arcWidth=arcWidth;
        p.arcHeight=arcHeight;
        p.c=c;
        p.command=commands.fillRoundRect;
        
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
    }/**
     * Draws a filled in rectangle with rounded corners
     * @param r the rectangle to be drawn and filled.
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param arcWidth width of the arcs in the corners
     * @param arcHeight height of the arcs in the corners
     * @param c color of the filled in rectangle
     * @param depth image depth of the filled in rectangle
     */
    public void fillRoundRect(Rect r, int arcWidth, int arcHeight, Color c, int depth) {
        Command p= new Command();
        p.width=r.width;
        p.height=r.height;
        p.arcWidth=arcWidth;
        p.arcHeight=arcHeight;
        p.c=c;
        p.command=commands.fillRoundRect;
        Vector2 pos=new Vector2(r.x,r.y);
        if(depthSorting){
            addToColl(p, depth, pos);
        }else{
            pos.add(new Vector2(v.GetX(), v.GetY()));
            p.position=pos;
            p.depth=depth;
            Batch.add(p);
        }
    }
    
    private enum commands{
        DrawString,
        drawStringFont,
        drawArc,
        drawLine,
        drawOval,
        drawPolyline,
        drawRect,
        drawRoundRect,
        fillArc,
        fillOval,
        fillRect,
        fillRoundRect;
    }
    
    private class Command extends Image2D{
        
        public commands command;
        public Color c;
        private Color cf;
        
        public String whatToSay;
        
        public int width, height,
        
        startAngle,arcAngle,
        
        arcWidth, arcHeight,
                
        Fontstyle, fonttype, fontsize;
        
        public int[] xPoints, yPoints;
        public int nPoints;
        Font oldFont;
        
        private void setColor(Graphics g, Color c){
            cf=g.getColor();
            g.setColor(c);
        }
        private void resetColor(Graphics g){
            g.setColor(cf);
        }
        private void changeFonts(Graphics g){
            oldFont=g.getFont();
            Font f;
            if(fonttype==FontType.DIALOG){
                f=new Font(Font.DIALOG,Fontstyle,fontsize);
            }else if(fonttype==FontType.DIALOG_INPUT){
                f=new Font(Font.DIALOG_INPUT,Fontstyle,fontsize);
            }else if(fonttype==FontType.MONOSPACED){
                f=new Font(Font.MONOSPACED,Fontstyle,fontsize);
            }else if(fonttype==FontType.SANS_SERIF){
                f=new Font(Font.SANS_SERIF,Fontstyle,fontsize);
            }else if(fonttype==FontType.SERIF){
                f=new Font(Font.SERIF,Fontstyle,fontsize);
            }else{
                f=oldFont;
            }
            g.setFont(f);
        }
        private void resetFonts(Graphics g){
            g.setFont(oldFont);
        }
        
        @Override
        public void Draw(Graphics2D g, ImageObserver l){
            if(command==commands.DrawString){
                setColor(g, c);
                g.drawString(whatToSay, (int)this.position.getX(), (int)this.position.getY());
                resetColor(g);
            }
            else if(command==commands.drawArc){
                setColor(g,c);
                g.drawArc((int)this.position.getX(), (int)this.position.getY(), width, height, startAngle, arcAngle);
                resetColor(g);
            }
            else if(command==commands.drawLine){
                setColor(g, c);
                g.drawLine((int)this.position.getX(), (int)this.position.getY(), width, height);
                resetColor(g);
                
            }
            else if(command==commands.drawOval){
                setColor(g, c);
                g.drawOval((int)this.position.getX(), (int)this.position.getY(), width, height);
                resetColor(g);
            }
            else if(command==commands.drawPolyline){
                setColor(g, c);
                g.drawPolyline(xPoints, yPoints, nPoints);
                resetColor(g);
            }
            else if(command==commands.drawRect){
                setColor(g, c);
                g.drawRect((int)this.position.getX(), (int)this.position.getY(), width, height);
                resetColor(g);
            }
            else if(command==commands.drawRoundRect){
                setColor(g, c);
                g.drawRoundRect((int)this.position.getX(), (int)this.position.getY(), width, height, arcWidth, arcHeight);
                resetColor(g);
            }
            else if(command==commands.fillArc){
                setColor(g, c);
                g.fillArc((int)this.position.getX(), (int)this.position.getY(), width, height, startAngle, arcAngle);
                resetColor(g);
            }
            else if(command==commands.fillOval){
                setColor(g, c);
                g.fillOval((int)this.position.getX(), (int)this.position.getY(), width, height);
                resetColor(g);
            }
            else if(command==commands.fillRect){
                setColor(g, c);
                g.fillRect((int)this.position.getX(), (int)this.position.getY(), width, height);
                resetColor(g);
            }
            else if(command==commands.fillRoundRect){
                setColor(g, c);
                g.fillRoundRect((int)this.position.getX(), (int)this.position.getY(), width, height, arcWidth, arcHeight);
                resetColor(g);
            }else if(command==commands.drawStringFont){
                changeFonts(g);
                setColor(g, c);
                g.drawString(whatToSay, (int)this.position.getX(), (int)this.position.getY());
                resetFonts(g);
                resetColor(g);
            }
        }
    }
    
    public class Node implements Serializable, Comparable, RadixSortable{
        private Node previous;
        private Node next;
        public int depth;
        private Image2D image;
        
        public Node(Image2D i, int depth, Node previous){
            this.previous=previous;
            this.depth=depth;
            this.image=i;
            if(previous!=null){
                next=previous.next;
                previous.setNext(this);
            }
            if(next!=null){
                next.setPrevious(this);
            }
        }
        
        public void setPrevious(Node p){
            previous=p;
        }
        public void setNext(Node n){
            next=n;
        }
        
        public void draw(Graphics2D g, ImageObserver context, Rect r){
            if(image instanceof Command){
                image.Draw(g, context);
            }else if(r.intersects(image.getRectangle())){
                image.Draw(g, context);
            }
        }
        
        public Node getPrevious(){
            return previous;
        }
        public Node getNext(){
            return next;
        }

        @Override
        public int compareTo(Object o) {
            Node n=(Node)o;
            if(n.depth<this.depth){
                return 1;
            }else if(n.depth == this.depth){
                return 0;
            }else{
                return -1;
            }
        }

        @Override
        public int getSortNum() {
            return this.depth;
        }
        
    }
    
    public static void RadixSort(ArrayList<Node> a, int maxNum){
        int exp=1;
        BucketMaster bucket= new BucketMaster();
        while(maxNum/exp > 0){
            for(int i=0; i<a.size(); ++i){
                Node b=a.get(i);
                bucket.put((b.getSortNum()/exp)%10, b);
            }
            bucket.replace(a);
            bucket.make();
            exp*=10;
        }
    }
    
    private static class BucketMaster{
        ArrayList<ArrayList<Node>> buckets;
        
        public BucketMaster(){
            make();
        }
        
        public void make(){
            buckets= new ArrayList<ArrayList<Node>>();
            for(int i=0; i<10; i++){
                buckets.add(i, new ArrayList<Node>());
            }
        }
        
        public void replace(List<Node> a){
            a.clear();
            int i=0;
            for(ArrayList<Node> arr : buckets){
                for(Node r: arr){
                    a.add(i, r);
                    i+=1;
                }
            }
        }
        
        public void replace(RadixSortable[] a){
            int i=0;
            for(ArrayList<Node> arr : buckets){
                for(RadixSortable r: arr){
                    a[i]=r;
                    i+=1;
                }
            }
        }
        
        public void put(int i, Node a){
            buckets.get(i).add(a);
        }
    }
}
