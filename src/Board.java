public class Board {
    private int rows;
    private int columns;
    private int startingSeeds;
    private int[][] board;
    private int currRow;//SHOULD ONLY BE 0 OR 1, 1 for Player Row, 0 for Computer Row
    //Constructor
    public Board(int rows, int columns, int currRow, int startingSeeds)
    {
        this.rows = rows;
        this.columns = columns;
        this.currRow = currRow;
        this.startingSeeds = startingSeeds;
        this.board = new int[this.rows][this.columns];
    }

    /*
    initializeBoard(..) takes the board and sets all the pieces.
    The board will start with three seeds in each hole, except for
    the player holes, which are designated as board[0][0] and
    board[0][columns-1]
    FUNCTION STATUS: WORKING AS INTENDED
     */
    public void initializeBoard()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 1; j < columns - 1; j++)
            {
                board[i][j] = startingSeeds;
            }
        }
    }

    /*displayBoardState will display the board state via print, and prompt the user for a move.
      expects the board to be 2 rows.
      FUNCTION STATUS: WORKING AS INTENDED
    */
    public void displayBoardState()
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

    public Hole playerMove(Hole choice)
    {
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
            return new Hole(-1, -1); //tells the driver to not continue with "sowing"
        }
        return new Hole(currRow, choice.getPos2());
    }

}
