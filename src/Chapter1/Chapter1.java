package Chapter1;


public class Chapter1 {
    public static void main(String[] args) {
        Chapter1 chapter1 = new Chapter1();
        chapter1.hanoid(4);
        //float x = chapter1.average(new int[]{1,2,3,4,5,32,12});
        //chapter1.permution(5,2);
    }

    public static int method1(int i){
        if (i==0){
            return 0;
        }
        else if (i%2==0){
            return method1(i/2);
        }
        else {
            return method1(i/2)+1;
        }
    }//Ex1计算一个数的二进制编码内的1的数量

    public  void permute(String str){
        permute(str.toCharArray(),0,str.length());
    }//Ex2两个permute方法打印出一个字符串所有排列
    private static void permute(char[] str,int low,int high){
        if (low==high){
            return;
        }
        if (low==high-1){
            System.out.println(String.valueOf(str));
        }
        else {
            for (int i = low; i < high; i++) {
                char temp = str[low];
                str[low] = str[i];
                str[i] = temp;
                //System.out.println("Changed str is"+String.valueOf(str));
                permute(str, low +1, high);
                temp = str[low];
                str[low] = str[i];
                str[i] = temp;
            }
        }

    }//Ex2

    public int maxnum(int[] array){
        return maxnum(array,0);
    }//Ex3.1计算数组内最大的元素
    public int maxnum(int[] array,int index){
        if (index==array.length-1){
            return array[index];
        }
        else
            return Math.max(array[index],maxnum(array,index+1));
    }//Ex3.1

    public float average(int[] array){
        return average(array,array.length);
    }//Ex3.2 计算n个整数的平均值
    public float average(int[] array,int n){
        if (n==1){
            return array[n-1];
        }
        else
            return (array[n-1]+(average(array,n-1))*(n-1))/n;
    }//Ex3.2

    private class Simplelinkedlist<T>{
        Node<T> head;
        class Node<T>{
            Node<T> next;
            T item;
            Node(T incoming){
                item = incoming;
            }
            Node<T> getNext() {
                return next;
            }
            public T getItem() {
                return item;
            }
        }
        public void add(T item){
            Node<T> last=head;
            while (last.getNext()!=null){
                last = last.getNext();
            }
            Node<T> newNode = new Node<>(item);
            newNode.next=null;
            last.next = newNode;

        }
        public int cal(Node<T> current){
            if (current.getNext()==null)
                return 1;
            else return cal(current.getNext())+1;
        }//Ex4 递归计算链表长度
    }//Ex 4 计算数组长度

    public boolean palindromes(String str,String Mode) {//选择输入str的类型
        if (Mode == "sentence") {//需要先去除句子中的非字母成分
            char[] array = str.toCharArray();
            StringBuffer stringBuffer = new StringBuffer();
            for (char i : array) {
                if ((i >= 'a' & i <= 'z') || (i >= 'A' & i <= 'Z')) {
                    if (i >= 'a') {
                        stringBuffer.append(Character.toUpperCase(i));
                    } else stringBuffer.append(i);

                }
            }
            char[] newarray = stringBuffer.toString().toCharArray();
            return palindromes(newarray, 0, newarray.length - 1);
        }
        if (Mode == "word"){
            return palindromes(str.toCharArray(),0,str.length()-1);
        }
        else System.out.println("Wrong Mode"); return false;
    }//Ex5 判断是否为回文
    public boolean palindromes(char[] array,int lower, int higher){
        if (lower>=higher) return true;
        else return (array[lower]==array[higher])&palindromes(array,lower+1,higher-1);
    }//Ex5 判断是否为回文


/**
 * 以下为上机作业
 */
    public void permution(int n,int r){
        permution(n,r,new int[r],0);
    }//上机作业1，找出从自然数1-n中任取r个数的所有组合

    public void permution(int n,int r,int[] a,int m){
        if (r==0){
            for (int i:a) {
                System.out.print(i);
            }
            System.out.println();
        }
        else
            for (int i=n;i>=r;i--){
                a[m]=i;//固定index为m的数，数组后面的数均小于这个数，可以固定（n-r+1）个数
                permution(i-1,r-1,a,m+1);
            }
    }//上机作业1

    public void hanoid(int n){
        if (n==0){
            System.out.println("Wrong Input!");
        }
        else
            move(n,'A','B','C');
    }//上机作业2，递归计算Hanoid塔
    public void move(int n,char from,char medium,char target){
        if (n==1){
            System.out.println("Move from "+from+" to "+target);
            return;
        }
        move(n-1,from,target,medium);//把n-1个move到过渡
        System.out.println("Move from "+from+" to "+target);//把最后一个move到target
        move(n-1,medium,from,target);//把n-1个从过渡move到target
    }//上机作业2
}

