package Chapter3_1;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class YangHui {
    public static void print(int n){
        int current;
        int former = 0;//用一个值来存储之前已经打印出的值
        SimpleQueue queue = new SimpleQueue();
        queue.enqueue(1);
        queue.enqueue(1);
        for (int i= 1;i<=n;i++){
            for (int k =1;k<=n-i;k++){//打印空格
                System.out.print(" ");
            }
            queue.enqueue(0);//开始打印这一行的同时，开始存储下一行的值
            for (int j=1;j<=i+2;j++){//下一行比这一行多一个数，又加上一个0
                current = (int)queue.dequeue();
                queue.enqueue(former+current);
                if (j!=i+2){//current不为0的时候打印
                    System.out.print(current+" ");
                }
                former = current;
            }
            System.out.println();
        }
    }
}
