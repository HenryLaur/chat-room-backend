package com.chatroom.chatroom.controller;

import com.chatroom.chatroom.message.Message;
import com.chatroom.chatroom.services.UsersInChannelService;
import com.chatroom.chatroom.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserInChannelController {
    @Autowired
    private UsersInChannelService usersInChannelService;
    @GetMapping("/{uuid}")
    public List<User> getUsersInChannel(@PathVariable String uuid) {
        return usersInChannelService.getUsersInChannel(uuid);
    }
}
