package com.chatroom.chatroom.services;

import com.chatroom.chatroom.repository.MessageRepository;
import com.chatroom.chatroom.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }

    public List<Message> getAllMessagesFromChannel(String channelUuid) {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(message -> {
            if(message.getChannel() != null && message.getChannel().getUuid().equals(channelUuid)) {
                messages.add(message);
            }
        });
        return messages;
    }
}
