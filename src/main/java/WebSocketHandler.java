import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class WebSocketHandler {

    private Session session;

    @OnWebSocketConnect
    public void onConnect(Session session) throws Exception {
        this.session = session;
        WebSocketBroadcaster.getInstance().join(this);
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        WebSocketBroadcaster.getInstance().bye(this);
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
    }

    public Session getSession() {
        return session;
    }
}
