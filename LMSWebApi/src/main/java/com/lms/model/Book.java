package com.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;
/**
 * 
 * 
 * @author yogesh.mangde
 *
 */

@Entity
@Table(name="book")
public class Book {

	@Id
	@Column(name="book_id")
	private int bookId;
	
	@NotNull
	@Column(name="title")
	private String title;
	
	@NotNull
	@Column(name="publication")
	private String publication;
	
	@NotNull
	@Column(name="authorname")
	private String author;
	
	@NonNull
	@Column(name="totalbooks")
	private int totalCopies;
	
	@Column(name="available")
	private int available;



	public Book(int bookId, String title, String publication,String author, int noOfCopies,int avail) {
		this.bookId = bookId;
		this.title = title;
		this.publication = publication;
		this.totalCopies=noOfCopies;
		this.author = author;
		this.available=avail;
	}

	public Book() {
	}

	public int getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookid) {
		this.bookId = bookid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String authorname) {
		this.author = authorname;
	}

	

	
	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}


	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", publication=" + publication + ", authorName="
				+ author + ", noOfCopies=" + totalCopies + ", Available=" + available + "]";
	}

	



}
