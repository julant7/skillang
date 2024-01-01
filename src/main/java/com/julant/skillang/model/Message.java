package com.julant.skillang.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "timestamp",nullable = false)
    private Long timestamp;

    @Column(name = "image",nullable = false)
    private String image;

    @JoinColumn(name = "user",nullable = false)
    @ManyToOne
    private User user;

    @JoinColumn(name = "chat_id", nullable = false)
    @JsonIgnore
    @ManyToOne
    private Chat chat;

    public Message() {

    }

    public Message(Long id, String content, Long timestamp, User user, Chat chat) {
        super();
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.user = user;
        this.chat = chat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", image='" + image + '\'' +
                ", user=" + user +
                ", chat=" + chat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(content, message.content) &&
                Objects.equals(timestamp, message.timestamp) && Objects.equals(image, message.image) &&
                Objects.equals(user, message.user) && Objects.equals(chat, message.chat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, timestamp, image, user, chat);
    }
}
