package com.lms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="bookissue_detail")
public class BookIssue {

	@Column(name="book_id")
	private int bookId;

	@Column(name="username")
	private String userName;	

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="date_due")
	private Date dueDate;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="date_return")
	private Date returnDate;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="date_out")
	private Date outDate;


	public BookIssue(int bookid, String uname, Date dauDate, Date retuenDate, Date outDate) {
		super();
		this.bookId = bookid;
		this.userName = uname;
		this.dueDate = dauDate;
		this.returnDate = retuenDate;
		this.outDate = outDate;
	}
	public BookIssue() {}

	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dauDate) {
		this.dueDate = dauDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date retuenDate) {
		this.returnDate = retuenDate;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookid) {
		this.bookId = bookid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String uname) {
		this.userName = uname;
	}
	@Override
	public String toString() {
		return "BookIssue [bookid=" + bookId + ", uname=" + userName + ", dauDate=" + dueDate + ", retuenDate="
				+ returnDate + ", outDate=" + outDate + "]";
	}

}
