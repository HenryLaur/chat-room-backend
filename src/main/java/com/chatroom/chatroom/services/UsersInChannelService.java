package com.chatroom.chatroom.services;

import com.chatroom.chatroom.repository.UsersInChannelRepository;
import com.chatroom.chatroom.user.UserInChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<String> getUsersInChannel(String channelUuid) {
        List<String> users = new ArrayList<>();
        usersInChannelRepository.findAll().forEach(userInChannel -> {
            if(userInChannel.getChannel().getUuid().equals(channelUuid)) {
                users.add(userInChannel.getUsername());
            }
        });
        return users;
    }
    public Map<String, List<String>> getUsersInChannelList(String[] channelUuids) {
        Map<String, List<String>> usersInChannels = new HashMap<>();
        for (String channelUuid : channelUuids) {
            System.out.println(new ArrayList<>(getUsersInChannel(channelUuid)) + "************");
            usersInChannels.put(channelUuid, new ArrayList<>(getUsersInChannel(channelUuid)));
        }
        System.out.println(usersInChannels.toString());
        return usersInChannels;
    }
}
