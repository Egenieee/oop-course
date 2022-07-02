package simplenetworking;

import practice10.PolylineEditorClient;

public class SimpleNetworkingTest {
    public static void main(String[] args) {

        new Thread(() -> new SimpleChatServer().go()).start(); //creating server

        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
        }

        new Thread( () -> new PolylineEditorClient().go("TalkerA")).start();
        new Thread( () -> new PolylineEditorClient().go("TalkerB")).start();
        new Thread( () -> new PolylineEditorClient().go("TalkerC")).start();
    }
}
