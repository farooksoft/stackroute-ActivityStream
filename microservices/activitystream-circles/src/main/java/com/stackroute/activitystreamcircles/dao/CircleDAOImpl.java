package com.stackroute.activitystreamcircles.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystreamcircles.entity.Circle;
import com.stackroute.activitystreamcircles.entity.UserCircle;

import com.stackroute.activitystreamcircles.repository.UserCircleRepository;

@Transactional
@Repository
public class CircleDAOImpl implements CircleDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Autowired
	private UserCircleRepository userCircleRepository;

	@SuppressWarnings("unchecked")
	@Override
	public List<Circle> getAllCircles() {
		String hql = "FROM Circle as crcl ORDER BY crcl.id";
		return (List<Circle>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Circle getCircleById(Long Id) {
		return entityManager.find(Circle.class, Id);
	}

	@Override
	public Circle getCircleByName(String circlename) {
		String hql = "FROM Circle as crcl WHERE crcl.circlename = ?";
		return (Circle) entityManager.createQuery(hql).setParameter(1, circlename).getResultList().get(0);
	}

	@Override
	public void addCircle(Circle circle) {
		entityManager.persist(circle);
	}

	@Override
	public void updateCircle(Circle circle) {
		Circle newCircle = getCircleById(circle.getId());
		newCircle.setCirclename(circle.getCirclename());
		newCircle.setCircledescr(circle.getCircledescr());
		entityManager.flush();		
	}

	@Override
	public void deleteCircle(Long Id) {
		entityManager.remove(getCircleById(Id));
	}

	@Override
	public boolean circleExists(String circlename) {
		String hql = "FROM Circle as crcl WHERE crcl.circlename = ?";
		int count = entityManager.createQuery(hql).setParameter(1, circlename)
		              .getResultList().size();
		return count > 0 ? true : false;
	}

	@Override
	public boolean addUser(Long userid, Long circleid) {
		
		UserCircle userCircle = userCircleRepository.findByUserIdAndCircleId(userid, circleid);
		
		if(userCircle == null) {
			UserCircle newuserCircle = new UserCircle();
			newuserCircle.setCircleId(circleid);
			newuserCircle.setUserId(userid);
			
			userCircleRepository.save(newuserCircle);
			
			return true;
		}
	
		return false;
	}

	@Override
	public boolean removeUser(Long userId, Long circleId) {
		UserCircle userCircle = userCircleRepository.findByUserIdAndCircleId(userId, circleId);
		
		userCircleRepository.delete(userCircle);
		return true;
	}

	@Override
	public List<UserCircle> getUserCircles(Long userId) {
		return userCircleRepository.findByUserId(userId);
	}

	@Override
	public UserCircle getUserCircle(Long userId, Long circleId) {
		return (UserCircle) userCircleRepository.findByUserIdAndCircleId(userId, circleId);
	}

}
