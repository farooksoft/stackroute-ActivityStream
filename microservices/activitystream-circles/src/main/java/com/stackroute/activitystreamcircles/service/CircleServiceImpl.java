package com.stackroute.activitystreamcircles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.activitystreamcircles.dao.CircleDAO;
import com.stackroute.activitystreamcircles.entity.Circle;
import com.stackroute.activitystreamcircles.entity.UserCircle;

@Service
public class CircleServiceImpl implements CircleService {

	@Autowired
	private CircleDAO circleDAO;
	
	@Override
	public List<Circle> getAllCircles() {
		return circleDAO.getAllCircles();
	}

	@Override
	public Circle getCircleById(Long Id) {
		Circle obj = circleDAO.getCircleById(Id);
		return obj;
	}

	@Override
	public Circle getCircleByName(String circlename) {
		Circle obj = circleDAO.getCircleByName(circlename);
		return obj;
	}

	@Override
	public synchronized boolean addCircle(Circle circle) {
		if (circleDAO.circleExists(circle.getCirclename())) {
			return false;
		} else {
    	   circleDAO.addCircle(circle);
    	   return true;
		}
	}

	@Override
	public void updateCircle(Circle circle) {
		circleDAO.updateCircle(circle);
	}

	@Override
	public void deleteCircle(Long Id) {
		circleDAO.deleteCircle(Id);
	}

	@Override
	public boolean addUser(Long userID, Long circleID) {
		return circleDAO.addUser(userID, circleID);
	}

	@Override
	public boolean removeUser(Long userID, Long circleID) {
		return circleDAO.removeUser(userID, circleID);
	}

	@Override
	public List<UserCircle> getUserCircles(Long userID) {
		return circleDAO.getUserCircles(userID);
	}

	@Override
	public UserCircle getUserCircle(Long userID, Long circleID) {
		return circleDAO.getUserCircle(userID, circleID);
	}

}
