/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StressTest;

import Networking.PortIP;
import Networking.UDP.UDPClient;
import Networking.UDP.UDPServer;
import Utilities.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ksweeney12
 */
public class AMainClass {
    public static void main(String[] args){
            //        Vector2 p =new Vector2(1234,222);
            //        Vector2 q=null;
            //        Hashtable i=null;
            //        
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
            //        byte[] input=null;
            //        
            //        
            //        try {
            //            FileOutputStream fos= new FileOutputStream("Output.ser");
            //            ByteArrayOutputStream baos=new ByteArrayOutputStream(10000);
            //            ObjectOutputStream out=new ObjectOutputStream(baos);
            //            out.writeObject(h);
            //            out.close();
            //            
            //            input=baos.toByteArray();
            //            
            //        } catch (Exception ex) {
            //            ex.printStackTrace();
            //        }
            //        
            //        try{
            //            
            //            FileInputStream fis=new FileInputStream("Output.ser");
            //            ByteArrayInputStream bais=new ByteArrayInputStream(input);
            //            ObjectInputStream in=new ObjectInputStream(bais);
            //            i=(Hashtable)in.readObject();
            //            in.close();
            //        }catch(Exception e){
            //            e.printStackTrace();
            //        }
            //        
            //        
            //        System.out.println(i);
                    
        try {
//            InetAddress localHost = InetAddress.getLocalHost();
//            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
//            System.out.println(networkInterface.getInterfaceAddresses().get(0));
            UDPServer server = new UDPServer("192.168.0.23",4447);
            for(int i=0; i<100; i++){
            //UDPClient client=new UDPClient("192.168.0.22",4446);
            
            server.send("Hello World!".getBytes());
            //System.out.println(new String(client.recieve(new byte[20])));
            for(int j=0; j<100000000; j++){}
            }
            server.end();

        } catch (Exception ex) {
            Logger.getLogger(AMainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
