package com.stackroute.activitystreammessages.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.stackroute.activitystreammessages.service.MessageService;
import com.stackroute.activitystreammessages.entity.Message;

@Controller
@RequestMapping("messages/")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@GetMapping("message/{id}")
	public ResponseEntity<Message> getMessageById(@PathVariable("id") Long id) {
		Message message = messageService.getMessageById(id);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@GetMapping("message")
	public ResponseEntity<List<Message>> getAllMessages() {
		List<Message> list = messageService.getAllMessages();
		return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
	}
	/*
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public ResponseEntity<List<Message>> getMessageByCircle(@RequestParam(value="circleid") Long circleId) {
		List<Message> messagelist = messageService.getMessageByCircle(circleId);
		return new ResponseEntity<List<Message>>(messagelist, HttpStatus.OK);
	}
	*/
	@GetMapping("/message/circle/{circleId}")
	public ResponseEntity<List<Message>> getMessageByCircle(@PathVariable("circleId") Long circleId) {
		List<Message> messagelist = messageService.getMessageByCircle(circleId);
		return new ResponseEntity<List<Message>>(messagelist, HttpStatus.OK);
	}
	
	@PostMapping("message")
	public ResponseEntity<Void> addMessage(@RequestBody Message message, UriComponentsBuilder builder) {
        boolean flag = messageService.addMessage(message);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/message/{id}").buildAndExpand(message.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("message")
	public ResponseEntity<Message> updateMessage(@RequestBody Message message) {
		messageService.updateMessage(message);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@DeleteMapping("message/{id}")
	public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long id) {
		messageService.deleteMessage(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
