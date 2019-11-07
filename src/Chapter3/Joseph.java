package Chapter3;

import java.util.ArrayList;

public class Joseph {
    public static void main(String [] args){
        System.out.println(new Joseph().solution1(14,3));
        //System.out.println(new Joseph().solution2(14,3));
    }

    public int solution1(int n, int m){//链表
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        for (int i=1;i<=n;i++){
            circularLinkedList.add(i);
        }
        CircularLinkedList.Node previous = circularLinkedList.findTail();
        CircularLinkedList.Node head;
        CircularLinkedList.Node tail = null;
        for (int i=1;i<n;i++){//淘汰n-1个人
            for (int j=1;j<=m-1;j++){
                previous = previous.next;//将被淘汰的人的前一个人
            }
            if (i==1){
                head = previous.next;
                tail = head;
            }
            else {
                tail.next = previous.next;//当前的tail指向即将被淘汰m的节点，
                tail = previous.next;//tail前进
            }
            previous.next = previous.next.next;//跳过被淘汰的值
        }
        return (Integer) previous.element;
    }
    public int solution2(int n,int m){//数组实现
        ArrayList<Boolean> list1 = new ArrayList<>();
        for (int i=1;i<=n;i++){
            list1.add(true);
        }
        int left = n;
        int count =0;//当前报数
        int i = 0;//index
        while (left>1){
            if(list1.get(i)){
                count+=1;
                if (count==m){
                    list1.set(i,false);
                    left-=1;
                    count = 0;
                }
            }
            i++;//之前指向已经被淘汰的人，淘汰过后，i++
            if (i==n){
                i=0;
            }
        }
        return list1.indexOf(true)+1;
    }

}
