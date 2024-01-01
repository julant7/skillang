package com.julant.skillang.controllers;

import com.julant.skillang.dto.SendMessageRequest;
import com.julant.skillang.exception.ChatException;
import com.julant.skillang.exception.MessageException;
import com.julant.skillang.exception.UserException;
import com.julant.skillang.model.Message;
import com.julant.skillang.model.User;
import com.julant.skillang.service.MessageService;
import com.julant.skillang.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class
MessageController {
    private MessageService messageService;
    private UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest request, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User user = userService.findUserByJwt(jwt);
        request.setUserId(user.getId());
        Message message = messageService.sendMessage(request);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getChatsMessagesHandler(@PathVariable Long chatId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User user = userService.findUserByJwt(jwt);
        List<Message> messages = messageService.getChatMessages(chatId, user);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessageHandler(@PathVariable Long messageId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException, MessageException {
        User user = userService.findUserByJwt(jwt);
        messageService.deleteMessage(messageId, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
