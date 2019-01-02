package com.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dao.BookIssueDao;
import com.lms.model.BookIssue;

@Service
public class BookIssueServiceImpl implements BookIssueService {

	@Autowired
	private BookIssueDao bookIssueDao;
	
	public BookIssueServiceImpl() {
	}

	public BookIssueServiceImpl(BookIssueDao bookIssueDao) {
		this.bookIssueDao=bookIssueDao;}


	public BookIssueDao getDao() {
		return bookIssueDao;
	}
	

	public void setDao(BookIssueDao dao) {
		this.bookIssueDao = dao;
	}

	

	@Override
	public int issueBook(BookIssue bi) throws Exception {
		
		return bookIssueDao.issueBook(bi);
	}
	@Override
	public int returnBook(BookIssue bi) throws Exception {
		return bookIssueDao.returnBook(bi);		
	}


	@Override
	public List<BookIssue> getAllIssuedBooks() throws Exception {
		return bookIssueDao.getAllIssuedBooks();
	}


	@Override
	public BookIssue getIssueBook(int bookid, String uname) throws Exception {
		return bookIssueDao.getIssueBook(bookid, uname);
	}

	@Override
	public List<BookIssue> getUserIssueBook(int userid ,String uname) throws Exception {
		return bookIssueDao.getUserIssueBook( userid,uname);
	}


}
