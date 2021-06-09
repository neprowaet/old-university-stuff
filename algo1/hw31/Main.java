import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        List<Comparable> l = sort(new Integer[][] {
                { 5, 7, 10, 23 },
                { 2, 12 },
                { 6, 7, 7, 9 },
                { 1 }
        });
        for(Comparable i : l)
            System.out.print(i + " ");


    }
    public static List<Comparable> sort(Comparable[][] input){
        LinkedList<Comparable> result = new LinkedList<>();

        PriorityQueue<Row> p = new PriorityQueue<>();
        for(Comparable[] i : input)
            p.add(new Row(new LinkedList<>(Arrays.asList(i))));

        while (!p.isEmpty()){
            Row roow = p.poll();
            result.add(roow.ar.get(0));
            roow.ar.removeFirst();
            if(!roow.ar.isEmpty()) p.add(roow);
        }

        return result;
    }


    public static class Row implements Comparable<Row>{
        LinkedList<Comparable> ar;
        public Row(LinkedList c) { ar= c ;}
        public int compareTo(Row r) {
            return ar.get(0).compareTo(r.ar.get(0));
        }
    }

}
