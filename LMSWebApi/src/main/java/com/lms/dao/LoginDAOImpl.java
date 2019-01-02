package com.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lms.model.Login;

@Repository
public class LoginDAOImpl  implements LoginDAO {
	
	private static final String loginQuery = "select username,password from admin_details where username = ? and password=?";

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;


	@Autowired
	public void setDataSource(DataSource ds) {
		this.dataSource=ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);

	}
	
	@PostConstruct
	private  void initialize() {
		setDataSource(dataSource);
	}
	
	

	@Override
	public Login validateUser(Login login) throws Exception {

		System.out.println("dao login"+","+login.getPassword());
		
	Login	user = jdbcTemplateObject.queryForObject(loginQuery, new Object[]{login.getUserName(),login.getPassword()}, new RowMapper<Login>(){

			@Override
			public Login mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Login us = new Login();
				System.out.println(rs.getString("username"));
				
				us.setUserName(rs.getString("username"));
				us.setPassword(rs.getString("password"));
				return us;
			}});

	return user;


	}
}
