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

        boolean go = true;
        while (go) {
            board.displayBoardState();
            int currRow = 1; //should be set to 1 for player row, 0 for computer row.
            System.out.print("\n\tPlease enter a move to make (1 to 10)");
            String choice = in.next();
            try {
                int choiceInt = Integer.parseInt(choice);
                Hole result = board.playerMove(new Hole(1, choiceInt));
                while (result.getPos1() > 0)
                {
                    result = board.playerMove(new Hole(currRow, result.getPos2()));
                }



            } catch(NumberFormatException e){
                System.out.println("Exiting...");
                go = false;
            }
        }


    }



}
