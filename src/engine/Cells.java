package engine;

import main.CellsPosition;

import javax.swing.plaf.nimbus.State;

public class Cells {
    public static State[][] createCells(int a, int b, CellsPosition cellsPosition, State[][] world) {

        if (cellsPosition == CellsPosition.RANDOM){
            world = createRandomCells(world);

        } else if(cellsPosition == CellsPosition.GLIDER){
            world = createGliderCells(world);
        } else if(cellsPosition == CellsPosition.IMMORTAL){
            world = createImmortalCells(world);
        } else if(cellsPosition == CellsPosition.LWSS){
            world = createLWSSCells(world);
        } else{
            System.out.println("W Cells coś poszło nie tak");
        }



        return world;
    }

    private static State[][] createLWSSCells(State[][] world) {
        return world;
    }

    private static State[][] createImmortalCells(State[][] world) {
        return world;
    }

    private static State[][] createGliderCells(State[][] world) {
        return world;
    }

    private static State[][] createRandomCells(State[][] world) {
        return world;
    }


}
