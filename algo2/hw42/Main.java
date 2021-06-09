import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

public class Main {
    public static void main (String[] args) {
        Main main = new Main();
    }
    final int inf = Integer.MAX_VALUE;
    List<City> grid = new ArrayList<>();
    List<Integer> toVisit = new ArrayList<>();
    int rowSize;
    public Main(){
        // узлы прямоугольной сетки - города, для каждого города заданы длины путей до четырех соседних (границы обозначаются как -1)
        grid.add(new City(-1, -1, 1, 2));
        grid.add(new City(-1, 1, 1, 3));
        grid.add(new City(-1, 1, 1, 2));
        grid.add(new City(-1, 1, -1, 1));
        grid.add(new City(2, -1, 4, 2));
        grid.add(new City(3, 4, 5, 5));
        grid.add(new City(2, 5, 3, 6));
        grid.add(new City(1, 3, -1, 2));
        grid.add(new City(2, -1, 5, -1));
        grid.add(new City(5, 5, 5, -1));
        grid.add(new City(6, 5, 5, -1));
        grid.add(new City(2, 5, -1, -1));

        toVisit = Arrays.asList(2, 3, 4, 6, 11); // список городов, обязательных к посещению (номера идут слева-направо и сверху-вниз)

        rowSize = grid.stream().map(a -> a.right).collect(Collectors.toList()).indexOf(-1) + 1; // находим первый элемент сетки, у которого значение right = -1
                                                                                                // так определяем длинну строк сетки



        int[] perm = new int[grid.size()];
        Arrays.fill(perm, -1);
        for (int i = 0; i < toVisit.size(); i++) {
            perm[toVisit.get(i)] = i;
        }

        // посчитаем расстояние между несоседними вершинами
        // воспользуемся  алгоритмом Дейкстры (реализация на двоичной куче)
        // O(V*NM*log(NM))
        int[][] dGraph = new int[toVisit.size()][toVisit.size()];
        for (int i = 0; i < toVisit.size(); i++) {
            int[] dist = new int[grid.size()];
            boolean[] used = new boolean[grid.size()];
            PriorityQueue<Road> queue = new PriorityQueue<>(grid.size());

            Arrays.fill(dist, inf);
            Arrays.fill(used, false);

            dist[toVisit.get(i)] = 0;
            queue.add(new Road(toVisit.get(i), 0));
            int counter = 0;
            while (counter < grid.size()) {
                Road min = queue.poll();
                if (min.weight != dist[min.v]) {
                    continue;
                }
                for (Road to : neighbours(min.v)) {
                    if (used[to.v]) continue;
                    if (dist[to.v] > dist[min.v] + to.weight) {
                        dist[to.v] = dist[min.v] + to.weight;
                        queue.add(new Road(to.v, dist[to.v]));
                    }
                }
                used[min.v] = true;
                counter++;
            }

            for (int j = 0; j < dist.length; j++) {
                if (perm[j] != -1)
                    dGraph[i][perm[j]] = dist[j];
            }
        }

        // построим минимальное остовное дерево
        // воспользуемся алгоримом Прима
        // O(V^2)
        int[] key = new int[toVisit.size()];
        int[] p = new int[toVisit.size()];
        boolean[] used = new boolean[toVisit.size()];
        Arrays.fill(key, inf);
        Arrays.fill(p, -1);
        Arrays.fill(used, false);
        int r = new Random().nextInt(toVisit.size());
        key[r] = 0;

        for (int o = 0; o < toVisit.size(); o++) {
            int min = 0;
            for (int i = 0; i < key.length; i++) {
                if (key[i] < key[min]) {
                    min = i;
                }
            }
            int current = min;

            for (int i = 0; i < toVisit.size(); i++) {
                if (current == i) {
                    continue;
                }
                if (!used[i] && key[i] > dGraph[current][i]) {
                    p[i] = current;
                    key[i] = dGraph[current][i];
                }
            }
            used[current] = true;
        }

        // собираем дерево
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < toVisit.size(); i++)
            tree.add(new ArrayList<>());
        for (int i = 0; i < toVisit.size(); i++)
            if (p[i] != -1)
                tree.get(p[i]).add(i);


        // обход + подсчет суммы
        List<Integer> path = new ArrayList<>();

        path.add(r);
        for(List<Integer> i: tree)
            for(Integer j : i)
                path.add(j); path.add(r);

        long sum = 0;
        for (int i = 0; i < path.size() - 1; i++)
            sum += dGraph[path.get(i)][path.get(i + 1)];

        System.out.println(sum);

    }

    // по заданому идентификатору выдает список ребер идущих из вершины
    private List<Road> neighbours(int id) {
        List<Road> r = new ArrayList<>();
        City t = grid.get(id);

        if(t.up != -1)  r.add(new Road(id - rowSize, t.up));
        if(t.down != -1) r.add(new Road(id + rowSize, t.down));
        if(t.left != -1) r.add(new Road(id - 1, t.left));
        if(t.right != -1) r.add(new Road(id + 1, t.right));

        return r;
    }
}
