package com.jyc.modulecore.keyword;

import com.jyc.modulecore.Status;
import com.jyc.modulecore.chat_room.ChatRoom;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "keyword")
@Getter
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "rank")
    private Integer rank;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "keyword")
    private List<ChatRoom> chatRooms;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Keyword() {
    }

    public Keyword(String keyword, Integer rank, Status status, List<ChatRoom> chatRooms) {
        this.keyword = keyword;
        this.rank = rank;
        this.status = status;
        this.chatRooms = chatRooms;
    }

    public Keyword(String keyword, Integer rank, Status status, List<ChatRoom> chatRooms, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.keyword = keyword;
        this.rank = rank;
        this.status = status;
        this.chatRooms = chatRooms;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
