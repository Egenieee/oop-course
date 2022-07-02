package assingment08;


import javax.swing.*;
import java.awt.*;

public class PolylineEditor {
    Polyline pline = new Polyline();

    public static void main(String[] args) {
        PolylineEditor gui = new PolylineEditor();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame("Polyline Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanal drawPanal = new MyDrawPanal();

        pline.setPanal(drawPanal);
        drawPanal.setPolyline(pline);
        drawPanal.addMouseListener(pline);
        drawPanal.addMouseMotionListener(pline);

        frame.getContentPane().add(BorderLayout.CENTER, drawPanal);

        JPanel myPanal = new JPanel();

        JButton clearButton = new JButton("clear");
        JButton closedButton = new JButton("closed");

        myPanal.add(clearButton);
        myPanal.add(closedButton);

        frame.getContentPane().add(BorderLayout.SOUTH, myPanal);

        clearButton.addActionListener((event) -> {
            drawPanal.clearPolyline();
            drawPanal.repaint();
            pline = new Polyline();
            pline.setPanal(drawPanal);
            drawPanal.setPolyline(pline); //패널에 새로만든 폴리라인 추가
            drawPanal.addMouseListener(pline); //새로운 마우스 이벤트 처리 추가
            drawPanal.addMouseMotionListener(pline);
        } );

        closedButton.addActionListener((event) -> {
            pline.setClosed();
            drawPanal.repaint();
            pline = new Polyline(); //클로즈 하면 새로운 폴리라인 생성하기.
            pline.setPanal(drawPanal);
            drawPanal.setPolyline(pline); //패널에 새로만든 폴리라인 추가
            drawPanal.addMouseListener(pline); //새로운 마우스 이벤트 처리 추가
            drawPanal.addMouseMotionListener(pline);
        } );

        BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();
        layout.setHgap(10); //가로 방향으로 10 정도 떨어지도록 함
        layout.setVgap(10); //세로 방향으로 10 정도 떨어지도록 함

        frame.setSize(800, 800);
        frame.setVisible(true);
    }

}
