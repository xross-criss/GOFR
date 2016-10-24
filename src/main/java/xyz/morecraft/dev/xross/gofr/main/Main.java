package xyz.morecraft.dev.xross.gofr.main;

import xyz.morecraft.dev.xross.gofr.engine.Processor;
import xyz.morecraft.dev.xross.gofr.world.World;

public class Main {

    public static void main(String[] args) {

        boolean showIt = true;
        int steps = 10;
        boolean isTorus = false;

        int height = 5;
        int width = 10;

        World w = World.createRandomWorld(height, width);
//        System.out.println(w.toString());

        w = Processor.processWorld(w, steps, showIt, height, width, isTorus);


    }

}
