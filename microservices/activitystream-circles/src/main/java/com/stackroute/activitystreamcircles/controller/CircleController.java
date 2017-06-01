package com.stackroute.activitystreamcircles.controller;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.stackroute.activitystreamcircles.entity.Circle;
import com.stackroute.activitystreamcircles.service.CircleService;
import com.stackroute.activitystreamcircles.entity.UserCircle;

@Controller
@RequestMapping("circles/")
public class CircleController {

	@Autowired
	private CircleService circleService;
	
	@GetMapping("circle/{id}")
	public ResponseEntity<Circle> getCircleById(@PathVariable("id") Long id) {
		Circle circle = circleService.getCircleById(id);
		return new ResponseEntity<Circle>(circle, HttpStatus.OK);
	}
	
	@GetMapping("circles")
	public ResponseEntity<List<Circle>> getAllCircles() {
		List<Circle> list = circleService.getAllCircles();
		return new ResponseEntity<List<Circle>>(list, HttpStatus.OK);
	}
	
	@PostMapping("circle")
	public ResponseEntity<Void> addCircle(@RequestBody Circle circle, UriComponentsBuilder builder) {
        boolean flag = circleService.addCircle(circle);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/circle/{id}").buildAndExpand(circle.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("circle")
	public ResponseEntity<Circle> updateCircle(@RequestBody Circle circle) {
		circleService.updateCircle(circle);
		return new ResponseEntity<Circle>(circle, HttpStatus.OK);
	}
	
	@DeleteMapping("circle/{id}")
	public ResponseEntity<Void> deleteCircle(@PathVariable("id") Long id) {
		circleService.deleteCircle(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/usercircle", method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestParam(value="userid") Long userid, @RequestParam(value="circleid") Long circleid, UriComponentsBuilder builder) {
        boolean flag = circleService.addUser(userid, circleid);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/usercircle", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUserCircle(@RequestParam(value="userid") Long userid, @RequestParam(value="circleid") Long circleid) {
		circleService.removeUser(userid, circleid);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/usercircles", method = RequestMethod.GET)
	public ResponseEntity<List<UserCircle>> getUserCircles(@RequestParam(value="userid") Long userid) {
		List<UserCircle> list = circleService.getUserCircles(userid);
		return new ResponseEntity<List<UserCircle>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usercircle", method = RequestMethod.GET)
	public ResponseEntity<UserCircle> getUserCircle(@RequestParam(value="userid") Long userid, @RequestParam(value="circleid") Long circleid) {
		UserCircle usercircle = circleService.getUserCircle(userid, circleid);
		return new ResponseEntity<UserCircle>(usercircle, HttpStatus.OK);
	}
}
