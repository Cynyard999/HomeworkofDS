package Chapter4_0;

import Chapter3_1.SimpleQueue;
import Chapter3_1.SimpleStack;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class SimpleBin_Tree<T> {
    private Node root;
    class Node <T>{
        Node left;
        Node right;
        T element;
        int level;
        int tag;
        public Node(T element){
            this.element = element;
        }
        public Node(){
            this(null);
        }
        public void setElement(T element) {
            this.element = element;
        }
        public T getElement() {
            return element;
        }
        public int getLevel() {
            return level;
        }
        public void setLevel(int i){
            this.level = i;
        }
    }
    public SimpleBin_Tree(){
        this.root = new Node();
        root.setLevel(1);
    }
    public SimpleBin_Tree(Node t){
        this.root = t;
    }

    private void clearTree(){
        this.root.left = null;
        this.root.right = null;
        this.root.element = null;
        this.root.level = 1;
    }
    public int getTreeDepth(Node root) {//以这个节点为树的层数，从1开始
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    //按层次建造一棵树，栈结构
    public void LevelcreateTree(T[] ts){//层次遍历构建树
        clearTree();
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

    //给树的每一个节点标上他的层号
    private void labelNode(){
        labelNode(this.root,1);
    }
    private void labelNode(Node root, int level){
        root.setLevel(level);
        if (root.left!=null){
            labelNode(root.left,level+1);
        }
        if (root.right!=null){
            labelNode(root.right,level+1);
        }
    }

    //按层次遍历打印树
    public void printTree(){
        this.labelNode();
        int currentLevel = root.getLevel();
        if (root.left==null&root.right==null){
            System.out.println("Wrong Tree");
        }
        SimpleQueue queue = new SimpleQueue();
        queue.enqueue(root);
        while (!queue.isEmpty()){
            Node temp = (Node) queue.dequeue();
            if (temp.getLevel()!=currentLevel){//如果进入了下一层了
                System.out.println();
                currentLevel+=1;
            }
            System.out.print(temp.element+" ");
            if (temp.left!=null){
                queue.enqueue(temp.left);
            }
            if (temp.right!=null){
                queue.enqueue(temp.right);
            }
        }
        System.out.println();
    }

    //根据后缀表达式建立一棵树
    public void createTree_byPostfix(String str){
        clearTree();
        String[] strs = str.replace(" ","").split("");
        createTree_byPostfix(this.root,(T[])strs);
    }
    private void createTree_byPostfix(Node current, T[] ts){
        int index = ts.length-1;
        while (ts[index]==null){
            index--;
            if (index<0){
                break;
            }
        }
        if (index<0){
            return;
        }
        current.setElement(ts[index]);
        ts[index] = null;
        if (!current.element.toString().matches("[a-z]")){//如果是符号
            if (current.element.toString().matches("~")){
                current.left = new Node();
                createTree_byPostfix(current.left,ts);
                return;
            }
            current.right = new Node();
            createTree_byPostfix(current.right,ts);
            current.left = new Node();
            createTree_byPostfix(current.left,ts);
        }
    }


    //按前序递归遍历打印树
    public void getPreorder1(){
        System.out.print("Preorder: ");
        getPreorder1(this.root);
    }
    private void getPreorder1(Node root){
        if (root==null){
            return;
        }
        System.out.print(root.element+" ");
        getPreorder1(root.left);
        getPreorder1(root.right);
    }
    //按前序非递归遍历打印树
    public void getPreorder2(){
        System.out.print("Preorder: ");
        getPreorder2(this.root);
        System.out.println();
    }
    private void getPreorder2(Node root){
        SimpleStack stack = new SimpleStack();
        Node p = root;
        while (p!=null||!stack.isEmpty()){
            if (p!=null){
                System.out.print(p.getElement()+" ");
                stack.push(p);
                p = p.left;
            }
            else {
                Node temp = (Node)stack.pop();
                p = temp.right;
            }
        }
    }


    //按中序递归遍历打印树
    public void getInorder1(){
        System.out.print("Inorder: ");
        getInorder1(this.root);
    }
    private void getInorder1(Node root){
        if (root==null){
            return;
        }
        getPreorder1(root.left);
        System.out.print(root.element+" ");
        getPreorder1(root.right);
    }
    //按中序非递归遍历打印树
    public void getInorder2(){
        System.out.print("Inorder: ");
        getInorder2(this.root);
        System.out.println();
    }
    private void getInorder2(Node root){
        SimpleStack stack = new SimpleStack();
        Node p = root;
        while (p!=null||!stack.isEmpty()){
            if (p!=null){
                stack.push(p);
                p = p.left;
            }
            else {
                Node temp = (Node)stack.pop();
                System.out.print(temp.getElement()+" ");
                p = temp.right;
            }
        }
    }

    //按后序递归遍历打印树
    public void getPostorder1(){
        System.out.print("Postorder: ");
        getPostorder1(this.root);
    }
    private void getPostorder1(Node root){
        if (root==null){
            return;
        }
        getPostorder1(root.left);
        getPostorder1(root.right);
        System.out.print(root.element+" ");
    }
    //按后序非递归遍历打印树

}
