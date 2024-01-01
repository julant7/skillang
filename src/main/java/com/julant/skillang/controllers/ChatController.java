package com.julant.skillang.controllers;

import com.julant.skillang.dto.GroupChatRequest;
import com.julant.skillang.dto.SingleChatRequest;
import com.julant.skillang.exception.ChatException;
import com.julant.skillang.model.Chat;
import com.julant.skillang.model.User;
import com.julant.skillang.exception.UserException;
import com.julant.skillang.dto.CreateChatRequest;
import com.julant.skillang.service.ChatService;
import com.julant.skillang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.julant.skillang.model.Role.ADMIN;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;
    @Autowired
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @PostMapping("/single")
    public ResponseEntity<Chat> createSingleChatHandler(@RequestHeader("Authorization")String jwt, @RequestBody SingleChatRequest singleChatRequest)
            throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        Chat chat = chatService.createChat(reqUser, singleChatRequest.getUserId());
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/group")
    public ResponseEntity<Chat> createGroupHandler(@RequestHeader("Authorization") String jwt, @RequestBody GroupChatRequest groupChatRequest)
            throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        Chat chat = chatService.createGroup(groupChatRequest, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@RequestHeader("Authorization") String jwt, @PathVariable Long chatId)
            throws UserException, ChatException {
        User reqUser = userService.findUserByJwt(jwt);
        if (reqUser.getRole() == ADMIN) {
            Chat chat = chatService.findChatById(chatId);
            return new ResponseEntity<>(chat, HttpStatus.OK);
        }
        throw new UserException("Insufficient permissions");
    }

    @GetMapping("/user")
    public ResponseEntity<List<Chat>> findAllChatByUserIdHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        List<Chat> chats = chatService.findAllChatByUserId(reqUser.getId());
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @PutMapping("/{chatId}/add/{userId}")
    public ResponseEntity<Chat> addUserToGroupHandler(@PathVariable Long chatId, @PathVariable Long userId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserByJwt(jwt);
        Chat chat = chatService.addUserToGroup(userId, chatId, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PutMapping("/{chatId}/remove/{userId}")
    public ResponseEntity<Chat> removeUserFromGroupHandler(@PathVariable Long chatId, @PathVariable Long userId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserByJwt(jwt);
        Chat chat = chatService.removeFromGroup(userId, chatId, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<Void> deleteChatHandler(@PathVariable Long chatId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserByJwt(jwt);
        chatService.deleteChat(chatId, reqUser.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserByJwt(jwt);
        List<Chat> chats = chatService.findUsersChat(user.getId());
        return chats;
    }
}
