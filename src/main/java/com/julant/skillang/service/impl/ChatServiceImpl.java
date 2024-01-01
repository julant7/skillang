package com.julant.skillang.service.impl;

import com.julant.skillang.dto.GroupChatRequest;
import com.julant.skillang.exception.ChatException;
import com.julant.skillang.exception.UserException;
import com.julant.skillang.model.Chat;
import com.julant.skillang.model.User;
import com.julant.skillang.repository.ChatRepository;
import com.julant.skillang.service.ChatService;
import com.julant.skillang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final UserService userService;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    @Override
    public Chat createChat(User reqUser, Long userId2) throws UserException {
        User user = userService.findUserById(userId2);
        Chat isChatExist = chatRepository.findSingleChatByUserIds(user, reqUser);
        if (isChatExist != null) {
            return isChatExist;
        }
        Chat chat = new Chat();
        chat.setCreatedBy(reqUser);
        chat.getUsers().add(user);
        chat.getUsers().add(reqUser);
        chat.setGroup(false);

        return chat;

    }

    @Override
    public Chat findChatById(Long chatId) throws ChatException {
        Optional <Chat> chat = chatRepository.findById(chatId);
        if (chat.isEmpty()) {
            throw new ChatException("Chat not found with id -" + chatId);
        }
        return chat.get();
    }

    @Override
    public List<Chat> findUsersChat(Long userId) {
        return chatRepository.findByUserId(userId);
    }

    @Override
    public List<Chat> findAllChatByUserId(Long userId) throws UserException {
        User user = userService.findUserById(userId);
        List<Chat> chats = chatRepository.findByUserId(user.getId());
        return chats;
    }

    @Override
    public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException {
        Chat group = new Chat();
        group.setGroup(true);
        group.setChatImage(req.getChatImage());
        group.setChatName(req.getChatName());
        group.setCreatedBy(reqUser);
        group.getAdmins().add(reqUser);
        for (Long userId:req.getUserIds()) {
            User user = userService.findUserById(userId);
            group.getUsers().add(user);
        }

        return group;
    }

    @Override
    public Chat addUserToGroup(Long userId, Long chatId, User reqUser) throws UserException, ChatException {
        Optional<Chat> opt = chatRepository.findById(chatId);
        User user = userService.findUserById(userId);
        if (opt.isPresent()) {
            Chat chat = opt.get();
            if (chat.getAdmins().contains(reqUser)) {
                chat.getUsers().add(user);
                return chatRepository.save(chat);
            }
            throw new UserException("Not enough rights to add user");
        }
        throw new ChatException("Chat not found with id " + chatId);
    }

    @Override
    public Chat renameGroup(Long chatId, String groupName, User reqUser) throws UserException, ChatException {
        Optional<Chat> opt = chatRepository.findById(chatId);
        if (opt.isPresent()) {
            Chat chat = opt.get();
            if (chat.getUsers().contains(reqUser)) {
                chat.setChatName(groupName);
                return chatRepository.save(chat);
            }
            throw new UserException("You are not a member of this group");
        }
        throw new ChatException("Chat not found with id " + chatId);
    }

    @Override
    public Chat removeFromGroup(Long userId, Long chatId, User reqUser) throws UserException, ChatException {
        Optional<Chat> opt = chatRepository.findById(chatId);
        User user = userService.findUserById(userId);
        if (opt.isPresent()) {
            Chat chat = opt.get();
            if (chat.getAdmins().contains(reqUser)) {
                chat.getUsers().remove(reqUser);
                return chatRepository.save(chat);
            }
            else if (chat.getUsers().contains(reqUser) && user.getId().equals(reqUser.getId())) {
                chat.getUsers().remove(reqUser);
                return chatRepository.save(chat);
            }
            throw new UserException("Not enough rights to remove user");
        }
        throw new ChatException("Chat not found with id " + chatId);
    }

    @Override
    public void deleteChat(Long chatId, Long userId) throws ChatException, UserException {
        Optional<Chat> opt = chatRepository.findById(chatId);
        if (opt.isPresent()) {
            Chat chat = opt.get();
            chatRepository.deleteById(chatId);
        }
    }
}
