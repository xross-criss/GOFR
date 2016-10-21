package xyz.morecraft.dev.xross.gofr.world;

import xyz.morecraft.dev.xross.gofr.engine.CellState;

import static xyz.morecraft.dev.xross.gofr.engine.CellState.ALIVE;
import static xyz.morecraft.dev.xross.gofr.engine.CellState.DEAD;

public class World {

    private CellState[][] world;

    public World(int x, int y) {
        world = new CellState[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                world[i][j] = DEAD;
            }
        }
    }

    public void setAlive(int x, int y) {
        world[x][y] = ALIVE;
    }

    public static World createRandomWorld(int x, int y) {
        World world = new World(x, y);

        int aliveCount = (int) (Math.random() * x * y * 0.8 + 1);

        System.out.println(aliveCount);

        /// TODO Set random cells alive

        return world;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                s += world[i][j];
            }
            s += "\n";
        }
        return s;
    }

}