import java.util.ArrayList;
import java.util.Collections;

public class Path {

    int[][] matrix; // матрица
    int[][] distances; // матрица расстояний до вершин
    Point[][] parents; // матрциа родителей вершин - в каждую вершину записывается родитель - предыдущая вершина (та, из которой эта была достигнута) 

    int matrixSize;  // размер матрицы

    boolean[][] checked;  // массив с информацией о посещенных вершинах
    int newPoints;  // кол-во непосещенных вершин

    class Point {

        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Path(int [][] matrix) { // инициализация 
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("n!=m");
        }
        this.matrix = matrix;
        matrixSize = matrix.length;
        distances = createDistances();
        parents = new Point[matrixSize][matrixSize];
        checked = new boolean[matrixSize][matrixSize];

        newPoints = matrixSize * matrixSize;

        findDistances();
    }
	
	 // создание массива расстояний и заполнение его максимальным значением для типа int
    public int[][] createDistances() {
        int[][] distances = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }

        }

        return distances;

    }
	
	// найти минимальные расстояния от начальной точки до каждой точки графа
    public void findDistances() {
        distances[0][0] = matrix[0][0];

        while (newPoints > 0) {
			//найти непосещенную вершину с мин. расстоянием до начальной
            Point point = getMinimalPoint();
			// посетить точку - расчитать расстояние от этой точки соседних точек
            checkPoint(point);

            newPoints--;
        }
    }


    //////////////    //////////////    //////////////
    //////////////    //////////////    //////////////

            //  ошибка была в методе findPath: путь не "собирался" справо и вниз
            //  для решения задачи используется алгоритм Дейкстры, пройстейшая реализация
            // O(V^2)

    //////////////    //////////////    //////////////
    //////////////    //////////////    //////////////

    public ArrayList<Point> findPath() {
		// имея информацию о родителях точек составим путь от конечной до начальной
		
        Point pointer = new Point(matrixSize - 1, matrixSize - 1);

        ArrayList<Point> path = new ArrayList<>();
        path.add(new Point(pointer.x, pointer.y)); // конечная точка является частью пути

        while (true) {
			// если родитель слева ( или сверху), переходим в эту точку, добавляем в путь
            if (parents[pointer.x][pointer.y].x == pointer.x - 1) { 
                pointer.x--;
                path.add(new Point(pointer.x, pointer.y));
                if (pointer.x == 0 && pointer.y == 0)
                    break; // пока не придем в начальную
            }

            if (parents[pointer.x][pointer.y].y == pointer.y - 1) {

                pointer.y--;
                path.add(new Point(pointer.x, pointer.y));
                if (pointer.x == 0 && pointer.y == 0)
                    break;
            }
            if (parents[pointer.x][pointer.y].x == pointer.x + 1) {
                pointer.x++;
                path.add(new Point(pointer.x, pointer.y));
                if (pointer.x == 0 && pointer.y == 0)
                    break; // пока не придем в начальную
            }

            if (parents[pointer.x][pointer.y].y == pointer.y + 1) {

                pointer.y++;
                path.add(new Point(pointer.x, pointer.y));
                if (pointer.x == 0 && pointer.y == 0)
                    break;
            }
        }

		// инвертируем путь
        Collections.reverse(path);
        return path;
    }
	
	
	// выводим путь path
    public void printPath(ArrayList<Point> path) {
        System.out.println("Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.println("  " + path.get(i).x + "  " + path.get(i).y);
        }
    }

	// найти непосещенную вершину с мин. расстоянием до начальной
    private Point getMinimalPoint() {
        Point point = new Point(0, 0);
        int min = Integer.MAX_VALUE; // текущее минимальное расстояние до вершины point
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (checked[i][j]) continue;

                if (distances[i][j] < min) { // сравниваем с текущей мин. вершиной point и расстонием до неё min, если меньше - перезаписываем
                    min = distances[i][j];
                    point.x = i;
                    point.y = j;
                }
            }
        }
        return point;
    }

	// рассчитать расстояние от точки до соседних
    private void checkPoint(Point point) {
        if (point.x > 0 && !checked[point.x - 1][point.y])
            checkNeighbour(point, new Point(point.x - 1, point.y));


        if (point.x < matrixSize - 1 && !checked[point.x + 1][point.y])
            checkNeighbour(point, new Point(point.x + 1, point.y));


        if (point.y > 0 && !checked[point.x][point.y - 1])
            checkNeighbour(point, new Point(point.x, point.y - 1));


        if (point.y < matrixSize - 1 && !checked[point.x][point.y + 1])
            checkNeighbour(point, new Point(point.x, point.y + 1));


        checked[point.x][point.y] = true;
    }

	// рассчитать расстоние до соседней точки
    private void checkNeighbour(Point point, Point neighbour) {
		// расстояние до текущей + расстоние до соседней
        int distance = distances[point.x][point.y] + matrix[neighbour.x][neighbour.y]; 

		// если меньше - перезаписываем расстоние и родителя
        if (distance < distances[neighbour.x][neighbour.y]) {

            distances[neighbour.x][neighbour.y] = distance; 
            parents[neighbour.x][neighbour.y] = point;
        }
    }
}

