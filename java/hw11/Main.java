public class Main {

    public static void main(String[] args) {

        List<Integer> sd = new List<Integer>();

        sd.add(5);
        sd.add(2);
        sd.add(4);
        sd.add(3);
        sd.add(1);
        sd.add(7);
        System.out.println(sd._toString());

        sd.swap(0,5);
        System.out.println(sd._toString());

        sd.add(8);
        sd.swap(5,6);
        System.out.println(sd._toString());

        sd.swap(6,5);
        System.out.println(sd._toString());

        sd.swap(6,0);
        System.out.println(sd._toString());

        sd.sort();
        System.out.println(sd._toString());

    }
}
