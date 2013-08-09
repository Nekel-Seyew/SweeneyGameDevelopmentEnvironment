/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author RomulusAaron
 */
public class SoundFile extends Thread implements Serializable{
    
  
    public String filename;
    private Position curPosition;
    //524288; // 128Kb
    private final int EXTERNAL_BUFFER_SIZE = 32;
    private boolean paused;
    private boolean kill;
    public float volume = 1f;
    public int ID;

    enum Position {

        LEFT, RIGHT, NORMAL
    };

    public SoundFile(String wavfile, int ID) {
        filename = wavfile;
        curPosition = Position.NORMAL;
        this.ID=ID;
    }

    public SoundFile(String wavfile, Position p, int ID) {
        filename = wavfile;
        curPosition = p;
        this.ID=ID;
    }

    @Override
    public void run() {

        File soundFile = new File(filename);
        if (!soundFile.exists()) {
            System.err.println("Wave file not found: " + filename);
            return;
        }

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
            return;
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (auline.isControlSupported(FloatControl.Type.PAN)) {
            FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
            if (curPosition == Position.RIGHT) {
                pan.setValue(1.0f);
            } else if (curPosition == Position.LEFT) {
                pan.setValue(-1.0f);
            }
        }
        if (auline.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            System.out.println("Master Gain control is supported on sound file: " + filename);
            FloatControl vol = (FloatControl) auline.getControl(FloatControl.Type.MASTER_GAIN);
            vol.setValue(this.volume);
        } else {
            System.out.println("Master Gain control is not supported on sound file: " + filename);
        }

        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
        try {
            synchronized (this) {
                while (nBytesRead != -1 && !kill) {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0) {
                        auline.write(abData, 0, nBytesRead);
                    }
                    while(paused){
                        try{
                            this.wait();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
            kill=true;
        }

    }

    public void pause() {
        this.paused=true;
    }

    public void unPause() {
        synchronized (this) {
            this.paused = false;
            this.notify();
        }
    }
    
    public boolean isPaused(){
        return this.paused;
    }
    
    public boolean isKilled(){
        return this.kill;
    }

    public void kill() {
        this.kill = true;
    }
}
