package com.stackroute.activitystreammessages.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.activitystreammessages.dao.MessageDAO;
import com.stackroute.activitystreammessages.entity.Message;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;
	
	@Override
	public List<Message> getAllMessages() {
		return messageDAO.getAllMessages();
	}

	@Override
	public Message getMessageById(Long Id) {
		Message obj = messageDAO.getMessageById(Id);
		return obj;
	}

	@Override
	public List<Message> getMessageBySender(Long senderId) {
		List<Message> obj = messageDAO.getMessageBySender(senderId);
		return obj;
	}

	@Override
	public boolean addMessage(Message message) {
		messageDAO.addMessage(message);
 	   	return true;
	}

	@Override
	public void updateMessage(Message message) {
		messageDAO.updateMessage(message);
	}

	@Override
	public void deleteMessage(Long Id) {
		messageDAO.deleteMessage(Id);
	}

}
