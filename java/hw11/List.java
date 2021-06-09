public class List<E extends Comparable<E>> {
    public static class Node<E> {
        E info;
        Node<E> next;
    }

    public Node<E> head = null;

    public void swap(int i, int j)  {
        if(i==j)return;
        if(i > j){int asd = i; i=j; j=asd;}

        int counter = 0;

        Node<E> current = head;

        Node<E> aa=null; Node<E> ii=null; Node<E> bb=null; Node<E> jj=null;

        while (current != null){
            if(counter == i-1) aa = current;
            if(counter == i) ii = current;
            if(counter == j-1) bb = current;
            if(counter == j) jj = current;

            counter++;
            current = current.next;
        }

        if(i < 0 || counter <= j) throw new IndexOutOfBoundsException();

        Node<E> sw;
        if(i==0 && counter==j-1)
        {
            System.out.println("1+2");
            bb.next = ii;
            head = jj;
            sw = head.next;
            head.next = ii.next;
            ii.next = sw;

            jj.next = ii.next;
            ii.next = null;
            return;
        } else
            if(i==0){
            bb.next = ii;
            head = jj;
            sw = head.next;
            head.next = ii.next;
            ii.next = sw;

        } else if(counter==j-1){

            jj.next = ii.next;
            ii.next = null;

            aa.next = jj;
            bb.next= ii;
        } else {
            aa.next = jj;
            bb.next= ii;

            sw = jj.next;
            jj.next = ii.next;
            ii.next = sw;
        }
    }

    public void sort () {
        Node<E> current = head;
        int counter = 0;
        while (current.next != null) {
            counter++;
            current = current.next;
        }
        current = head;

        for(int i=1;i<=counter;i++) {
            Node<E> currentJ = current;

            for(int j=i;j>0 && getByIndex(j-1).info.compareTo(getByIndex(j).info)>0;j--){


                swap(j-1,j);
            }
            if(current.next!=null){
                current = current.next;
            }
        }
    }

    public Node<E> getByIndex ( int i){
        Node<E> current = head;
        int counter = 0;
        while (current.next != null ) {
            if(counter == i) break;
            counter++;
            current = current.next;

        }
        return current;
    }

    public void add(E e) {
        Node<E> c = new Node<E>();
        c.info = e;
        c.next = null;

        if(head == null){
            head = c;
            return;
        }

        int counter = 0;
        Node<E> current = head;

        while (current.next != null)
            current = current.next;
        current.next = c;
    }


    public String _toString (){
        String a = "";
        Node<E> current = head;
        while (current != null) {
            a+=current.info.toString()+" ";
            current = current.next;
        }
        return a;
    }
}

