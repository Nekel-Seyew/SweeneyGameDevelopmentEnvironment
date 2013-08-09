/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking.UDP;

import Networking.UDP.UDPNetworkClass;
import Hardware_Accelerated.AGame;

/**
 * A class for running a thread in the background for multiplayer access. Note, Only compatable with Hardware Accelerated games.
 * @author kdsweenx
 */
public class NetworkThread implements Runnable{
    UDPNetworkClass networkSide;
    Thread self;
    AGame game;
    UDPServerListener listener;
    byte[] data;

    private NetworkThread(UDPNetworkClass networkSide, AGame game, byte[] averageDataSize) {
        this.networkSide = networkSide;
        this.game = game;
        self=new Thread(this);
        if(averageDataSize==null){
            data=new byte[1024];
        }
    }
    /**
     * The normal constructor for a NetworkThread.
     * @param networkSide A server or Client this thread will be running.
     * @param game the game this thread will be running in
     * @param averageDataSize an average byte package which will be sent or recieved.
     * @param listener An event listener which will be notified that something has been recieved and be given the data.
     */
    public NetworkThread(UDPNetworkClass networkSide, AGame game, byte[] averageDataSize, UDPServerListener listener){
        this(networkSide, game, averageDataSize);
        this.listener=listener;
    }
   /**
    * Stars the thread.
    */
    public void start(){
        self.start();
    }
    
    @Override
    public void run() {
        while(game.isAlive()){
            if(listener==null){
                //oh, this is awkward....
            }else{
                //that's better
                listener.recieveData(networkSide.recieve(data));
            }
        }
    }
    
    public boolean sendData(byte[] data){
        return networkSide.send(data);
    }
}
