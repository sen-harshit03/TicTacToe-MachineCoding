package entity;

public class Board {
    private static String[][] board;

    private Board() {

    }

    public static String[][] getBoard() {
        if(board == null){

            synchronized (Board.class) {
                if(board == null) {
                    board = new String[3][3];
                }
            }
        }
        return board;
    }

    public static void initializeBoard() {
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j<board[0].length; j++) {
                board[i][j] = "-";
            }
        }
    }

    public static void printBoard() {
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j<board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
