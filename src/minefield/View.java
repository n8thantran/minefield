package minefield;

import tools.Subscriber;

import javax.swing.*;
import java.awt.*;

class Tile {
    int row;
    int col;
    boolean visited;
    boolean isMine;

    Tile(int row, int col, boolean visited, boolean isMine) {
        this.row = row;
        this.col = col;
        this.visited = visited;
        this.isMine = isMine;
    }
}

public class View extends JPanel implements Subscriber {
    private Model model;
    private final int cellSize = 20;
    private Tile[][] minefield = new Tile[20][20];

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
        setPreferredSize(new Dimension(500, 450));

        //initialize tiles
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                minefield[i][j] = new Tile(i, j, false, false);
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Minefield
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                // Cell unvisited BG color
                if(!minefield[i][j].visited){
                    g.setColor(Color.darkGray);
                    g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                }

                // Border of cell
                g.setColor(Color.BLACK);
                g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
            }
        }

        // Model
        g.setColor(Color.GREEN);
        g.drawRect(model.getCol() * cellSize, model.getRow() * cellSize, cellSize, cellSize);
    }

    public void update() {
        repaint();
    }
}
