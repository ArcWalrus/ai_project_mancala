import java.util.ArrayList;

public class Tree {
    private Node<Board> head;
    //private Node<Board> currNode;
    //private int currPlayer;

    public Tree(Node<Board> currNode){
        //this.head = genTree(currNode, currPlayer, 1);
        this.head = currNode;
        //this.currNode = currNode;
        //this.currPlayer = currPlayer;
    }

    public int minmax(Board actualBoard, int currPlayer, int depth) {
        if (actualBoard.checkIfWon() >= 0 || depth <= 0) {
            //System.out.println("Leaf value: " + currNode.getData().getHValue());
            //System.out.println("Leaf HValue: " + + actualBoard.getHValue());
            return actualBoard.getHValue();
        }
        int value = -100;
        //int numCols = currNode.getData().getCols();
        int numCols = actualBoard.getCols();
        int bestValue = -100;
        int bestHole = -100;
       // ArrayList<String> hValues = new ArrayList<>();
        for (int currHole = 1; currHole < numCols - 1; currHole++) {
            //if (currNode.getData().getHoleValue(currPlayer, currHole) > 0) {


            if (actualBoard.getHoleValue(currPlayer, currHole) > 0){

                //Node<Board> move = new Node<>(currNode.getData().makeMove(currPlayer, currHole));
                Board move = new Board(actualBoard.getRows(), actualBoard.getCols(),
                                        actualBoard.getCurrRow(), actualBoard.getStartingSeeds());

//                for(int i=0; i<childHValues.size(); i++) {
//                    hValues.add(childHValues.get(i));
//                }

                move = move.copyBoard(actualBoard);
                move.makeMove(currPlayer, currHole);
                if (currPlayer == 1) {
                    currPlayer = 0;
                } else {
                    currPlayer = 1;
                }
                //System.out.println("minmax");
                //value = minmax(actualBoard, move, currPlayer, depth - 1);
                //System.out.println("RUNNING MINIMAX");
                value = minmax(move, currPlayer, depth-1);
                //System.out.println("MINIMAX FINISHED");

                MancalaDriver.hValues.set(currHole-1,Integer.toString(value));

                if(currPlayer==0) {
                    if (value > bestValue) {
                        bestValue = value;
                        bestHole = currHole;
                    }
                }
                else {
                    if (value < bestValue) {
                        bestValue = value;
                        bestHole = currHole;
                    }
                }
            }
            else {
                MancalaDriver.hValues.set(currHole-1, "X");
            }
        }
        if (currPlayer == 0) {
            actualBoard.makeMove(currPlayer, bestHole);
            MancalaDriver.hValues.set(MancalaDriver.hValues.size()-1,Integer.toString(bestHole));
        }
        return bestValue;
    }



    //There might be a problem, there might not, God only knows.
    /*public Node<Board> genTree(Node<Board> currNode, int currPlayer) {
        System.out.println("genTree");
        if (currNode.getData().checkIfWon() >= 0) {
            currNode.getData().displayBoardState();
            return currNode;
        } else {
            int numCols = currNode.getData().getCols();
            for (int currHole = 1; currHole < numCols - 1; currHole++) {
                if (currNode.getData().getHoleValue(currPlayer, currHole) > 0) {
                    Node<Board> nextBoard = new Node(currNode.getData().makeMove(currPlayer, currHole));
                    //nextBoard.getData().displayBoardState();
                    if (currPlayer == 1) {
                        currPlayer = 0;
                    } else {
                        currPlayer = 1;
                    }

                    if (nextBoard != null) {
                        nextBoard = genTree(nextBoard, currPlayer);
                    }
                    currNode.addChild(nextBoard);
                }

            }
            return currNode;
        }
    }
    */
}
