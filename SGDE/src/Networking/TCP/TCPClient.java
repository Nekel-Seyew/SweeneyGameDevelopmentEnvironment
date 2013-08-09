/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking.TCP;

import Networking.PortIP;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author kdsweenx
 */
public class TCPClient {

    private PortIP portIP;
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    
    
    
    public boolean send(byte[] data) {
        try{
            socket.getOutputStream().write(data);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    
    public byte[] recieve(byte[] data) {
        try{
            socket.getInputStream().read(data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
}
