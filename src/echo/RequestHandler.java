package echo;

import java.net.Socket;

//entire class edited 4/23
public class RequestHandler extends Correspondent implements Runnable {
    protected boolean active; // response can set to false to terminate thread

    public RequestHandler(Socket s) {
        super(s);
        active = true;
    }

    public RequestHandler() {
        super();
        active = true;
    }

    // override in a subclass:
    protected String response(String msg) throws Exception {
        return "echo: " + msg;
    }

    // any housekeeping can be done by an override of this:
    protected void shutDown() {
        if (Server.DEBUG) System.out.println("handler shutting down");
    }

    public void run() {
        while(active) {
            try {
                // receive request
                String request = receive();

                // Check if the request is to quit
                if(request.equals("quit")) {
                    shutDown();
                    break;
                }

                // send response
                String responseMsg = response(request);
                send(responseMsg);

                // sleep
                Thread.sleep(100); // Sleep for 1 second (1000 milliseconds)

            } catch(Exception e) {
                // Handle any exceptions that occur during execution
                send(e.getMessage() + "... ending session");
                break;
            }
        }
        // close
        close(); // Close the connection
    }
}