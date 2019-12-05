package Chapter4_1;

public class AVL<T extends Comparable<? super T>> extends BST{
    protected node root;
    class node extends BST.node{
        int balance;
        node(T key){
            super(key);
            balance = 0;
        }
        node(T key, node left,node right){
            super(key,left,right);
            balance = right.balance-left.balance;
        }
    }

    @Override
    public void add(Comparable key) {
        if (isEmpty()){
            setRoot(key);
        }
        else
            add(key,this.root);
    }
    private void add(Comparable key, BST.node root){
        if (root ==null){
            root = new node((T) key);
        }
        else if (key.compareTo(root.key)>0){
            add(key,root.right);
            //TODO:
        }
        //TODO:
    }
}
