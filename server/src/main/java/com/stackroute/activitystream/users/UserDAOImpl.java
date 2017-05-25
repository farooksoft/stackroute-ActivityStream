package com.stackroute.activitystream.users;

import java.util.List;
import com.stackroute.activitystream.users.User;

public interface UserDAOImpl{
    List<User> getAllUsers();
    User getUserById(Long userId);
    User getUserByName(String username);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long userId);
    boolean userExists(String username);
    boolean userValidate(String username, String password);
}