package com.chatroom.chatroom.services;

import com.chatroom.chatroom.repository.UsersInChannelRepository;
import com.chatroom.chatroom.user.User;
import com.chatroom.chatroom.user.UserInChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class UsersInChannelService {
    @Autowired
    private UsersInChannelRepository usersInChannelRepository;

    public void saveUserInChannel(UserInChannel userInChannel) {
        usersInChannelRepository.save(userInChannel);
    }

    public void deleteUserInChannel(UserInChannel userInChannel) {
        usersInChannelRepository.findAll().forEach(DBUserInChannel -> {
            if(DBUserInChannel.getChannel().getUuid().equals(userInChannel.getChannel().getUuid()) && DBUserInChannel.getUsername().equals(userInChannel.getUsername())) {
                usersInChannelRepository.delete(DBUserInChannel);
            }
        });
    }
    public void deleteUserInChannelByWebSocketId(String webSocketId) {
        usersInChannelRepository.findAll().forEach(userInChannel -> {
            if(userInChannel.getWebSocketId().equals(webSocketId)) {
                usersInChannelRepository.delete(userInChannel);

            }
        });
    }
    public List<User> getUsersInChannel(String channelUuid) {
        List<User> users = new ArrayList<>();
        usersInChannelRepository.findAll().forEach(userInChannel -> {
            if(userInChannel.getChannel().getUuid().equals(channelUuid)) {
                User user = new User();
                user.setName(userInChannel.getUsername());
                users.add(user);
            }
        });
        return users;
    }
}
