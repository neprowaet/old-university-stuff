import java.io.PrintStream;

/**
 * Created by Home on 20.06.2016.
 */

public class MyArray implements Sequence<Integer> {
    private Integer[] array;
    public MyArray(Integer[] elements) { array = elements; }
    public int size() { return array.length; }
    public void print(String delimiter, PrintStream ps) {
        if(array.length == 0) return;

        for(Integer i : array){
            String del = delimiter;
            if(i.equals(array[array.length-1])) del = "";
            ps.print(i + del);
        }
    }
}