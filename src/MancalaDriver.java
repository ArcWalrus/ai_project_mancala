import java.util.Scanner;

public class MancalaDriver {
    private static int rows = 2; //should always be 2
    private static int columns = 12; //columns must always be greater >= 2
    private static int startingSeeds = 3; //The amount of seeds to place in each hole to initialize the board

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Board board = new Board(rows, columns, 1, startingSeeds);
        board.initializeBoard();

        int currPlayer = 1; //1 for player, 0 for cpu

        boolean go = true;
        while (go) {
            board.displayBoardState();
            System.out.print("\n\tPlease enter a move to make (1 to 10)");
            String choice = in.next();
            try {
                int choiceInt = Integer.parseInt(choice);
                Hole result = board.playerMove(new Hole(currPlayer, choiceInt), currPlayer);
                //while the result is valid and not a player basket.
                while (result.getPos1() > -1 && (result.getPos2() < 11 && result.getPos2() > 0))
                {
                    result = board.playerMove(result, currPlayer);
                }


            } catch(NumberFormatException e){
                System.out.println("Exiting...");
                go = false;
            }
        }


    }



}
