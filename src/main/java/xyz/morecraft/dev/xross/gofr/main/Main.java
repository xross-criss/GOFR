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

    }

    private static class Dupa {
        private int rozmiarDupy;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || !(o instanceof Dupa)) {
                return false;
            }

            Dupa dupa = (Dupa) o;

            return rozmiarDupy == dupa.rozmiarDupy;

        }

        @Override
        public int hashCode() {
            return rozmiarDupy;
        }
    }

}
