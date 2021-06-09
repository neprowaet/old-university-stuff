import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Polygon.Point> t1 = Arrays.asList(new Polygon.Point(2, 2), new Polygon.Point(2, -2), new Polygon.Point(-2, 2), new Polygon.Point(-2, -2));
        List<Polygon.Point> t2 = Arrays.asList(new Polygon.Point(2, -2), new Polygon.Point(1,0), new Polygon.Point(2, 2), new Polygon.Point(-2, 2), new Polygon.Point(-2, -2));
        System.out.println(new Polygon(t1).isConvex() ? "+" : "-");
        System.out.println(new Polygon(t2).isConvex() ? "+" : "-");
    }
}
