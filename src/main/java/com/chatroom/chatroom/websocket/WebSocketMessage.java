package com.chatroom.chatroom.websocket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WebSocketMessage {
    private String type;
    private WebSocketMessageContent content;
}
