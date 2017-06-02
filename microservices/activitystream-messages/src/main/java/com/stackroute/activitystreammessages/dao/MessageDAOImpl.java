package com.stackroute.activitystreammessages.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystreammessages.entity.Message;

@Transactional
@Repository
public class MessageDAOImpl implements MessageDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getAllMessages() {
		String hql = "FROM Message as msg ORDER BY msg.id";
		return (List<Message>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Message getMessageById(Long Id) {
		return entityManager.find(Message.class, Id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessageBySender(Long senderId) {
		String hql = "FROM Message as msg WHERE msg.senderId = ?";
		return (List<Message>) entityManager.createQuery(hql).setParameter(1, senderId).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessageByCircle(Long circleId) {
		String hql = "FROM Message as msg WHERE msg.circleId = ? ORDER BY msg.createddate DESC";
		return (List<Message>) entityManager.createQuery(hql).setParameter(1, circleId).getResultList();
	}

	@Override
	public void addMessage(Message message) {
		entityManager.persist(message);
	}

	@Override
	public void updateMessage(Message message) {
		Message newMessage = getMessageById(message.getId());
		newMessage.setMsgtext(message.getMsgtext());
		newMessage.setSenderid(message.getSenderid());
		newMessage.setCircleid(message.getCircleid());
		newMessage.setMsgtype(message.getMsgtype());
		newMessage.setCreateddate(message.getCreateddate());
		entityManager.flush();	
	}

	@Override
	public void deleteMessage(Long Id) {
		entityManager.remove(getMessageById(Id));
	}
	
	
}
