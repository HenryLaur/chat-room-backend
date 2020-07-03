package com.chatroom.chatroom.repository;

import com.chatroom.chatroom.message.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, String> {
}
