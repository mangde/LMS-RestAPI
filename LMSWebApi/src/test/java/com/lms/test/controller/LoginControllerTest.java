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

import com.lms.service.LoginService;
import com.lms.service.UserService;
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Mock
	private LoginService loginService;

	
	MockMvc mockMvc;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginService).build();
		
		   
	}

	@Test
	public void testShowLogin() throws Exception {

		this.mockMvc.perform(get("/login"))
		.andExpect(status().isOk()).andReturn()
		.getModelAndView().getView();
	}
	
	@Test
	public void testLoginProcess( )throws Throwable{

		try{
			this.mockMvc.perform(post("/loginProcess"))		
		.andExpect(status().isOk()).andReturn().getModelAndView();
			
		}catch (NestedServletException e) {
			 assertNotNull( e );
		        assertNotNull( e.getCause() );
		        assertTrue( e.getCause() instanceof NullPointerException );
		        throw e.getCause();
		}
}
}