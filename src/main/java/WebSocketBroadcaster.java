import com.theeyetribe.clientsdk.GazeManager;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by yumebayashi on 5/14/17.
 */
public class WebSocketBroadcaster {

    private static WebSocketBroadcaster INSTANCE = new WebSocketBroadcaster();
    private List<WebSocketHandler> clients = new ArrayList<>();

    private WebSocketBroadcaster() {
        GazeManager gm = GazeManager.getInstance();
        gm.getScreenPhysicalHeight();
        gm.getScreenPhysicalWidth();
        gm.getScreenResolutionWidth();
        gm.getScreenResolutionHeight();

        System.out.println(gm.activate());
        GazeListener gazeListener = new GazeListener();
        gm.addGazeListener(gazeListener);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("finished");
                gm.removeGazeListener(gazeListener);
                gm.deactivate();
            }
        });
    }

    protected static WebSocketBroadcaster getInstance() {
        return INSTANCE;
    }

    protected void join(WebSocketHandler socket) {
        clients.add(socket);
    }

    protected void bye(WebSocketHandler socket) {
        clients.remove(socket);
    }

    //全ソケットにmessageを送信
    protected void sendToAll(String message) {
        for (WebSocketHandler member : clients) {
            try {
                member.getSession().getRemote().sendString(String.valueOf(new JSONObject().put("pos", message)));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


}
