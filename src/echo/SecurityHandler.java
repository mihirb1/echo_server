package echo;

import java.util.HashMap;

public class SecurityHandler extends ProxyHandler {
    SafeTable users = new SafeTable();
    Boolean loggedIn = false;

    protected String response(String request) throws Exception {
        String[] cmmd = request.split("\\s+");
        String answer = "";
        if(cmmd[0].equalsIgnoreCase("new")) {

            if (users.get(cmmd[1]) == null ) {
                users.put(cmmd[1], cmmd[2]);
                answer = "ACCOUNT CREATED";
            } else {
                return "ACCOUNT ALREADY EXISTS, TRY AGAIN";
            }


        } else if (cmmd[0].equalsIgnoreCase("login")) {
            String pw = users.get(cmmd[1]);
            if (cmmd[2].equals(pw)) {
                loggedIn = true;
                return "LOGGED IN SUCCESSFULLY";
            } else {
                return "INCORRECT LOGIN INFORMATION";
            }

        } else {
            if (loggedIn) {
                answer = super.response(request);
            } else {
                return "NOT LOGGED IN";
            }
        }
        return answer;
    }
}