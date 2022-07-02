package assignment07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LabMain {
    private JFrame frame;
    private MyDrawPanel myDraw;
    private JPanel myPanal;

    public void go() {
        frame = new JFrame("RotatingJava");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        myPanal = new JPanel();
        myDraw = new MyDrawPanel();
        frame.getContentPane().add(BorderLayout.CENTER, myDraw);

        JButton ccwButton = new JButton("CCW");
        JButton cwButton = new JButton("CW");
        JButton scaleUpButton = new JButton("Scale Up");
        JButton scaleDownButton = new JButton("Scale Down");
        JButton replayButton = new JButton("Replay");

        ccwButton.addActionListener((event) -> { myDraw.rotateJava(-10); myDraw.ccwButton(); } );
        cwButton.addActionListener((event) -> { myDraw.rotateJava(10); myDraw.cwButton(); } );
        scaleUpButton.addActionListener((event) -> { myDraw.scaleJava(0.1); myDraw.scaleUpButton(); } );
        scaleDownButton.addActionListener((event) -> { myDraw.scaleJava(-0.1); myDraw.scaleDownButton(); } );
        replayButton.addActionListener((event) -> { Thread workerThread = new Thread(() -> myDraw.replayJava());
        workerThread.start(); } );

        myPanal.add(scaleUpButton);
        myPanal.add(scaleDownButton);

        frame.getContentPane().add(BorderLayout.NORTH, myPanal);

        frame.getContentPane().add(BorderLayout.WEST, ccwButton);
        frame.getContentPane().add(BorderLayout.EAST, cwButton);
        frame.getContentPane().add(BorderLayout.SOUTH, replayButton);

        frame.setVisible(true);
    }

    public static void main(String[] args){
        LabMain lab = new LabMain();
        lab.go();
    }
}
