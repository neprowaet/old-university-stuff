import java.util.*;
public class Main
{
    // модифицированный алгоритм Форда-Беллмана О(V^2*E)
    public static List<Integer> search(Graph g, int f, int t){
        List<Integer> r = new ArrayList<>();

        int[] dist = new int[g.V];
        int[] prev = new int[g.V];
        Arrays.fill(dist, -1);
        Arrays.fill(prev, -1);
        dist[f] = Integer.MAX_VALUE;
        for(int i = 0; i < g.V(); i++){
            for (int v = 0; v < g.V(); v++) {
                for (DirectedEdge e : g.adj(v)) {
//                    if (dist[e.to()] < dist[e.from()] + e.weight()) {  distance(x) =    min   [  distance(v) + width(e)  ]
//                        dist[e.to()] = dist[e.from()] + e.weight();
//                        prev[e.to()] = v;
//                    }
                    if (dist[e.to()] < Math.min(dist[v], e.weight)) { // модификация для решения maximum bottleneck problem
                        dist[e.to()] =  Math.min(dist[v], e.weight()); //  bottleneck(x) =    max   [min(bottleneck(v),width(e))]
                        prev[e.to()] = v;
                    }
                }
            }
        }
        r.add(t);
        int cur = prev[t];
        while (cur != -1) {
            r.add(cur);
            cur = prev[cur];
        }
        Collections.reverse(r);
        return r;
    }

    public static void main(String args[]){

        int vertexNumber = 7;

        Graph g = new Graph(vertexNumber);

        g.addEdge(0,1,5);
        g.addEdge(0,5,2);

        g.addEdge(1,2,1);
        g.addEdge(1,3,3);

        g.addEdge(2,4,6);

        g.addEdge(3,4,3);

       // g.addEdge(4,0,1);

        g.addEdge(5,6,3);

        g.addEdge(6,4,2);


        System.out.println(search(g, 0, 4).toString());
        System.out.println(search(g, 1, 3).toString());


        g.addEdge(4,0,1);
        System.out.println(search(g, 1, 5).toString());
    }
}