package minefield;

import tools.Publisher;

import java.io.Serializable;

public class Model extends Publisher implements Serializable {
    private int row;
    private int col;
    private int nearbyMines;

    public Model() {
        row = 0;
        col = 0;
    }

    public int getNearbyMines() {
        //TODO: scan 8 tiles around the model for mines
        return nearbyMines;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // should be able to call notifysubscribers without having to make a new method i think
}
