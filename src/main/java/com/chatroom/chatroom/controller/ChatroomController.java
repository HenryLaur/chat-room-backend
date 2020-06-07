package com.chatroom.chatroom.controller;

import com.chatroom.chatroom.message.Message;
import com.chatroom.chatroom.services.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatroomController {

    @Autowired
    private ChatroomService chatroomService;

    @PostMapping("/message")
    public Message message(@RequestBody Message message) {
        chatroomService.saveMessage(message);
        return message;
    }
    @GetMapping("/all")
    public List<Message> all() {
        System.out.println(chatroomService.getAll().get(0).getId());
        return chatroomService.getAll();
    }
}
