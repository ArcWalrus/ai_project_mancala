public class Board implements Cloneable {
    private int rows;
    private int columns;
    private int startingSeeds;
    private int[][] board;
    private int currRow;//SHOULD ONLY BE 0 OR 1, 1 for Player Row, 0 for Computer Row
    private int h_value;
    //Constructor
    public Board(int rows, int columns, int currRow, int startingSeeds)
    {
        this.rows = rows;
        this.columns = columns;
        this.currRow = currRow;
        this.startingSeeds = startingSeeds;
        this.board = new int[this.rows][this.columns];
        this.h_value = 0;
    }

    public int getRows(){
        return this.rows;
    }
    public int getCols(){
        return this.columns;
    }
    public int getHoleValue(int row, int col){
        return board[row][col];
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
        System.out.print("\n\tComputer's Basket: " + board[0][0] + "\n");
        for (int jc = 1; jc < columns-1; jc++)
        {
            System.out.print("C" + jc + ": " + board[0][jc] + " ");
        }
        System.out.println();
        for (int jp = 1; jp < columns-1; jp++)
        {
            System.out.print("P" + jp + ": " + board[rows-1][jp] + " ");
        }
        System.out.println("\n\tPlayer's Basket: " + board[1][columns-1]);
    }

    /*
    playerMove(Hole, int) takes a hole choice and a currentPlayer. It then moves all the seeds
    out of the hole chosen, dropping one by one into the subsequent holes. When it encounters a basket,
    if it is the player's basket whose turn it is, it will drop one into the hole, otherwise it will skip it.
    It is intended to be called again and again using the last hole it drops a seed in, assuming
    there is more than one seed in the final hole. The function will return a hole for this purpose, and
    a hole that is (-1, -1) to tell makeMove() not to continue calling the function.
     */
    //FUNCTION STATUS: WORKING
    private Hole playerMove(Hole choice, int currPlayer)
    {
        int seedsInHand = board[choice.getPos1()][choice.getPos2()];
        board[choice.getPos1()][choice.getPos2()] = 0;
        //System.out.println("Seeds in Hand: " + seedsInHand);
        //moves to the next position, +1 if player side, -1 if computer side.
        if (choice.getPos1() == 1)
        {
            choice.incPos2();
        }
        else
        {
            choice.decPos2();
        }
        while (seedsInHand > 0)
        {
            if (choice.getPos1() == 1) //if the player row
            {
                //if the last column on the player side
                if (choice.getPos2() == columns-1)
                {
                    //if the current player is the User
                    if (currPlayer == 1) {
                        board[choice.getPos1()][choice.getPos2()]++; //add one to the user's basket
                        seedsInHand--; //subtract one from seeds in hand
                    }
                    if (seedsInHand > 0) {
                        //flip boardSide to computer side and continue
                        choice.decPos1();
                        choice.decPos2();
                    }

                    //board[choice.getPos1()][choice.getPos2()]++;
                }
                else {
                    board[choice.getPos1()][choice.getPos2()]++; //add one to the current hole
                    seedsInHand--; //subtract one from seeds in hand
                    if (seedsInHand > 0)
                    {
                        //go to the next hole if there are more seeds in hand
                        choice.incPos2();
                    }
                }


            }
            else //if the computer row
            {
                //if the first index
                if (choice.getPos2() == 0)
                {
                    //if the current player is the Computer
                    if (currPlayer == 0) {
                        board[choice.getPos1()][choice.getPos2()]++; //add one to computer basket
                        seedsInHand--; //subtract one from seeds in hand
                    }
                    if (seedsInHand > 0) {
                        //flip the boardSide to the player side and continue
                        choice.incPos1();
                        choice.incPos2();
                    }

                }
                else {
                    board[choice.getPos1()][choice.getPos2()]++;//add one to the current hole
                    seedsInHand--; //subtract one from seeds in hand
                    if (seedsInHand > 0) {
                        choice.decPos2(); //if there are seeds in hand, move to next hole on computer's side.
                    }
                }


            }
        }
        if (board[choice.getPos1()][choice.getPos2()] <= 1)
        {
            //System.out.println("RETURN -1");
            return new Hole(-1, -1); //tells makeMove() to not continue with "sowing"
        }
        else {
            //System.out.println(choice);
            return choice; //tells makeMove() where to continue with sowing
        }
    }

    /*
    makeMove(int, int) calls playerMove on the result of playerMove over and over until
    the player should stop moving.
     */
    public Board makeMove(int currPlayer, int choice){
        Hole result = playerMove(new Hole(currPlayer, choice), currPlayer);
        //while the result is valid and not a player basket.
        while (result.getPos1() > -1 && (result.getPos2() < columns-1 && result.getPos2() > 0))
        {
            result = playerMove(result, currPlayer);
        }
        //assignHVal();
        return this;
    }


    //assignHVal() sets the heuristic value of the board to number of seeds in CPU basket - Human basket
    private void assignHVal(){
        this.h_value = board[0][0] - board[1][columns-1];
    }

    public int getHValue(){
        return h_value;
    }
    //Returns true if there cannot be any more moves made.
    public boolean checkIfWon(){
        boolean finished = false;
        int cpuCount = 0;
        int playerCount = 0;
        for (int i = 1; i < columns-1; i++){
            cpuCount += board[0][i];
            playerCount += board[rows-1][i];
        }
        if (cpuCount <= 0 && playerCount > 0) //if cpu board state is 0 or less for whatever reason
        {
            finished = true;
            //board[rows-1][columns-1] += playerCount; //add the remaining seeds in player row to player basked
        }
        else if (playerCount <= 0 && cpuCount > 0) //if player board state is 0 or less for whatever reason
        {
            finished = true;
            //board[0][0] += cpuCount; //add the remaining seeds in the cpu row to the cpu basket
        }
        else if (playerCount <= 0 && cpuCount <= 0)
        {
            finished = true;
        }
        assignHVal();
        return finished;
    }

}
