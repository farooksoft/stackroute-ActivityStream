package com.stackroute.activitystream.users;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.users.User;

@Transactional
@Repository
public class UserDAO implements UserDAOImpl {
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public User getUserById(Long userId) {
		return entityManager.find(User.class, userId);
	}
	
	@Override
	public User getUserByName(String username) {
		String hql = "FROM User as usr WHERE usr.name = ?";
		return (User) entityManager.createQuery(hql).setParameter(1, username).getResultList().get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		String hql = "FROM User as usr ORDER BY usr.userId";
		return (List<User>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}
	
	@Override
	public void updateUser(User user) {
		User newUser = getUserById(user.getId());
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		entityManager.flush();
	}
	
	@Override
	public void deleteUser(Long userId) {
		entityManager.remove(getUserById(userId));
	}
	
	@Override
	public boolean userExists(String name) {
		String hql = "FROM User as usr WHERE usr.name = ?";
		int count = entityManager.createQuery(hql).setParameter(1, name)
		              .getResultList().size();
		return count > 0 ? true : false;
	}
	
	@Override
	public boolean userValidate(String name, String password) {
		String hql = "FROM User as usr WHERE usr.name = ? and usr.password = ?";
		
		int count = entityManager.createQuery(hql).setParameter(1, name)
					  .setParameter(2, password)
		              .getResultList().size();
		
		return count > 0 ? true : false;
	}
}