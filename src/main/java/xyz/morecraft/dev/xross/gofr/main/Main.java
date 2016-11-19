package xyz.morecraft.dev.xross.gofr.main;

import xyz.morecraft.dev.xross.gofr.engine.Processor;
import xyz.morecraft.dev.xross.gofr.engine.WorldType;
import xyz.morecraft.dev.xross.gofr.io.IO;
import xyz.morecraft.dev.xross.gofr.world.World;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        World w = new World(5, 5, WorldType.TORUS);

        w.setAlive(0, 1);
        w.setAlive(1, 0);
        w.setAlive(2, 1);
        w.setAlive(1, 2);

        IO.saveFileAsTXT("test.txt", w, 1);

        w = IO.readFileFromTXT("test.txt");

        Processor processor = new Processor(w);

        System.out.println(w);
        System.out.println();
        processor.makeCycle();
        System.out.println(w);
        System.out.println();
        processor.makeCycle();
        System.out.println(w);
        System.out.println();
        processor.makeCycle();
        System.out.println(w);
        System.out.println();

        IO.saveFileAsTXT("test.txt", w, 1);

    }
}