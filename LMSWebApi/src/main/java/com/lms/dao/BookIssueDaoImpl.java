package com.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lms.model.BookIssue;

@Repository
public class BookIssueDaoImpl implements BookIssueDao {

	private static final String issueBookInsertQuery = "insert into bookissue_detail (book_id,user_id,date_issue,date_due,username) values (?,?,?,?,?)";

	private static final String returnBookupdateQuery = "update bookissue_detail set date_return=? where book_id=? and username=?";

	private static final String getAllIssuedBooksQuery = "select book_id, user_id,username,date_issue,date_return,date_due from bookissue_detail";

	private static final String getIssueBookQuery = "select book_id,username,date_issue,date_due,date_return from bookissue_detail where (book_id=? and username=?)";

	private static final String getUserIssueBookQuery = "select book_id,username,date_issue,date_due,date_return from bookissue_detail where (user_id=? and username=?)";


	private static final String getAvailableQuery="select available from book where book_id=?";

	private static final String getUIDQuery="select user_id from user where username=?";		

	private static final String availableupdateQuery="update book set available=? where book_id=?";

	private static final String GetUidAndBookid="SELECT book_id,user_id from bookissue_detail where book_id=? and user_id=?";

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public BookIssueDaoImpl() {	}

	public BookIssueDaoImpl(JdbcTemplate jdbcTemplateObject2) {
		this.jdbcTemplateObject=jdbcTemplateObject2;	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.dataSource=ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);

	}

	@Override
	public int issueBook(BookIssue bookIssue)throws Exception {

		int userId = 0;
		int out=0;
		//get user_id from user
		userId=jdbcTemplateObject.queryForObject(getUIDQuery, new Object [] {bookIssue.getUserName()}, Integer.class);

		try{
			Map<String, Object> result=jdbcTemplateObject.queryForMap(GetUidAndBookid, bookIssue.getBookId(),userId);
		}catch(EmptyResultDataAccessException e) {

			out = jdbcTemplateObject.update(issueBookInsertQuery, bookIssue.getBookId(),userId,bookIssue.getOutDate(),bookIssue.getDueDate(),bookIssue.getUserName());//insert into issuebook

			if(out !=0){
				int avail=jdbcTemplateObject.queryForObject(getAvailableQuery, Integer.class,  bookIssue.getBookId());

				jdbcTemplateObject.update(availableupdateQuery, (avail-1),bookIssue.getBookId());
			}
		}


		return out;
	}


	@Override
	public int returnBook(BookIssue bookIssue) throws Exception {

		int out =jdbcTemplateObject.update(returnBookupdateQuery, bookIssue.getReturnDate(),bookIssue.getBookId(),bookIssue.getUserName());

		if(out !=0){
			int avail=jdbcTemplateObject.queryForObject(getAvailableQuery, Integer.class,bookIssue.getBookId());

			jdbcTemplateObject.update(availableupdateQuery, (avail+1),bookIssue.getBookId());

		}

		return out;
	}


	@Override
	public List<BookIssue> getAllIssuedBooks()throws Exception {		

		return jdbcTemplateObject.query(getAllIssuedBooksQuery, (resultSet, i) -> {
			return toBookIssue(resultSet);
		});

	}


	@Override
	public List<BookIssue> getUserIssueBook(int userid,String uname)throws Exception {

		return jdbcTemplateObject.query( getUserIssueBookQuery,  new Object[]{userid,uname},(resultSet, i) -> {
			return toBookIssue(resultSet);
		});
	}

	private BookIssue toBookIssue(ResultSet resultSet) throws SQLException{
		BookIssue bookIssue= new BookIssue();
		bookIssue.setBookId(resultSet.getInt("book_id"));
		bookIssue.setUserName(resultSet.getString("username"));
		bookIssue.setDueDate((java.util.Date) resultSet.getDate("date_due"));
		bookIssue.setOutDate((java.util.Date) resultSet.getDate("date_issue"));
		bookIssue.setReturnDate((java.util.Date)resultSet.getDate("date_return"));

		return bookIssue;
	}

	@Override
	public BookIssue getIssueBook(int bookid, String uname) throws Exception {
		
		BookIssue bookIssue = null;
		try {
			//using RowMapper anonymous class, we can create a separate RowMapper for reuse
			bookIssue = jdbcTemplateObject.queryForObject(getIssueBookQuery, new Object[]{bookid,uname},  new RowMapper<BookIssue>(){

				@Override
				public BookIssue mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					BookIssue bkissue = new BookIssue();
					bkissue.setBookId(rs.getInt("book_id"));
					bkissue.setUserName(rs.getString("username"));
					bkissue.setDueDate((java.util.Date) rs.getDate("date_due"));
					bkissue.setOutDate((java.util.Date) rs.getDate("date_issue"));
					bkissue.setReturnDate((java.util.Date)rs.getDate("date_return"));

					return bkissue;
				}});
		}catch (Exception e) {
			e.printStackTrace();

		}
		
		
		return bookIssue;
	}

	
}
