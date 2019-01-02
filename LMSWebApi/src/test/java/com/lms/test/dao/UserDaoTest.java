package com.lms.test.dao;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lms.dao.UserDao;
import com.lms.dao.UserDaoImpl;
import com.lms.model.User;

public class UserDaoTest {

	@InjectMocks
	private User user ;

	@Mock
	private JdbcTemplate jdbcTemplateObject;


	private UserDao userDao;
	private List<User> usersList;


	@BeforeMethod
	public void setUp() throws Exception {		
		MockitoAnnotations.initMocks(this);
		user= new User(1,"yogesh",95458265,"hr");
		userDao= new UserDaoImpl(jdbcTemplateObject);
	}

	@Test
	public void testAddUserk() throws Exception {
		when(userDao.addUser(user)).thenReturn(1);

		int result=userDao.addUser(user);
		AssertJUnit.assertEquals(1, result);


	}

	@Test
	public void testUpdateUser() throws Exception {
		when(userDao.updateUser(user)).thenReturn(1);

		int result=userDao.updateUser(user);
		AssertJUnit.assertEquals(1, result);

	}


	@Test
	public void testDeleteBook() throws Exception {
		when(userDao.deleteUser(1)).thenReturn(1);

		int result=userDao.deleteUser(1);
		AssertJUnit.assertEquals(1,result);
	}



	@Test
	public void testGetUsers() throws Exception{
		when(userDao.getAllUsers()).thenReturn(usersList);		

		List<User> list=userDao.getAllUsers();
		AssertJUnit.assertNotSame(usersList,list);


	}

	@Test
	public void testGetUser() throws Exception{
		when(userDao.getUser(1)).thenReturn(user);		

		User list=userDao.getUser(1);
		AssertJUnit.assertNotSame(user,list);


	}


	@Test
	public void testSearchUser() throws Exception {
		when(userDao.searchUser("yogesh")).thenReturn(usersList);	

		List<User> list=userDao.searchUser("yogesh");
		AssertJUnit.assertEquals(usersList,list);


	}

}
