package com.lms.service;

import java.util.List;

import com.lms.model.Login;
import com.lms.model.User;

public interface UserService {
	public int addUser(User user)throws Exception;

	int updateUser(User user)throws Exception;
	int deleteUser(int id) throws Exception;

	User getUser(int id)throws Exception;//search user

	List<User>    getAllUsers()throws Exception;

	List<User> searchUser(String username)throws Exception;
	Login validateUser(Login login)throws Exception;
}
