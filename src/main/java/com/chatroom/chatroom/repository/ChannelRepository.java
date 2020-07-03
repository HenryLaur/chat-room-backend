package com.chatroom.chatroom.repository;

import com.chatroom.chatroom.channels.Channel;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<Channel, String> {
}
