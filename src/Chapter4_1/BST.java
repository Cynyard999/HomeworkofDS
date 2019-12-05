package Chapter4_1;

import Chapter4_0.SimpleBin_Tree;

public class BST<T extends Comparable<? super T>> {
    private node root;

    class node {//indexed binary search tree
        T key;
        node left;
        node right;

        node(T key, node left, node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        node(T key) {
            this(key, null, null);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        this.root = null;
    }

    public void setRoot(T key) {
        this.root = new node(key);
    }

    //插入一个数，也可以写有返回值的情况，返回的就是插入的节点的指针
    public void add(T key) {
        if (isEmpty()) {
            setRoot(key);
        } else {
            add(key, root);
        }
    }

    private void add(T key, node root) {
        if (root == null) {
            root = new node(key);
        }
        if (key.compareTo(root.key) > 0) {//增加到右节点
            add(key, root.right);
        } else if (key.compareTo(root.key) == 0) {
            //Do nothing
        } else {
            add(key, root.left);
        }
    }

    //find，返回指针,没找到就返回null
    public node find(T key) {
        return find(key, root);
    }

    private node find(T key, node root) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.key) > 0) {
            return find(key, root.right);
        } else if (key.compareTo(root.key) < 0) {
            return find(key, root.left);
        } else return root;
    }

    public node findMin() {
        return findMin(this.root);
    }

    private node findMin(node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        } else return findMin(root.left);
    }

    public node findMax() {
        return findMax(this.root);
    }

    private node findMax(node root) {
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return root;
        } else return findMax(root.right);
    }

    public void delete(T key) {
        delete(key,this.root);
    }
    private void delete(T key, node root){
        if (root==null){
            System.out.println("Not Found");
        }
        if (key.compareTo(root.key)>0){
            delete(key,root);
        }
        else if (key.compareTo(root.key)<0){
            delete(key,root.left);
        }
        else if (root.left!=null&&root.right!=null){
            root.key = findMin(root.right).key;//找到右子树中最小的那个元素，替换上来
            delete(root.key,root.right);//在右子树中删除最小的那个元素，就相当于删除一个叶节点嗷
        }
        else {//左右都为空（叶节点），或者左边右边有一边为空
            root = (root.left==null)?root.right:root.left;
        }
    }
}



