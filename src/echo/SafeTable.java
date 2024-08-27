package echo;

import java.util.HashMap;

public class SafeTable extends HashMap<String, String> {

    // Private constructor to prevent instantiation from outside
    public SafeTable() {
        // Call the constructor of HashMap to initialize the underlying map
        super();
    }

    // Get the singleton instance

    public synchronized String get(String request) {
        String answer = (String) super.get(request);
        if (answer != null) {
            System.out.println("Previous response: " + answer);
        }
        return super.get(request);
    }

    // Override the put method to make it thread-safe
    @Override
    public synchronized String put(String request, String response) {
        // Call the put method of HashMap
        return super.put(request, response);
    }
}