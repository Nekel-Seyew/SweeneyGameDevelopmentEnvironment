/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
