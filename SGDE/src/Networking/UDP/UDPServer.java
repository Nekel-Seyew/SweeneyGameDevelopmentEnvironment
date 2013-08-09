/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking.UDP;

import Networking.PortIP;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A UDP Server.
 * @author Kyle Sweeney
 */
public class UDPServer implements UDPNetworkClass{
    private PortIP portIP;
    private InetAddress group;
    private DatagramSocket socket;
    
    /**
     * Constructs a server based on the ip address and port number of the passed in PortIP
     * @param addressAndPort the ip address and port number
     */
    public UDPServer(PortIP addressAndPort){
        try {
            this.portIP=addressAndPort;
            group=InetAddress.getByName(portIP.ipAddress);
            socket=new DatagramSocket(portIP.port);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Constructs a server based on the ip address and port number.
     * @param ipAddress
     * @param port 
     */
    public UDPServer(String ipAddress, int port){
        try {
            portIP=new PortIP(ipAddress, port);
            group=InetAddress.getByName(portIP.ipAddress);
            socket=new DatagramSocket(portIP.port);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Sends the given data to all client listening to the port.
     * @param data to be sent
     * @return true if no errors occured. False otherwise.
     */
    public boolean send(byte[] data){
        try{
            DatagramPacket packet= new DatagramPacket(data, data.length, group, portIP.port);
            socket.send(packet);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * Recieves data back from a client. Which client is not knowable. 
     * @param data a sample data array which is the same size as the one being recieved.
     * @return the data from a client.
     */
    public byte[] recieve(byte[] data){
        try{
            DatagramPacket packet= new DatagramPacket(data, data.length);
            socket.receive(packet);
            return packet.getData();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * To be called so the port and ip address can be freeded an returned back to the system.
     */
    public void end(){
        this.socket.close();
    }
    
    @Override
    public void finalize() throws Throwable{
        this.socket.close();
        super.finalize();
    }
}