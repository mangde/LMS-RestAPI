package com.lms.controller;
/*
 * This is a BookIssue controller class. where handle all request regarding BookIssue*/

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lms.model.Book;
import com.lms.model.BookIssue;
import com.lms.model.User;
import com.lms.service.BookIssueService;
import com.lms.service.BookService;

@RestController
public class BookIssueController {
	private int count=0;

	@Autowired
	private BookIssueService bookIssueServiceObj;

	@Autowired
	private BookService bookServiceObj;


	/*used for date() formating as per database database*/
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/*Request for bookIssue and map to view*/
	@GetMapping(value="/issuebook", produces="text/html")
	@ResponseBody
	public ModelAndView issueBookPage( @ModelAttribute BookIssue bookIssue, @RequestParam(value=" bookId",required=false) Integer bookId) {	

		ModelAndView modelAndView = new ModelAndView("issue-book");	
		modelAndView.addObject("bookIssue", new BookIssue());	

		try{
			Book book = bookServiceObj.getBook(bookId);
			modelAndView.addObject("book",book);


		}catch (Exception e) {
			e.printStackTrace();		}
		return modelAndView;

	}

	/*Response to BookIssue to user*/
	@PostMapping(value="/issuebook/process",produces ="text/html")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ResponseBody
	public ModelAndView addIssueBook(@ModelAttribute BookIssue issueBook ) throws Exception {
		ModelAndView modelAndView=null;
		try{
			
			int val=bookIssueServiceObj.issueBook(issueBook); 
			if(val!=0) {
				modelAndView= new ModelAndView("view-issuebook");
				modelAndView.addObject("issueBook", new BookIssue());	

				modelAndView.addObject("issueBook", issueBook);
			}

		}	catch (EmptyResultDataAccessException e) {

			modelAndView= new ModelAndView("adduser");
			modelAndView.addObject("user", new User());	
			modelAndView.addObject("message", "Username Not in Database please Register user!!");
		}catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		return modelAndView;	 
	}

	/*shows all issued book list*/
	@GetMapping(value="/issuebook/list",produces="text/html")
	@ResponseBody
	public ModelAndView listOfIssuedBooks() {
		ModelAndView modelAndView = new ModelAndView("issuedbookview");
		try{
			List<BookIssue>   issuedBooks = bookIssueServiceObj.getAllIssuedBooks();
			if(issuedBooks.isEmpty()) {
				modelAndView.addObject("message","no Issued Book Found");
			}else {
				issuedBooks.sort(Comparator.comparing(BookIssue::getOutDate).reversed());
				modelAndView.addObject("issuedBooks", issuedBooks);

			}
		}catch (Exception e) {
			e.printStackTrace();	

		}

		return modelAndView;

	}

	/*Return bookRequest */
	@GetMapping(value="/issuebook/return", produces="text/html")
	@ResponseBody
	public ModelAndView returnBookPage(@ModelAttribute BookIssue bookIssue,@RequestParam(value="bookId",required=false) Integer bookId,@RequestParam(value="userName",required=false) String userName) {

		ModelAndView modelAndView = null;
		try
		{
			modelAndView = new ModelAndView("bookreturn");
			modelAndView.addObject("bookIssue", new BookIssue());	

			 bookIssue = bookIssueServiceObj.getIssueBook(bookId,userName);
			modelAndView.addObject("bookIssue",bookIssue);

		}catch (Exception e) {
			e.printStackTrace();
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("message","Failed to return Book");

		}
		return modelAndView;

	}

	/*return  book response*/
	@PostMapping(value="/issuebook/return", produces="text/html")
	@ResponseBody
	public ModelAndView returnBook(@ModelAttribute BookIssue bookIssue ) throws Exception {

		ModelAndView modelAndView = new ModelAndView("issuedbookview");
		modelAndView.addObject("bookIssue", new BookIssue());	

			try{
				System.out.println(bookIssue.getBookId());
				System.out.println(bookIssue.getUserName());
				int out=bookIssueServiceObj.returnBook(bookIssue);

				if(out!=0) {
					List<BookIssue>   issuedBooks = bookIssueServiceObj.getAllIssuedBooks();
					modelAndView.addObject("issuedBooks", issuedBooks);
				}else {
					modelAndView.addObject("message", "Fail to BookIssue return");

				}
			
		}catch (Exception e) {
			e.printStackTrace();	
		}
		return modelAndView;

	}

	/*sort a issued book as per name of the user*/
	@GetMapping(value="/bookissue/sort/name",produces="text/html")
	@ResponseBody
	public ModelAndView sortByuName() throws Exception {
		ModelAndView modelAndView = new ModelAndView("issuedbookview");		
		count++;
		List<BookIssue>   issuedBooks ;
		try{
			issuedBooks = bookIssueServiceObj.getAllIssuedBooks();
			if(count%2!=0) {
				issuedBooks.sort(Comparator.comparing(BookIssue::getUserName));
				modelAndView.addObject("issuedBooks", issuedBooks);
			}else {
				issuedBooks.sort(Comparator.comparing(BookIssue::getUserName).reversed());

				modelAndView.addObject("issuedBooks", issuedBooks);

			}
		}catch (Exception e) {
			e.printStackTrace();		}
		return modelAndView;

	}


}
