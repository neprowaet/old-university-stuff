public class Road implements Comparable<Road> {
    final int v;
    final int weight;

    public Road(int v, int weight){
        this.v = v; this.weight = weight;
    }

    @Override
    public int compareTo(Road a){
        int temp = Integer.compare(weight, a.weight);
        if(temp == 0) return Integer.compare(v, a.v);
        return temp;
    }
}
