package Chapter4_0;

import Chapter3_1.SimpleQueue;

public class SimpleBin_Tree<T> {
    private Node root;
    class Node <T>{
        public Node(T data){
            this.element = data;
        }
        public Node(){
            this(null);
        }

        Node left;
        Node right;
        T element;
        public void setElement(T element) {
            this.element = element;
        }
    }
    public SimpleBin_Tree(){
        this.root = new Node();
    }
    public SimpleBin_Tree(Node t){
        this.root = t;
    }
    public void LevelcreateTree(T[] ts){//层次遍历构建树
        if (ts.length<=1){
            System.out.println("Wrong input");
        }
        root.element = ts[0];
        SimpleQueue queue = new SimpleQueue();
        queue.enqueue(root);
        int i = 1;
        while (true){
            Node temp = (Node) queue.dequeue();
            temp.left = new Node(ts[i]);
            queue.enqueue(temp.left);
            i++;
            if (i==ts.length){
                break;
            }
            temp.right = new Node(ts[i]);
            queue.enqueue(temp.right);
            i++;
            if (i==ts.length){
                break;
            }
        }
    }


}
