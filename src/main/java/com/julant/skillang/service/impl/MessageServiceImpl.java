package com.julant.skillang.service.impl;

import com.julant.skillang.dto.SendMessageRequest;
import com.julant.skillang.exception.ChatException;
import com.julant.skillang.exception.MessageException;
import com.julant.skillang.exception.UserException;
import com.julant.skillang.model.Chat;
import com.julant.skillang.model.Message;
import com.julant.skillang.model.User;
import com.julant.skillang.repository.MessageRepository;
import com.julant.skillang.service.ChatService;
import com.julant.skillang.service.MessageService;
import com.julant.skillang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private UserService userService;
    private ChatService chatService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserService userService, ChatService chatService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.chatService = chatService;
    }
    @Override
    public Message sendMessage(SendMessageRequest request) throws UserException, ChatException {
        User user = userService.findUserById(request.getUserId());
        Chat chat = chatService.findChatById(request.getChatId());

        Message message = new Message();
        message.setChat(chat);
        message.setUser(user);
        message.setContent(request.getContent());
        message.setTimestamp(System.currentTimeMillis() / 1000);
        return message;
    }

    @Override
    public List<Message> getChatMessages(Long chatId, User reqUser) throws ChatException, UserException {
        Chat chat = chatService.findChatById(chatId);
        if (!chat.getUsers().contains(reqUser)) {
            throw new UserException("You are not a member of this chat with id " + chat.getId());
        }
        List<Message> messages = messageRepository.findByChatId(chat.getId());
        return messages;
    }


    @Override
    public Message findMessageById(Long messageId) throws MessageException {
        Optional<Message> opt = messageRepository.findById(messageId);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new MessageException("Message not found with id " + messageId);
    }

    @Override
    public void deleteMessage(Long messageId, User reqUser) throws MessageException, UserException {
        Message message = findMessageById(messageId);
        if (message.getUser().getId().equals(reqUser.getId())) {
            messageRepository.deleteById(messageId);
        }
        throw new UserException("You can't delete another user's message" + reqUser.getFullName());
    }

}
