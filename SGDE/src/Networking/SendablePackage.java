/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking;

/**
 * Something. I'm not sure what.
 * @author Kyle Sweeney
 */
public class SendablePackage {
    public static int count=0;
    public int id;
    public byte[] dataSent;
    
    public SendablePackage(byte[] data){
        dataSent=data;
        id=count++;
    }
}
