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
 * A collection of characters, used for keyboard input.
 * @author Kyle Maximilian Dieter Sweeney
 */
public class keyChars implements Serializable{

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
}
