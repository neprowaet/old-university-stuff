import java.util.ArrayList;
import java.util.LinkedList;

public class Graph{
    protected int V; //vertex;
    protected int E; //edges;
    protected ArrayList<DirectedEdge>[] adj;
    protected LinkedList<DirectedEdge> edgesList = new LinkedList<>();

    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<DirectedEdge>();
        }
    }
    public int V(){
        return this.V;
    }

    public void addEdge(int v, int w, int weight){
        this.E++;
        DirectedEdge item = new DirectedEdge(v, w, weight);
        adj[v].add(item);
        edgesList.add(item);
    }

    public Iterable<DirectedEdge> adj(int v) {
        if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
        return this.adj[v];
    }
}