package com.chatroom.chatroom.channels;

import com.chatroom.chatroom.message.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Channel {
    private String name;
    @Id

    private String uuid;
    @OneToMany(mappedBy = "channel")
    @JsonIgnore
    private List<Message> messages;
}
