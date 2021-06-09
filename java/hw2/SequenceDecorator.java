import java.io.PrintStream;

/**
 * Created by Home on 20.06.2016.    void print(String delimiter, PrintStream ps);
 int size();
 */

public abstract class SequenceDecorator implements Sequence {
    protected Sequence decoratedShape;

    public SequenceDecorator(Sequence decoratedShape){
        this.decoratedShape = decoratedShape;
    }
    public  void print(String delimiter, PrintStream ps){
        decoratedShape.print( delimiter,  ps);
    }

    public int size() {
        return decoratedShape.size();
    }
}


