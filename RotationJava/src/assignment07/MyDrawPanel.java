package assignment07;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyDrawPanel extends JPanel{

    private Image javaImg;

    public MyDrawPanel() {
        javaImg = new ImageIcon("java.jpeg").getImage();
    }

    private ArrayList<String> buttons = new ArrayList<String>();

    private double angle = 0;
    private double scale = 1.0;

    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.rotate(angle / 100 * Math.PI);
        g2d.scale(scale, scale);

        g.drawImage(javaImg, -100, -100, 200,200, this);
    }

    public void rotateJava(double dangle) {
        angle += dangle;
        repaint();
    }

    public void scaleJava(double dsc) {
        scale += dsc;
        repaint();
    }

    public void ccwButton() {
        buttons.add("ccwButton");
    } //리플레이 하기 위해 눌리는 버튼들 문자열로 추가
    public void cwButton() {
        buttons.add("cwButton");
    }
    public void scaleUpButton() {
        buttons.add("scaleUpButton");
    }
    public void scaleDownButton() {
        buttons.add("ScaleDownButton");
    }

    public synchronized void replayJava() {
        angle = 0;
        scale = 1.0; //원상태에서 시작
        repaint();

        try{  //리플레이 버튼 누르고 바로 시작하지 않고 잠깐 멈춘 후 리플레이 실행
            Thread.sleep(300);
        } catch (Exception ex) {}

        for(String button : buttons){//저장된 버튼리스트 순서대로 다시 실행
            if(button == "ccwButton"){
                angle += -10;
                repaint();
            }
            else if(button == "cwButton"){
                angle += 10;
                repaint();
            }
            else if(button == "scaleUpButton"){
                scale += 0.1;
                repaint();
            }
            else if(button == "ScaleDownButton"){
                scale += -0.1;
                repaint();
            }
            try{
                Thread.sleep(150);
            }catch (Exception ex) { }
        }
    }

}
