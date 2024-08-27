package echo;

import java.net.Socket;


public class ProxyHandler extends RequestHandler {

    protected Correspondent peer;

    public ProxyHandler(Socket s) {
        super(s);
    }

    public ProxyHandler() {
        super();
    }

    public void initPeer(String host, int port) {
        peer = new Correspondent();
        peer.requestConnection(host, port);
    }

    @Override
    protected void shutDown() {
        super.shutDown();
        peer.send("quit");
    }

    //edited 4/26
    protected String response(String msg) throws Exception {
        System.out.println("Sending: " + msg);
        peer.send(msg);
        String resp = peer.receive();
        System.out.println("Receiving: " + resp);
        if (msg.equals("quit")) {
            super.shutDown();
        }
        return resp;
    }
}
