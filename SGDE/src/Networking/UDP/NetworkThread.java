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
