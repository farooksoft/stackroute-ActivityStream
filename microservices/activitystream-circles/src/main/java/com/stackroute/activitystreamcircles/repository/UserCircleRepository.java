package com.stackroute.activitystreamcircles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stackroute.activitystreamcircles.entity.*;

@Repository
public interface UserCircleRepository extends JpaRepository<UserCircle, Long> {
	public UserCircle findByUserIdAndCircleId(Long userId, Long circleId);
	public List<UserCircle> findByUserId(Long userId);
}

