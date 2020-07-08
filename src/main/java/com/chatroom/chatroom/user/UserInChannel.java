package com.chatroom.chatroom.user;

import com.chatroom.chatroom.channels.Channel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class UserInChannel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String username;
    @JsonIgnore
    private String webSocketId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CHANNEL_UUID")
    @JsonIgnore
    private Channel channel;
}
