package mineField;

import mvc.*;
import java.awt.*;

public class MinefieldView extends View {
    private static final int CELL_SIZE = 25;

    public MinefieldView(Minefield model) {
        super(model);
    }

    public void setModel(Model newModel) {
        super.setModel(newModel);
        repaint();
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Minefield field = (Minefield) model;
        int size = field.getSize();

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                gc.setColor(Color.GRAY);
                gc.fillRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                gc.setColor(Color.BLACK);
                gc.drawRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
                if (r == size - 1 && c == size - 1) {
                    gc.setColor(Color.GREEN);
                    gc.drawRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
                }
                else if (field.isVisited(r, c)) {
                    gc.setColor(Color.WHITE);
                    gc.drawRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
                }


                gc.setColor(Color.BLACK);
                if (field.isVisited(r, c)) {
                    gc.drawString(Integer.toString(field.getMineCountAt(r, c)), c * CELL_SIZE + 10, r * CELL_SIZE + 20);
                } else {
                    gc.drawString("?", c * CELL_SIZE + 10, r * CELL_SIZE + 20);
                }
            }
        }
    }
}

