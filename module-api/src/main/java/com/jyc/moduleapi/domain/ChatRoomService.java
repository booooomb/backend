package com.jyc.moduleapi.domain;

import com.jyc.moduleapi.controller.response.ChatRoomResponseDto;
import com.jyc.modulecore.chat_room.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Page<ChatRoomResponseDto> findAll(Pageable pageable) {
        return chatRoomRepository.findAll(pageable).map(chatRoom ->
            ChatRoomResponseDto.from(chatRoom)
        );
    }
}