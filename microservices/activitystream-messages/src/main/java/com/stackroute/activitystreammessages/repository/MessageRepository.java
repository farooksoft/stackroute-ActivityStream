package com.stackroute.activitystreammessages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stackroute.activitystreammessages.entity.Message;;

@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message, Long> {

}
