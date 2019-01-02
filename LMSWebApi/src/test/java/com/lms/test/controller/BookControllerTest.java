package com.lms.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lms.controller.BookController;
import com.lms.model.Book;
import com.lms.service.BookService;

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(controllers=BookController.class)
@RunWith(SpringRunner.class)
public class BookControllerTest {

	@Mock
	private BookService bookService;

	@Autowired
	private MockMvc mockMvc;

	private Book book;
	private List<Book> listBook;

	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(new BookController()).build();
		book= new Book(1,"java","tech","ram",5,5);
		book.setBookId(1);
		book.setTitle("java");
		book.setAuthor("ram");
		book.setPublication("tech");
		book.setAvailable(5);
		book.setTotalCopies(5);

	}

	@Test
	public void testAddBookPage() throws Exception {
		assertThat(this.bookService).isNotNull();

		mockMvc.perform(MockMvcRequestBuilders.get("/book/addbook"))
		.andExpect(status().isOk())
		.andExpect(view().name("addBook"))
		.andExpect(MockMvcResultMatchers.view().name("addBook"))
		.andDo(print());

	}


	@Test
	public void testAddingBook() throws Exception {
		assertThat(this.bookService).isNotNull();
		when(bookService.addBook(book)).thenReturn(1);

		mockMvc.perform(MockMvcRequestBuilders.post("/book/addbook/process"))
		.andExpect(status().isOk())
		.andExpect(view().name("viewBook"))
		.andExpect(MockMvcResultMatchers.view().name("viewBook"))
		.andReturn().getModelAndView();

	}

	@Test
	public void testListOfbooks() throws Exception {
		assertThat(this.bookService).isNotNull();
		when(bookService.getAllBooks()).thenReturn(listBook);

		mockMvc.perform(MockMvcRequestBuilders.get("/book/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("list-of-books"))
		.andExpect(MockMvcResultMatchers.view().name("list-of-books"))
		.andDo(print());

	}

	@Test
	public void testEditBookPage()throws Exception{

		assertThat(this.bookService).isNotNull();		
		when(bookService.getBook(1)).thenReturn(book);

		mockMvc.perform(MockMvcRequestBuilders.get("/book/edit/",1))
		.andExpect(status().isOk())
		.andExpect(view().name("edit-book"))
		.andExpect(MockMvcResultMatchers.view().name("edit-book"))
		.andDo(print());
	}
	@Test
	public void testEditingBook()throws Exception{

		assertThat(this.bookService).isNotNull();
		when(bookService.updateBook(book)).thenReturn(1);

		mockMvc.perform(post("/book/edit/process"))
		.andExpect(status().isOk())
		.andExpect(view().name("list-of-books"))
		.andExpect(MockMvcResultMatchers.view().name("list-of-books"))
		.andDo(print());


	}

	@Test
	public void testSearchokkPage()throws Exception {
		assertThat(this.bookService).isNotNull();

		mockMvc.perform(MockMvcRequestBuilders.get("/book/search"))
		.andExpect(status().isOk())
		.andExpect(view().name("searchBook"))
		.andExpect(MockMvcResultMatchers.view().name("searchBook"))
		.andDo(print());

	}
	@Test
	public void testSearchBookp()throws Exception {
		assertThat(this.bookService).isNotNull();
		when(bookService.searchBook("java")).thenReturn(listBook);
		
		mockMvc.perform(post("/book/searchBook"))
		.andExpect(status().isOk())
		.andExpect(view().name("show-book"))
		.andExpect(MockMvcResultMatchers.view().name("show-book"))
		.andDo(print());	}

	@Test
	public void testdeleteUser()throws Exception{
		assertThat(this.bookService).isNotNull();
		when(bookService.deleteBook(1)).thenReturn(1);
	

		mockMvc.perform(MockMvcRequestBuilders.get("/book/delete"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(MockMvcResultMatchers.view().name("index"))
		.andDo(print());

	}
}
