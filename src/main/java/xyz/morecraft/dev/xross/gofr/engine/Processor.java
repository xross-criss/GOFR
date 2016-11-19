package xyz.morecraft.dev.xross.gofr.engine;

import xyz.morecraft.dev.xross.gofr.world.World;

import java.io.IOException;

public class Processor {

    private World world;

    public Processor(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void makeCycles(int cyclesCount) {
        for (int s = 0; s < cyclesCount; s++) {
            makeCycle();
        }
    }

    public void makeCycle() {
        world.prepareEmptyCopy();
        for (int i = 0; i < world.getWidth(); i++) {
            for (int j = 0; j < world.getHeight(); j++) {
                int n = 0;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (world.getCellSafe(k, l) == CellState.ALIVE) {
                            n++;
                        }
                    }
                }
                CellState c = world.getCell(i, j);
                if ((c == CellState.ALIVE && (n == 4 || n == 3)) || (c == CellState.DEAD && n == 3)) {
                    world.setInCopy(i, j, CellState.ALIVE);
                } else {
                    world.setInCopy(i, j, CellState.DEAD);
                }
            }
        }
        world.swapCopy();
    }

}