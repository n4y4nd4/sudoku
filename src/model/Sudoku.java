package model;

public class Sudoku {

    private int[][] board;
    private boolean[][] fixed;
    private GameStatus status;

    public Sudoku() {
        this.board = new int[9][9];
        this.fixed = new boolean[9][9];
        this.status = GameStatus.NOT_STARTED;
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean[][] getFixed() {
        return fixed;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}