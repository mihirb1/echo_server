package echo;
import java.net.Proxy;
import java.net.Socket;
import java.util.HashMap;


import java.net.Socket;
/*
public class CacheHandler extends ProxyHandler{
    public static SafeTable cache = new SafeTable();
    public CacheHandler(Socket s) { super(s); }
    public CacheHandler() { super(); }

    protected String response(String msg) throws Exception {
        String answer = cache.get(msg);
        if(answer == null) {
            answer = super.response(msg);
            cache.put(msg, answer);
        }
        return answer;
    }
}
*/
public class CacheHandler extends ProxyHandler {

    private static SafeTable cache = new SafeTable();

    // Constructor
    public CacheHandler(Socket s) {
        super(s);
    }
    public CacheHandler() {
        super();
    }

    // Override the response method
    @Override
    protected synchronized String response(String msg) throws Exception {
        String cachedResponse = cache.get(msg);
        if (cachedResponse != null) {
            System.out.println("Cached response found: " + cachedResponse);
            return  cachedResponse; // Return cached response if available
        } else {
            // Forward the request to the peer
            String forwardedResponse = super.response(msg);
            // Update cache with the response
            cache.put(msg, forwardedResponse);
            return forwardedResponse;
        }
    }
}
