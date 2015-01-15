/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Utilities.Vector2;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author KyleSweeney
 */
public class Vector2Test {
    
    public Vector2Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void nullVector(){
        Vector2 v = new Vector2();
        assertTrue(v.getX() == 0.0);
        assertTrue(v.getY() == 0.0);
    }
    @Test
    public void deltaFunctions(){
        double X = Math.random()*1000;
        double Y = Math.random()*1000;
        double dx = Math.random()*1000;
        double dy = Math.random()*1000;
        
        Vector2 v = new Vector2(X,Y);
        v.dX(dx);
        v.dY(dy);
        
        X += dx;
        Y += dy;
        assertEquals(X,v.getX(),0.000001);
        assertEquals(Y,v.getY(),0.000001);
    }
    @Test
    public void rotate(){
        double X = Math.random()*1000;
        double Y = Math.random()*1000;
        double theta = Math.random()*1000 * (Math.random() > 0.5 ? 1 : -1);
        
        Vector2 v = new Vector2(X,Y);
        v.rotate(theta);
        
        double temp = X;
        X = X*Math.cos(theta) - Y*Math.sin(theta);
        Y = temp*Math.sin(theta) + Y*Math.cos(theta);
        
        assertEquals(X,v.getX(),0.000001);
        assertEquals(Y,v.getY(),0.000001);
    }
    // @Test
    // public void hello() {}
}
