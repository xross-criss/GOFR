package xyz.morecraft.dev.xross.gofr.engine;

//import main.CellsPosition;

import java.util.ArrayList;

public class Cells {
//    public static CellState[][] createCells(int a, int b, CellsPosition cellsPosition, CellState[][] world) {
//
//        if (cellsPosition == CellsPosition.RANDOM) {
//            world = createRandomCells(world, a, b);
//        } else if (cellsPosition == CellsPosition.GLIDER) {
//            world = createGliderCells(world, a, b);
//        } else if (cellsPosition == CellsPosition.IMMORTAL) {
//            world = createImmortalCells(world, a, b);
//        } else if (cellsPosition == CellsPosition.LWSS) {
//            world = createLWSSCells(world, a, b);
//        } else {
//            System.out.println("W Cells coś poszło nie tak");
//        }
//
//        return world;
//    }

    private static CellState[][] createLWSSCells(CellState[][] world, int a, int b) {
        return world;
    }

    private static CellState[][] createImmortalCells(CellState[][] world, int a, int b) {
        return world;
    }

    private static CellState[][] createGliderCells(CellState[][] world, int a, int b) {
        return world;
    }

    private static CellState[][] createRandomCells(CellState[][] world, int a, int b) {
        int liczbaZywychKomorek = (int) (Math.random() * (((a * b) * (80 / 100)) + 1));

        Point point = new Point(0, 0);

        int randomX = random(a);
        int randomY = random(b);

        ArrayList<Point> pointList = new ArrayList<>(liczbaZywychKomorek);
        boolean mustAdd;

//        for (int i = 0; i < pointList.size(); i++) {
//            check_numbers:
//            if (randomX == /*x z listy*/ && randomY ==/*y z listy*/) { //TODO
//                randomX = random(a);
//                randomY = random(b);
//                mustAdd = false;
//
//            } else if ((randomX == /*x z listy*/ && randomY != /*y z listy*/) ||
//                    randomX != /*x z listy*/ && randomY == /*y z listy*/) {
//                mustAdd = true;
//                point.setX(randomX);
//                point.setY(randomY);
//
//            } else {
//                System.out.println("Błąd przy sprawdzaniu duplikatu punktów");
//            }
//
//            while (mustAdd == true) {
//                pointList.add(i, point);
//            }
//        }

        return world;
    }

    public static int random(int a) {
        return (int) (Math.random() * a + 1);
    }
}