package com.stackroute.activitystreamcircles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.stackroute.activitystreamcircles.entity.*;

@RepositoryRestResource
public interface CircleRepository extends JpaRepository<Circle, Long> {

}
