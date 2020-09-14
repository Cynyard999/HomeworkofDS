package Chapter4_0;

import Chapter3_1.SimpleQueue;
import java.util.Stack;

public class SimpleBin_Tree<T> {

  private TreeNode<T> root;

  static class TreeNode<T> {

    public TreeNode<T> left;
    public TreeNode<T> right;
    T element;
    int level;
    int timeOnTop;

    public int getTimeOnTop() {
      return timeOnTop;
    }

    public void setTimeOnTop(int timeOnTop) {
      this.timeOnTop = timeOnTop;
    }

    TreeNode(T element) {
      this.element = element;
    }

    TreeNode() {
      this(null);
    }

    void setElement(T element) {
      this.element = element;
    }

    T getElement() {
      return element;
    }

    int getLevel() {
      return level;
    }

    void setLevel(int i) {
      this.level = i;
    }
  }

  public SimpleBin_Tree() {
    this.root = new TreeNode<T>();
    root.setLevel(1);
  }

  public SimpleBin_Tree(TreeNode<T> t) {
    this.root = t;
  }

  public TreeNode<T> getRoot() {
    return root;
  }

  public void setRoot(TreeNode<T> root) {
    this.root = root;
  }

  private void clearTree() {
    this.root.left = null;
    this.root.right = null;
    this.root.element = null;
    this.root.level = 1;
  }

  public int getTreeDepth(TreeNode<T> root) {//以这个节点为树的层数，从1开始
    return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
  }

  //按层次建造一棵树，栈结构
  public void LevelcreateTree(T[] ts) {//层次遍历构建树
    clearTree();
    if (ts.length <= 1) {
      System.out.println("Wrong input");
    }
    root.element = ts[0];
    SimpleQueue queue = new SimpleQueue();
    queue.enqueue(root);
    int i = 1;
    while (true) {
      TreeNode<T> temp = (TreeNode<T>) queue.dequeue();
      temp.left = new TreeNode<T>(ts[i]);
      queue.enqueue(temp.left);
      i++;
      if (i == ts.length) {
        break;
      }
      temp.right = new TreeNode<T>(ts[i]);
      queue.enqueue(temp.right);
      i++;
      if (i == ts.length) {
        break;
      }
    }
  }

  //给树的每一个节点标上他的层号
  private void labelNode() {
    labelNode(this.root, 1);
  }

  private void labelNode(TreeNode<T> root, int level) {
    root.setLevel(level);
    if (root.left != null) {
      labelNode(root.left, level + 1);
    }
    if (root.right != null) {
      labelNode(root.right, level + 1);
    }
  }

  //按层次遍历打印树
  public void printTree() {
    this.labelNode();
    int currentLevel = root.getLevel();
    if (root.left == null & root.right == null) {
      System.out.println("Wrong Tree");
    }
    SimpleQueue queue = new SimpleQueue();
    queue.enqueue(root);
    while (!queue.isEmpty()) {
      TreeNode<T> temp = (TreeNode<T>) queue.dequeue();
      if (temp.getLevel() != currentLevel) {//如果进入了下一层了
        System.out.println();
        currentLevel += 1;
      }
      System.out.print(temp.element + " ");
      if (temp.left != null) {
        queue.enqueue(temp.left);
      }
      if (temp.right != null) {
        queue.enqueue(temp.right);
      }
    }
    System.out.println();
  }

  //根据后缀表达式建立一棵树
  public void createTree_byPostfix(String str) {
    clearTree();
    String[] strs = str.replace(" ", "").split("");
    createTree_byPostfix(this.root, (T[]) strs);
  }

  private void createTree_byPostfix(TreeNode<T> current, T[] ts) {
    int index = ts.length - 1;
    while (ts[index] == null) {
      index--;
      if (index < 0) {
        break;
      }
    }
    if (index < 0) {
      return;
    }
    current.setElement(ts[index]);
    ts[index] = null;
    if (!current.element.toString().matches("[a-z]")) {//如果是符号
      if (current.element.toString().matches("~")) {
        current.left = new TreeNode<T>();
        createTree_byPostfix(current.left, ts);
        return;
      }
      current.right = new TreeNode<T>();
      createTree_byPostfix(current.right, ts);
      current.left = new TreeNode<T>();
      createTree_byPostfix(current.left, ts);
    }
  }


