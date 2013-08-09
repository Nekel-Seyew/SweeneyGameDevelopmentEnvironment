/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;

/**
 *
 * @author RomulusAaron
 */
public class GameState implements Serializable{
    private Hashtable state;
    
    public GameState(String gameName){
        state=new Hashtable();
        state.put("Game Name", gameName);
    }
    
    public String getname(){
        return (String)state.get("Game Name");
    }
    
    public void put(String objectName, Object o){
        state.put(objectName, o);
    }
    
    public Object get(String objectName){
        return state.get(objectName);
    }
    
    public byte[] getSerializedArray(int sizeOfArray) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(sizeOfArray);
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(state);
        out.close();
        return baos.toByteArray();
    }
}
