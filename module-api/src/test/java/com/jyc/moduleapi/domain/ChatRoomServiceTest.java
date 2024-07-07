package com.jyc.moduleapi.domain;

import com.jyc.moduleapi.controller.response.ChatRoomResponseDto;
import com.jyc.modulecore.Status;
import com.jyc.modulecore.chat_room.ChatRoom;
import com.jyc.modulecore.chat_room.ChatRoomRepository;
import com.jyc.modulecore.keyword.Keyword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChatRoomServiceTest {

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @InjectMocks
    private ChatRoomService chatRoomService;

    private Keyword keyword;
    private ChatRoom chatRoom1;
    private ChatRoom chatRoom2;

    @BeforeEach
    public void setUp() {
        keyword = new Keyword("jyc in the building~", 1, Status.ACTIVE, null, LocalDateTime.now(), LocalDateTime.now());
        chatRoom1 = new ChatRoom(keyword, null, LocalDateTime.now(), LocalDateTime.now());
        chatRoom2 = new ChatRoom(keyword, null, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    @DisplayName("존재하는 모든 채팅방을 조회하는데 성공한다.")
    public void successFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ChatRoom> chatRooms = new PageImpl<>(Arrays.asList(chatRoom1, chatRoom2), pageable, 2);

        when(chatRoomRepository.findAll(pageable)).thenReturn(chatRooms);

        Page<ChatRoomResponseDto> result = chatRoomService.findAll(pageable);

        assertEquals(2, result.getTotalElements());

        ChatRoomResponseDto dto1 = result.getContent().get(0);
        assertEquals("jyc in the building~", dto1.keyword().getKeyword());
        assertEquals(Status.ACTIVE, dto1.keyword().getStatus());

        ChatRoomResponseDto dto2 = result.getContent().get(1);
        assertEquals("jyc in the building~", dto2.keyword().getKeyword());
        assertEquals(Status.ACTIVE, dto2.keyword().getStatus());
    }

    @Test
    @DisplayName("채팅방에 메시지가 작성되지 않은 채팅방 조회에 성공한다.")
    public void successFindAllWithNullFields() {
        ChatRoom chatRoomWithNulls = new ChatRoom(keyword, null, LocalDateTime.now(), LocalDateTime.now());
        Pageable pageable = PageRequest.of(0, 10);
        Page<ChatRoom> chatRooms = new PageImpl<>(Collections.singletonList(chatRoomWithNulls), pageable, 1);

        when(chatRoomRepository.findAll(pageable)).thenReturn(chatRooms);

        Page<ChatRoomResponseDto> result = chatRoomService.findAll(pageable);

        assertEquals(1, result.getTotalElements());

        ChatRoomResponseDto dto = result.getContent().get(0);
        assertEquals(keyword, dto.keyword());
    }
}
