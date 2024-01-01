package com.julant.skillang.service;

import com.julant.skillang.dto.GroupChatRequest;
import com.julant.skillang.exception.ChatException;
import com.julant.skillang.exception.UserException;
import com.julant.skillang.model.Chat;
import com.julant.skillang.model.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, Long userId2) throws UserException;
    public Chat findChatById(Long chatId) throws ChatException;
    public List<Chat> findUsersChat(Long userId);
    public List<Chat> findAllChatByUserId(Long userId) throws UserException;
    public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException;
    public Chat addUserToGroup(Long userId, Long chatId, User reqUser) throws UserException, ChatException;
    public Chat renameGroup(Long chatId, String groupName, User reqUser) throws UserException, ChatException;
    public Chat removeFromGroup(Long userId, Long chatId, User reqUser) throws UserException, ChatException;
    public void deleteChat(Long chatId, Long userId) throws ChatException, UserException;
}
