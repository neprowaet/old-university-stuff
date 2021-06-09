import java.io.PrintStream;

/**
 * Created by Home on 20.06.2016.
 */

public class SequenceEndDecorator extends SequenceDecorator {
    public SequenceEndDecorator(Sequence decoratedShape, String d, String p) {
        super(decoratedShape);
        setEnd(d,p);
    }

    public void setEnd(String d, String p){
        this.d = d; this.p=p;
    }

    String d,p;

    private void printEnd(PrintStream ps){
        ps.print(" " + d + " ");
        ps.print(super.size());
        ps.print(" " + p + " ");
    }

    @Override
    public void print(String delimiter, PrintStream ps){

        super.print(delimiter, ps);
        printEnd(ps);
    }

}

