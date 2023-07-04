import entity.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<String> players = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String cross = sc.next();
        String player1 = sc.next();

        String circle = sc.next();
        String player2 = sc.next();

        String[][] board = Board.getBoard();
        Board.initializeBoard();
        Board.printBoard();



        int player = -1;          // Player = 0 - player1 (X)     1 - player2(O)
        int moveCount = 0;
        do {
            player++;
            if(player >= 2) {
                player = 0;
            }

            int row = sc.nextInt();
            int col = sc.nextInt();

            if(isInValidCoordinate(row, col, board)) {
                System.out.println("Invalid move, index out of bound");
                continue;
            }

            if(isInvalidMove(board, row, col)) {
                System.out.println("Invalid move");
                row = sc.nextInt();
                col = sc.nextInt();
            }


            String symbol = getSymbol(player);
            board[row-1][col-1] = symbol;
            ++moveCount;
            Board.printBoard();

            // Check for win
            if(checkForWin(board, row, col, symbol)) {
                System.out.println("player" + player + " wins");
                break;
            }


        } while (!isGameOver(moveCount));

    }

    private static boolean isGameOver(int moveCount) {
        System.out.println(moveCount);
        if(moveCount == 9) {
            return true;
        }
        return false;
    }

    private static String getSymbol(int player) {
        if(player == 0) {
            return "X";
        }else{
            return "O";
        }
    }

    private static boolean isInValidCoordinate(int row, int col, String[][] board) {
        return row > 3 || col > 3;
    }

    private static boolean checkForWin(String[][] board, int row, int col, String symbol) {
        int horizontalCount = 0;
        int verticalCount = 0;
        int leftDiagonalCount = 0;
        int rightDiagonalCount = 0;

        // horizontal
        for(int i = 0; i<board.length; i++) {
            if(Objects.equals(board[row-1][i], symbol)) {
                horizontalCount++;
            }
        }

        for(int i = 0; i<board.length; i++) {
            if(Objects.equals(board[i][col-1], symbol)) {
                verticalCount++;
            }
        }

        for(int i = 0, j=0; i<board.length && j<board.length; i++, j++) {
            if(Objects.equals(board[i][j], symbol)) {
                leftDiagonalCount++;
            }
        }

        for(int i = board.length-1, j=board.length-1; i>=0 && j>=0; i--, j--) {
            if(Objects.equals(board[i][j], symbol)) {
                rightDiagonalCount++;
            }
        }
        return horizontalCount == 3 || verticalCount == 3 || leftDiagonalCount == 3 || rightDiagonalCount == 3;
    }


    private static boolean isInvalidMove(String[][] board, int row, int col) {
        if(!Objects.equals(board[row-1][col-1], "-")) {
            return true;
        }
        return false;
    }
}