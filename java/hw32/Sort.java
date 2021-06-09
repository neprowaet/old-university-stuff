import java.util.Random;

/**
 * Created by Home on 20.06.2016.
 */

public class Sort{

    Sort(){
        rand = new Random();
    }

    public static <E extends Comparable<E>> void InsertSort(E[] array){
        E[] a = array;
        for (int i = 0; i < a.length; i++)
        {
            E temp = a[i];
            int j =i-1;
            while(j >= 0 && a[j].compareTo(temp) > 0)
            {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }

    public <E extends Comparable<E>>  void BubbleSort(E[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[j + 1])>0) {
                    E t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }

    private static Random rand;

    public static  <E extends Comparable<E>> void QuickSort(E[] array) {
        QuickSortCore(array, 0, array.length-1);
    }

    public static  <E extends Comparable<E>> void QuickSortCore(E[] array, int l, int r) {
        int i = l;
        int j = r;
        E x = array[l + rand.nextInt(r - l + 1)];
        while (i <= j) {
            while (array[i].compareTo(x) < 0) {
                i++;
            }
            while (array[j].compareTo(x) > 0) {
                j--;
            }
            if (i <= j) {
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (l < j) {
            QuickSortCore(array, l, j);
        }
        if (i < r) {
            QuickSortCore(array, i, r);
        }
    }
}
