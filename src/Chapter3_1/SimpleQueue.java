package Chapter3_1;

public class SimpleQueue {//数组实现
    private Object [] array;
    static final int MAX_CAPACITY = 100;
    public SimpleQueue(int capacity){
        array = new Object[capacity] ;
        front = 0;
        currentSize = 0;
        back = -1;
    }
    public SimpleQueue(){
        this(MAX_CAPACITY);
    }
    int front;
    int back;
    int currentSize;
    public void makeEmpty(){
        front = 0;
        back = -1;
        currentSize = 0;
    }
    public boolean isEmpty(){
        return currentSize==0;
    }
    public boolean isFull(){
        return currentSize== array.length;
    }
    public void enqueue(Object o) {
        if (!isFull()) {
            back = increment(back);
            array[back] = o;
            currentSize += 1;
        }
        else System.out.println("The queue is full");
    }
    public Object dequeue(){//出队列
        if (isEmpty()){
            return null;
        }
        currentSize--;
        Object temp = array[front];
        array[front] = null;
        front = increment(front);
        return temp;
    }

    private int increment(int a){//循环结构
        if (++a == array.length){
            a=0;
        }
        return a;//在括号中已经加了1
    }


}
