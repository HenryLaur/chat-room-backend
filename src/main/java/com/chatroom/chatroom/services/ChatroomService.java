package com.chatroom.chatroom.services;

import com.chatroom.chatroom.repository.ChatroomRepository;
import com.chatroom.chatroom.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ChatroomService {
    @Autowired
    private ChatroomRepository chatroomRepository;

    public void saveMessage(Message message) {
        chatroomRepository.save(message);
    }

    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        chatroomRepository.findAll().forEach(messages::add);
        return messages;
    }
}
