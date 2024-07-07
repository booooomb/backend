package com.jyc.modulecore.chat_room;

import com.jyc.modulecore.keyword.Keyword;
import com.jyc.modulecore.message.Message;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "chat_room")
@Getter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @OneToMany(mappedBy = "chatRoom")
    private List<Message> messages;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ChatRoom() {}

    public ChatRoom(Keyword keyword, List<Message> messages, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.keyword = keyword;
        this.messages = messages;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
