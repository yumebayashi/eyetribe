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

    }

    public static void broadcastMessage(String type, String message) {
        try {
            if (session == null) return;
            session.getRemote().sendString(String.valueOf(new JSONObject().put("type", type).put("pos", message)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
