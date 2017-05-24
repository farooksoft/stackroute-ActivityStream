package com.stackroute.activitystream.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.activitystream.users.UserDAOImpl;
import com.stackroute.activitystream.users.User;

@Service
public class UserService implements UserServiceImpl {
	
	@Autowired
	private UserDAOImpl userDAO;
	
	@Override
	public User getUserById(Long userId) {
		User obj = userDAO.getUserById(userId);
		return obj;
	}
	
	@Override
	public List<User> getAllUsers(){
		return userDAO.getAllUsers();
	}
	
	@Override
	public synchronized boolean addUser(User user){
       if (userDAO.userExists(user.getName())) {
    	   return false;
       } else {
    	   userDAO.addUser(user);
    	   return true;
       }
	}
	
	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}
	
	@Override
	public void deleteUser(Long userId) {
		userDAO.deleteUser(userId);
	}
	
	@Override
	public boolean userValidate(String name, String password) {
		if (userDAO.userValidate(name, password)) {
	    	   return true;
	       } else {
	    	   return false;
	       }
	}
}