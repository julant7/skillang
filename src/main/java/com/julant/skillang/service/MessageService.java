package com.julant.skillang.service;

import com.julant.skillang.dto.SendMessageRequest;
import com.julant.skillang.exception.ChatException;
import com.julant.skillang.exception.MessageException;
import com.julant.skillang.exception.UserException;
import com.julant.skillang.model.Message;
import com.julant.skillang.model.User;

import java.util.List;

public interface MessageService {
    public Message sendMessage(SendMessageRequest request) throws UserException, ChatException;
    public List<Message> getChatMessages(Long chatId, User reqUser) throws ChatException, UserException;
    public Message findMessageById(Long messageId) throws MessageException;
    public void deleteMessage(Long messageId, User reqUser) throws MessageException, UserException;
}
