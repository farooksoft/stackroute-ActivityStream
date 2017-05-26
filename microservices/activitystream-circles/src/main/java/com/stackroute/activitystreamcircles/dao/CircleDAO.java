package com.stackroute.activitystreamcircles.dao;

import java.util.List;
import com.stackroute.activitystreamcircles.entity.Circle;
import com.stackroute.activitystreamcircles.entity.UserCircle;

public interface CircleDAO{
    List<Circle> getAllCircles();
    Circle getCircleById(Long Id);
    Circle getCircleByName(String circlename);
    void addCircle(Circle circle);
    void updateCircle(Circle circle);
    void deleteCircle(Long Id);
    boolean circleExists(String circlename);
    
    boolean addUser(Long userID, Long circleID);
	boolean removeUser(Long userID, Long circleID);
	List<UserCircle> getUserCircles(Long userID);
	UserCircle getUserCircle(Long userID, Long circleID);
}