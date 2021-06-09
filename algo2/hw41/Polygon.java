import java.util.*;

public class Polygon {
    static class Point {
        int x,y;
        Point(int x, int y){
            this.x = x; this.y = y;
        }
    }

    List<Polygon.Point> ar;
    public Polygon(List<Polygon.Point> ar){
        if (ar.size() < 3) {
            throw new IllegalArgumentException("not enough points");
        }
        this.ar = ar;
        sort();
    }

	// ищет угол <abc
    public static double angle(Point a, Point b, Point c) {
        double angle1 = Math.atan2(a.y - c.y, a.x - c.x);
        double angle2 = Math.atan2(b.y - c.y, b.x - c.x);

        return angle1 - angle2;
    }

    enum Direction {
        POSITIVE,		// Направление поворота в положительном направлении (против часовой стрелки)
        NEGATIVE,		// Направление поворота в отрицательном направлении (по часовой стрелке)
    }

    // упорядочиваем точки путем сортировки по величине угла с отрезоком  (0,0)-(0,1)
    void sort(){
        ar.sort(Comparator.comparingDouble(a -> angle(a, new Point(0,0), new Point(1,0))));
    }

    private Direction getTurn(Point a, Point b, Point c){
        int val = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
        return val > 0 ? Direction.POSITIVE : Direction.NEGATIVE;
    }

    public boolean isConvex() {
        Direction flag = getTurn(
                ar.get(0), ar.get((1) % ar.size()), ar.get(2 % ar.size()));
        for (int i = 2; i < ar.size() + 2; i++) {
            if(!flag.equals(getTurn(ar.get(i - 2), ar.get((i - 1) % ar.size()), ar.get(i % ar.size()))))
                return false;
        }
        return true;
    }
}
