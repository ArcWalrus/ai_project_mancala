import java.util.ArrayList;

public class Node<T> {
    private T data;
    private ArrayList<Node<T>> children = new ArrayList<Node<T>>();
    private Node<T> parent = null;

    public Node(T data){
        this.data = data;
    }

    public ArrayList<Node<T>> getChildren(){
        return this.children;
    }

    public Node<T> getParent(){
        return this.parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public void setData(T data){
        this.data = data;
    }

    public T getData()
    {
        return this.data;
    }

    public void setChildren(ArrayList<Node<T>> children)
    {
        //Sets multiple nodes to the same parent
        for (int i = 0; i < children.size(); i++){
            children.get(i).setParent(this);
            this.children.add(children.get(i));
        }
    }

    public Node<T> addChild(Node<T> child)
    {
        child.setParent(this);
        this.children.add(child);
        return child;
    }


}
