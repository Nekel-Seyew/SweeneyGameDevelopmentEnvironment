/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking.UDP;

/**
 * An event listener which will be notified that something has been recieved and be given the data.
 * @author Kyle Sweeney
 */
public interface UDPServerListener {
    /**
     * The Data you recieve
     * @param data the data you recieve from client or server.
     */
    public void recieveData(byte[] data);
}
