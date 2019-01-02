package com.lms.test.dao;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lms.dao.BookIssueDao;
import com.lms.dao.BookIssueDaoImpl;
import com.lms.model.BookIssue;

public class BookIssueDAOTest {

	@InjectMocks
	private BookIssue bookIssue;


	@Mock
	private JdbcTemplate jdbcTemplateObject;
	
	private List<BookIssue> bookList;
	private BookIssueDao bookIssueDao;
	

	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		
		bookIssueDao= new BookIssueDaoImpl(jdbcTemplateObject);
		bookIssue=new BookIssue();
	}


	@Test
	public void testReturnBook() throws Exception {

		when(bookIssueDao.returnBook(bookIssue)).thenReturn(1);

		try{
			int result=bookIssueDao.returnBook(bookIssue);
			AssertJUnit.assertEquals(1, result);

		}catch (Exception e) {
				e.printStackTrace();	
				
		}

	}

	@Test
	public void testGetIssuedBooks() throws Exception {
		when(bookIssueDao.getAllIssuedBooks()).thenReturn(bookList);

		List<BookIssue> list=bookIssueDao.getAllIssuedBooks();
		AssertJUnit.assertNotSame(bookList, list);


	}

	@Test
	public void testGetIssueBook() throws Exception {
		when(bookIssueDao.getIssueBook(11,"yogesh")).thenReturn(anyObject());

		BookIssue bk=bookIssueDao.getIssueBook(anyInt(),anyString());
		AssertJUnit.assertSame(null,bk);


	}
}
