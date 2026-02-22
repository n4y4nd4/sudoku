package view;

import controller.SudokuController;

import javax.swing.*;
import java.awt.*;

public class SudokuGUI {

    private SudokuController controller;
    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] cells;
    private boolean removeMode = false;

    public SudokuGUI(String[] args) {
        controller = new SudokuController();
        controller.startNewGame(args);
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sudoku");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        createMenu();
        createBoard();

        frame.setVisible(true);
    }

    private void createMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(1, 7));

        JButton newGame = new JButton("Novo");
        JButton remove = new JButton("Remover");
        JButton status = new JButton("Status");
        JButton clear = new JButton("Limpar");
        JButton finish = new JButton("Finalizar");

        newGame.addActionListener(e -> {
            removeMode = false;
            frame.dispose();
            new SudokuGUI(new String[]{});
        });

        remove.addActionListener(e -> {
            removeMode = !removeMode;
            JOptionPane.showMessageDialog(frame,
                    removeMode ? "Modo remoção ativado" : "Modo inserção ativado");
        });

        status.addActionListener(e -> {
            boolean errors = controller.hasErrors();
            JOptionPane.showMessageDialog(frame,
                    "Status: " + controller.getStatus() +
                            (errors ? " (com erros)" : " (sem erros)"));
        });

        clear.addActionListener(e -> {
            controller.clear();
            updateBoard();
        });

        finish.addActionListener(e -> {
            boolean ok = controller.finishGame();
            JOptionPane.showMessageDialog(frame,
                    ok ? "Jogo finalizado com sucesso!" : "Preencha corretamente!");
        });

        menu.add(newGame);
        menu.add(remove);
        menu.add(status);
        menu.add(clear);
        menu.add(finish);

        frame.add(menu, BorderLayout.NORTH);
    }

    private void createBoard() {
        boardPanel = new JPanel(new GridLayout(9, 9));
        cells = new JButton[9][9];

        int[][] board = controller.getBoard();
        boolean[][] fixed = controller.getFixed();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JButton cell = new JButton();
                cell.setFont(new Font("Arial", Font.BOLD, 20));

                if (board[i][j] != 0) {
                    cell.setText(String.valueOf(board[i][j]));
                }

                if (fixed[i][j]) {
                    cell.setBackground(Color.CYAN);
                    cell.setEnabled(false);
                }

                int row = i;
                int col = j;

                int top = (i % 3 == 0) ? 3 : 1;
                int left = (j % 3 == 0) ? 3 : 1;
                int bottom = (i == 8) ? 3 : 1;
                int right = (j == 8) ? 3 : 1;

                cell.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                cell.addActionListener(e -> handleCellClick(row, col));

                cells[i][j] = cell;
                boardPanel.add(cell);
            }
        }

        frame.add(boardPanel, BorderLayout.CENTER);
    }

    private void handleCellClick(int row, int col) {

        if (removeMode) {
            boolean removed = controller.removeNumber(row, col);
            if (!removed) {
                JOptionPane.showMessageDialog(frame, "Não pode remover!");
            }
        } else {
            String input = JOptionPane.showInputDialog(frame, "Número:");
            if (input != null) {
                try {
                    int value = Integer.parseInt(input);
                    boolean ok = controller.insertNumber(row, col, value);
                    if (!ok) {
                        JOptionPane.showMessageDialog(frame, "Posição ocupada!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Valor inválido!");
                }
            }
        }

        updateBoard();
    }

    private void updateBoard() {
        int[][] board = controller.getBoard();
        boolean[][] fixed = controller.getFixed();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    cells[i][j].setText("");
                } else {
                    cells[i][j].setText(String.valueOf(board[i][j]));
                }

                if (!fixed[i][j]) {
                    cells[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}