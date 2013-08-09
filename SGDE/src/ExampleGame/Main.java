/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ExampleGame;

import Game.GameBase;
import Hardware_Accelerated.AccelGame;

/**
 *
 * @author Kyle Maximilian Dieter Sweeney
 */
public class Main {
    public static void main(String[] args){
        //GameBase gameWindow = new GameBase(new ExampleGame(), "ExampleGame");
        AccelGame gameWindow=new AccelGame(new ExampleGame(), "ExampleGame");
        gameWindow.run();
//        Runtime run = Runtime.getRuntime();
//        long memleft=run.totalMemory();
//        while(true){
//            long mem=run.freeMemory();
//            if(memleft!=mem){
//                System.out.println("Memory left: "+mem);
//                memleft=mem;
//            }
//        }
    }

}
