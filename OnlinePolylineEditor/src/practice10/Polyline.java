package practice10;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Polyline extends MouseAdapter {
    private ArrayList<Point> mPts = new ArrayList<>();
    private MyDrawPanel myPanel = null;

    public void clear(){
        mPts.clear();
    }

    public PolylineEditor.Broadcaster getBroadcaster() {
        return broadcaster;
    }

    private PolylineEditor.Broadcaster broadcaster;
    public Polyline(PolylineEditor.Broadcaster c) {
        originalPoint = null;
        originalPointY = 0;
        originalPointX = 0;
        mClosed = false;
        myPanel = null;
        pointIdx = -1;

        broadcaster = c;
    }

    private boolean mClosed = false;

    private Point currentPoint = null;
    private Point originalPoint = null;
    private double originalPointX = 0;
    private double originalPointY = 0;


    int pointIdx = -1;


    public void setPanel(MyDrawPanel panel) {
        myPanel = panel;
    }

    public boolean isClosed() {
        return mClosed;
    }

    public void setClosed() {
        mClosed = true;
    }

    public int getNumPts() {
        return mPts.size();
    }

    public Point getPoint(int i) {
        return mPts.get(i);
    }



    public void mouseReleased(MouseEvent e) {
        if(mPts.size() > 0) {
            if(isClosed()){
                return;
            }
        }
        int polyIndex = -1;
        int pointIndex = -1;
        try{
           polyIndex = myPanel.isPreviousPolygon(currentPoint.getX(),currentPoint.getY());
        }catch(NullPointerException x){
            System.out.println(x);
        }

        try{
           pointIndex = myPanel.getExistedPointIndex(currentPoint.getX(),currentPoint.getY(),polyIndex);
        }catch(NullPointerException x){
            System.out.println(x);
        }

        if( polyIndex != -1 && pointIndex != -1){
            System.out.println("This is " + polyIndex + "th poly");
            // 지금 내가 누른 점이 들어있는 완성된 closed 폴리곤의 인덱스 (drawPanel의 arraylist 안에서)
            // 몇 번째 폴리곤에 속한 점인지 = polyIndex
            // getPolylineIndex? -> 몇 번째 속한 점인지 알았어
            double changedX = e.getX();
            double changedY = e.getY();
            broadcaster.broadcasterCommand("polySet " + changedX + " " + changedY + " " + polyIndex + " " + pointIndex);

            // myPanel 안의 polyIndex 번째 폴리곤 안의 pointIndex 번째 점의 좌표를 set 해라 -> changed로

        }
        else{
            // 새로운 점인 경우다.
            System.out.println("Hello from else");
            if(currentPoint != null) {
                double changedX = e.getX();
                double changedY = e.getY();
                System.out.println("first point is " + originalPointX + " " + originalPointY);
                System.out.println("final point is " + changedX + " " + changedY);
                ((JPanel)(e.getSource())).repaint();
                broadcaster.broadcasterCommand("set " + originalPointX + " " + originalPointY + " " + changedX + " " + changedY);
            }
        }



    }

    public void mousePressed(MouseEvent e) {
        if(mPts.size() > 0) {
            if(isClosed()){ //폴리곤이 닫혀있으면 더 이상 닫힌 폴리곤에 점 추가 못하도록 함.
                return;
            }
        }

        currentPoint = myPanel.getExistedPointPosition(e.getX(), e.getY());
        pointIdx = myPanel.getExistedPointIndex(e.getX(), e.getY());
        System.out.println("selected point's index is " + pointIdx);


        // 누른 곳이 기존에 이미 점
        if(currentPoint != null) {
            Point point = myPanel.getExistedPointPosition(e.getX(), e.getY());
            originalPoint = new Point(point.getX(), point.getY());
            originalPointX = originalPoint.getX();
            originalPointY = originalPoint.getY();
            ((JPanel)e.getSource()).repaint();

        } else {
            // 누른 곳이 그냥 하얀 곳
            int x = e.getX();
            int y = e.getY();
            mPts.add(new Point(x, y));
            ((JPanel)e.getSource()).repaint();
            broadcaster.broadcasterCommand("add " + x + " " + y);
        }

    }

    public void mouseDragged(MouseEvent e) {
        if(mPts.size() > 0) {
            if(isClosed()){
                return;
            }
        }

        if(currentPoint != null) { //기존 점이 있을 경우 기존 점이 선택되어 드래그 가능하게 함.
            currentPoint.setX(e.getX());
            currentPoint.setY(e.getY());
            ((JPanel)(e.getSource())).repaint();
        } else { //방금 추가한 점들도 드래그 가능하게 함.
            int latestPressdedPointIndex = mPts.size() - 1;
            mPts.get(latestPressdedPointIndex).setX(e.getX());
            mPts.get(latestPressdedPointIndex).setY(e.getY());
            ((JPanel)(e.getSource())).repaint();
        }
    }


    //맨 처음, 수정 전의 점의 좌표 어떻게 저장해두지?

    public boolean executeCommand(String cmd) {
        String[] tokens = cmd.split(" ");
        if(tokens[0].equals("add")) {
            mPts.add(new Point(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
            return true;
        } else if(tokens[0].equals("set")) {
            double beforeModifyPointX = Double.parseDouble(tokens[1]);
            double beforeModifyPointY = Double.parseDouble(tokens[2]);
            double afterModifyPointX = Double.parseDouble(tokens[3]);
            double afterModifyPointY = Double.parseDouble(tokens[4]);

            int pointIndex = myPanel.getExistedPointIndex(beforeModifyPointX, beforeModifyPointY);

            mPts.get(pointIndex).setX(afterModifyPointX);
            mPts.get(pointIndex).setY(afterModifyPointY);

            return true;
        }
        else if(tokens[0].equals("polySet")) {
            double afterModifyPointX = Double.parseDouble(tokens[1]);
            double afterModifyPointY = Double.parseDouble(tokens[2]);
            int polyIndex = Integer.parseInt(tokens[3]);
            int pointIndex = Integer.parseInt(tokens[4]);

            ArrayList<Polyline> poly = myPanel.getPolylines();
            poly.get(polyIndex).mPts.get(pointIndex).setX(afterModifyPointX);
            poly.get(polyIndex).mPts.get(pointIndex).setY(afterModifyPointY);
            return true;
        }
        return false;
    }

}