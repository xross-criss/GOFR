package xyz.morecraft.dev.xross.gofr.engine;

import xyz.morecraft.dev.xross.gofr.world.World;

public class Processor {

/*    public World processWorld(World world) {
        // TODO Process world
        return world;
    }*/

    public static World processWorld(World w, int steps, boolean showIt, int height, int width, boolean isTorus) {
        int[][] cellNeighbourhood = new int[height][width];

        cellNeighbourhood = countNeighbours(w, cellNeighbourhood, height, width, isTorus);

        for (int i = 0; i < steps; i++) {
            w = process(w, cellNeighbourhood, height, width);

            if (showIt) {
                printSteps(w);
            }
        }
        return w;
    }

    private static World process(World w, int[][] cellNeighbourhood, int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (w.getCell(i, j) == CellState.DEAD && (cellNeighbourhood[i][j] < 6 && cellNeighbourhood[i][j] > 2)) {
                    w.setAlive(i, j);
                } else if (w.getCell(i, j) == CellState.ALIVE && (cellNeighbourhood[i][j] > 5 || cellNeighbourhood[i][j] < 3)) {
                    w.setAlive(i, j);
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
                if (i == 0 && j == 0) {
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
                if (i == height - 1 && j == width - 1) {
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

                if (i == 0 && j == width - 1) {
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

                if (i == height - 1 && j == 0) {
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

    private static void printSteps(World w) {

    }
}
