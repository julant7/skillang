package com.julant.skillang.dto;

import java.util.List;

public class GroupChatRequest {
    private List<Long> userIds;
    private String chatName;
    private String chatImage;

    public GroupChatRequest() {
    }

    public GroupChatRequest(List<Long> userIds, String chatName, String chatImage) {
        this.userIds = userIds;
        this.chatName = chatName;
        this.chatImage = chatImage;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatImage(String chatImage) {
        this.chatImage = chatImage;
    }

    public String getChatImage() {
        return chatImage;
    }
}
