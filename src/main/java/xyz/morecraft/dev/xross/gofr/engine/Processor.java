package xyz.morecraft.dev.xross.gofr.engine;

import xyz.morecraft.dev.xross.gofr.io.Input;
import xyz.morecraft.dev.xross.gofr.world.World;

import java.io.IOException;

public class Processor {

    private static final int[][] NEIGHBOURS = {
            {-1, -1}, {-1, 0}, {-1, +1},
            {0, -1}, /* us */ {0, +1},
            {+1, -1}, {+1, 0}, {+1, +1}
    };

    public static World processWorld(World w, int steps, boolean showIt, int height, int width, boolean isTorus, boolean saveFile, String filename) throws IOException {
        int[][] cellNeighbourhood = new int[height][width];

        System.out.println(printSteps(w));

        if (showIt) { // jeśli true to pokazuje co jakąś iteracje
            iteration(w, cellNeighbourhood, height, width, steps, saveFile, filename, isTorus);
        } else { // w przeciwnym wypadku pokazuje tylko pierwszy i ostatni krok
            if (saveFile) { //do pliku zapisuje wszystkie kroki, niezależnie od wyświetlania
                Input.saveFileAsTXT(filename, w, height, width, 0);
                for (int i = 0; i < steps; i++) {
                    cellNeighbourhood = countNeighbours(w, cellNeighbourhood, height, width, isTorus);
                    w = process(w, cellNeighbourhood, height, width);
                    Input.saveFileAsTXT(filename, w, height, width, i);
                }
                System.out.println(printSteps(w));
            } else { // jeśli nie zapisujemy do pliku
                for (int i = 0; i < steps; i++) {
                    cellNeighbourhood = countNeighbours(w, cellNeighbourhood, height, width, isTorus);
                    w = process(w, cellNeighbourhood, height, width);
                }
                System.out.println(printSteps(w));
            }
        }
        return w;
    }

    private static void iteration(World w, int[][] cellNeighbourhood, int height, int width, int steps, boolean saveFile, String filename, boolean isTorus) throws IOException {

        int iteration = 1;

        if (steps >= 100 && steps < 1000) {
            iteration = 10;
        } else if (steps >= 1000 && steps < 10000) {
            iteration = 100;
        } else if (steps >= 10000 && steps < 1000000) {
            iteration = steps % 100;
        } else if (steps >= 1000000) {
            iteration = steps % 1000;
        }

        // wiem wiem, paździoch, ale stwierdziłem, że takie rozwiązanie będzie "wydajniejsze" niż umieszczenie if'a w forach.
        if (saveFile) {
            Input.saveFileAsTXT(filename, w, height, width, 0);
            for (int i = 0; i < steps; i += iteration) {
                for (int j = 0; j < steps; j++) {
                    cellNeighbourhood = countNeighbours(w, cellNeighbourhood, height, width, isTorus);
                    w = process(w, cellNeighbourhood, height, width);
                    Input.saveFileAsTXT(filename, w, height, width, j);

                }
                System.out.println(printSteps(w));
            }
        } else {
            for (int i = 0; i < steps; i += iteration) {
                for (int j = 0; j < steps; j++) {
                    cellNeighbourhood = countNeighbours(w, cellNeighbourhood, height, width, isTorus);
                    w = process(w, cellNeighbourhood, height, width);

                }
                System.out.println(printSteps(w));
            }
        }
    }

    private static World process(World w, int[][] cellNeighbourhood, int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (w.getCell(i, j) == CellState.DEAD && cellNeighbourhood[i][j] == 3) {
                    w.setAlive(i, j);
                } else if (w.getCell(i, j) == CellState.ALIVE && (cellNeighbourhood[i][j] != 3 || cellNeighbourhood[i][j] != 2)) {
                    w.setDead(i, j);
                }
            }
        }
        return w;
    }

    private static int[][] countNeighbours(World w, int[][] cellNeighbourhood, int height, int width, boolean isTorus) {
        if (isTorus) {

            cellNeighbourhood = countForTorus(w, cellNeighbourhood, height, width);

        } else {
            cellNeighbourhood = coutIfNotTorus(w, cellNeighbourhood, height, width);
        }

        return cellNeighbourhood;
    }

    private static String printSteps(World w) {
        String s = "";
        for (int i = 0; i < w.getWidth(); i++) {
            for (int j = 0; j < w.getHeight(); j++) {
                s += w.getCell(i, j);
            }
            s += "\n";
        }
        return s;
    }

    private static int[][] countForTorus(World w, int[][] cellNeighbourhood, int height, int width) {
        return cellNeighbourhood;

        //TODO - counting for Torus
    }

    private static int[][] coutIfNotTorus(World w, int[][] cellNeighbourhood, int height, int width) {
        int neighbours = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int[] offset : NEIGHBOURS) {
                    if (w.isLiveAt(i + offset[1], j + offset[0])) {
                        neighbours++;
                    }
                }
                cellNeighbourhood[i][j] = neighbours;
            }
        }
        return cellNeighbourhood;
    }

}