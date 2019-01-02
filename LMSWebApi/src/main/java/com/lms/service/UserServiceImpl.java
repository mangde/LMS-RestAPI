package com.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dao.UserDao;
import com.lms.model.User;
import com.lms.model.Login;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public UserServiceImpl(UserDao dao) {
		this.userDao=dao;
	}
 public UserServiceImpl() {
}
	@Override
	public int addUser(User user) throws Exception {
		return userDao.addUser(user);		
	}

	@Override
	public int updateUser(User user) throws Exception {
		return userDao.updateUser(user);		
	}

	@Override
	public User getUser(int id) throws Exception {
		return userDao.getUser(id);
	}

	@Override
	public int deleteUser(int id) throws Exception {
		return userDao.deleteUser(id);		
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		return userDao.getAllUsers();
	}

	@Override
	public List<User> searchUser(String username) throws Exception {
		return userDao.searchUser(username);
	}

	
	@Override
	public Login validateUser(Login login) throws Exception {
		return userDao.validateUser(login);
	}

}
