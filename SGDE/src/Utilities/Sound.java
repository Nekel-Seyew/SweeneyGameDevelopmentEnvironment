/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.Serializable;

/**
 *
 * @author RomulusAaron
 */
public class Sound implements Serializable{
    private static int ID=0;
    public String fileName;
    private int id;
    
    public Sound(String fileName){
        this.fileName=fileName;
        id=ID++;
    }
    
    public int getID(){
        return id;
    }
}
