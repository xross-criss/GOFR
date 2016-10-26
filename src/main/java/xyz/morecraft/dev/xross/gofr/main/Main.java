package xyz.morecraft.dev.xross.gofr.main;

import xyz.morecraft.dev.xross.gofr.engine.Processor;
import xyz.morecraft.dev.xross.gofr.world.World;

public class Main {

    public static void main(String[] args) {

        boolean showIt = true; //pokazuje krok po kroku
        int steps = 1000; //ilość kroków
        boolean isTorus = false; //czy świat ma być torusem

        int height = 5; // ilość wierszy (pionowo)
        int width = 10; // ilość kolumn (poziomo)



        World w = World.createRandomWorld(height, width);
//        System.out.println(w.toString());

        w = Processor.processWorld(w, steps, showIt, height, width, isTorus);


    }

}
