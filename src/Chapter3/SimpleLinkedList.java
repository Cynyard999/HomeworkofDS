package Chapter3;


public class SimpleLinkedList {

    Node header;
    class Node{
        Object element;
        int coe;
        int exp;
        Node next;
        boolean hasNext(){
            return next!=null;
        }
        Node(Object a){
            this(a,null);
        }
        Node(Object a,Node next){
            element = a;
            this.next = next;
        }
        Node(int a, int b){
            coe = a;
            exp = b;
            this.next = null;
        }
    }
    public static void main(String[] args){
        SimpleLinkedList list = new SimpleLinkedList();
        for (int i = 0;i<10;i++){
            list.add(i);//自动封装
        }
        list.printList();
        list.reverse();
        list.printList();
    }
    public SimpleLinkedList(){
        header = new Node(null);
    }
    public SimpleLinkedList(Node a){
        header = new Node(null);
        header.next = a;
    }
    public void add(Object o){
        Node endNode = getEndNode();
        endNode.next = new Node(o);
    }
    public Node getEndNode(){
        Node endNode = header;
        while (endNode.next!=null){
            endNode = endNode.next;
        }
        return endNode;
    }
    public void printList(){
        Node current = header;
        while (current.next!=null){
            System.out.print(current.next.element.toString()+" ");
            current = current.next;
        }
        System.out.println();
    }

    public Node findPrevious(Object current){
        Node p = header;
        while (p.next!=null&&!p.next.element.equals(current)){
            p = p.next;
        }
        if (p.next==null){
            return null;//如果p已经指向最后一个节点了，标示着没有找到，返回null；
        }
        return p;
    }
    public void swap(Object a,Object b){
        Node p = findPrevious(a);
        Node temp = p.next;//a=temp
        p.next = p.next.next;//p.next>-b
        temp.next= p.next.next;//a.next->b.next
        p.next.next = temp;//b.next = a
    }

    public void reverse(){//只翻转数据节点
        Node p = this.header.next;//previous
        Node c = p.next;//current
        p.next = null;
        Node n;
        while (c.next!=null){
            n = c.next;//next
            c.next = p;
            p = c;
            c = n;
        }
        c.next = p;
        header.next = c;
    }
    public SimpleLinkedList sum(SimpleLinkedList a,SimpleLinkedList b){
        Node pa = a.header.next;
        Node pb = b.header.next;
        Node pc = new Node(null);//定义为头节点,本身存的是之前运算的结果，指向的是将要运算出的结果
        while (pb!=null&&pa!=null) {
            if (pa.exp == pb.exp) {
                pa.coe += pb.coe;
                if (pa.coe == 0) {
                    pa = pa.next;
                    pb = pb.next;
                }
                else {
                    pc.next =pa;
                    pc = pa;
                    pa = pa.next;
                    pb = pb.next;
                }
            }
            else {
                if (pa.exp>pb.exp){
                    pc.next = pa;
                    pc = pa;
                    pa = pa.next;
                }
                else {
                    pc.next = pb;
                    pc = pb;
                    pb = pb.next;
                }
            }
        }
        if (pb==null){
            pc.next = pa;
        }
        if (pa==null){
            pc.next = pb;
        }
        return new SimpleLinkedList(pc.next);
    }
}
