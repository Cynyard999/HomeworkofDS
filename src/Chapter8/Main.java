package Chapter8;

public class Main {
    public static void main(String[] args){
        Digraph digraph = new Digraph();
        digraph.addVertex(1);
        digraph.addVertex(2);
        digraph.addVertex(3);
        digraph.addVertex(4);
        digraph.addVertex(5);
        digraph.addEdge(1,2);
        digraph.addEdge(2,3);
        digraph.addEdge(3,4);
        digraph.addEdge(4,5);
        digraph.addEdge(5,1);
        digraph.addEdge(5,2);
        digraph.addEdge(5,3);
        digraph.findCircle();
    }

}
