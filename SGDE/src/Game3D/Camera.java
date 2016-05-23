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
    double fov;
    double fovd;
    int screenSizeX, screenSizeY;
    double near, far;
    double aspectRatio;
    
    public enum projection{
        perspective,
        orthographic
    }
    
    public Camera(Vector3 pos, Vector3 up, Vector3 forward, double fov, int height, int width){
        this.pos = pos;
        this.up = up;
        this.forward = forward;
        this.sideways = forward.crossProduct(up);
        this.fov = fov;
        this.fovd = 1.0/Math.tan(fov/2.0);
        this.screenSizeX = height;
        this.screenSizeY = width;
        this.currentProjection = projection.perspective;
        this.aspectRatio = (width*1.0)/height;
        near = 0.01;
        far = 1000;
        
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
        this.up.normalize();
        this.sideways = q.rotate(this.sideways);
        this.sideways.normalize();
    }
    /**
     * Spins the camera left/right, around the"up" axis
     * @param theta radians to spin
     */
    public void yaw(double theta){
        Quaternion q = new Quaternion(this.up,theta);
        this.forward = q.rotate(this.forward);
        this.forward.normalize();
        this.sideways = q.rotate(this.sideways);
        this.sideways.normalize();
    }
    /**
     * Rotates the camera up/down, around the sideways axis
     * @param theta radians to rotate
     */
    public void pitch(double theta){
        Quaternion q = new Quaternion(this.sideways,theta);
        this.up = q.rotate(this.up);
        this.up.normalize();
        this.forward = q.rotate(this.forward);
        this.forward.normalize();
    }

    public Vector3 getPos() {
        return pos;
    }

    public Vector3 getUp() {
        return up;
    }

    public Vector3 getForward() {
        return forward;
    }

    public Vector3 getSideways() {
        return sideways;
    }
    
    public void setProjection(projection p){
        this.currentProjection = p;
    }
    public projection getProjection(){
        return this.currentProjection;
    }
    
    public double getfov(){
        return this.fov;
    }
    public double getfovd(){
        return this.fovd;
    }
    
    public int getWidth(){
        return this.screenSizeX;
    }
    public int getHeight(){
        return this.screenSizeY;
    }

    public double getFov() {
        return fov;
    }

    public void setFov(double fov) {
        this.fov = fov;
    }

    public int getScreenSizeX() {
        return screenSizeX;
    }

    public void setScreenSizeX(int screenSizeX) {
        this.screenSizeX = screenSizeX;
        this.aspectRatio = screenSizeX/screenSizeY;
    }

    public int getScreenSizeY() {
        return screenSizeY;
    }

    public void setScreenSizeY(int screenSizeY) {
        this.screenSizeY = screenSizeY;
        this.aspectRatio = screenSizeX/screenSizeY;
    }

    public double getNear() {
        return near;
    }

    public void setNear(double near) {
        this.near = near;
    }

    public double getFar() {
        return far;
    }

    public void setFar(double far) {
        this.far = far;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }
    
}
