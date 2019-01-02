package com.lms.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dao.BookDao;
import com.lms.model.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDaoObj;	
	
	public  BookServiceImpl() {
	}
	public BookServiceImpl(BookDao bdao) {
		this.bookDaoObj=bdao;
	}
	
	public BookDao getDao() {
		return bookDaoObj;
	}
	public void setDao(BookDao dao) {
		this.bookDaoObj = dao;
	}
	
	
	@Override
	public int addBook(Book book) throws Exception {
		return bookDaoObj.addBook(book);		
	}

	@Override
	public int  updateBook(Book book) throws Exception {
		return bookDaoObj.updateBook(book);
	}

	@Override
	public Book getBook(int id) throws Exception {
		return bookDaoObj.getBook(id);
	}

	@Override
	public int deleteBook(int id) throws Exception {
			return bookDaoObj.deleteBook(id);
	}

	@Override
	public List<Book> getAllBooks() throws Exception {
		return bookDaoObj.getAllBooks();
	}
	@Override
	public List<Book> searchBook(String title)throws Exception {
		
		return bookDaoObj.searchBook(title);
	}
	
}
