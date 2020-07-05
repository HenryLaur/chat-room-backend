package com.chatroom.chatroom.message;

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
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String messageBody;
    private String user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CHANNEL_UUID")
    @JsonIgnore
    private Channel channel;

}
