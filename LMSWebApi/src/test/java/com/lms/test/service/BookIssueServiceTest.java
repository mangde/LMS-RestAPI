package com.lms.test.service;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.lms.dao.BookIssueDao;
import com.lms.model.BookIssue;
import com.lms.service.BookIssueService;
import com.lms.service.BookIssueServiceImpl;

public class BookIssueServiceTest {

	private BookIssueService bookIssueService;
	private List<BookIssue> bookList;	
	private BookIssue bookIssue;


	@Mock
	private BookIssueDao bookIssueDaoObj;
	
	
	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
		bookIssueService=new BookIssueServiceImpl(bookIssueDaoObj);
		bookIssue= new BookIssue();
	}


	@Test
	public void testIssueBook() throws Exception {
		when(bookIssueDaoObj.issueBook(bookIssue)).thenReturn(anyInt());

		int result=bookIssueService.issueBook(bookIssue);
		AssertJUnit.assertEquals(0, result);
		
		Mockito.verify(bookIssueDaoObj).issueBook(bookIssue);
	}
	
	@Test
	public void testReturnBook() throws Exception {
		when(bookIssueDaoObj.returnBook(bookIssue)).thenReturn(1);
		
		int result=bookIssueService.returnBook(bookIssue);
		AssertJUnit.assertEquals(1, result);
		
		Mockito.verify(bookIssueDaoObj).returnBook(bookIssue);
	}

	@Test
	public void testGetAllIssuedBooks() throws Exception {

		when(bookIssueDaoObj.getAllIssuedBooks()).thenReturn(bookList);

		List<BookIssue> list=bookIssueService.getAllIssuedBooks();
		AssertJUnit.assertEquals(bookList, list);
		
		Mockito.verify(bookIssueDaoObj).getAllIssuedBooks();
	}

	@Test
	public void testGetIssueBook() throws Exception {
		when(bookIssueDaoObj.getIssueBook(anyInt(), anyString())).thenReturn(bookIssue);

		BookIssue bk=bookIssueService.getIssueBook(anyInt(),anyString());
		AssertJUnit.assertEquals(bookIssue,bk);
		
		Mockito.verify(bookIssueDaoObj).getIssueBook(anyInt(), anyString());

	}

}
