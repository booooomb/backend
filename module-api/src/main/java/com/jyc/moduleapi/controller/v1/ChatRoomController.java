package com.jyc.moduleapi.controller.v1;

import com.jyc.moduleapi.controller.response.ApiResponse;
import com.jyc.moduleapi.controller.response.ChatRoomResponseDto;
import com.jyc.moduleapi.domain.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat-rooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping
    public ApiResponse<Page<ChatRoomResponseDto>> getAllChatRooms(Pageable pageable) {
        Page<ChatRoomResponseDto> chatRooms = chatRoomService.findAll(pageable);
        return ApiResponse.success(chatRooms);
    }
}