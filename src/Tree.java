public class Tree {
    private Node<Board> head;
    private Node<Board> currNode;
    private int currPlayer;

    public Tree(Node<Board> currNode, int currPlayer){
        //this.head = genTree(currNode, currPlayer, 1);
        this.head = currNode;
        this.currNode = currNode;
        this.currPlayer = currPlayer;
    }

    //There might be a problem, there might not, God only knows.
    public Node<Board> genTree(Node<Board> currNode, int currPlayer) {
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
}
