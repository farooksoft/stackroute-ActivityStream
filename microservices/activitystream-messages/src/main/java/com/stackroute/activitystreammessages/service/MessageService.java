package com.stackroute.activitystreammessages.service;

import java.util.List;

import com.stackroute.activitystreammessages.entity.Message;

public interface MessageService {
	List<Message> getAllMessages();
    Message getMessageById(Long Id);
    List<Message> getMessageBySender(Long senderId);
    List<Message> getMessageByCircle(Long circleId);
    boolean addMessage(Message message);
    void updateMessage(Message message);
    void deleteMessage(Long Id);
}
