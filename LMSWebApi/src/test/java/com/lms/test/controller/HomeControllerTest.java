package com.lms.test.controller;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.swing.text.html.HTMLEditorKit.LinkController;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lms.controller.HomeController;
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

		@InjectMocks
		private HomeController homeController;

		MockMvc mockMvc;

		@BeforeMethod
		public void setup() {
			MockitoAnnotations.initMocks(this);
			this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();

		}

		@Test
		public void testMainPage() throws Exception {

			this.mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andReturn().getModelAndView().getView();

		}

}
