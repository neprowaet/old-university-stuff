import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {

    private class Point { // класс для хранения координат

        private int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    private static int dist[][], excent[]; // матрица расстояний и массив эксцентриситетов

    int max; // максимальный из индексов
    int infinity = Integer.MAX_VALUE; // максимальное значение для типа int

    List<Point> edges = new ArrayList<>(); // граф представленный списком ребер

    public static void main(String[] args) {
        Main main = new Main();
    }
    public Main (){
        edges.add(new Point(0,1));
        edges.add(new Point(1,0));
        edges.add(new Point(2,0));
        edges.add(new Point(0,3));

        max = 3; 
        System.out.println(findD());
        System.out.println(findE(3));
    }
    public int findD(){  // алгоритм Флойда-Уоршелла O(max^3)
        ++max;
        dist = new int[max][max];
        for(int i = 0; i < max; ++i) { // заполняем массив расстояний
            Arrays.fill(dist[i], infinity); // для последующей релаксации
            dist[i][i] = 0; // сама в себя - 0
			}
        for(Point e : edges)
            dist[e.x][e.y] = dist[e.y][e.x] = 1;
        for(int k = 0; k < max; ++k)
            for(int i = 0; i < max; ++i)
                for(int j = 0; j < max; ++j)
                    if(dist[i][k] < infinity && dist[k][j] < infinity)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]); // релаксация
        max = 0; // в переменной мах посчитаем диаметр графа
        for(int[] d2 : dist)
            for(int d3 : d2)
                max = Math.max(max, d3);
        return max;
    }
	
    public int findE (int x){ // находит эксцентриситеты и выводит для х
        excent = new int[dist.length];
        for(int i = 0; i < dist.length; ++i)
            for (int j = 0; j < dist.length; ++j)
                excent[i] = Math.max(excent[i], dist[i][j]);
        return excent[x - 1];
    }
}