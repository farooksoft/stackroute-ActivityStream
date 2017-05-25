package com.stackroute.activitystream.users;
import java.util.List;

import com.stackroute.activitystream.users.User;

public interface UserServiceImpl {
     List<User> getAllUsers();
     User getUserById(Long userId);
     User getUserByName(String username);
     boolean addUser(User user);
     void updateUser(User user);
     void deleteUser(Long userId);
     boolean userValidate(String name, String password);
}