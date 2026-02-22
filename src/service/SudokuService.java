package service;

import model.Sudoku;
import model.GameStatus;

public class SudokuService {

    private Sudoku sudoku;

    public SudokuService() {
        this.sudoku = new Sudoku();
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public void startNewGame(String[] args) {
        sudoku = new Sudoku();

        for (String arg : args) {
            String[] parts = arg.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            int value = Integer.parseInt(parts[2]);

            sudoku.getBoard()[row][col] = value;
            sudoku.getFixed()[row][col] = true;
        }

        sudoku.setStatus(GameStatus.INCOMPLETE);
    }

    public boolean insertNumber(int row, int col, int value) {
        if (sudoku.getBoard()[row][col] != 0) {
            return false;
        }

        sudoku.getBoard()[row][col] = value;
        updateStatus();
        return true;
    }

    public boolean removeNumber(int row, int col) {
        if (sudoku.getFixed()[row][col]) {
            return false;
        }

        sudoku.getBoard()[row][col] = 0;
        updateStatus();
        return true;
    }


    public void clear() {
        int[][] board = sudoku.getBoard();
        boolean[][] fixed = sudoku.getFixed();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!fixed[i][j]) {
                    board[i][j] = 0;
                }
            }
        }

        sudoku.setStatus(GameStatus.INCOMPLETE);
    }

    public boolean hasErrors() {
        int[][] board = sudoku.getBoard();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = board[i][j];
                if (value != 0) {
                    board[i][j] = 0;
                    if (!isValid(i, j, value)) {
                        board[i][j] = value;
                        return true;
                    }
                    board[i][j] = value;
                }
            }
        }

        return false;
    }

    public GameStatus getStatus() {
        return sudoku.getStatus();
    }

    public boolean finishGame() {
        if (hasErrors()) {
            return false;
        }

        int[][] board = sudoku.getBoard();
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }

        sudoku.setStatus(GameStatus.COMPLETE);
        return true;
    }


    private void updateStatus() {
        if (isBoardEmpty()) {
            sudoku.setStatus(GameStatus.NOT_STARTED);
        } else {
            sudoku.setStatus(GameStatus.INCOMPLETE);
        }
    }

    private boolean isBoardEmpty() {
        for (int[] row : sudoku.getBoard()) {
            for (int cell : row) {
                if (cell != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, int value) {
        return !existsInRow(row, value)
                && !existsInColumn(col, value)
                && !existsInBox(row, col, value);
    }

    private boolean existsInRow(int row, int value) {
        for (int col = 0; col < 9; col++) {
            if (sudoku.getBoard()[row][col] == value) {
                return true;
            }
        }
        return false;
    }

    private boolean existsInColumn(int col, int value) {
        for (int row = 0; row < 9; row++) {
            if (sudoku.getBoard()[row][col] == value) {
                return true;
            }
        }
        return false;
    }

    private boolean existsInBox(int row, int col, int value) {
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (sudoku.getBoard()[i][j] == value) {
                    return true;
                }
            }
        }

        return false;
    }
}