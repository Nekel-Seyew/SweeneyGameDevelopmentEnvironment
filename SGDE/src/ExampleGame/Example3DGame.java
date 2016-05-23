/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExampleGame;

import Game3D.Game3D;
import Game3D.PolygonTexture;
import Game3D.SceneBatch;
import Game3D.Vector3;
import Utilities.ImageCollection;
import Utilities.Vector2;
import Utilities.keyChars;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Nekel
 */
public class Example3DGame extends Game3D{
    
    PolygonTexture pt1;
    PolygonTexture pt2;
    PolygonTexture pt3;
    PolygonTexture pt4;
    PolygonTexture pt5;
    PolygonTexture pt6;
    
    int count;
    long time;
    
    @Override
    public void InitializeAndLoad() {
        time = System.currentTimeMillis();
        Vector3 v3ULR = new Vector3(-0.5, 0.5, -0.5);
        Vector3 v3URR = new Vector3(0.5, 0.5, -0.5);
        Vector3 v3LLR = new Vector3(-0.5, -0.5, -0.5);
        Vector3 v3LRR = new Vector3(0.5, -0.5, -0.5);
        Vector3 v3ULF = new Vector3(-0.5, 0.5, 0.5);
        Vector3 v3URF = new Vector3(0.5, 0.5, 0.5);
        Vector3 v3LLF = new Vector3(-0.5, -0.5, 0.5);
        Vector3 v3LRF = new Vector3(0.5, -0.5, 0.5);
        Vector3 v3ULS = new Vector3(-0.5, 0.5, -0.5);
        Vector3 v3URS = new Vector3(-0.5, 0.5, 0.5);
        Vector3 v3LLS = new Vector3(-0.5, -0.5, -0.5);
        Vector3 v3LRS = new Vector3(0.5, 0.5, -0.5);
        //Vector3 v3

        Vector2 v2UL = new Vector2(0, 1);
        Vector2 v2UR = new Vector2(1, 1);
        Vector2 v2LL = new Vector2(0, 0);
        Vector2 v2LR = new Vector2(1, 0);
        pt1 = new PolygonTexture("Sprites/redbrick.png", new Vector2[]{v2UL.clone(), v2LL.clone(), v2UR.clone()},
                new Vector3[]{v3ULF, v3LLF, v3URF});
        pt1.setOutline(true);
        pt2 = new PolygonTexture("Sprites/redbrick.png", new Vector2[]{v2LL.clone(), v2LR.clone(), v2UR.clone()},
                new Vector3[]{v3LLF, new Vector3(.5,-.5,.5),new Vector3(.5,.5,.5)});
        pt2.setOutline(true);
//        pt3 = new PolygonTexture("Sprites/redbrick.png", new Vector2[]{v2UL.clone(), v2LL.clone(), v2UR.clone()},
//                new Vector3[]{v3ULF, v3LLF, v3URF});
//        pt3.setOutline(true);
//        pt4 = new PolygonTexture("Sprites/redbrick.png", new Vector2[]{v2LL.clone(), v2UR.clone(), v2LR.clone()},
//                new Vector3[]{v3LLF, v3URF, v3LRF});
//        pt4.setOutline(true);
        pt5 = new PolygonTexture("Sprites/bluewall.png", new Vector2[]{v2UL.clone(), v2LL.clone(), v2UR.clone()},
                new Vector3[]{new Vector3(.5,.5,.5), new Vector3(.5,-.5,.5), new Vector3(.5,.5,-.5)});
        pt5.setOutline(true);
        pt6 = new PolygonTexture("Sprites/bluewall.png", new Vector2[]{v2LL.clone(), v2LR.clone(), v2UR.clone()},
                new Vector3[]{new Vector3(.5,-.5,.5), new Vector3(.5,-.5,-.5), new Vector3(.5,.5,-.5)});
        pt6.setOutline(true);
    }
    
    @Override
    public void Render(Graphics2D g, SceneBatch batch) {
        batch.renderPolygon(pt1);
        batch.renderPolygon(pt2);
        //batch.renderPolygon(pt3);
        //batch.renderPolygon(pt4);
        batch.renderPolygon(pt5);
        batch.renderPolygon(pt6);
    }

    @Override
    public void DrawFlatImage(Graphics2D g, ImageCollection batch) {
        
    }

    @Override
    public void UnloadContent() {
        
    }

    @Override
    public void Update() {
        if(this.keyboard.isKeyDown('a')){
            this.camera.translateLateral(-0.05);
        }
        if(this.keyboard.isKeyDown('d')){
            this.camera.translateLateral(0.05);
        }
        if(this.keyboard.isKeyDown('s')){
            this.camera.translateHorizontal(-0.05);
        }
        if(this.keyboard.isKeyDown('w')){
            this.camera.translateHorizontal(0.05);
        }
        if(this.keyboard.isKeyDown('z')){
            this.camera.translateVertical(0.05);
        }
        if(this.keyboard.isKeyDown('x')){
            this.camera.translateVertical(-0.05);
        }
        if(this.keyboard.isKeyDown(KeyEvent.VK_UP)){
            this.camera.pitch(0.05);
        }
        if(this.keyboard.isKeyDown(KeyEvent.VK_DOWN)){
            this.camera.pitch(-0.05);
        }
        if(this.keyboard.isKeyDown(KeyEvent.VK_LEFT)){
            this.camera.yaw(-0.05);
        }
        if(this.keyboard.isKeyDown(KeyEvent.VK_RIGHT)){
            this.camera.yaw(0.05);
        }
        if(this.keyboard.isKeyDown(keyChars.q)){
            this.camera.roll(-0.05);
        }
        if(this.keyboard.isKeyDown(keyChars.e)){
            this.camera.roll(0.05);
        }
        if(System.currentTimeMillis() - time < 1000){
            count++;
        }else{
            time = System.currentTimeMillis();
            System.out.println("FPS: "+count);
            count = 0;
        }
    }
    
}
