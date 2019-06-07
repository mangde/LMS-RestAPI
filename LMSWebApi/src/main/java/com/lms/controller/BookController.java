package com.lms.controller;
/*
 * This is a BookController class manage and control Book CRUD Operations */

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lms.model.Book;
import com.lms.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	private int count=0;

	@Autowired
	private BookService bookService;

	/* Add book request mapping to view*/

	@GetMapping(value="/addbook",produces="text/html" )
	@ResponseBody
	public ModelAndView addBookPage() {	
		ModelAndView modelAndView = new ModelAndView("addBook");	
		modelAndView.addObject("book", new Book());	
		return modelAndView;

	}
	/* Add book response to new entry  add in database*/
	@PostMapping(value="/addbook/process",produces="text/html")
	@ResponseBody
	public ModelAndView addingBook(@ModelAttribute Book book) {
		ModelAndView modelAndView = null;
		try{
			modelAndView = new ModelAndView("viewBook");
			int out=	bookService.addBook(book);	
			if(out!=0) {
				modelAndView.addObject("book", book);

			}else {
				modelAndView = new ModelAndView("addBook");
				modelAndView.addObject("message","Add book Fail");

			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return modelAndView;	 
	}

	/*Mapping method listOfBooks method show all the available Book in library also apply sorting on title of the book*/
	@GetMapping(value="/list",produces="text/html")
	@ResponseBody
	public ModelAndView listOfBooks() {
		ModelAndView modelAndView = new ModelAndView("list-of-books");
		count++;
		try{
			List<Book>   books = bookService.getAllBooks();
			if(count%2!=0) {
				books
				.stream()
				.sorted((object1, object2) -> object1.getTitle().compareTo(object2.getTitle()));
				modelAndView.addObject("books", books);
			}else {
				books.sort(Comparator.comparing(Book::getTitle));
				modelAndView.addObject("books", books);

			}
		}catch (Exception e) {
			e.printStackTrace();

		}
		return modelAndView;
	}


	/*Edit Book request*/
	@GetMapping(value="/edit", produces="text/html")	
	@ResponseBody

	public ModelAndView editBookPage(@ModelAttribute Book Book ,@RequestParam (value="bookId" ,required=false) Integer bookId) {
		ModelAndView modelAndView = new ModelAndView("edit-book");
		try{
			System.out.println(bookId);
			Book book = bookService.getBook(bookId);
			modelAndView.addObject("book",book);

		}catch (Exception e) {
			e.printStackTrace();	
		}
		return modelAndView;

	}

	/*Edit book Response to successfully updated*/
	@PostMapping(value="/edit/process", produces="text/html")
	@ResponseBody
	public ModelAndView edditingBook(@ModelAttribute Book book) {
		ModelAndView modelAndView = new ModelAndView("list-of-books");
		try{
				int out=bookService.updateBook(book);

			if(out!=0) {
				List<Book>   books = bookService.getAllBooks();		
				modelAndView.addObject("books", books);
				modelAndView.addObject("message", "Successfully  updated Book");
			}else {
				modelAndView.addObject("message", "Failed to update Book");
			}
		}catch (Exception e) {
			e.printStackTrace();	
		}
		return modelAndView;
	}

	/*Search Book Request*/
	@GetMapping(value="/search", produces="text/html")
	@ResponseBody
	public ModelAndView searchBookPage(@ModelAttribute Book book) {	
		ModelAndView modelAndView = new ModelAndView("searchBook");	
		modelAndView.addObject("book", new Book());	
		return modelAndView;
	}

	/**Response to the search Book*/
	@PostMapping(value = "/searchBook", produces="text/html")
	public ModelAndView searchBookprocess (@RequestParam(value="title" ,required=false) String title) {
		ModelAndView modelAndView = new ModelAndView("show-book");	 
		try{
			List<Book> books = bookService.searchBook(title);
			modelAndView.addObject("book", new Book());	
			modelAndView.addObject("books",books);	

		}catch (Exception e) {
			modelAndView.addObject("message", "Not Available");
		}
		return modelAndView;	
	}

	/* delete Book response to the request*/
	@DeleteMapping(value="/delete", produces="text/html")
	@ResponseBody
	public ModelAndView deleteBook(@RequestParam(value="bookId",required=false) Integer bookId) {
		ModelAndView modelAndView = new ModelAndView("index");
		try {
			if(1==bookService.deleteBook(bookId)) {
				String message = "Book was successfully deleted.";
				modelAndView.addObject("message", message);
			}else {
				String message = "a Book Cannot delete or update ... ";
				modelAndView.addObject("message", message);
			}
		}catch(Exception e) {
			String message = "Cannot delete or update a parent row: a foreign key constraint fails   ";
			modelAndView.addObject("message", message);
				e.printStackTrace();

		}
		return modelAndView;
	}

	/*Sorting a book as per status is available or not*/
	@GetMapping(value="/sort/status",produces="text/html")
	@ResponseBody
	public ModelAndView sortByStatus() {
		ModelAndView modelAndView = new ModelAndView("list-of-books");
		count++;
		List<Book> books;
		try {
			books = bookService.getAllBooks();
			if(count%2!=0) {
				books.sort(Comparator.comparing(Book::getAvailable));
				modelAndView.addObject("books", books);
			}else {
				books.sort(Comparator.comparing(Book::getAvailable).reversed());
				modelAndView.addObject("books", books);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
}
