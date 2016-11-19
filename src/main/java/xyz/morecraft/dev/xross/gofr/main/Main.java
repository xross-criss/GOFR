package xyz.morecraft.dev.xross.gofr.main;

import xyz.morecraft.dev.xross.gofr.engine.Processor;
import xyz.morecraft.dev.xross.gofr.io.Output;
import xyz.morecraft.dev.xross.gofr.world.World;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        boolean showIt = true; //pokazuje krok po kroku
        boolean isTorus = false; //czy świat ma być torusem
        boolean saveFile = true; //czy zapisać świat
        boolean readFile = false; //czy czytać świat z pliku?
        String filename = "first.txt";

        int steps = 3; //ilość kroków
        int height = 5; // ilość wierszy (pionowo)
        int width = 10; // ilość kolumn (poziomo)

        World w;

        if (readFile) {
            w = Output.readFileFromTXT(filename);
            height = w.getHeight();
            width = w.getWidth();
        } else {
            w = World.createRandomWorld(height, width);
        }
        w = Processor.processWorld(w, steps, showIt, height, width, isTorus, saveFile, filename);

    }
}