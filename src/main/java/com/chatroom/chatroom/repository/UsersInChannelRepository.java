package com.chatroom.chatroom.repository;

import com.chatroom.chatroom.user.UserInChannel;
import org.springframework.data.repository.CrudRepository;

public interface UsersInChannelRepository extends CrudRepository<UserInChannel, Long> {
}
