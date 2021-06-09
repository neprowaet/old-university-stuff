import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class Main {
/*
Класс содержит описания методов сортировки массива объектов разными способами. Каждый метод имеет стандартный интерфейс вида
public static <E extends Comparable<E>> void sort(E[] array)
где sort - имя метода. Реализовать три разных метода сортировки (например, “простыми вставками”, “слиянием”, “быстрая”, можно выбрать любые свои,
 лишь бы интерфейс метода был таким, как заявлено). Написать программу, которая получает в качестве аргумента название метода,
  создает случайный массив объектов, а затем сортирует указанным методом.
 */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Integer[] a = new Integer[20];

        Random rand = new Random();

        for(int i=0;i<20;i++) a[i] = rand.nextInt(99);

        Sort b = new Sort();

        Class c = b.getClass();
        Class[] cArg = new Class[1];
        cArg[0] = Comparable[].class;

        try {
            Method method = c.getMethod(args[0], cArg);
            Object[] arg = new Object[] { a };
            method.invoke(b, arg);
        } catch ( NoSuchMethodException e) { System.out.println("cannot sort, try -InsertSort, -QuickSort, -BubbleSort");}

        for (Integer i : a) System.out.println(i);
    }


}