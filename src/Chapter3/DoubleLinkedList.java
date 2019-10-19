package Chapter3;


public class DoubleLinkedList {
    Node header;
    class Node{
        Node left;
        Node right;
        Object element;
        Node(Object o){
            this(o,null,null);
        }
        Node(Object o, Node previous, Node next ){
            this.element = o;
            this.left = previous;
            this.right = next;
        }
    }
    public DoubleLinkedList(){
        header = new Node(null);
    }
    public Node find(Object o){
        Node current = header;
        while (current.right!=null&&!current.right.element.equals(o)){
            Node temp = current;
            current = current.right;
            current.left = temp;
        }
        return current;
    }
    public void adjacentSwap(Object a,Object b){//a and b is adjacent,p a b c
        Node temp = find(a);
        temp.left.right = temp.right;//p->b
        temp.right.left = temp.left;//完成p<-b
        temp.left = temp.right;//b<-a
        temp.right = temp.right.right;//a->c
        temp.left.right = temp;//b->a
        temp.right.left = temp;//b<-c
    }
}
