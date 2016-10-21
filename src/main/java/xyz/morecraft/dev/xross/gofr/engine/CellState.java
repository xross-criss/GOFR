package xyz.morecraft.dev.xross.gofr.engine;

public enum CellState {

    DEAD('O'), ALIVE('X');

    private char sign;

    CellState(char sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "" + sign;
    }

}
