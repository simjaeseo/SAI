package com.ssafy.sai.api.controller;

import com.ssafy.sai.api.service.ChattingService;
import com.ssafy.sai.db.entity.Chatting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChattingController {
    @Autowired
    private ChattingService chattingService;

    @MessageMapping("/receive")
    @SendTo("/send")
    public Chatting chattingHandler(Chatting chatting) {
        return chattingService.chattingHandler(chatting);
    }
}