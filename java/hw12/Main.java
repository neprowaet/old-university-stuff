import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        Sequence<Integer> list1 = new Sequence<Integer>();
        Sequence<Integer> list2 = new Sequence<Integer>();

        list1.add(4);
        list1.add(1);list1.add(2);list1.add(3); list1.add(4);
        list2.add(1);list2.add(2);list2.add(3); list2.add(4);
        list2.add(4);
//        list2.add(4);list2.add(4);list2.add(4);

        System.out.println("listsEqual([" + list1.toString() + "],[" + list2.toString() + "]) = " + listsEqual(list1, list2));
    }

    public static <E> boolean listsEqual(CycList<E> list1, CycList<E> list2) {
        Iterator it1=list1.iterator();
        Iterator it2=list2.iterator();

        int c1 = 0; int c2 = 0;

        while (it1.hasNext()) {
            it1.next();
            c1++;
        }
        while (it2.hasNext()) {
            it2.next();
            c2++;
        }

        if(c1 != c2) return false;

        boolean toreturn = true;

        for(int i=0; i<c1;i++) {
            Iterator list1Iterator = list1.iterator();
            Iterator list2Iterator = list2.iterator();

            while ( list1Iterator.hasNext() && toreturn) {
                if(!list1Iterator.next().equals(list2Iterator.next())) toreturn = false;
            }

            if(toreturn) return toreturn;
            toreturn = true;
            list1.shift(1);
        }

        toreturn = false;
        return toreturn;
    }
}
