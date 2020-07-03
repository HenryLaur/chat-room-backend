package com.chatroom.chatroom.websocket;

import com.chatroom.chatroom.websocket.SocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketHandler(), "/ws/*").setAllowedOrigins("*");
    }
}