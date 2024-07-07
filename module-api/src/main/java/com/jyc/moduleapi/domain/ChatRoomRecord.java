package com.jyc.moduleapi.domain;


import com.jyc.modulecore.keyword.Keyword;
import com.jyc.modulecore.message.Message;

import java.time.LocalDateTime;
import java.util.List;

public record ChatRoomRecord(
    Integer id,
    Keyword keyword,
    List<Message> messages,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
