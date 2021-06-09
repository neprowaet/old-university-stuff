import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        int[][] matrix = new int[5][5];
//        Random r = new Random();
//
//        for (int i = 0; i < matrix.length; i++) {
//
//            for (int j = 0; j < matrix[0].length; j++) {
//
//                matrix[i][j] = (r.nextInt(9) + 1)*100;
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
        int[][] matrix =
                {
                        {1, 20, 1, 1, 1},
                        {1, 20, 1, 20, 1},
                        {1, 20, 1, 20, 1},
                        {1, 20, 1, 20, 1},
                        {1, 1, 1, 20, 1}
                };
        Path test = new Path(matrix);
        test.printPath(test.findPath());
    }
}
