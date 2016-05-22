/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game3D;

import Hardware_Accelerated.AGame;
import Utilities.ImageCollection;
import java.awt.Graphics2D;

/**
 *
 * @author Nekel
 */
public abstract class Game3D extends AGame{

    public Camera camera = new Camera(new Vector3(0,0,1), Vector3.J, new Vector3(0,0,-1), Math.PI/3,600,800);//60degrees fov
    private SceneBatch scenebatch = new SceneBatch(camera);

    
    @Override
    public void Draw(Graphics2D g, ImageCollection batch) {
        Render(g,scenebatch);
        scenebatch.render(g, camera);//draw before ImageCollection does
        DrawFlatImage(g,batch);
    }
    
    public abstract void Render(Graphics2D g, SceneBatch batch);
    public abstract void DrawFlatImage(Graphics2D g, ImageCollection batch);
    
}
