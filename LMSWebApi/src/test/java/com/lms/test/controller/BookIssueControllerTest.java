package com.lms.test.controller;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import org.junit.runner.RunWith;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.lms.controller.BookIssueController;
import com.lms.model.BookIssue;
import com.lms.service.BookIssueService;

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(controllers=BookIssueController.class)
@RunWith(SpringRunner.class)

public class BookIssueControllerTest {


	@Mock
	private BookIssueService bookIssueServiceObj;


	private MockMvc mockMvc;
	private BookIssue bookIssue;
	private List<BookIssue> listIssue;

	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(new BookIssueController()).build();
		bookIssue=new BookIssue();


	}

	@Test
	public void testIssueBookPage() throws Exception {
		assertThat(this.bookIssueServiceObj).isNotNull();

		mockMvc.perform(MockMvcRequestBuilders.get("/issuebook"))
		.andExpect(status().isOk())
		.andExpect(view().name("issue-book"))
		.andExpect(MockMvcResultMatchers.view().name("issue-book"))
		.andDo(print());

	}


	@Test
	public void testAddIssueBook() throws Exception  {
		assertThat(this.bookIssueServiceObj).isNotNull();
		when(bookIssueServiceObj.issueBook(bookIssue)).thenReturn(1);
		
		mockMvc.perform(post("/issuebook/process"))		
		.andExpect(status().isOk())
		.andExpect(view().name("viewIssueBook"))
		.andExpect(MockMvcResultMatchers.view().name("viewIssueBook"))
		.andDo(print());


	}

	@Test
	public void testListOfissuedBooks() throws Exception {
		assertThat(this.bookIssueServiceObj).isNotNull();
		when(bookIssueServiceObj.getAllIssuedBooks()).thenReturn(listIssue);
	
		this.mockMvc.perform(get("/issuebook/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("issuedbookview"))
		.andExpect(MockMvcResultMatchers.view().name("issuedbookview"))
		.andDo(print());

	}

	@Test
	public void testreturnBookPage()throws Exception{
		
		assertThat(this.bookIssueServiceObj).isNotNull();
		when(bookIssueServiceObj.getIssueBook(1, "yogesh")).thenReturn(bookIssue);
	
			mockMvc.perform(get("/issuebook/return"))
			.andExpect(status().isOk())
			.andExpect(view().name("bookreturn"))
			.andExpect(MockMvcResultMatchers.view().name("bookreturn"))
			.andDo(print());

	}
	@Test
	public void testreturnBook()throws Exception{
		try{

			mockMvc.perform(post("/issuebook/return"))
			.andExpect(status().isOk()).andReturn().getModelAndView();
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}


	@Test
	public void testSortByuName()throws Exception{

		try{
			mockMvc.perform(get("/bookissue/sort/name"))
			.andExpect(status().isOk()).andReturn().getModelAndView();
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

}
