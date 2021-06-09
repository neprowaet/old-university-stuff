public class Main {

    static int vertices = 4; // максимальный индекс вершины
    static int[][] graph = new int[vertices][vertices]; // матрицы смежности
    static int infinity = Integer.MAX_VALUE;

    public static void main(String[] args) {
        graph[0][0] = 0; graph[0][1] = 1; graph[0][2] = 6; graph[0][3] = infinity;
        graph[1][0] = 1; graph[1][1] = 0; graph[1][2] = 4; graph[1][3] = 1;
        graph[2][0] = 6; graph[2][1] = 4; graph[2][2] = 0; graph[2][3] = 1;
        graph[3][0] = infinity; graph[3][1] = 1; graph[3][2] = 1; graph[3][3] = 0;




		// алгоритм Флойда-Уоршелла O(vertices^3)
        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++)
                for (int k = 0; k < vertices; k++)
                    if (graph[j][i] < infinity && graph[i][k] < infinity)
                        graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);

        int[] eccentricity_arr = new int[vertices];
		// найдем эксцентриситеты - наибольшие расстояния от вершины до остальных
        int current;
        for (int i = 0; i < vertices; i++) {
            current = 0;
            for (int j = 0; j < vertices; j++) {
                if(graph[i][j] > current)
                    current = graph[i][j];
                eccentricity_arr[i] = current;
            }
        }

		// найдем радиус графа - минимальный из эксцентриситетов
        int radius = eccentricity_arr[0];
        for(int i = 0; i < vertices; i++)
            if(eccentricity_arr[i] < radius)
                radius = eccentricity_arr[i];

		// выводим центр графа - множество вершин с эксцентриситетами равными радиусу графа
        for(int i = 0; i < vertices; i++)
            if(eccentricity_arr[i]==radius)
                System.out.println(i);

    }
}
