package com.lms.test.controller;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.lms.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {



	@Mock
	private UserService userservice;

	MockMvc mockMvc;


	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userservice).build();

		

	}

	@Test
	public void testAdduser() throws Exception {

		this.mockMvc.perform(get("/adduser"))
		.andExpect(status().isOk())
		.andReturn().getModelAndView().setViewName("adduser");

	}


	@Test
	public void testAddinguser() throws Exception {

		this.mockMvc.perform(post("/adduser/process"))
		.andExpect(status().isOk())
		.andReturn().getModelAndView();

	}

	@Test
	public void testListUser()  {

		try {
			this.mockMvc.perform(get("/user/list"))
			.andExpect(status().isOk())
			.andReturn().getModelAndView();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testEditUserPage()throws Throwable{
		try{

			mockMvc.perform(get("/user/edit"))
			.andExpect(status().isOk()).andReturn().getModelAndView();
		} catch ( NestedServletException e )
		{
			assertNotNull( e );
			assertNotNull( e.getCause() );
			assertTrue( e.getCause() instanceof NullPointerException );
			throw e.getCause();
		}

	}
	 
	@Test
	public void testEditingUser()throws Exception{
		try{
			mockMvc.perform(post("/user/edit"))
			.andExpect(status().isOk()).andReturn().getModelAndView();
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

	@Test
	public void testSearchUserPages()throws Exception {
		mockMvc.perform(get("/user/search"))
		.andExpect(status().isOk()).andReturn().getModelAndView();
	}
	@Test
	public void testSearchUserProcess()throws Exception {
		mockMvc.perform(post("/user/searchUser"))
		.andExpect(status().isOk()).andReturn().getModelAndView();
	}

	@Test
	public void testdeleteUser()throws Exception{
		try{
			mockMvc.perform(get("/user/delete"))
			.andExpect(status().isOk()).andReturn().getModelAndView();
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
}
