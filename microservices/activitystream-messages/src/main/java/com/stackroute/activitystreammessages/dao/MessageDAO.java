package com.stackroute.activitystreammessages.dao;

import java.util.List;

import com.stackroute.activitystreammessages.entity.Message;

public interface MessageDAO {
	List<Message> getAllMessages();
    Message getMessageById(Long Id);
    List<Message> getMessageBySender(Long senderId);
    List<Message> getMessageByCircle(Long circleId);
    void addMessage(Message message);
    void updateMessage(Message message);
    void deleteMessage(Long Id);
}
