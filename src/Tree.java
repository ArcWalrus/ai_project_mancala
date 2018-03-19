public class Tree {
    private Node<Board> head;
    private Node<Board> currNode;

    public Tree(Node<Board> head){
        this.head = head;
        this.currNode = head;
    }

    //NOT WORKING
    public void genTree(Node<Board> currNode, int currPlayer){
        System.out.println("genTree");
        if (currNode.getData().checkIfWon() == true){
            currNode.getData().displayBoardState();
            //return currNode;
        }
        else {
            int numCols = currNode.getData().getCols();
            for (int currHole = 1; currHole < numCols - 1; currHole++) {
                System.out.println("Current Hole: " + currHole);
                if (currNode.getData().getHoleValue(currPlayer, currHole) > 0) {
                    System.out.println("Current Hole Value: " + currNode.getData().getHoleValue(currPlayer, currHole));
                    Node<Board> child = new Node<>(currNode.getData().makeMove(currPlayer, currHole));
                    System.out.println("Current Player: " + currPlayer);
                    currNode.getData().displayBoardState();
                    //System.out.println("Child H Value: " + child.getData().getHValue());
                    currNode.addChild(child);
                    if (currPlayer == 1) {
                        currPlayer = 0;
                    } else {
                        currPlayer = 1;
                    }
                    genTree(child, currPlayer);
                    //child.addChild(genTree(child, currPlayer));
                }
            }
        }
        //return null;
    }
}
