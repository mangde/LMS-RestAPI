package com.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.model.BookIssue;

@Service
public interface BookIssueService {
	
	 int issueBook(BookIssue bi) throws Exception;
	 int returnBook(BookIssue bi)throws Exception;


	 List<BookIssue> getAllIssuedBooks()throws Exception;


	 BookIssue getIssueBook(int bookid, String uname)throws Exception;
	
	 List<BookIssue> getUserIssueBook(int userid, String uname) throws Exception;


}
