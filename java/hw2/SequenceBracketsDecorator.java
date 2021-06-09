import java.io.PrintStream;

/**
 * Created by Home on 20.06.2016.
 */

public class SequenceBracketsDecorator extends SequenceDecorator {
    public SequenceBracketsDecorator(Sequence decoratedShape, String d, String p) {
        super(decoratedShape);
        setBrackets(d,p);
    }

    public void setBrackets(String d, String p){
        this.d = d; this.p=p;
    }

    String d,p;

    private void printEnd(PrintStream ps){
        ps.print(p);
    }
    private void printHead(PrintStream ps){
        ps.print(d);
    }

    @Override
    public void print(String delimiter, PrintStream ps){
        printHead(ps);
        super.print(delimiter, ps);
        printEnd(ps);
    }

}
