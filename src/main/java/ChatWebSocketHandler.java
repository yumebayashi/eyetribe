import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class ChatWebSocketHandler {

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        Chat.session = user;
//        String username = "User" + Chat.nextUserNumber++;
//        Chat.userUsernameMap.put(user, username);
//        Chat.broadcastMessage(sender = "Server", msg = (username + " joined the chat"));
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
//        String username = Chat.userUsernameMap.get(user);
//        Chat.userUsernameMap.remove(user);
//        Chat.broadcastMessage(sender = "Server", msg = (username + " left the chat"));
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
//        Chat.broadcastMessage(message);
    }

}
