import java.util.Iterator;
import java.util.NoSuchElementException;
public class Sequence<E> implements CycList<E> {

    @Override
    public void shift(int delta) {
        for (int i = 0; i < delta; i++) {
            head = head.next;
            last = last.next;
        }
    }

    private class Node { E item; Node next; }

    private Node head = null;
    private Node last = null;
    private int count = 0;

    public int size() {
        return count;
    }

    public void add(E input) {
        Node x = new Node();
        x.item = input;
        count++;
        if (head == null) {
            head = x;
            head.next = head;
            last = head;
        } else {
            last.next = x;
            last = last.next;
            last.next = head;
        }
//        System.out.println("ADDED");
//        System.out.println(last.item);
    }

    public String toString(){
        String toreturn = "";
        Iterator it = iterator();
        while (it.hasNext())toreturn+= it.next().toString() + " ";
        return toreturn;
    }

    public void print() {
        System.out.println("===========HEAD===========");
        System.out.println(head.item.toString());
        System.out.println("==========================");

        System.out.println("===========SIZE===========");
        System.out.println(count);
        System.out.println("==========================");
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private Node current = head;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (head == null) return false;
                if (currentIndex >= count) return false;
                return true;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                Sequence.Node toreturn = current;
                current = current.next;
                currentIndex++;
                return (E)toreturn.item;
            }
        };
    }
}
