package com.chatroom.chatroom.controller;

import com.chatroom.chatroom.channels.Channel;
import com.chatroom.chatroom.services.ChannelService;
import com.chatroom.chatroom.services.UsersInChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    public ChannelService channelService;

    @Autowired
    private UsersInChannelService usersInChannelService;

    @PostMapping("/create")
    public List<String> createChannel(@RequestBody Channel channel) {
        channelService.saveChannel(channel);
        return usersInChannelService.getUsersInChannel(channel.getUuid());
    }
    @GetMapping("/{uuid}")
    public Channel createChannel(@PathVariable String uuid) {
        return channelService.getByUuid(uuid).orElse(null);
    }
}
