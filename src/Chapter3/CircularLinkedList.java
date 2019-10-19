package Chapter3;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class CircularLinkedList {//不带头节点
    Node first;
    class Node{
        Object element;
        Node next;
        Node(Object a){
            this(a,first);
        }
        Node(Object a,Node next){
            this.element = a;
            this.next = next;
        }
    }
    public Node findTail(){
        Node tail = first;
        while (tail.next!=first){
            tail = tail.next;
        }
        return tail;
    }
    public boolean isEmpty(){
        return first==null;
    }
    public void add(Object o){
        if (isEmpty()){
            first = new Node(o);
            first.next = first;
            return;//重要啊重要
        }
        Node tail = findTail();
        tail.next = new Node(o);
        tail.next.next = first;//尾部增加一个节点，并且让这个节点指向第一个节点
    }


}
