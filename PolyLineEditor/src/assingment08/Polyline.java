package assingment08;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Polyline extends MouseAdapter {
    private ArrayList<Point> mPts = new ArrayList<>();
    private MyDrawPanal myPanal = null;

    private boolean mClosed = false;

    private Point currentPoint = null;

    public void setPanal(MyDrawPanal panal) {
        myPanal = panal;
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
        currentPoint = null;
    }

    public void mousePressed(MouseEvent e) {
        if(mPts.size() > 0) {
            if(isClosed()){ //폴리곤이 닫혀있으면 더 이상 닫힌 폴리곤에 점 추가 못하도록 함.
                return;
            }
        }

        currentPoint = myPanal.getExistedPointPosition(e.getX(), e.getY());

        if(currentPoint != null) {
            ((JPanel)e.getSource()).repaint();
        } else {
            double x = e.getX();
            double y = e.getY();
            mPts.add(new Point(x, y));
            ((JPanel)e.getSource()).repaint();
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

}
