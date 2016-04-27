/*
Copyright (c) 2011, Kyle D. Sweeney
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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