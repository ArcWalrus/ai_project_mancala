import java.util.Scanner;

public class MancalaDriver {
    private static final int rows = 2; //should always be 2
    private static final int columns = 12; //columns must always be greater >= 2
    private static final int startingSeeds = 3; //The amount of seeds to place in each hole to initialize the board

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Board board = new Board(rows, columns, 1, startingSeeds);
        board.initializeBoard();

        int currPlayer = 1; //1 for player, 0 for cpu

        Board board2 = new Board(rows, columns, 1, startingSeeds);
        board2.initializeBoard();
        Node<Board> n1 = new Node(board2);
        Tree tree = new Tree(n1, currPlayer);
        n1 = tree.genTree(n1, currPlayer);

        boolean go = true;
        while (go) {
            board.displayBoardState();

            System.out.print("\n\tPlease enter a move to make (1 to 10)");
            String choice = in.next();
            try {
                int choiceInt = Integer.parseInt(choice);
                board.makeMove(currPlayer, choiceInt);
                //System.out.println("Current h value: " + board.getHValue());



            } catch(NumberFormatException e){
                System.out.println("Exiting...");
                go = false;
            }
        }


    }



}
