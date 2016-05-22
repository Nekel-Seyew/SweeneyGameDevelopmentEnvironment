/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game3D;

import java.awt.Graphics2D;

/**
 *
 * @author Nekel
 */
public interface Renderable {
    public void render(Graphics2D g, Camera c);
    public double distance(Vector3 v);
}
