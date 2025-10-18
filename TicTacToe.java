import java.util.Scanner;
public class TicTacToe {
    private static char[][] board;
    private static int size;
    private static String player1, player2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Welcome to TicTacToe Game!");
        System.out.print("Enter Player 1 name (X): ");
        player1 = sc.nextLine();
        System.out.print("Enter Player 2 name (O): ");
        player2 = sc.nextLine();
        System.out.print("Enter board size (e.g., 3 for 3x3): ");
        size = sc.nextInt();
        board = new char[size][size];
        initializeBoard();
        char currentPlayerSymbol = 'X';
        String currentPlayerName = player1;
        while (true) {
            printBoard();
            System.out.println(currentPlayerName + "'s turn (" + currentPlayerSymbol + ")");
            int row, col;
            while (true) {
                System.out.print("Enter your move (row and column): ");
                row = sc.nextInt();
                col = sc.nextInt();
                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayerSymbol;
                    break;
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }
            if (hasWon(currentPlayerSymbol)) {
                printBoard();
                System.out.println("Congratulations, " + currentPlayerName + " wins!");
                break;
            }

            if (isDraw()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            // Switch players
            if (currentPlayerSymbol == 'X') {
                currentPlayerSymbol = 'O';
                currentPlayerName = player2;
            } else {
                currentPlayerSymbol = 'X';
                currentPlayerName = player1;
            }
        }

        sc.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("\nCurrent Board:");

        // Print column indexes
        System.out.print("    ");
        for (int j = 0; j < size; j++) {
            System.out.printf(" %2d  ", j);
        }
        System.out.println();

        // Top border
        System.out.print("   +");
        for (int j = 0; j < size; j++) {
            System.out.print("----+");
        }
        System.out.println();

        // Rows with indexes
        for (int i = 0; i < size; i++) {
            System.out.printf(" %2d ", i);
            for (int j = 0; j < size; j++) {
                System.out.printf("| %c  ", board[i][j]);
            }
            System.out.println("|");

            // Horizontal border line after each row
            System.out.print("   +");
            for (int j = 0; j < size; j++) {
                System.out.print("----+");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && board[row][col] == ' ';
    }

    private static boolean hasWon(char symbol) {
        // Check rows
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (board[i][j] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Check columns
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (board[j][i] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Check diagonals
        boolean win = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i] != symbol) {
                win = false;
                break;
            }
        }
        if (win) return true;

        win = true;
        for (int i = 0; i < size; i++) {
            if (board[i][size - 1 - i] != symbol) {
                win = false;
                break;
            }
        }
        return win;
    }

    private static boolean isDraw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

}
