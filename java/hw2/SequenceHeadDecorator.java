import java.io.PrintStream;

/**
 * Created by Home on 20.06.2016.
 * @Override
public void draw() {
decoratedShape.draw();
setRedBorder(decoratedShape);
}
private void setRedBorder(Shape decoratedShape){
System.out.println("Border Color: Red");
}

 */

public class SequenceHeadDecorator extends SequenceDecorator {
        public SequenceHeadDecorator(Sequence decoratedShape, String d) {
            super(decoratedShape);
            setHead(d);
        }
        private String head;
        public void setHead (String t) {this.head = t;}

        private void printHead(PrintStream ps){
            ps.print(head);
        }

        @Override
        public void print(String delimiter, PrintStream ps){
            printHead(ps);
            super.print(delimiter, ps);
        }

}
