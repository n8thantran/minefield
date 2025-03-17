package mineField;

import mvc.*;

import java.util.Random;

public class Minefield extends Model {
    public static int percentMined = 8;
    private static final int SIZE = 20;
    private boolean[][] mines;
    private boolean[][] visited;
    int playerRow, playerCol;
    private boolean gameOver;

    public Minefield() {
        mines = new boolean[SIZE][SIZE];
        visited = new boolean[SIZE][SIZE];
        generateMines();
        playerRow = 0;
        playerCol = 0;
        visited[playerRow][playerCol] = true;
        gameOver = false;
    }

    private void generateMines() {
        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (rand.nextInt(100) < percentMined) {
                    mines[i][j] = true;
                }
            }
        }
        mines[SIZE - 1][SIZE - 1] = false; // Ensure goal is not mined
    }

    public void move(Heading heading) throws Exception {
        if (gameOver) throw new Exception("Game over! Start a new game.");

        int newRow = playerRow + heading.getRowChange();
        int newCol = playerCol + heading.getColChange();

        if (newRow < 0 || newRow >= SIZE || newCol < 0 || newCol >= SIZE) {
            throw new Exception("Out of bounds!");
        }

        playerRow = newRow;
        playerCol = newCol;
        visited[playerRow][playerCol] = true;

        if (mines[playerRow][playerCol]) {
            gameOver = true;
            throw new Exception("Oops! You stepped on a mine!");
        }

        if (playerRow == SIZE - 1 && playerCol == SIZE - 1) {
            gameOver = true;
            throw new Exception("Congratulations! You reached the goal!");
        }

        changed();
    }

    public int getMineCountAt(int r, int c) {
        int count = 0;
        for (Heading h : Heading.values()) {
            int nr = r + h.getRowChange();
            int nc = c + h.getColChange();
            if (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE && mines[nr][nc]) {
                count++;
            }
        }
        return count;
    }

    public boolean isVisited(int r, int c) {
        return visited[r][c];
    }

    public boolean isMined(int r, int c) {
        return mines[r][c];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getPlayerRow() { return playerRow; }
    public int getPlayerCol() { return playerCol; }
    public int getSize() { return SIZE; }

    public enum Heading {
        NORTH(-1, 0), SOUTH(1, 0), EAST(0, 1), WEST(0, -1),
        NORTHEAST(-1, 1), NORTHWEST(-1, -1), SOUTHEAST(1, 1), SOUTHWEST(1, -1);

        private final int rowChange;
        private final int colChange;

        Heading(int rowChange, int colChange) {
            this.rowChange = rowChange;
            this.colChange = colChange;
        }

        public int getRowChange() { return rowChange; }
        public int getColChange() { return colChange; }
    }
}