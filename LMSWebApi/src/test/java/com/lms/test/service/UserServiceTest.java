package com.lms.test.service;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.lms.dao.UserDao;
import com.lms.model.User;
import com.lms.service.UserService;
import com.lms.service.UserServiceImpl;


public class UserServiceTest {

	@InjectMocks
	private User user ;

	@Mock
	private UserDao userDaoObj;
	
	private UserService userServiceObj;

	private List<User> listUser;


	@BeforeMethod
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		userServiceObj= new UserServiceImpl(userDaoObj);
		user= new User();
	}


	@Test
	public void testAddUser() throws Exception {
		when(userDaoObj.addUser(user)).thenReturn(1);
		
		int result=userServiceObj.addUser(user);
		AssertJUnit.assertEquals(1, result);
		
		Mockito.verify(userDaoObj).addUser(user);
	}


	@Test
	public void testUpdateUser() throws Exception {
		when(userDaoObj.updateUser(user)).thenReturn(1);
		int result=userServiceObj.updateUser(user);
		AssertJUnit.assertEquals(1, result);
		
		Mockito.verify(userDaoObj).updateUser(user);
	}


	
	@Test
	public void testGetUser() throws Exception {
		when(userDaoObj.getUser(anyInt())).thenReturn(user);

		User us=userServiceObj.getUser(anyInt());
		AssertJUnit.assertEquals(user, us);
		
		Mockito.verify(userDaoObj).getUser(anyInt());
	}
	@Test
	public void testDeleteUser() throws Exception {
		when(userDaoObj.deleteUser(anyInt())).thenReturn(1);

		int us=userServiceObj.deleteUser(anyInt());
		AssertJUnit.assertEquals(1, us);
		
		Mockito.verify(userDaoObj).deleteUser(anyInt());
	}
	@Test
	public void   testGetUsers() throws Exception {
		when(userDaoObj.getAllUsers()).thenReturn(listUser);

		List<User> lu=userServiceObj.getAllUsers();
		AssertJUnit.assertEquals(listUser, lu);
		
		Mockito.verify(userDaoObj).getAllUsers();
	}

	@Test
	public void testSearchUser() throws Exception {
		when(userDaoObj.searchUser(anyString())).thenReturn(listUser);

		List<User> ulist=userServiceObj.searchUser(anyString());
		AssertJUnit.assertEquals(listUser, ulist);
		
		Mockito.verify(userDaoObj).searchUser(anyString());
	}
	

}
