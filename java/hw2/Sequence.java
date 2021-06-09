import java.io.PrintStream;

/**
 * Created by Home on 20.06.2016.
 */

public interface Sequence<E> {
    void print(String delimiter, PrintStream ps);
    int size();
}
