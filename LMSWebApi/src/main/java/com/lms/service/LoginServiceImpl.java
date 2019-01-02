package com.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dao.LoginDAO;
import com.lms.model.Login;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDAO loginDao;

	@Override
	public Login validateUser(Login login) throws Exception {
		return loginDao.validateUser(login);
	}
	
	
	
}
