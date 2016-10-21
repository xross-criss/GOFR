package xyz.morecraft.dev.xross.gofr.main;

import xyz.morecraft.dev.xross.gofr.world.World;

public class Main {

    public static void main(String[] args) {

        World w = World.createRandomWorld(5, 10);

        System.out.println(w.toString());

        w.setAlive(0, 3);
        w.setAlive(1, 2);

        System.out.println(w.toString());

    }

}
