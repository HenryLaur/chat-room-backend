package com.chatroom.chatroom.controller;

import com.chatroom.chatroom.channels.Channel;
import com.chatroom.chatroom.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    public ChannelService channelService;

    @PostMapping("/create")
    public void createChannel(@RequestBody Channel channel) {
        channelService.saveChannel(channel);
    }
    @GetMapping("/{uuid}")
    public Channel createChannel(@PathVariable String uuid) {
        return channelService.getByUuid(uuid).orElse(null);
    }

}
