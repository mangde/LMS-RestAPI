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

import com.lms.dao.BookDao;
import com.lms.dao.BookDaoImpl;
import com.lms.model.Book;

public class BookDaoTest {

	
	private BookDao bookDao; 
	private List<Book> books;

	@Mock
	private JdbcTemplate jdbcTemplateObject;
	
	@InjectMocks
	private Book  book= new Book(12,"java","tech","rao",3, 3);



	@BeforeMethod
	public void setUp() throws Exception {		
		MockitoAnnotations.initMocks(this);

		bookDao= new BookDaoImpl(jdbcTemplateObject);
	}

	@Test
	public void testAddBook() throws Exception {
		when(bookDao.addBook(book)).thenReturn(1);

		int result=bookDao.addBook(book);
		AssertJUnit.assertEquals(1, result);


	}

	@Test
	public void testUpdateBook() throws Exception {
		when(bookDao.updateBook(book)).thenReturn(1);

		int result=bookDao.updateBook(book);
		AssertJUnit.assertEquals(1, result);

	}


	@Test
	public void testDeleteBook() throws Exception {
		when(bookDao.deleteBook(1)).thenReturn(1);

		int result=bookDao.deleteBook(1);
		AssertJUnit.assertEquals(1,result);
	}

	

	@Test
	public void testGetBooks() throws Exception{
		when(bookDao.getAllBooks()).thenReturn(books);		
	
		List<Book> list=bookDao.getAllBooks();
		AssertJUnit.assertNotSame(books,list);


	}


	@Test
	public void testSearchBook() throws Exception {
		when(bookDao.searchBook("java")).thenReturn(books);	
		
		List<Book> list=bookDao.searchBook("java");
		AssertJUnit.assertEquals(books,list);


	}


}
