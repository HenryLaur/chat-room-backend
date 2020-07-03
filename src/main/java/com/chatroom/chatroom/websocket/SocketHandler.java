package com.chatroom.chatroom.websocket;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {

    HashMap<String,List<WebSocketSession>> sessions = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        String uuid = getSessionUuid(session);
        if(uuid != null) {
            List<WebSocketSession> webSocketSessionsForUuid = sessions.get(uuid);
            System.out.println(message);

            for (WebSocketSession webSocketSession : webSocketSessionsForUuid) {
                if (webSocketSession.isOpen()) {
                    webSocketSession.sendMessage(message);
                }

            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //the messages will be broadcasted to all users.
        String uuid = getSessionUuid(session);
        if (uuid != null) {
            List<WebSocketSession> webSocketSessions = sessions.get(uuid);
            if(webSocketSessions == null) {
                webSocketSessions = new ArrayList<>();
            }
            webSocketSessions.add(session);
            sessions.put(uuid, webSocketSessions);
        } else {
            System.out.println("COULD NOT FIND SESSION UUID FOR SESSION " + session);
        }
    }

    public String getSessionUuid(WebSocketSession session) {
        if (session.getUri() != null) {
            String[] SplitURL = session.getUri().getPath().split("/");
            return SplitURL[SplitURL.length - 1];

        } else {
            return null;
        }
    }
}