record Point(int x, int y) {
}

enum Color {RED, GREEN, BLUE}

record ColoredPoint(Point p, Color c) {
}

public class Jep456_E5 {
    public static void main(String[] args) {
        oldInstanceOfPattern();
        newInstanceOfPattern();
    }

    private static void oldInstanceOfPattern() {
        var cp = new ColoredPoint(new Point(3, 4), Color.GREEN);
        if (cp instanceof ColoredPoint(Point p, Color c)) {
            System.out.printf("1 只需要Point参数 p.x=%d p.y=%d\n", p.x(), p.y()); //1 只需要Point参数 p.x=3 p.y=4
        }
        if (cp instanceof ColoredPoint(Point p, Color c)) {
            System.out.printf("2 只需要Color参数 color=%s\n", c); //2 只需要Color参数 color=GREEN
        }
        if (cp instanceof ColoredPoint(Point(int x, int y), var c)) {
            System.out.printf("3 只需要坐标参数 x=%d y=%d\n", x, y); //3 只需要坐标参数 x=3 y=4
        }
        if (cp instanceof ColoredPoint(Point(int x, int y), var c)) {
            System.out.printf("4 只需要坐标x参数 x=%d\n", x); //4 只需要坐标x参数 x=3
        }
    }

    private static void newInstanceOfPattern() {
        var cp = new ColoredPoint(new Point(3, 4), Color.GREEN);
        if (cp instanceof ColoredPoint(var p, _)) {
            System.out.printf("1 只需要Point参数 p.x=%d p.y=%d\n", p.x(), p.y()); //1 只需要Point参数 p.x=3 p.y=4
        }
        if (cp instanceof ColoredPoint(_, var c)) {
            System.out.printf("2 只需要Color参数 color=%s\n", c); //2 只需要Color参数 color=GREEN
        }
        if (cp instanceof ColoredPoint(Point(var x, var y), _)) {
            System.out.printf("3 只需要坐标参数 x=%d y=%d\n", x, y); //3 只需要坐标参数 x=3 y=4
        }
        if (cp instanceof ColoredPoint(Point(var x, _), _)) {
            System.out.printf("4 只需要坐标x参数 x=%d\n", x); //4 只需要坐标x参数 x=3
        }
    }
}
