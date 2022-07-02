package simplenetworking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SimpleChatServer {

    private ArrayList<PrintWriter> writers = new ArrayList<>();

    public void go() { //socket 만들어서 클라이언트 요청올때까지 대기
        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            while(true) { //클라이언트 요청이 들어올때마다 연결해준다.
                Socket sock = serverSocket.accept();
                writers.add(new PrintWriter(sock.getOutputStream()));
                echoMessages(sock);
            }
        } catch (Exception ex) { }
    }

    //소켓 연결될때마다 아웃풋스트림으로 생기는 라이터들을 어레이 형식으로 담아둔다.

    private synchronized void echoMessages(Socket sock) {//연결된 클라이언트에다가 메세지 뿌려주는 메소드
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
