package com.chatroom.chatroom.websocket;

import com.chatroom.chatroom.channels.Channel;
import com.chatroom.chatroom.message.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WebSocketMessageContent {
    private String user;
    private Channel channel;
    private Message message;
}
