public class Main {
    public static void main(String[] args) {
        Sequence a = new MyArray(new Integer[]{1,2,3,4,5});


        //1, 2, 3, 4, 5
        a.print(", ", System.out);
        System.out.println();

        //[1, 2, 3, 4, 5]
        Sequence b = new SequenceBracketsDecorator(a, "[", "]");
        b.print(", ", System.out);
        System.out.println();

        //Элементы: 1;2;3;4;5 всего 5 элементов
        b = new SequenceEndDecorator(new SequenceHeadDecorator(a, "Элементы: "), "всего", "элементов");
        b.print(";", System.out);
        System.out.println();

        //Элементы: { 1, 2, 3, 4, 5 }
        b = new SequenceHeadDecorator(new SequenceBracketsDecorator(a, "{ ", " }"), "Элементы:");
        b.print(", ", System.out);
        System.out.println();
    }
}
