/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game3D;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Nekel
 */
public class SceneBatch {
    ArrayList<Node> batch;
    Camera c;
    
    public SceneBatch(Camera c){
        batch = new ArrayList<Node>(10000);
        this.c = c;
    }
    
    public void render(Graphics2D g, Camera c){
        for(Node n : batch){
            n.prerender(c);
        }
        Collections.sort(batch);
        for(Node n : batch){
            n.render(g);
        }
        batch.clear();
    }
    
    public void renderPolygon(PolygonTexture pt){
        batch.add(new Node(pt,this.c.getPos()));
    }
    
    private class Node implements Comparable{
        Renderable r;
        Vector3 v;
        public Node(Renderable r, Vector3 v){
            this.r = r;
            this.v = v;
        }
        public void prerender(Camera c){
            this.r.prerender(c);
        }
        public void render(Graphics2D g){
            this.r.render(g);
        }

        @Override
        public int compareTo(Object o) {
            Node other = (Node)o;
            double thisd = this.r.zdepth();
            double otherd = other.r.zdepth();
            if(thisd < otherd){
                return -1;
            }else if(thisd == otherd){
                return 0;
            }else{
                return 1;
            }
        }
    }
}
