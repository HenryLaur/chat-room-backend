package com.chatroom.chatroom.controller;

import com.chatroom.chatroom.services.UsersInChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserInChannelController {
    @Autowired
    private UsersInChannelService usersInChannelService;
    @GetMapping("/{uuid}")
    public List<String> getUsersInChannel(@PathVariable String uuid) {
        return usersInChannelService.getUsersInChannel(uuid);
    }
    @PostMapping("/channels")
    public Map<String, List<String>> getUsersInChannels(@RequestBody String[] channelUuids) {
        System.out.println(Arrays.toString(channelUuids));
        System.out.println(usersInChannelService.getUsersInChannelList(channelUuids).toString());
        return usersInChannelService.getUsersInChannelList(channelUuids);
    }
}
