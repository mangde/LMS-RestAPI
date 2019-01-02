package com.lms.dao;

import com.lms.model.Login;

public interface LoginDAO {
	public Login validateUser(Login login) throws Exception;


}
