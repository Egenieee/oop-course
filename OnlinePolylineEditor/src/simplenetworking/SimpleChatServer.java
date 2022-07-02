package simplenetworking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SimpleChatServer {

    private ArrayList<PrintWriter> writers = new ArrayList<>();

    public void go() {
        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            while (true) {
                Socket sock = serverSocket.accept();
                writers.add(new PrintWriter(sock.getOutputStream()));
                echoMessages(sock);
            }
        } catch (Exception ex) {
        }
    }

    private void echoMessages(Socket sock) {//연결된 클라이언트에다가 메세지 뿌려주는 메소드
        new Thread( () -> {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                while(true) {
                    String msg = reader.readLine();
                    for (PrintWriter writer : writers) {
                        writer.println(msg);
                        writer.flush();
                    }
                }
            } catch (Exception ex) {}
        }).start();

    }
}
