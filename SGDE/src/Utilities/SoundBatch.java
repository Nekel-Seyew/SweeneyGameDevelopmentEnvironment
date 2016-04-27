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
package Utilities;

import java.io.Serializable;

/**
 *
 * @author Ksweeney12
 */
public class SoundBatch implements Serializable{
    private SoundFile[] batch;
    private int currPosition;
    
    public SoundBatch(){
        batch=new SoundFile[100];
    }
    
    public Sound loadSound(String fileName){
        Sound b= new Sound(fileName);
        SoundFile d=new SoundFile(fileName, b.getID());
        batch[currPosition++]=d;
        return b;
    }
    
    public Sound loadSound(Sound s){
        SoundFile d=new SoundFile(s.fileName, s.getID());
        batch[currPosition++]=d;
        return s;
    }
    
    public boolean playSound(Sound s){
        SoundFile st=findSound(s.getID());
        if(st==null){
            return false;
        }
        else if(st.isPaused()){
            st.unPause();
            return true;
        }
        else if(st.isKilled()){
            return false;
        }else{
            st.run();
            return true;
        }
    }
    public boolean playSound(int ID){
       SoundFile st=findSound(ID);
       if(st==null){
            return false;
        }
        else if(st.isPaused()){
            st.unPause();
            return true;
        }
        else if(st.isKilled()){
            return false;
        }else{
            st.run();
            return true;
        }
    }
    
    public boolean pauseSound(Sound s){
        SoundFile st=findSound(s.getID());
        if(st==null){
            return false;
        }
        else if(st.isPaused()){
            return true;
        }
        else if(st.isKilled()){
            return false;
        }else{
            st.pause();
            return true;
        }
    }
    public boolean pauseSound(int ID){
        SoundFile st=findSound(ID);
        if(st==null){
            return false;
        }
        else if(st.isPaused()){
            return true;
        }
        else if(st.isKilled()){
            return false;
        }else{
            st.pause();
            return true;
        }
    }
    
    public boolean killSound(Sound s){
        SoundFile st=findSound(s.getID());
        if(st==null){
            return false;
        }
        else if(st.isPaused()){
            st.kill();
            return true;
        }
        else if(st.isKilled()){
            return true;
        }else{
            st.kill();
            return true;
        }
    }
    public boolean killSound(int ID){
        SoundFile st=findSound(ID);
        if(st==null){
            return false;
        }
        else if(st.isPaused()){
            st.kill();
            return true;
        }
        else if(st.isKilled()){
            return true;
        }else{
            st.kill();
            return true;
        }
    }
    
    public boolean isAlive(int ID){
        SoundFile st=findSound(ID);
        if(st==null){
            return false;
        }else if(!st.isKilled()){
            return true;
        }else{
            return false;
        }
    }
    public boolean isAlive(Sound s){
        SoundFile st=findSound(s.getID());
        if(st==null){
            return false;
        }else if(!st.isKilled()){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isPaused(int ID){
        SoundFile st=findSound(ID);
        if(st==null){
            return false;
        }else if(st.isPaused()){
            return true;
        }else{
            return false;
        }
    }
    public boolean isPaused(Sound s){
        SoundFile st=findSound(s.getID());
        if(st==null){
            return false;
        }else if(st.isPaused()){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isDead(Sound s){
        SoundFile st=findSound(s.getID());
        if(st==null){
            return false;
        }else if(st.isKilled()){
            return true;
        }else{
            return false;
        }
    }
    public boolean isDead(int ID){
        SoundFile st=findSound(ID);
        if(st==null){
            return false;
        }else if(st.isKilled()){
            return true;
        }else{
            return false;
        }
    }
    
    private SoundFile findSound(int id){
        SoundFile d=null;
        int left=0, right=batch.length, middle=0;
        while(left<=right){
            middle=(left+right)/2;
            int check=batch[middle].ID;
            if(check==id){
                d=batch[middle];
                break;
            }else if(check>id){
                left=middle+1;
            }else if(check<id){
                right=middle-1;
            }
        }
        return d;
    }
    
    public void Update(){
        for(int i=0; i<batch.length; i++){
            if(batch[i]==null){
                return;
            }
            if(batch[i].isKilled()){
                for(int j=i;j<batch.length; j++ ){
                    if(batch[i]==null)
                        return;
                    batch[i]=batch[i+1];
                }
            }
        }
    }
}
