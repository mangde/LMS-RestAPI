package com.lms.dao;

import java.util.List;

import com.lms.model.Book;

public interface BookDao {
	
		 int addBook(Book book) throws Exception;	
	     int updateBook(Book book) throws Exception;
	     int deleteBook(int id) throws Exception;	
	     Book getBook(int id) throws Exception;//search	    
		 List<Book>   getAllBooks() throws Exception;
		 List<Book> searchBook(String title) throws Exception;

   


}
