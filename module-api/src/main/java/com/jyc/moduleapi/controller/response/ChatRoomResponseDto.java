package com.jyc.moduleapi.controller.response;

import com.jyc.modulecore.chat_room.ChatRoom;
import com.jyc.modulecore.keyword.Keyword;
import com.jyc.modulecore.message.Message;

import java.time.LocalDateTime;
import java.util.List;

public record ChatRoomResponseDto(
    Integer id,
    Keyword keyword,
    List<Message> messages,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static ChatRoomResponseDto from(ChatRoom chatRoom) {
        return new ChatRoomResponseDto(
            chatRoom.getId(),
            chatRoom.getKeyword(),
            chatRoom.getMessages(),
            chatRoom.getCreatedAt(),
            chatRoom.getUpdatedAt()
        );
    }
}
