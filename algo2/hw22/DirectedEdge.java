public class DirectedEdge{
    protected int x;
    protected int y;
    protected int weight;

    public DirectedEdge(int x, int y, int weight){
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    public int from(){
        return x;
    }

    public int to(){
        return y;
    }

    public int weight(){
        return weight;
    }
}