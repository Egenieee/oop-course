package practice10;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyDrawPanel extends JPanel {


    private ArrayList<Polyline> polylines = new ArrayList<>();

    public ArrayList<Polyline> getPolylines() {
        return polylines;
    }

    public void setPolyline(Polyline pline) {
        polylines.add(pline);
    }

    public int getPolylineNum(){return polylines.size();}

    public void clearPolyline() {
        polylines.clear();
    }

    public int isPreviousPolygon(double X, double Y){

        for (int i = 0 ; i < polylines.size(); i++) {
            for (int j = 0; j < polylines.get(i).getNumPts(); j++) {
                double existedX = polylines.get(i).getPoint(j).getX();
                double existedY = polylines.get(i).getPoint(j).getY();
                if (Math.abs(existedX - X) < 10 && Math.abs(existedY - Y) < 10) {
                    return i;
                }
            }
        }
        return -1;
    }

    public Point getExistedPointPosition (double pressedX, double pressedY) {
        for (Polyline polyline : polylines) {
            for (int i = 0; i < polyline.getNumPts(); i++) {
                double existedX = polyline.getPoint(i).getX();
                double existedY = polyline.getPoint(i).getY();
                if (Math.abs(existedX - pressedX) < 10 && Math.abs(existedY - pressedY) < 10) {
                    return polyline.getPoint(i);
                }
            }
        }
        return null;
    }


    //
    public int getExistedPointIndex (double pressedX, double pressedY) {
        for (Polyline polyline : polylines) {
            for (int i = 0; i < polyline.getNumPts(); i++) {
                double existedX = polyline.getPoint(i).getX();
                double existedY = polyline.getPoint(i).getY();
                if (Math.abs(existedX - pressedX) < 10 && Math.abs(existedY - pressedY) < 10) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int getExistedPointIndex (double pressedX, double pressedY, int idx) {

            for (int i = 0; i < polylines.get(idx).getNumPts(); i++) {
                double existedX = polylines.get(idx).getPoint(i).getX();
                double existedY = polylines.get(idx).getPoint(i).getY();
                if (Math.abs(existedX - pressedX) < 10 && Math.abs(existedY - pressedY) < 10) {
                    return i;
                }
            }

        return -1;
    }


    public void paintComponent(Graphics g) {
        g.setColor(Color.white);//for clear
        g.fillRect(0, 0, this.getWidth(), this.getHeight());//for clear
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));//width is 3

        for (Polyline polyline : polylines) {
            int eachPolygonNumPts = polyline.getNumPts(); //저장된 폴리곤의 점 개수 저장
            int eachPolygonNumLines = polyline.getNumPts() - 1; //저장된 폴리곤의 선분 개수 저장
            for (int i = 0; i < eachPolygonNumLines; i++) {
                Point p0 = polyline.getPoint(i); //처음 점 저장
                Point p1 = polyline.getPoint(i + 1);// 그 다음 점 저장
                if (polyline.isClosed()) {
                    int size = polyline.getNumPts() - 1;
                    Point firstPoint = polyline.getPoint(0); //처음 점 저장
                    Point lastPoint = polyline.getPoint(size); //마지막 점 저장
                    g.drawLine((int) firstPoint.getX(), (int) firstPoint.getY(), (int) lastPoint.getX(), (int) lastPoint.getY());
                }
                g.setColor(Color.black);
                g.drawLine((int) p0.getX(), (int) p0.getY(), (int) p1.getX(), (int) p1.getY());
            }

            for (int j = 0; j < eachPolygonNumPts; j++) {
                Point point = polyline.getPoint(j);
                g.setColor(Color.RED);
                g.fillRect((int) point.getX() - 3, (int) point.getY() - 3, 10, 10);
            }

        }

    }
}
