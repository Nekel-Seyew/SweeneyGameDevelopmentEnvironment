/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking;

/**
 * A class which holds the address and port of a server or client.
 * @author Kyle Sweeney
 */
public class PortIP {
    
    /**
     * the IP Address.
     */
    public final String ipAddress;
    /**
     * The Port number.
     */
    public final int port;
    
    public PortIP(String ip, int port){
        this.ipAddress=new String(ip);
        this.port=port;
    }
    
    /**
     * A normal localhost.
     */
    public static PortIP localhost= new PortIP("localhost",5589);
    /**
     * The example Sun Microsystems gave.
     */
    public static PortIP SunExample=new PortIP("230.0.0.1",4446);
}
