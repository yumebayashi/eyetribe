import org.eclipse.jetty.websocket.api.*;
import org.json.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static j2html.TagCreator.*;
import static spark.Spark.*;

public class Chat {

    static Session session;

    public static void main(String[] args) {
        staticFileLocation("public");
        webSocket("/chat", ChatWebSocketHandler.class);
        init();
        EyeTrack eyeTrack = new EyeTrack();
        eyeTrack.execute();
//        testloop();

    }

    //Sends a message from one user to all users, along with a list of current usernames
    public static void broadcastMessage(String type, String message) {
        try {
            if (session == null) return;
            session.getRemote().sendString(String.valueOf(new JSONObject().put("type", type).put("pos", message)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void testloop() {
        while (true) {
            try {
                Thread.sleep(500);
                Integer i = (int) (Math.random() * 9);
                String ret = "";
                switch (i) {
                    case 0:
                        ret = "leftup";
                        break;
                    case 1:
                        ret = "centerup";
                        break;
                    case 2:
                        ret = "rightup";
                        break;
                    case 3:
                        ret = "leftmiddle";
                        break;
                    case 4:
                        ret = "centermiddle";
                        break;
                    case 5:
                        ret = "rightmiddle";
                        break;
                    case 6:
                        ret = "leftdown";
                        break;
                    case 7:
                        ret = "centerdown";
                        break;
                    case 8:
                        ret = "rightdown";
                        break;
                }

                broadcastMessage("stop", ret);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