  //按前序递归遍历打印树
  public void getPreorder1() {
    System.out.print("Preorder: ");
    getPreorder1(this.root);
  }

  private void getPreorder1(TreeNode<T> root) {
    if (root == null) {
      return;
    }
    System.out.print(root.element + " ");
    getPreorder1(root.left);
    getPreorder1(root.right);
  }

  //按前序非递归遍历打印树
  public void getPreorder2() {
    System.out.print("Preorder: ");
    getPreorder2(this.root);
    System.out.println();
  }

  private void getPreorder2(TreeNode<T> root) {
    Stack<TreeNode<T>> stack = new Stack<>();
    TreeNode<T> p = root;
    while (p != null || !stack.isEmpty()) {
      if (p != null) {
        System.out.print(p.getElement() + " ");
        stack.push(p);
        p = p.left;
      } else {
        TreeNode<T> temp = stack.pop();
        p = temp.right;
      }
    }
  }


  //按中序递归遍历打印树
  public void getInorder1() {
    System.out.print("Inorder: ");
    getInorder1(this.root);
  }

  private void getInorder1(TreeNode<T> root) {
    if (root == null) {
      return;
    }
    getPreorder1(root.left);
    System.out.print(root.element + " ");
    getPreorder1(root.right);
  }

  //按中序非递归遍历打印树
  public void getInorder2() {
    System.out.print("Inorder: ");
    getInorder2(this.root);
    System.out.println();
  }

  private void getInorder2(TreeNode<T> root) {
    Stack<TreeNode<T>> stack = new Stack<>();
    TreeNode<T> p = root;
    while (p != null || !stack.isEmpty()) {
      if (p != null) {
        stack.push(p);
        p = p.left;
      } else {
        TreeNode<T> temp = stack.pop();
        System.out.print(temp.getElement() + " ");
        p = temp.right;
      }
    }
  }

  //按后序递归遍历打印树
  public void getPostorder1() {
    System.out.print("Postorder: ");
    getPostorder1(this.root);
  }

  private void getPostorder1(TreeNode<T> root) {
    if (root == null) {
      return;
    }
    getPostorder1(root.left);
    getPostorder1(root.right);
    System.out.print(root.element + " ");
  }

  //按后序非递归遍历打印树
  public void getPostorder2() {
    System.out.print("Postorder: ");
    getPostorder2(this.root);
  }

  private void getPostorder2(TreeNode<T> root) {
    Stack<TreeNode<T>> stack = new Stack<>();
    TreeNode<T> p = root;
    while (p != null || !stack.isEmpty()) {
      if (p != null) {
        p.setTimeOnTop(1);
        stack.push(p);
        p = p.left;
      } else {
        TreeNode<T> temp = stack.pop();
        if (temp.getTimeOnTop()==1){
          temp.setTimeOnTop(2);
          stack.push(temp);
          p = temp.right;
        }
        else {
          System.out.print(temp.getElement() + " ");
        }
      }
    }
  }

  //递归计算叶节点的个数
  public int countLeaves() {
    return countLeaves(this.root);
  }

  private int countLeaves(TreeNode root) {
    if (root == null) {//非叶片
      return 0;
    } else if (root.left == null && root.right == null) {//root为叶片
      return 1;
    } else {
      return countLeaves(root.left) + countLeaves(root.right);//分支
    }
  }

  public static SimpleBin_Tree swapBinTree(SimpleBin_Tree tree) {
    tree.setRoot(tree.swapSubTree(tree.getRoot()));
    return tree;
  }

  //交换一棵子树的所有左右节点
  public TreeNode swapSubTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    root = swap(root);
    root.left = swapSubTree(root.left);
    root.right = swapSubTree(root.right);
    return root;
  }

  //交换一个节点的左右叶片
  public TreeNode swap(TreeNode treeNode) {
    TreeNode temp = treeNode.left;
    treeNode.left = treeNode.right;
    treeNode.right = temp;
    return treeNode;
  }
}

