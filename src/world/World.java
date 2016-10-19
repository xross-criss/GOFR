package world;

import engine.CellState;
import main.CellsPosition;

import javax.swing.plaf.nimbus.State;

public class World {
    public static void create(int a, int b, boolean torus, CellsPosition cellsPosition) {

        State[][] world = new State[a][b];

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                world[a][b] = CellState.O;
            }
        }

        world = engine.Cells.createCells(a, b, cellsPosition, world);

    }
}