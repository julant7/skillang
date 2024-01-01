package com.julant.skillang.controllers;

import com.julant.skillang.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class RealtimeChatController {
    private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/message")
    @SendTo("/goup/public")
    public Message recieveMessage(@Payload Message message) {
        simpMessagingTemplate.convertAndSend("/goup/"+message.getChat().getId().toString(), message);

        return message;
    }
}
