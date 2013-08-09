/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking.UDP;

import Networking.PortIP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * A UDP client.
 * @author Kyle Sweeney
 */
public class UDPClient implements UDPNetworkClass{
    private PortIP portIP;
    private InetAddress group;
    private MulticastSocket socket;
    
    /**
     * A constructor which accepts the IP address and ports as a PortIP.
     * @param IpAddressAndPort 
     */
    public UDPClient(PortIP IpAddressAndPort){
        try{
            this.portIP=IpAddressAndPort;
            socket=new MulticastSocket(portIP.port);
            group=InetAddress.getByName(portIP.ipAddress);
            socket.joinGroup(group);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * A contructor which accepts an address and port
     * @param IPAdress
     * @param port 
     */
    public UDPClient(String IPAdress, int port){
        try{
            this.portIP=new PortIP(IPAdress, port);
            socket=new MulticastSocket(portIP.port);
            group=InetAddress.getByName(portIP.ipAddress);
            socket.joinGroup(group);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Recieves data from the server. 
     * @param data A sample, empty byte array which is the same size as the data you will recieve.
     * @return the data you recive.
     */
    public byte[] recieve(byte[] data){
        try{
            DatagramPacket packet=new DatagramPacket(data, data.length);
            socket.receive(packet);
            return packet.getData();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Sends the passed in data
     * @param data the data to send to the server.
     * @return whether or not the data was sent and no errors occured.
     */
    public boolean send(byte[] data){
        try{
            DatagramPacket packet=new DatagramPacket(data, data.length);
            socket.send(packet);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * Ends the client and frees the ipaddress and port back to the system
     */
    public void end(){
        try{
            this.socket.leaveGroup(group);
            this.socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void finalize() throws Throwable{
        this.socket.leaveGroup(group);
        this.socket.close();
        super.finalize();
    }
    
}
