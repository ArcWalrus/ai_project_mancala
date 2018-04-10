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

    //NOT WORKING PROBLEM IS HERE.... SOMEWHERE
    public Node<Board> genTree(Node<Board> currNode, int currPlayer, int depth) {
        System.out.println("genTree");
        /*if (currNode.getData().checkIfWon() == true){
            currNode.getData().displayBoardState();
            return null;
        }*/
        if (depth > 100) {
            currNode.getData().displayBoardState();
            return null;
        } else {
            int numCols = currNode.getData().getCols();
            for (int currHole = 1; currHole < numCols - 1; currHole++) {
                /*System.out.println("Current Hole: " + currHole);
                if (currNode.getData().getHoleValue(currPlayer, currHole) > 0) { //if there are 1 or more seeds in the hole
                    //System.out.println("Current Hole Value: " + currNode.getData().getHoleValue(currPlayer, currHole));
                    Node<Board> childBoard = new Node<>(currNode.getData().makeMove(currPlayer, currHole));
                    System.out.println("Current Player: " + currPlayer);
                    //currNode.getData().displayBoardState();
                    childBoard.getData().displayBoardState();
                    //System.out.println("Child H Value: " + child.getData().getHValue());
                    //currNode.addChild(child);
                    if (currPlayer == 1) {
                        currPlayer = 0;
                    } else {
                        currPlayer = 1;
                    }
                    if (childBoard != null) {
                        Node<Board> childTree = genTree(childBoard, currPlayer, depth);
                        currNode.addChild(childTree);

                    }
                    //childTree.addChild(genTree(child, currPlayer));
                }
            }
            */
                if (currNode.getData().getHoleValue(currPlayer, currHole) > 0) {
                    Node<Board> nextBoard = new Node(currNode.getData().makeMove(currPlayer, currHole));
                    nextBoard.getData().displayBoardState();
                    if (currPlayer == 1) {
                        currPlayer = 0;
                    } else {
                        currPlayer = 1;
                    }
                    depth++;
                    nextBoard = genTree(nextBoard, currPlayer, depth);
                    currNode.addChild(nextBoard);
                }

            }
            return currNode;
        }
    }
}
