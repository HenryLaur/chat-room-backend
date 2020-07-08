package com.chatroom.chatroom.websocket;

import com.chatroom.chatroom.channels.Channel;
import com.chatroom.chatroom.message.Message;
import com.chatroom.chatroom.services.MessageService;
import com.chatroom.chatroom.services.UsersInChannelService;
import com.chatroom.chatroom.user.UserInChannel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SocketHandler extends TextWebSocketHandler {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UsersInChannelService usersInChannelService;

    Map<String,List<WebSocketSession>> sessions = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        String uuid = getSessionUuid(session);
        if(uuid != null) {
            List<WebSocketSession> webSocketSessionsForUuid = sessions.get(uuid);
            System.out.println(message.getPayload());
            System.out.println(webSocketSessionsForUuid);
            handleMessageType(session, message);
            for (WebSocketSession webSocketSession : webSocketSessionsForUuid) {
                if (webSocketSession.isOpen()) {
                    try {
                        webSocketSession.sendMessage(message);
                    } catch (EOFException e) {
                        System.out.println("ERROR: " + e);
                    }
                }
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
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
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("CLOSING: " + session);
        String uuid = getSessionUuid(session);
        if (uuid != null) {
            List<WebSocketSession> webSocketSessions = sessions.get(uuid);
            webSocketSessions.remove(session);
            sessions.put(uuid, webSocketSessions);
            UserInChannel userInChannelByWebSocketID = usersInChannelService.getUserInChannelByWebSocketID(session.getId());
            if(userInChannelByWebSocketID != null) {
                WebSocketMessage leaveMessageFromClosedConnection = createLeaveMessageFromClosedConnection(userInChannelByWebSocketID);
                sendMessageToAllConnectionsInChannel(leaveMessageFromClosedConnection,uuid);
            }

            usersInChannelService.deleteUserInChannelByWebSocketId(session.getId());


        } else {
            System.out.println("COULD NOT FIND SESSION UUID FOR SESSION " + session);
        }
    }

    private WebSocketMessage createLeaveMessageFromClosedConnection(UserInChannel userInChannel) {
        WebSocketMessage webSocketMessage = new WebSocketMessage();
        webSocketMessage.setType("LEAVE");
        WebSocketMessageContent webSocketMessageContent = new WebSocketMessageContent();
        webSocketMessageContent.setChannel(userInChannel.getChannel());
        webSocketMessageContent.setUser(userInChannel.getUsername());
        webSocketMessage.setContent(webSocketMessageContent);
        return webSocketMessage;
    }

    private void sendMessageToAllConnectionsInChannel(WebSocketMessage webSocketMessage, String channelUuid) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<WebSocketSession> webSocketSessionsForUuid = sessions.get(channelUuid);
        String jsonMessage = objectMapper.writeValueAsString(webSocketMessage);
        TextMessage textMessage = new TextMessage(jsonMessage);
        for (WebSocketSession webSocketSession : webSocketSessionsForUuid) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(textMessage);
            }
        }
    }
    private void SaveMessageToDB(WebSocketSession session, Message message) {
        Channel channel = new Channel();
        channel.setUuid(getSessionUuid(session));
        message.setChannel(channel);
        messageService.saveMessage(message);
    }

    public String getSessionUuid(WebSocketSession session) {
        if (session.getUri() != null) {
            String[] SplitURL = session.getUri().getPath().split("/");
            return SplitURL[SplitURL.length - 1];

        } else {
            return null;
        }
    }

    private void handleMessageType(WebSocketSession session, TextMessage message) {
        WebSocketMessage webSocketMessage = new Gson().fromJson(message.getPayload(), WebSocketMessage.class);
        System.out.println(webSocketMessage.getType());
        switch (webSocketMessage.getType()) {
            case "MESSAGE":
                System.out.println(webSocketMessage.getContent().getMessage());
                SaveMessageToDB(session, webSocketMessage.getContent().getMessage());
                break;
            case "JOIN": {
                UserInChannel userInChannel = new UserInChannel();
                userInChannel.setChannel(webSocketMessage.getContent().getChannel());
                userInChannel.setUsername(webSocketMessage.getContent().getUser());
                userInChannel.setWebSocketId(session.getId());
                usersInChannelService.saveUserInChannel(userInChannel);
                break;
            }
            case "LEAVE": {
                UserInChannel userInChannel = new UserInChannel();
                userInChannel.setChannel(webSocketMessage.getContent().getChannel());
                userInChannel.setUsername(webSocketMessage.getContent().getUser());
                userInChannel.setWebSocketId(session.getId());
                usersInChannelService.deleteUserInChannel(userInChannel);
                break;
            }
        }
    }
}