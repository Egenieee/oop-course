package simplenetworking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatGUI {
    private JTextField outgoing;
    private JTextArea incoming;
    private PrintWriter writer;
    private BufferedReader reader;

    private String name;
    public SimpleChatGUI(Socket s, String n) {
        name = n;
        setUpNetworking(s);
        go(name);
        new Thread( () -> { //전송받은 메세지 받아서 띄우기
            while(true) {
                try {
                    String inmsg = reader.readLine();
                    incoming.append(inmsg + "\n");
                } catch (IOException ex) {}
            }
        }).start();
    }

    public void go(String name) {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel receivePanel = new JPanel();
        receivePanel.setLayout(new BoxLayout(receivePanel, BoxLayout.Y_AXIS));
        incoming = new JTextArea(10, 20);
        incoming.setEditable(false);
        JScrollPane	scpane = new JScrollPane(incoming);
        receivePanel.add(new JLabel("incoming message"));
        receivePanel.add(scpane);

        JPanel sendPanel = new JPanel();
        sendPanel.setLayout(new BoxLayout(sendPanel, BoxLayout.Y_AXIS));
        outgoing = new JTextField(20);

        JButton sendButton = new JButton("Send");
        sendPanel.add(new JLabel("outgoing message"));
        sendPanel.add(outgoing);
        sendPanel.add(sendButton);

        SendButtonListener sendListener = new SendButtonListener();
        outgoing.addActionListener(sendListener);
        sendButton.addActionListener(sendListener);

        frame.getContentPane().add(BorderLayout.NORTH, receivePanel);
        frame.getContentPane().add(BorderLayout.SOUTH, sendPanel);

        frame.setSize(400,300);
        frame.setVisible(true);

    }


    private boolean setUpNetworking(Socket s) {
        try {
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            writer = new PrintWriter(s.getOutputStream());
        } catch(IOException ex) {
            return false;
        }
        return true;
    }

    public class SendButtonListener implements ActionListener {//서버로 메세지를 보내줘야 됨
        public void actionPerformed(ActionEvent ev) {
            try {
                writer.println(name + ": " + outgoing.getText()); //서버로 보내줄 메세지.
                writer.flush();
            } catch (Exception ex) {}
            outgoing.setText(""); //보내고 난 뒤 공문자열로 세팅
            outgoing.requestFocus();
        }
    }
}
