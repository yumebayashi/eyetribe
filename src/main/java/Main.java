import org.eclipse.jetty.websocket.api.*;
import org.json.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static j2html.TagCreator.*;
import static spark.Spark.*;

public class Main {

    static Session session;

    //run eyetribe server in advance Applications/EyeTribe/EyeTribe
    public static void main(String[] args) {
        staticFileLocation("public");
        webSocket("/eyetribe", WebSocketHandler.class);
        init();
    }
}
