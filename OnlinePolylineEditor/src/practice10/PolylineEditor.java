package practice10;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PolylineEditor {

    private PrintWriter writer;
    private BufferedReader reader;

    private MyDrawPanel drawPanel;
    private Polyline pline;

    private Broadcaster caster = new Broadcaster();

    private String	name;

    public PolylineEditor(Socket s, String n) {
        name = n;
        setUpNetworking(s);
        go();
        Thread thread = new Thread(new PolylineEditor.IncomingReader());
        thread.start();
    }

    public class IncomingReader implements Runnable { //서버에서 정보 받아서 화면에 띄워야 됨
        public void run() {
            System.out.println("ready to receive");
            String message;
            try {
                while((message = reader.readLine()) != null) {
                    String[] tokens = message.split(":");
                    if(tokens[0].equals(name)) {
                        continue;
                    }
                    if(tokens[1].equals("clear")) {
                        drawPanel.clearPolyline();
                        pline.clear();
//                        pline = new Polyline(pline.getBroadcaster());
//                        pline.setPanel(drawPanel);
                        drawPanel.setPolyline(pline);
                        drawPanel.repaint();
                        //패널에 새로만든 폴리라인 추가
                        drawPanel.addMouseListener(pline); //새로운 마우스 이벤트 처리 추가
                        drawPanel.addMouseMotionListener(pline);
                    }
                    if(tokens[1].equals("closed")) {
                        if(pline.getNumPts() == 0) continue;
                        pline.setClosed();
                        drawPanel.repaint();
//                        drawPanel.setPolyline(pline); //패널에 새로만든 폴리곤 추가
                        pline = new Polyline(pline.getBroadcaster());
//                        pline = new Polyline(new Broadcaster());

                        pline.setPanel(drawPanel);
                        drawPanel.setPolyline(pline);
                        drawPanel.addMouseListener(pline); //새로운 마우스 이벤트 처리 추가
                        drawPanel.addMouseMotionListener(pline);
                    } else {
                        pline.executeCommand(tokens[1]);
                        drawPanel.repaint();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class Broadcaster {
        public void broadcasterCommand(String cmd) {
            writer.println(name + ":" + cmd);
            writer.flush();
            System.out.println(name + ":" + cmd);
        }
    }

    private boolean setUpNetworking(Socket s) {
        try {
            Socket socket = s;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch(IOException ex) {
            return false;
        }
        return true;
    }

    public void go() {
        JFrame frame = new JFrame("Polyline Editor: " + name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new MyDrawPanel();
        pline = new Polyline(caster);

        pline.setPanel(drawPanel);
        drawPanel.setPolyline(pline);
        drawPanel.addMouseListener(pline);
        drawPanel.addMouseMotionListener(pline);

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        JPanel myPanal = new JPanel();

        JButton clearButton = new JButton("clear");
        JButton closedButton = new JButton("closed");

        myPanal.add(clearButton);
        myPanal.add(closedButton);

        frame.getContentPane().add(BorderLayout.SOUTH, myPanal);


        clearButton.addActionListener((event) -> {
            drawPanel.clearPolyline();
            pline.clear();
            drawPanel.setPolyline(pline);
            drawPanel.repaint();

            caster.broadcasterCommand("clear");

            drawPanel.addMouseListener(pline); //새로운 마우스 이벤트 처리 추가
            drawPanel.addMouseMotionListener(pline);
        });

        closedButton.addActionListener((event) -> {
            if(pline.getNumPts() == 0) return;
            pline.setClosed();
            drawPanel.repaint();

            caster.broadcasterCommand("closed");

            pline = new Polyline(pline.getBroadcaster()); //클로즈 하면 새로운 폴리라인 생성하기.
            pline.setPanel(drawPanel);
            drawPanel.setPolyline(pline);
             //패널에 새로만든 폴리라인 추가
            drawPanel.addMouseListener(pline); //새로운 마우스 이벤트 처리 추가
            drawPanel.addMouseMotionListener(pline);
        } );




        BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();
        layout.setHgap(10); //가로 방향으로 10 정도 떨어지도록 함
        layout.setVgap(10); //세로 방향으로 10 정도 떨어지도록 함

        frame.setSize(400, 400);
        frame.setVisible(true);
    }

}
