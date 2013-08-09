/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking.UDP;

/**
 * An interface which abstracts the basic functions of both the server and client.
 * @author Kyle Sweeney
 */
public interface UDPNetworkClass {
    public boolean send(byte[] data);
    public byte[] recieve(byte[] data);
    public void end();
}
