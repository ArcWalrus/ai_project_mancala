public class MancalaDriver {
    private static int rows = 2;
    private static int columns = 12;
    private static int startingSeeds = 3;
    public static void main(String[] args)
    {
        int[][] board = new int[rows][columns];
        initializeBoard(board);
        displayBoardState(board);
    }

    /*initializeBoard takes the board and sets all the pieces.
    The board will start with three seeds in each hole, except for
    the player holes, which are designated as board[0][0] and
    board[0][columns-1]
     */
    private static void initializeBoard(int[][] board)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 1; j < columns - 1; j++)
            {
                board[i][j] = startingSeeds;
            }
        }
    }

    //displayBoardState will display the board state, and prompt the user for a move.
    private static void displayBoardState(int[][] board)
    {
        System.out.print("\tComputer's Basket: " + board[0][0] + "\n");
        for (int i = 0; i < rows; i++)
        {
            for (int j = 1; j < columns - 1; j++)
            {
                System.out.print("H" + j + ": " + board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("\n\tPlayer's Basket: " + board[0][columns-1]);
    }

}
