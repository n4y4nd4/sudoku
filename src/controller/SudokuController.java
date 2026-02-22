package controller;

import service.SudokuService;
import model.GameStatus;

public class SudokuController {

    private SudokuService service;

    public SudokuController() {
        this.service = new SudokuService();
    }

    public void startNewGame(String[] args) {
        service.startNewGame(args);
    }

    public boolean insertNumber(int row, int col, int value) {
        return service.insertNumber(row, col, value);
    }

    public boolean removeNumber(int row, int col) {
        return service.removeNumber(row, col);
    }

    public int[][] getBoard() {
        return service.getSudoku().getBoard();
    }

    public boolean[][] getFixed() {
        return service.getSudoku().getFixed();
    }

    public GameStatus getStatus() {
        return service.getStatus();
    }

    public boolean hasErrors() {
        return service.hasErrors();
    }
    public void clear() {
        service.clear();
    }

    public boolean finishGame() {
        return service.finishGame();
    }
}