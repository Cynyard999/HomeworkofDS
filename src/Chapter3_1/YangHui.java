package Chapter3_1;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class YangHui {
    public static void print(int n){
        int current;//被打印的值
        int former = 0;//用一个值来存储之前已经打印出的值
        SimpleQueue queue = new SimpleQueue();
        queue.enqueue(1);
        queue.enqueue(1);
        for (int i= 1;i<=n;i++){
            for (int k =1;k<=n-i;k++){//打印空格
                System.out.print(" ");
            }
            queue.enqueue(0);//用来计算下一行的最后一个1，和下下行的第一个1
            //举个例子：队列中的数依次为11 0 121 0 1331 0 14641 0
            for (int j=1;j<=i+2;j++){//下一行比这一行多一个数，又加上一个0，存储下一行
                current = (int)queue.dequeue();
                queue.enqueue(former+current);//key：开始打印这一行的同时，开始存储下一行的值
                if (j!=i+2){//current不为0的时候打印
                    System.out.print(current+" ");
                }
                former = current;
            }
            System.out.println();
        }
    }
}
