package com.lms.dao;

import java.util.List;

import com.lms.model.BookIssue;

public interface BookIssueDao {

	 int issueBook(BookIssue bi)throws Exception;
	 int returnBook(BookIssue bi)throws Exception;


	 List<BookIssue> getAllIssuedBooks()throws Exception;


	 BookIssue getIssueBook(int bookid, String uname)throws Exception;
	List<BookIssue> getUserIssueBook(int userid, String uname) throws Exception;
}
