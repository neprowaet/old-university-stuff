public class DirectedEdge{
    protected int v;
    protected int w;
    protected int weight;

    public DirectedEdge(int v, int w, int weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public int weight(){
        return weight;
    }
}