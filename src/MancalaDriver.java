import java.util.Scanner;

public class MancalaDriver {
    private static int rows = 2; //should always be 2
    private static int columns = 12; //columns must always be greater >= 2
    private static int startingSeeds = 3; //The amount of seeds to place in each hole to initialize the board

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int[][] board = new int[rows][columns];
        initializeBoard(board);

        boolean go = true;
        while (go) {
            displayBoardState(board);
            int currRow = 1; //should be set to 1 for player row, 0 for computer row.
            System.out.print("\n\tPlease enter a move to make (1 to 10)");
            String choice = in.next();
            try {
                int choiceInt = Integer.parseInt(choice);
                Hole result = playerMove(new Hole(1, choiceInt), currRow, board);
                while (result.getPos1() > 0)
                {
                    result = playerMove(result, currRow, board);
                }



            } catch(NumberFormatException e){
                System.out.println("Exiting...");
                go = false;
            }
        }


    }

    /*
    initializeBoard(..) takes the board and sets all the pieces.
    The board will start with three seeds in each hole, except for
    the player holes, which are designated as board[0][0] and
    board[0][columns-1]
    FUNCTION STATUS: WORKING AS INTENDED
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
    //  expects the board to be 2 rows.
    //FUNCTION STATUS: WORKING AS INTENDED
    private static void displayBoardState(int[][] board)
    {
        System.out.print("\tComputer's Basket: " + board[0][0] + "\n");
        for (int jc = 1; jc < columns-1; jc++)
        {
            System.out.print("C" + jc + ": " + board[0][jc] + " ");
        }
        System.out.println();
        for (int jp = 1; jp < columns-1; jp++)
        {
            System.out.print("P" + jp + ": " + board[rows-1][jp] + " ");
        }
        System.out.print("\n\tPlayer's Basket: " + board[0][columns-1]);
    }

    private static Hole playerMove(Hole choice, int currRow, int[][] board)
    {
        boolean shouldMoveAgain = false;

        int seedsInHand = board[rows-1][choice.getPos2()];
        board[rows-1][choice.getPos2()] = 0;
        while (seedsInHand > 0)
        {
            if (currRow == 1) //if the player row
            {
                choice.incPos2();
                board[rows - 1][choice.getPos2()]++;
                seedsInHand--;
                if (choice.getPos2() >= columns - 1)
                {
                    //flip boardSide and continue
                    currRow--;
                    board[0][choice.getPos2()]++;
                }
            }
            else //if the computer row
            {
                choice.decPos2();
                board[0][choice.getPos2()]++;
                seedsInHand--;
                if (choice.getPos2() <= 1)
                {
                    //flip the boardSide and continue
                    currRow++;
                    board[rows-1][choice.getPos2()]++;
                }
            }
        }
        if (board[currRow][choice.getPos2()] <= 0)
        {
            return new Hole(-1, -1);
        }
        return new Hole(currRow, choice.getPos2());
    }


}
