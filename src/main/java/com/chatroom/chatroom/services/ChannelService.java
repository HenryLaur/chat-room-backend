package com.chatroom.chatroom.services;

import com.chatroom.chatroom.channels.Channel;
import com.chatroom.chatroom.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;

    public void saveChannel(Channel channel) {
        channelRepository.save(channel);
    }

    public Optional<Channel> getByUuid(String uuid) {
        return channelRepository.findById(uuid);
    }
}
