import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {
    private int[] parent;

    public static void main(String[] args) {
        Main main = new Main();
    }
    public Main () { // используем disjoint-set-union, О(max log edges.size()))
        List<DirectedEdge> edges = new ArrayList<>(); // граф представленный списком ребер с нагрузкой

        edges.add(new DirectedEdge(0, 1, 2));
        edges.add(new DirectedEdge(1, 2, 3));
        edges.add(new DirectedEdge(2, 3, 4));
        edges.add(new DirectedEdge(3, 4, 1));
        edges.add(new DirectedEdge(0, 3, 2));

        int max = 4; // максимальный индекс вершины

        edges.sort(Comparator.comparingInt(a -> a.weight)); // отсортируем ребра по возврастанию веса

        parent = new int[max+1];
        for(int i = 0; i < parent.length; ++i) { // создадим множества для каждой вершины
            makeSet(i);
        }

        List<DirectedEdge> result = new ArrayList<>();
        for(DirectedEdge e : edges) {
            if (findSet(e.x) != findSet(e.y)) { // если ребро соединяет вершины, которые находятся в разных множествах, то добавим это ребро
                unionSets(e.x, e.y);
                result.add(e);
            }
        }

        for(DirectedEdge e : result) { // выводим полученное остовное дерево 
            System.out.println(e.x + "->" + e.y + " : " + e.weight); 
        }
    }

    private void makeSet(int v) { //  добавляет новый элемент v, помещая его в новое множество, состоящее из одного него
        parent[v] = v;
    }

    private int findSet(int v) {  // возвращяет "представителя" множества, в котором находится v
        if(v == parent[v]){
            return v;
        } else {
            return parent[v] = findSet(parent[v]);
        }
    }

    private void unionSets(int a, int b) { // объединяет два указанных множества (множество, в котором находится элемент a, и множество, в котором находится элемент b)
        a = findSet(a); b = findSet(b);
        if(a == b)
            return;
        if(new Random().nextBoolean())
            parent[a] = b;
        else
            parent[b] = a;
    }
}