package com.chatroom.chatroom.controller;

import com.chatroom.chatroom.channels.Channel;
import com.chatroom.chatroom.message.Message;
import com.chatroom.chatroom.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService chatroomService;

    @GetMapping("/all")
    public List<Message> all() {
        return chatroomService.getAll();
    }

    @GetMapping("/{uuid}")
    public List<Message>  getMessagesFromChannel(@PathVariable String uuid) {
        return chatroomService.getAllMessagesFromChannel(uuid);
    }

}
