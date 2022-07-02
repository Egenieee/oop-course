package practice10;

public class Point {
    private double posX, posY;

    public Point(double x, double y) {
        posX = x; posY = y;
    }

    public double getX() { return posX; }
    public double getY() { return posY; }
    public double setX(double x) { posX = x; return posX; }
    public double setY(double y) { posY = y; return posY; }
}
