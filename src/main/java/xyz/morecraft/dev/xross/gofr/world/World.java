package xyz.morecraft.dev.xross.gofr.world;

import xyz.morecraft.dev.xross.gofr.engine.CellState;
import xyz.morecraft.dev.xross.gofr.engine.WorldType;

import static xyz.morecraft.dev.xross.gofr.engine.CellState.ALIVE;
import static xyz.morecraft.dev.xross.gofr.engine.CellState.DEAD;

public class World {

    private CellState[][] world;
    private CellState[][] worldCopy;
    private int height;
    private int width;
    private WorldType worldType;

    public World(int width, int height) {
        this(width, height, WorldType.SQUARE);
    }

    public World(int width, int height, WorldType worldType) {

        this.width = width;
        this.height = height;
        this.worldType = worldType;

        this.world = new CellState[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = DEAD;
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public CellState getCell(int x, int y) {
        return world[x][y];
    }

    public CellState getCellSafe(int x, int y) {
        if (worldType == WorldType.TORUS) {
            return world[(x + width) % width][(y + height) % height];
        } else if (worldType == WorldType.SQUARE) {
            if (x < 0 || y < 0 || x >= width || y >= height) {
                return DEAD;
            } else {
                return getCell(x, y);
            }
        } else {
            return null;
        }
    }

    public World setCell(int x, int y, CellState state) {
        world[x][y] = state;
        return this;
    }

    public World setAlive(int x, int y) {
        return setCell(x, y, ALIVE);
    }

    public World setDead(int x, int y) {
        return setCell(x, y, DEAD);
    }

    public WorldType getWorldType() {
        return worldType;
    }

    public void setWorldType(WorldType worldType) {
        this.worldType = worldType;
    }

    public static World createRandomWorld(int width, int height, WorldType worldType) {
        World world = new World(width, height, worldType);
        int aliveCount = (int) (Math.random() * width * height * 0.8 + 1);

        for (int i = 0; i < aliveCount; i++) {
            int a, b;
            do {
                a = (int) (Math.random() * (width - 1));
                b = (int) (Math.random() * (height - 1));
            } while (world.getCell(a, b) == ALIVE);
            world.setAlive(a, b);
        }

        return world;
    }

    @Override
    public String toString() {
        String s = "";
        for (CellState[] row : world) {
            for (CellState cs : row)
                s += cs;
            s += "\n";
        }
        return s.length() > 0 ? s.substring(0, s.length() - 1) : s;
    }

    public boolean isAliveAt(int x, int y) {
        return getCell(x, y) == CellState.ALIVE;
    }

    public void prepareEmptyCopy() {
        worldCopy = new CellState[width][height];
    }

    public void setInCopy(int x, int y, CellState state) {
        worldCopy[x][y] = state;
    }

    public void swapCopy() {
        world = worldCopy;
    }

}