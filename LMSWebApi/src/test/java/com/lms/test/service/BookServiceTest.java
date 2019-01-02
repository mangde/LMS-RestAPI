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

import com.lms.dao.BookDao;
import com.lms.model.Book;
import com.lms.service.BookService;
import com.lms.service.BookServiceImpl;

public class BookServiceTest {

	@InjectMocks
	private Book book ;

	private List<Book> books;
	private BookService bookServiceObj;


	@Mock
	private BookDao bookDaoObj;




	@BeforeMethod
	public void setUp() throws Exception {		
		MockitoAnnotations.initMocks(this);
		bookServiceObj= new BookServiceImpl(bookDaoObj);
		book= new Book();
	}

	@Test
	public void testAddBook() throws Exception {
		when(bookDaoObj.addBook(book)).thenReturn(1);

		int result=bookServiceObj.addBook(book);
		AssertJUnit.assertEquals(1, result);

		Mockito.verify(bookDaoObj).addBook(book);
	}

	@Test
	public void testUpdateBook() throws Exception {
		when(bookDaoObj.updateBook(book)).thenReturn(1);

		int result=bookServiceObj.updateBook(book);
		AssertJUnit.assertEquals(1, result);
		
		Mockito.verify(bookDaoObj).updateBook(book);
	}

	@Test
	public void testGetBook() throws Exception {
		when(bookDaoObj.getBook(anyInt())).thenReturn(book);

		Book result=bookServiceObj.getBook(anyInt());
		AssertJUnit.assertEquals(book, result);

		Mockito.verify(bookDaoObj).getBook(anyInt());
	}

	@Test
	public void testDeleteBook() throws Exception {
		when(bookDaoObj.deleteBook(anyInt())).thenReturn(1);

		int result=bookServiceObj.deleteBook(anyInt());
		AssertJUnit.assertEquals(1,result);

		Mockito.verify(bookDaoObj).deleteBook(anyInt());
	}

	@Test
	public void testGetBooks() throws Exception{
		when(bookDaoObj.getAllBooks()).thenReturn(books);

		List<Book> list=bookServiceObj.getAllBooks();
		AssertJUnit.assertEquals(books, list);

		Mockito.verify(bookDaoObj).getAllBooks();
	}

	@Test
	public void testSearchBook() throws Exception {
		when(bookDaoObj.searchBook(anyString())).thenReturn(books);

		List<Book> list=bookServiceObj.searchBook(anyString());
		AssertJUnit.assertEquals(books, list);

		Mockito.verify(bookDaoObj).searchBook(anyString());

	}


}
