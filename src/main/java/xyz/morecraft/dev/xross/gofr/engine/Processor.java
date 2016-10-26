package xyz.morecraft.dev.xross.gofr.engine;

import xyz.morecraft.dev.xross.gofr.world.World;

public class Processor {

    public static World processWorld(World w, int steps, boolean showIt, int height, int width, boolean isTorus) {
        int[][] cellNeighbourhood = new int[height][width];

        cellNeighbourhood = countNeighbours(w, cellNeighbourhood, height, width, isTorus);

        System.out.println(printSteps(w));

        if (showIt) {
            iteration(w, cellNeighbourhood, height, width, steps);
        } else {
            for (int i = 1; i < steps; i++) {
                w = process(w, cellNeighbourhood, height, width);
            }
            System.out.println(printSteps(w));
        }

        return w;
    }

    private static void iteration(World w, int[][] cellNeighbourhood, int height, int width, int steps) {

        int iteration = 0;

        if (steps >= 100 && steps < 1000) {
            iteration = 10;
        } else if (steps >= 1000 && steps < 10000) {
            iteration = 100;
        } else if (steps >= 10000 && steps < 100000) {
            iteration = 1000;
        } else if (steps >= 100000) {
            iteration = 10000;
        }
        for (int i = 1; i < steps; i += iteration) {
            for (int j = 1; j < steps; j++) {
                w = process(w, cellNeighbourhood, height, width);
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
        if (isTorus == true) {

            cellNeighbourhood = countForTorus(w, cellNeighbourhood, height, width);

        } else {
            cellNeighbourhood = coutIfNotTorus(w, cellNeighbourhood, height, width);
        }

        return cellNeighbourhood;
    }

    private static int[][] countForTorus(World w, int[][] cellNeighbourhood, int height, int width) {
        return cellNeighbourhood;

        //TODO - counting for Torus
    }

    private static int[][] coutIfNotTorus(World w, int[][] cellNeighbourhood, int height, int width) {
        int neighbours = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 && j == 0) { // lewy górny róg
                    if (w.getCell(i, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                }

                if (i == height - 1 && j == width - 1) { //prawy górny róg
                    if (w.getCell(i, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i - 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i - 1, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                }

                if (i == 0 && j == width - 1) {//prawy dolny róg
                    if (w.getCell(i, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                }

                if (i == height - 1 && j == 0) { // lewy dolny róg
                    if (w.getCell(i - 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i - 1, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                }

                if (i == 0 && j > 0 && j < width - 1) {
                    if (w.getCell(i, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                }

                if (j == 0 && i > 0 && i < height - 1) {
                    if (w.getCell(i - 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i - 1, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                }

                if (i == height - 1 && j > 0 && j < width - 1) {
                    if (w.getCell(i, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i - 1, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i - 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i - 1, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                }

                if (j == width - 1 && i > 0 && i < height - 1) {
                    if (w.getCell(i - 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i - 1, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                }

                if (i > 0 && i < height - 1 && j > 0 && j < width - 1) {
                    if (w.getCell(i - 1, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i - 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i - 1, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j + 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i + 1, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                    if (w.getCell(i, j - 1) == CellState.ALIVE) {
                        neighbours++;
                    }
                }

                cellNeighbourhood[i][j] = neighbours;

            }
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
}