/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StressTest;

import Networking.UDP.UDPClient;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RomulusAaron
 */
public class HashtableTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Hashtable h= new Hashtable();
//        ArrayList<MyObject> a= new ArrayList<MyObject>();
//        a.add(new MyObject());
//        a.add(new MyObject());
//        a.add(new MyObject());
//        
//        h.put("First object", new Integer(22));
//        h.put("Sectond object", new Double(2222222.3));
//        h.put("Third object", "piee");
//        h.put("First myObject", new MyObject());
//        h.put("ArrayList of myObject", a);
//        
//        
//        System.out.println(h);
//        try {
//            System.out.println(InetAddress.getLocalHost().toString());
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(HashtableTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        try{
            UDPClient client=new UDPClient("192.168.0.23",4447);
            System.out.println(new String(client.recieve(new byte[20])));
            client.end();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
