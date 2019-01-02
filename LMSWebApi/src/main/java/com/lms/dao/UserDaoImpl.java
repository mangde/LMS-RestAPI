package com.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lms.model.Login;
import com.lms.model.User;

@Repository
public class UserDaoImpl implements UserDao{


	private static final String insertQuery= "insert into user (user_id,username,department,phone) values (?,?,?,?)";

	private static final String updateQuery = "update user set username=?, phone=?,department=? where user_id=?";

	private static final String getUserQuery  = "select user_id,username,phone,department from user where user_id = ?";

	private static final String deleteQuery = "delete from user where user_id=?";

	private static final String getAllusersQuery = "select user_id,username,phone,department from user";

	private static final String searchQuery = "select * from user where username = ?";

	//private static final String loginQuery = "select username,password from admin_details where username = ? and password=?";



	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public UserDaoImpl() {	}

	public UserDaoImpl(JdbcTemplate jdbcTemplateObject2) {
		this.jdbcTemplateObject=jdbcTemplateObject2;	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.dataSource=ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public int addUser(User user)throws Exception {
		
			return jdbcTemplateObject.update(insertQuery, user.getUserId(),user.getUserName(),user.getDepartment(),user.getPhone());
		
	}
	@Override
	public int updateUser(User user)throws Exception {
		
			return jdbcTemplateObject.update(updateQuery, user.getUserName(),user.getPhone(),user.getDepartment(),user.getUserId());

	
	}

	@Override
	public User getUser(int id)throws Exception {

		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
		User user = jdbcTemplateObject.queryForObject(getUserQuery, new Object[]{id}, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum)throws SQLException {
				User us = new User();
				us.setUserId(rs.getInt(1));
				us.setUserName(rs.getString("username"));
				us.setDepartment(rs.getString("department"));
				us.setPhone(rs.getInt("phone"));
				return us;
			}});

		return user;
	}

	@Override
	public int deleteUser(int id)throws Exception {
	
			return jdbcTemplateObject.update(deleteQuery, id);
		
	}

	@Override
	public List<User> getAllUsers()throws Exception {
	
			return jdbcTemplateObject.query(getAllusersQuery, (resultSet, i) -> {
				return toUser(resultSet);
			});
		
	}

	@Override
	public List<User> searchUser(String userName)throws Exception {

		List<User> users = jdbcTemplateObject.query( searchQuery,  new Object[]{userName}, (resultSet, i) -> {
			return toUser(resultSet);
		});

		if (users.size() >= 1) {
			return  users;
		}


		return null;
	}

	private User toUser(ResultSet resultSet) throws SQLException{
		User user=new User();
		user.setUserId(resultSet.getInt("user_id"));
		user.setUserName(resultSet.getString("username"));
		user.setDepartment(resultSet.getString("department"));
		user.setPhone(resultSet.getInt("phone"));
		return user;

	}

	@Override
	public Login validateUser(Login login) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}