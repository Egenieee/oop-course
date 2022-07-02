package simplenetworking;

import java.net.Socket;

public class SimpleChatClient {
    public void go(String name) {
        try {
            Socket sock = new Socket("127.0.0.1", 4242);
            new SimpleChatGUI(sock, name);
        } catch (Exception ex) {}
    }
}
