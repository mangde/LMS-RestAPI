package com.lms.service;

import com.lms.model.Login;

public interface LoginService {
	public Login validateUser(Login login) throws Exception;

}
