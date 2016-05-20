/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game3D;


/**
 *
 * @author Nekel
 */
public class Camera {
    Vector3 pos;
    Vector3 up;
    Vector3 forward;
    Vector3 sideways;
    
    public Camera(Vector3 pos, Vector3 up, Vector3 forward){
        this.pos = pos;
        this.up = up;
        this.forward = forward;
        this.sideways = forward.crossProduct(up);
        
        this.up.normalize();
        this.forward.normalize();
        this.sideways.normalize();
    }
    
    /**
     * Moves the camera "up/down" by multiplying x by unit vector up and
     * adding this result to the position. Positive moves up, negative moves down.
     * @param x multiplier to unit vector up
     */
    public void translateVertical(double x){
        Vector3 du = up.clone();
        du.scalarMultiply(x);
        this.pos.add(du);
    }
    /**
     * Moves the camera "right/left" by multiplying x by unit vector sideways and
     * adding this result to the position. Positive moves right, negative moves down.
     * @param x 
     */
    public void translateLateral(double x){
        Vector3 du = sideways.clone();
        du.scalarMultiply(x);
        this.pos.add(du);
    }
    /**
     * Moves the camera "forward/backward" by multiplying x by unit vector forward and
     * adding this result to the position. Positive moves forward, negative moves backward.
     * @param x 
     */
    public void translateHorizontal(double x){
        Vector3 du = forward.clone();
        du.scalarMultiply(x);
        this.pos.add(du);
    }
    
    public void roll(double theta){
        
    }
    public void yaw(double theta){
        
    }
    public void pitch(double theta){
        
    }
    public void rotate(Quaternion qr){
        
    }
}
