import org.eclipse.jetty.websocket.api.*;
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
