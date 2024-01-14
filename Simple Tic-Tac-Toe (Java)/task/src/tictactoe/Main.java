package tictactoe;

import javax.management.StringValueExp;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
//
public static void printGrid(char[][] grid) {
    System.out.println("---------");
    for (char[] row : grid) {
        System.out.print("| ");
        for (char cell : row) {
            System.out.print(cell + " ");
        }
        System.out.println("|");
    }
    System.out.println("---------");
}

    public static boolean isCellOccupied(char[][] grid, int row, int col) {
        return grid[row][col] != ' ';
    }

    public static boolean checkWinner(char[][] grid, char player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if ((grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) ||
                    (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player)) {
                return true;
            }
        }

        if ((grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) ||
                (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player)) {
            return true;
        }

        return false;
    }

    public static boolean isGridFull(char[][] grid) {
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize an empty grid
        char[][] grid = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        char currentPlayer = 'X';

        printGrid(grid);

        // Game loop
        while (true) {
            // Get user input for the move
            int row, col;

            while (true) {
                try {
                    System.out.print("Enter the coordinates (row col): ");
                    row = scanner.nextInt() - 1;
                    col = scanner.nextInt() - 1;

                    if (row < 0 || row > 2 || col < 0 || col > 2) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        continue;
                    }

                    if (isCellOccupied(grid, row, col)) {
                        System.out.println("This cell is occupied! Choose another one!");
                        continue;
                    }

                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine(); // Consume the invalid input
                }
            }

            // Make the move
            grid[row][col] = currentPlayer;

            // Print the updated grid
            printGrid(grid);

            // Check for a winner
            if (checkWinner(grid, currentPlayer)) {
                System.out.println(currentPlayer + " wins");
                break;
            }

            // Check for a draw
            if (isGridFull(grid)) {
                System.out.println("Draw");
                break;
            }

            // Switch to the other player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }
}
