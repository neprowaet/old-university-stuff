import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(3, 8, 12, 4, 2, 6, 9, 13, 1,1);
        array = first(array, 4);
        for(Integer i : array) System.out.print( i + " ");
    }

    static <T extends Comparable<T>> List<T> first(Iterable<T> array, int m) {
        PriorityQueue<T> p = new PriorityQueue<T>(new Comp<T>());

        Iterator<T> it = array.iterator();

        for(int i = 0; i < m; i++)
            p.add(it.next());

        while (it.hasNext()){
            p.add(it.next());
            p.poll();
        }

        List<T> toreturn = new ArrayList<T>();
        toreturn.addAll(p);
        return toreturn;
    }
}
class Comp<T extends Comparable<T>> implements Comparator<T> {
    public int compare(T s1, T s2) {
        return s2.compareTo(s1);
    }
}

