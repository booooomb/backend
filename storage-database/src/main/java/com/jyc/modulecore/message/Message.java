package com.jyc.modulecore.message;


import com.jyc.modulecore.chat_room.ChatRoom;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Getter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Column(name = "user_id")
    private Integer userId;

    @Lob
    @Column(name = "contents")
    private String contents;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Message() {
    }

    public Message(ChatRoom chatRoom, Integer userId, String contents) {
        this.chatRoom = chatRoom;
        this.userId = userId;
        this.contents = contents;
    }

    public Message(ChatRoom chatRoom, Integer userId, String contents, LocalDateTime createdAt) {
        this.chatRoom = chatRoom;
        this.userId = userId;
        this.contents = contents;
        this.createdAt = createdAt;
    }
}
