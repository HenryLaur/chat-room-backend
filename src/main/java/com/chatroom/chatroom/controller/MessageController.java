package com.chatroom.chatroom.controller;

import com.chatroom.chatroom.channels.Channel;
import com.chatroom.chatroom.message.Message;
import com.chatroom.chatroom.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService chatroomService;

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
