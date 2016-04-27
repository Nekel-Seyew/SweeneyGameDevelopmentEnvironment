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

import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * A Class which can hold the state of the Keyboard at the current instant.
 * @author Kyle Maximilian Dieter Sweeney
 */
public class KeyBoard implements Cloneable, Serializable{

    private boolean[] alphabetKeys;
    private boolean[] numberKeys;
    private boolean[] actionKeys;
    private boolean[] numpadKeys;

    /**
     *Constructs a new KeyBoard.
     *
     * Currently only supports A-Z and 9-0 and Up, Down, Left, Right, Down, Shift, Alt, Ctrl, Backspace, Space and Enter. 
     * Also the Numpad;
     */
    public KeyBoard(){
        alphabetKeys=new boolean[26];
        numberKeys=new boolean[10];
        actionKeys=new boolean[12];
        numpadKeys=new boolean[10];
    }

    /**
     * This takes in a KeyEvent from the KeyPressed method in a KeyListener class.
     * It will then determine what key was pressed,and set that key to true in the class.
     *
     * Currently only supports A-Z and 0-9
     * @param e -the KeyEvent from the method
     */
    public void setPressed(KeyEvent e){
        //Alphabet Keys
        if(e.getKeyCode()==KeyEvent.VK_A){
            alphabetKeys[0]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_B){
            alphabetKeys[1]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_C){
            alphabetKeys[2]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            alphabetKeys[3]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_E){
            alphabetKeys[4]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_F){
            alphabetKeys[5]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_G){
            alphabetKeys[6]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_H){
            alphabetKeys[7]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_I){
            alphabetKeys[8]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_J){
            alphabetKeys[9]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_K){
            alphabetKeys[10]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_L){
            alphabetKeys[11]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_M){
            alphabetKeys[12]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_N){
            alphabetKeys[13]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_O){
            alphabetKeys[14]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_P){
            alphabetKeys[15]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_Q){
            alphabetKeys[16]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_R){
            alphabetKeys[17]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            alphabetKeys[18]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_T){
            alphabetKeys[19]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_U){
            alphabetKeys[20]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_V){
            alphabetKeys[21]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_W){
            alphabetKeys[22]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_X){
            alphabetKeys[23]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_Y){
            alphabetKeys[24]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_Z){
            alphabetKeys[25]=true;
        }
        //Number Keys
        if(e.getKeyCode()==KeyEvent.VK_0){
            numberKeys[0]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_1){
            numberKeys[1]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_2){
            numberKeys[2]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_3){
            numberKeys[3]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_4){
            numberKeys[4]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_5){
            numberKeys[5]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_6){
            numberKeys[6]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_7){
            numberKeys[7]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_8){
            numberKeys[8]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_9){
            numberKeys[9]=true;
        }
        //Action Keys
        if(e.getKeyCode()==KeyEvent.VK_UP){
            actionKeys[0]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            actionKeys[1]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            actionKeys[3]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            actionKeys[4]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            actionKeys[5]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_CONTROL){
            actionKeys[6]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_ALT){
            actionKeys[7]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
            actionKeys[8]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_SHIFT){
            actionKeys[9]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            actionKeys[10]=true;
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            actionKeys[11]=true;
        }
        //NumPad
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD0){
            numpadKeys[0]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD1){
            numpadKeys[1]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD2){
            numpadKeys[2]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD3){
            numpadKeys[3]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD4){
            numpadKeys[4]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD5){
            numpadKeys[5]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD6){
            numpadKeys[6]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD7){
            numpadKeys[7]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD8){
            numpadKeys[8]=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD9){
            numpadKeys[9]=true;
        }

    }

    /**
     * This takes in a KeyEvent from the KeyReleased method in a KeyListener class.
     * It will then determine what key was released,and set that key to false in the class.
     * @param e -the KeyEvent which occurred.
     */
    public void setRelease(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_A){
            alphabetKeys[0]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_B){
            alphabetKeys[1]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_C){
            alphabetKeys[2]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            alphabetKeys[3]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_E){
            alphabetKeys[4]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_F){
            alphabetKeys[5]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_G){
            alphabetKeys[6]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_H){
            alphabetKeys[7]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_I){
            alphabetKeys[8]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_J){
            alphabetKeys[9]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_K){
            alphabetKeys[10]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_L){
            alphabetKeys[11]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_M){
            alphabetKeys[12]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_N){
            alphabetKeys[13]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_O){
            alphabetKeys[14]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_P){
            alphabetKeys[15]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_Q){
            alphabetKeys[16]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_R){
            alphabetKeys[17]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            alphabetKeys[18]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_T){
            alphabetKeys[19]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_U){
            alphabetKeys[20]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_V){
            alphabetKeys[21]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_W){
            alphabetKeys[22]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_X){
            alphabetKeys[23]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_Y){
            alphabetKeys[24]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_Z){
            alphabetKeys[25]=false;
        }
        //Number Keys
        if(e.getKeyCode()==KeyEvent.VK_0){
            numberKeys[0]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_1){
            numberKeys[1]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_2){
            numberKeys[2]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_3){
            numberKeys[3]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_4){
            numberKeys[4]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_5){
            numberKeys[5]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_6){
            numberKeys[6]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_7){
            numberKeys[7]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_8){
            numberKeys[8]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_9){
            numberKeys[9]=false;
        }
        //Action Keys
        if(e.getKeyCode()==KeyEvent.VK_UP){
            actionKeys[0]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            actionKeys[1]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            actionKeys[3]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            actionKeys[4]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            actionKeys[5]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_CONTROL){
            actionKeys[6]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_ALT){
            actionKeys[7]=false;;
        }
        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
            actionKeys[8]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_SHIFT){
            actionKeys[9]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            actionKeys[10]=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            actionKeys[11]=false;
        }
        //NumPad
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD0){
            numpadKeys[0]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD1){
            numpadKeys[1]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD2){
            numpadKeys[2]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD3){
            numpadKeys[3]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD4){
            numpadKeys[4]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD5){
            numpadKeys[5]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD6){
            numpadKeys[6]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD7){
            numpadKeys[7]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD8){
            numpadKeys[8]=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD9){
            numpadKeys[9]=false;
        }
    }

    /**
     * Takes in a character value, and checks to see if that value is currently set to true.
     *
     * Will only return false if that character's associated key is not pressed, or if it is not supported
     * 
     * Only Supports Alpha-Numeric Keys
     * @param b -character value of a key
     * @return -whether or not that key is currently pressed down
     */
    public boolean isKeyDown(char b){
        if(b==keyChars.a){
            return alphabetKeys[0];
        }
        if(b==keyChars.b){
            return alphabetKeys[1];
        }
        if(b==keyChars.c){
            return alphabetKeys[2];
        }
        if(b==keyChars.d){
            return alphabetKeys[3];
        }
        if(b==keyChars.e){
            return alphabetKeys[4];
        }
        if(b==keyChars.f){
            return alphabetKeys[5];
        }
        if(b==keyChars.g){
            return alphabetKeys[6];
        }
        if(b==keyChars.h){
            return alphabetKeys[7];
        }
        if(b==keyChars.i){
            return alphabetKeys[8];
        }
        if(b==keyChars.j){
            return alphabetKeys[9];
        }
        if(b==keyChars.k){
            return alphabetKeys[10];
        }
        if(b==keyChars.l){
            return alphabetKeys[11];
        }
        if(b==keyChars.m){
            return alphabetKeys[12];
        }
        if(b==keyChars.n){
            return alphabetKeys[13];
        }
        if(b==keyChars.o){
            return alphabetKeys[14];
        }
        if(b==keyChars.p){
            return alphabetKeys[15];
        }
        if(b==keyChars.q){
            return alphabetKeys[16];
        }
        if(b==keyChars.r){
            return alphabetKeys[17];
        }
        if(b==keyChars.s){
            return alphabetKeys[18];
        }
        if(b==keyChars.t){
            return alphabetKeys[19];
        }
        if(b==keyChars.u){
            return alphabetKeys[20];
        }
        if(b==keyChars.v){
            return alphabetKeys[21];
        }
        if(b==keyChars.w){
            return alphabetKeys[22];
        }
        if(b==keyChars.x){
            return alphabetKeys[23];
        }
        if(b==keyChars.y){
            return alphabetKeys[24];
        }
        if(b==keyChars.z){
            return alphabetKeys[25];
        }
        if(b==keyChars.Zero){
            return numberKeys[0]||numpadKeys[0];
        }
        if(b==keyChars.One){
            return numberKeys[1]||numpadKeys[1];
        }
        if(b==keyChars.Two){
            return numberKeys[2]||numpadKeys[2];
        }
        if(b==keyChars.Three){
            return numberKeys[3]||numpadKeys[3];
        }
        if(b==keyChars.Four){
            return numberKeys[4]||numpadKeys[4];
        }
        if(b==keyChars.Five){
            return numberKeys[5]||numpadKeys[5];
        }
        if(b==keyChars.Six){
            return numberKeys[6]||numpadKeys[6];
        }
        if(b==keyChars.Seven){
            return numberKeys[7]||numpadKeys[7];
        }
        if(b==keyChars.Eight){
            return numberKeys[8]||numpadKeys[8];
        }
        if(b==keyChars.Nine){
            return numberKeys[9]||numpadKeys[9];
        }
        
        return false;

    }

    /**
     *Takes in an Int value from the KeyEvent class's Virtual Key values and checks to see if that value is currently set to true.
     *
     * Will only return false if that int's associated key is not pressed, or if it is not supported
     * @param c -the Int value of a Virtual Key
     * @return -whether or not that value's key is pressed or not
     * @see KeyEvent
     */
    public boolean isKeyDown(int c){
        if(c==KeyEvent.VK_A){
            return alphabetKeys[0];
        }
        if(c==KeyEvent.VK_B){
            return alphabetKeys[1];
        }
        if(c==KeyEvent.VK_C){
            return alphabetKeys[2];
        }
        if(c==KeyEvent.VK_D){
            return alphabetKeys[3];
        }
        if(c==KeyEvent.VK_E){
            return alphabetKeys[4];
        }
        if(c==KeyEvent.VK_F){
            return alphabetKeys[5];
        }
        if(c==KeyEvent.VK_G){
            return alphabetKeys[6];
        }
        if(c==KeyEvent.VK_H){
            return alphabetKeys[7];
        }
        if(c==KeyEvent.VK_I){
            return alphabetKeys[8];
        }
        if(c==KeyEvent.VK_J){
            return alphabetKeys[9];
        }
        if(c==KeyEvent.VK_K){
            return alphabetKeys[10];
        }
        if(c==KeyEvent.VK_L){
            return alphabetKeys[11];
        }
        if(c==KeyEvent.VK_M){
            return alphabetKeys[12];
        }
        if(c==KeyEvent.VK_N){
           return alphabetKeys[13];
        }
        if(c==KeyEvent.VK_O){
            return alphabetKeys[14];
        }
        if(c==KeyEvent.VK_P){
            return alphabetKeys[15];
        }
        if(c==KeyEvent.VK_Q){
            return alphabetKeys[16];
        }
        if(c==KeyEvent.VK_R){
            return alphabetKeys[17];
        }
        if(c==KeyEvent.VK_S){
            return alphabetKeys[18];
        }
        if(c==KeyEvent.VK_T){
            return alphabetKeys[19];
        }
        if(c==KeyEvent.VK_U){
            return alphabetKeys[20];
        }
        if(c==KeyEvent.VK_V){
            return alphabetKeys[21];
        }
        if(c==KeyEvent.VK_W){
            return alphabetKeys[22];
        }
        if(c==KeyEvent.VK_X){
            return alphabetKeys[23];
        }
        if(c==KeyEvent.VK_Y){
            return alphabetKeys[24];
        }
        if(c==KeyEvent.VK_Z){
            return alphabetKeys[25];
        }
        //Action Keys
        if(c==KeyEvent.VK_UP){
            return actionKeys[0];
        }
        if(c==KeyEvent.VK_DOWN){
            return actionKeys[1];
        }
        if(c==KeyEvent.VK_LEFT){
            return actionKeys[3];
        }
        if(c==KeyEvent.VK_RIGHT){
            return actionKeys[4];
        }
        if(c==KeyEvent.VK_ENTER){
            return actionKeys[5];
        }
        if(c==KeyEvent.VK_CONTROL){
            return actionKeys[6];
        }
        if(c==KeyEvent.VK_ALT){
            return actionKeys[7];
        }
        if(c==KeyEvent.VK_BACK_SPACE){
            return actionKeys[8];
        }
        if(c==KeyEvent.VK_SHIFT){
            return actionKeys[9];
        }
        if(c==KeyEvent.VK_SPACE){
            return actionKeys[10];
        }
        if(c==KeyEvent.VK_ESCAPE){
            return actionKeys[11];
        }
        
        //NumPad
        if(c==KeyEvent.VK_NUMPAD0){
            return numpadKeys[0];
        }
        if(c==KeyEvent.VK_NUMPAD1){
            return numpadKeys[1];
        }
        if(c==KeyEvent.VK_NUMPAD2){
            return numpadKeys[2];
        }
        if(c==KeyEvent.VK_NUMPAD3){
            return numpadKeys[3];
        }
        if(c==KeyEvent.VK_NUMPAD4){
            return numpadKeys[4];
        }
        if(c==KeyEvent.VK_NUMPAD5){
            return numpadKeys[5];
        }
        if(c==KeyEvent.VK_NUMPAD6){
            return numpadKeys[6];
        }
        if(c==KeyEvent.VK_NUMPAD7){
            return numpadKeys[7];
        }
        if(c==KeyEvent.VK_NUMPAD8){
            return numpadKeys[8];
        }
        if(c==KeyEvent.VK_NUMPAD9){
            return numpadKeys[9];
        }
        
        return false;

    }

    /**
     * Tests to see if a Virtual Key int valu of a key is currently not being pressed
     *
     * Will return false if the key is pressed, or if that wanted key is not implemented
     * @param c -the int value from a Virtual Key
     * @return -Whether or not that key is pressed
     * @see KeyEvent
     */
    public boolean isKeyUp(int c){
        if(c==KeyEvent.VK_A){
            return !alphabetKeys[0];
        }
        if(c==KeyEvent.VK_B){
            return !alphabetKeys[1];
        }
        if(c==KeyEvent.VK_C){
            return !alphabetKeys[2];
        }
        if(c==KeyEvent.VK_D){
            return !alphabetKeys[3];
        }
        if(c==KeyEvent.VK_E){
            return !alphabetKeys[4];
        }
        if(c==KeyEvent.VK_F){
            return !alphabetKeys[5];
        }
        if(c==KeyEvent.VK_G){
            return !alphabetKeys[6];
        }
        if(c==KeyEvent.VK_H){
            return !alphabetKeys[7];
        }
        if(c==KeyEvent.VK_I){
            return !alphabetKeys[8];
        }
        if(c==KeyEvent.VK_J){
            return !alphabetKeys[9];
        }
        if(c==KeyEvent.VK_K){
            return !alphabetKeys[10];
        }
        if(c==KeyEvent.VK_L){
            return !alphabetKeys[11];
        }
        if(c==KeyEvent.VK_M){
            return !alphabetKeys[12];
        }
        if(c==KeyEvent.VK_N){
           return !alphabetKeys[13];
        }
        if(c==KeyEvent.VK_O){
            return !alphabetKeys[14];
        }
        if(c==KeyEvent.VK_P){
            return !alphabetKeys[15];
        }
        if(c==KeyEvent.VK_Q){
            return !alphabetKeys[16];
        }
        if(c==KeyEvent.VK_R){
            return !alphabetKeys[17];
        }
        if(c==KeyEvent.VK_S){
            return !alphabetKeys[18];
        }
        if(c==KeyEvent.VK_T){
            return !alphabetKeys[19];
        }
        if(c==KeyEvent.VK_U){
            return !alphabetKeys[20];
        }
        if(c==KeyEvent.VK_V){
            return !alphabetKeys[21];
        }
        if(c==KeyEvent.VK_W){
            return !alphabetKeys[22];
        }
        if(c==KeyEvent.VK_X){
            return !alphabetKeys[23];
        }
        if(c==KeyEvent.VK_Y){
            return !alphabetKeys[24];
        }
        if(c==KeyEvent.VK_Z){
            return !alphabetKeys[25];
        }
        //Action Keys
        if(c==KeyEvent.VK_UP){
            return !actionKeys[0];
        }
        if(c==KeyEvent.VK_DOWN){
            return !actionKeys[1];
        }
        if(c==KeyEvent.VK_LEFT){
            return !actionKeys[3];
        }
        if(c==KeyEvent.VK_RIGHT){
            return !actionKeys[4];
        }
        if(c==KeyEvent.VK_ENTER){
            return !actionKeys[5];
        }
        if(c==KeyEvent.VK_CONTROL){
            return !actionKeys[6];
        }
        if(c==KeyEvent.VK_ALT){
            return !actionKeys[7];
        }
        if(c==KeyEvent.VK_BACK_SPACE){
            return !actionKeys[8];
        }
        if(c==KeyEvent.VK_SHIFT){
            return !actionKeys[9];
        }
        if(c==KeyEvent.VK_SPACE){
            return !actionKeys[10];
        }
        if(c==KeyEvent.VK_ESCAPE){
            return !actionKeys[11];
        }
        //numbers
        if(c==KeyEvent.VK_0){
            return !numberKeys[0];
        }
        if(c==KeyEvent.VK_1){
            return !numberKeys[1];
        }
        if(c==KeyEvent.VK_2){
            return !numberKeys[2];
        }
        if(c==KeyEvent.VK_3){
            return !numberKeys[3];
        }
        if(c==KeyEvent.VK_4){
            return !numberKeys[4];
        }
        if(c==KeyEvent.VK_5){
            return !numberKeys[5];
        }
        if(c==KeyEvent.VK_6){
            return !numberKeys[6];
        }
        if(c==KeyEvent.VK_7){
            return !numberKeys[7];
        }
        if(c==KeyEvent.VK_8){
            return !numberKeys[8];
        }
        if(c==KeyEvent.VK_9){
            return !numberKeys[9];
        }
        
        
        //NumPad
        if(c==KeyEvent.VK_NUMPAD0){
            return !numpadKeys[0];
        }
        if(c==KeyEvent.VK_NUMPAD1){
            return !numpadKeys[1];
        }
        if(c==KeyEvent.VK_NUMPAD2){
            return !numpadKeys[2];
        }
        if(c==KeyEvent.VK_NUMPAD3){
            return !numpadKeys[3];
        }
        if(c==KeyEvent.VK_NUMPAD4){
            return !numpadKeys[4];
        }
        if(c==KeyEvent.VK_NUMPAD5){
            return !numpadKeys[5];
        }
        if(c==KeyEvent.VK_NUMPAD6){
            return !numpadKeys[6];
        }
        if(c==KeyEvent.VK_NUMPAD7){
            return !numpadKeys[7];
        }
        if(c==KeyEvent.VK_NUMPAD8){
            return !numpadKeys[8];
        }
        if(c==KeyEvent.VK_NUMPAD9){
            return !numpadKeys[9];
        }
        
        return false;
        
    }

    /**
     * Takes the passed in character and tests if the key associated with that character is false.
     *
     * Will only return false if the key is down, or not implemented
     * @param b -character of the key
     * @return -will return if
     */
    public boolean isKeyUp(char b){
        if(b==keyChars.a){
            return !alphabetKeys[0];
        }
        if(b==keyChars.b){
            return !alphabetKeys[1];
        }
        if(b==keyChars.c){
            return !alphabetKeys[2];
        }
        if(b==keyChars.d){
            return !alphabetKeys[3];
        }
        if(b==keyChars.e){
            return !alphabetKeys[4];
        }
        if(b==keyChars.f){
            return !alphabetKeys[5];
        }
        if(b==keyChars.g){
            return !alphabetKeys[6];
        }
        if(b==keyChars.h){
            return !alphabetKeys[7];
        }
        if(b==keyChars.i){
            return !alphabetKeys[8];
        }
        if(b==keyChars.j){
            return !alphabetKeys[9];
        }
        if(b==keyChars.k){
            return !alphabetKeys[10];
        }
        if(b==keyChars.l){
            return !alphabetKeys[11];
        }
        if(b==keyChars.m){
            return !alphabetKeys[12];
        }
        if(b==keyChars.n){
            return !alphabetKeys[13];
        }
        if(b==keyChars.o){
            return !alphabetKeys[14];
        }
        if(b==keyChars.p){
            return !alphabetKeys[15];
        }
        if(b==keyChars.q){
            return !alphabetKeys[16];
        }
        if(b==keyChars.r){
            return !alphabetKeys[17];
        }
        if(b==keyChars.s){
            return !alphabetKeys[18];
        }
        if(b==keyChars.t){
            return !alphabetKeys[19];
        }
        if(b==keyChars.u){
            return !alphabetKeys[20];
        }
        if(b==keyChars.v){
            return !alphabetKeys[21];
        }
        if(b==keyChars.w){
            return !alphabetKeys[22];
        }
        if(b==keyChars.x){
            return !alphabetKeys[23];
        }
        if(b==keyChars.y){
            return !alphabetKeys[24];
        }
        if(b==keyChars.z){
            return !alphabetKeys[25];
        }
        
        //NumPad
        if(b==KeyBoard.Zero){
            return !numberKeys[0]||!numpadKeys[0];
        }
        if(b==KeyBoard.One){
            return !numberKeys[1]||!numpadKeys[1];
        }
        if(b==KeyBoard.Two){
            return !numberKeys[2]||!numpadKeys[2];
        }
        if(b==KeyBoard.Three){
            return !numberKeys[3]||!numpadKeys[3];
        }
        if(b==KeyBoard.Four){
            return !numberKeys[4]||!numpadKeys[4];
        }
        if(b==KeyBoard.Five){
            return !numberKeys[5]||!numpadKeys[5];
        }
        if(b==KeyBoard.Six){
            return !numberKeys[6]||!numpadKeys[6];
        }
        if(b==KeyBoard.Seven){
            return !numberKeys[7]||!numpadKeys[7];
        }
        if(b==KeyBoard.Eight){
            return !numberKeys[8]||!numpadKeys[8];
        }
        if(b==KeyBoard.Nine){
            return !numberKeys[9]||!numpadKeys[9];
        }
        
        return false;
        
    }

    /**
     * Will clone this instance of the class.
     *
     * @return -a clone of this instance
     */

    public KeyBoard clone(){
        KeyBoard k = new KeyBoard();
        k.alphabetKeys=this.alphabetKeys;
        k.numberKeys=this.numberKeys;
        return k;
    }
    
    static final private String alphebet = "abcdefghijklmnopqrstuvwxyz";
    static final private char[] alph = alphebet.toCharArray();
    /**
     * Character value for "A"
     */
    public static char a = alph[0];
    /**
     *Character value for "B"
     */
    public static char b = alph[1];
    /**
     *Character value for "C"
     */
    public static char c = alph[2];
    /**
     *Character value for "D"
     */
    public static char d = alph[3];
    /**
     *Character value for "E"
     */
    public static char e = alph[4];
    /**
     *Character value for "F"
     */
    public static char f = alph[5];
    /**
     *Character value for "G"
     */
    public static char g = alph[6];
    /**
     *Character value for "H"
     */
    public static char h = alph[7];
    /**
     *Character value for "I"
     */
    public static char i = alph[8];
    /**
     *Character value for "J"
     */
    public static char j = alph[9];
    /**
     *Character value for "K"
     */
    public static char k = alph[10];
    /**
     *Character value for "L"
     */
    public static char l = alph[11];
    /**
     *Character value for "M"
     */
    public static char m = alph[12];
    /**
     *Character value for "N"
     */
    public static char n = alph[13];
    /**
     *Character value for "O"
     */
    public static char o = alph[14];
    /**
     *Character value for "P"
     */
    public static char p = alph[15];
    /**
     *Character value for "Q"
     */
    public static char q = alph[16];
    /**
     *Character value for "R"
     */
    public static char r = alph[17];
    /**
     *Character value for "S"
     */
    public static char s = alph[18];
    /**
     *Character value for "T"
     */
    public static char t = alph[19];
    /**
     *Character value for "U"
     */
    public static char u = alph[20];
    /**
     *Character value for "V"
     */
    public static char v = alph[21];
    /**
     *Character value for "W"
     */
    public static char w = alph[22];
    /**
     *Character value for "X"
     */
    public static char x = alph[23];
    /**
     *Character value for "Y"
     */
    public static char y = alph[24];
    /**
     *Character value for "Z"
     */
    public static char z = alph[25];
    private static final String characters = "1234567890";
    private static final char[] asdf = characters.toCharArray();
    /**
     *Character value for "1"
     */
    public static char One = asdf[0];
    /**
     *Character value for "2"
     */
    public static char Two = asdf[1];
    /**
     *Character value for "3"
     */
    public static char Three = asdf[2];
    /**
     *Character value for "4"
     */
    public static char Four = asdf[3];
    /**
     *Character value for "5"
     */
    public static char Five = asdf[4];
    /**
     *Character value for "6"
     */
    public static char Six = asdf[5];
    /**
     *Character value for "7"
     */
    public static char Seven = asdf[6];
    /**
     * Character value for "8"
     */
    public static char Eight = asdf[7];
    /**
     *Character value for "9"
     */
    public static char Nine = asdf[8];
    /**
     *Character value for "0"
     */
    public static char Zero = asdf[9];
    
    public static int NumPad_0=KeyEvent.VK_NUMPAD0;
    public static int NumPad_1=KeyEvent.VK_NUMPAD1;
    public static int NumPad_2=KeyEvent.VK_NUMPAD2;
    public static int NumPad_3=KeyEvent.VK_NUMPAD3;
    public static int NumPad_4=KeyEvent.VK_NUMPAD4;
    public static int NumPad_5=KeyEvent.VK_NUMPAD5;
    public static int NumPad_6=KeyEvent.VK_NUMPAD6;
    public static int NumPad_7=KeyEvent.VK_NUMPAD7;
    public static int NumPad_8=KeyEvent.VK_NUMPAD8;
    public static int NumPad_9=KeyEvent.VK_NUMPAD9;

}
