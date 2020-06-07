package com.chatroom.chatroom.repository;

import com.chatroom.chatroom.message.Message;
import org.springframework.data.repository.CrudRepository;

public interface ChatroomRepository extends CrudRepository<Message, Long> {
}
