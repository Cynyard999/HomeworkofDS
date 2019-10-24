package Chapter3_1;
//链表实现栈,以链表头为栈顶
public class SimpleStack<T> {
    int size = 0;
    class Node{
        Node next;
        T element;
        boolean hasNext(){
            return next!=null;
        }
        Node(){
            this(null,null);
        }
        Node(T o,Node n){
            element = o;
            next = n;
        }
    }
    Node stackTop;
    SimpleStack(){
        stackTop = null;
    }
    public void push(T o){
        stackTop = new Node(o,stackTop);
        size+=1;
    }
    public boolean isEmpty(){
        return this.stackTop==null;
    }
    public void printStack(){
        if (this.isEmpty()){
            //
        }
        else {
            while (!this.isEmpty()) {
                System.out.print(stackTop.element);
                this.pop();
            }
        }
    }
    public T pop(){
        if (isEmpty()){
            System.out.println("Empty Stack");
            return null;
        }
        Node temp = stackTop;
        stackTop = stackTop.next;
        size-=1;
        return temp.element;
    }
    public T getTop(){
        return stackTop.element;
    }



}
