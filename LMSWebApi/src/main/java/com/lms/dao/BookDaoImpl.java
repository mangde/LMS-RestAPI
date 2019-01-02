package com.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lms.model.Book;

@Repository
public class BookDaoImpl implements BookDao {
	
	private static final String insertQuery="INSERT INTO book (title ,publication,authorname,totalbooks,available) VALUES(?,?,?,?,?)";

	private static final String getBookQuery = "SELECT book_id,title,publication,authorname,totalbooks,available FROM book WHERE book_id= ?";

	private static final String getAllbooksQuery ="SELECT book_id,title,publication,authorname,totalbooks,available FROM book";

	private static final String searchquery =  "SELECT book_id,title,publication,authorname,totalbooks,available FROM book WHERE title= ?";
	
	private static final String updateQuery="UPDATE book SET title=?,publication=?,authorname=?,totalbooks=?,available=? WHERE book_id=?";

	private static final String deleteQuery=  "DELETE FROM book WHERE book_id=?";

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;



	public BookDaoImpl() {
	}

	public BookDaoImpl(JdbcTemplate jdbcTemplateObject2) {
		this.jdbcTemplateObject=jdbcTemplateObject2;}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.dataSource=ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);

	}


	@Override
	public int addBook(Book book) throws Exception {

		return jdbcTemplateObject.update(insertQuery, book.getTitle(),book.getPublication(),book.getAuthor(),book.getTotalCopies(),book.getTotalCopies());

	}


	@Override
	public int updateBook(Book book) throws Exception {

		return jdbcTemplateObject.update(updateQuery, book.getTitle(),book.getPublication(),book.getAuthor(),book.getTotalCopies(),book.getTotalCopies(),book.getBookId());

	}
	@Override
	public int deleteBook(int id)throws Exception {

		return jdbcTemplateObject.update(deleteQuery, id);

	}
	@Override
	public Book getBook(int id) throws Exception {

		List<Book> books = jdbcTemplateObject.query( getBookQuery,  new Object[]{id}, (resultSet, i) -> {
			return toBook(resultSet);
		});

		if (books.size() == 1) {
			return books.get(0);
		}
		return null;

	}

	
	@Override
	public List<Book> getAllBooks() throws Exception {
			return jdbcTemplateObject.query(getAllbooksQuery, (resultSet, i) -> {
				return toBook(resultSet);
			});
		
	}



	@Override
	public List<Book> searchBook(String title)throws Exception {
		
		List<Book> books = jdbcTemplateObject.query( searchquery,  new Object[]{title}, (resultSet, i) -> {
			return toBook(resultSet);
		});

		if (books.size() >= 1) {
			return  books;
		}


		return null;

	}
	private Book toBook(ResultSet resultSet) throws SQLException{
		Book book = new Book();
		book.setBookId(resultSet.getInt("book_id"));
		book.setTitle(resultSet.getString("title"));
		book.setAuthor(resultSet.getString("authorname"));
		book.setPublication(resultSet.getString("publication"));
		book.setTotalCopies(resultSet.getInt("totalbooks"));
		book.setAvailable(resultSet.getInt("available"));
		return book;
	}

}
