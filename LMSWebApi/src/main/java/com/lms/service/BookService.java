package com.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.model.Book;

@Service
public interface BookService {
	 int addBook(Book book) throws Exception;
     int deleteBook(int id) throws Exception;	
     int updateBook(Book book) throws Exception;

     Book getBook(int id) throws Exception;//search
    
	 List<Book> getAllBooks()throws Exception;

	 List<Book> searchBook(String title) throws Exception;
	

    
}
