package xyz.morecraft.dev.xross.gofr.main;

public class Main {

    public static void main(String[] args) {

        Dupa dupaKowalskiego = new Dupa();
        dupaKowalskiego.rozmiarDupy = 10;

        Dupa dupaKowalskiej = new Dupa();
        dupaKowalskiej.rozmiarDupy = 10;

        // To sa takie same obiekty, ale nie te same obiekty
        // false
        System.out.println(dupaKowalskiego.equals(dupaKowalskiej));

//
//        World w = World.createRandomWorld(5, 10);
//
//        System.out.println(w.toString());
//
//        w.setAlive(0, 3);
//        w.setAlive(1, 2);
//
//        System.out.println(w.toString());

    }

    private static class Dupa {
        private int rozmiarDupy;
    }

}
