package echo;


import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.io.*;
import java.net.*;


public class Server {


    protected ServerSocket mySocket;
    protected int myPort;
    public static boolean DEBUG = true;
    protected Class<?> handlerType;


    public Server(int port, String handlerTypeName) {
        try {
            myPort = port;
            mySocket = new ServerSocket(myPort);
            this.handlerType = Class.forName(handlerTypeName);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } // catch
    }


    //edited on 4/23
    //edited on 4/23
    public void listen() {
        System.out.println("Server listening at port " + myPort); // Change this line
        while (true) {
            try {
                // Accept a connection
                Socket clientSocket = mySocket.accept();

                // Make handler
                RequestHandler handler = makeHandler(clientSocket);

                // Start handler in its own thread
                Thread handlerThread = new Thread(handler);
                handlerThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //edited on 4/23
    public RequestHandler makeHandler(Socket s) {
        try {
            // Create an instance of the handler
            RequestHandler handler = (RequestHandler) handlerType.getDeclaredConstructor().newInstance();


            // Set handler's socket to s
            handler.setSocket(s);


            // Return handler
            return handler;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }




    public static void main(String[] args) {
        int port = 5555;
        String service = "echo.RequestHandler";
        if (1 <= args.length) {
            service = args[0];
        }
        if (2 <= args.length) {
            port = Integer.parseInt(args[1]);
        }
        Server server = new Server(port, service);
        server.listen();

    }
}
