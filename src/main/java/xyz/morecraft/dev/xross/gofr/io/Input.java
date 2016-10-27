package xyz.morecraft.dev.xross.gofr.io;

import xyz.morecraft.dev.xross.gofr.engine.CellState;
import xyz.morecraft.dev.xross.gofr.world.World;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Input {
    private static char DeadChar = 'O';
    private static char AliveChar = 'X';

    public static void saveFileAsTXT(String filename, World w, int height, int width, int step) {
        try {
            File file = new File(filename);

            if (!file.exists()) {
                file.createNewFile();
            } else {
                System.out.println("plik został nadpisany");
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

//        bw.write( w.getWidth() + " " + w.getHeight() );
            bw.write("============== " + step + " ==============");
            bw.newLine();

            for (int i = 0; i < w.getHeight(); i++) {
                for (int j = 0; j < w.getWidth(); j++) {

                    bw.write(w.getCell(i, j) == CellState.ALIVE ? Input.AliveChar : Input.DeadChar);
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
