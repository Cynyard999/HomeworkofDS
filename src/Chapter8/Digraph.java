package Chapter8;

import java.awt.*;
import java.util.*;

public class Digraph <T extends Comparable<T>> {
    class Edge{
        Vertex dest;//每条边的终点（另外一个顶点）
        int value;//每条边的权重
        Edge nextEdge;
        public Edge(int value,Vertex dest){
            this.value = value;
            this.dest = dest;
            this.nextEdge = null;
        }
        public Edge(Vertex dest){
            this(0,dest);
        }
        public boolean hasNext(){
            return nextEdge!=null;
        }
    }
    class Vertex{
        T mark;
        Edge firstEdge;
        int inDegree;

        public Vertex(T mark){
            this.mark = mark;
            firstEdge = null;
            numOfVertices+=1;
        }
        private Edge lastEdge(){
            Edge lastEdge = firstEdge;
            while (lastEdge.hasNext()){
                lastEdge = lastEdge.nextEdge;
            }
            return lastEdge;
        }
        public boolean connect(Vertex to){
            if (firstEdge==null){
                firstEdge = new Edge(to);
            }
            else {
                lastEdge().nextEdge = new Edge(to);
            }
            to.inDegree++;
            return true;
        }
        public Vertex getNextNeighbor(){
            Edge currentEdge = firstEdge;
            Vertex next;
            while (currentEdge!=null){
                next = currentEdge.dest;
                if (!visited.contains(next)){
                    return next;
                }
                currentEdge = currentEdge.nextEdge;
            }
            return null;
        }
        public Edge getNeighborEdge(Edge currentEdge){
            if (currentEdge.hasNext()){
                return currentEdge.nextEdge;
            }
            return null;
        }
        public Edge getFirstEdge(){
            return this.firstEdge;
        }
    }
        private int numOfVertices;
        private int numOfEdges;
        private Map<T,Vertex> vertices = new LinkedHashMap<>();
        public boolean addVertex(T mark){
            if (numOfVertices==0){
                Vertex first = new Vertex(mark);
                vertices.put(mark,first);
                return true;
            }
            if (vertices.containsKey(mark)){
                System.out.println("Add a Repeated vertex: "+mark.toString());
                return false;
            }
            else {
                vertices.put(mark,new Vertex(mark));
                return true;
            }
    }
         boolean addEdge(T from, T to){
            if (!vertices.containsKey(from)){
                System.out.println("Not find an existing starting vertex which is"+ from.toString());
                return false;
            }
            Vertex start = vertices.get(from);
            Vertex dest;
            if (!vertices.containsKey(to)){
                addVertex(to);//如果终点没有，那么新增一个
            }
            dest = vertices.get(to);
            return start.connect(dest);
        }
        public boolean removeEdge(T from,T to){
            return true;
        }
        public void clear(){
        vertices.clear();
    }
        public void findCircle() {
            Iterator<Map.Entry<T,Vertex>> iterator= vertices.entrySet().iterator();
            while (iterator.hasNext()){
                Vertex start = iterator.next().getValue();
                if (!visited.contains(start)){
                    findCircle(start);
                }
            }
            if (!hasCircle){
                System.out.println("No circle");
            }
        }
        private ArrayList<Vertex> visited = new ArrayList<>();
        private ArrayList<Vertex> trace = new ArrayList<>();
        private boolean hasCircle = false;
        private void findCircle(Vertex start){
            if (visited.contains(start)){//如果这个节点被访问过并且在当前的trace中
                if (trace.contains(start)){
                    trace.add(start);
                    hasCircle = true;
                    printTrace(trace.indexOf(start),trace.size()-1);
                    trace.remove(trace.size()-1);
                    return;
                }
            }
            visited.add(start);
            trace.add(start);
            Edge firstEdge = start.getFirstEdge();
            while (firstEdge!=null){
                findCircle(firstEdge.dest);
                firstEdge = start.getNeighborEdge(firstEdge);
            }
            trace.remove(trace.size()-1);
        }
        private void printTrace(int start,int end){
            System.out.print("Circle: ");
            for (int i = start;i<=end;i++){
                System.out.print(trace.get(i).mark.toString()+" ");
            }
            System.out.println();
        }

}
