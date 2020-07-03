package com.chatroom.chatroom.channels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Channel {
    private String name;
    @Id
    private String uuid;
}
