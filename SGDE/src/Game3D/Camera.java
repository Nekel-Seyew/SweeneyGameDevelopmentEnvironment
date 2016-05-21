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
    projection currentProjection;
    
    public enum projection{
        perspective,
        orthographic
    }
    
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
    /**
     * Rolls left/right the camera along the "forward" axis
     * @param theta radians to rotate
     */
    public void roll(double theta){
        Quaternion q = new Quaternion(this.forward,theta);
        this.up = q.rotate(this.up);
        this.sideways = q.rotate(this.sideways);
    }
    /**
     * Spins the camera left/right, around the"up" axis
     * @param theta radians to spin
     */
    public void yaw(double theta){
        Quaternion q = new Quaternion(this.up,theta);
        this.forward = q.rotate(this.forward);
        this.sideways = q.rotate(this.sideways);
    }
    /**
     * Rotates the camera up/down, around the sideways axis
     * @param theta radians to rotate
     */
    public void pitch(double theta){
        Quaternion q = new Quaternion(this.sideways,theta);
        this.up = q.rotate(this.up);
        this.forward = q.rotate(this.forward);
    }
    
    public void setProjection(projection p){
        this.currentProjection = p;
    }
}
