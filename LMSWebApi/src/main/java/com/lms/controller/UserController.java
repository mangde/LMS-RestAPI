package com.lms.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lms.model.BookIssue;
import com.lms.model.User;
import com.lms.service.BookIssueService;
import com.lms.service.UserService;

@RestController
public class UserController {
	int count=0;

	@Autowired
	private UserService userService;

	@Autowired
	private BookIssueService bookIssueService;

	/*request to add new user*/
	@GetMapping(value="/adduser",produces ="text/html")
	public ModelAndView addUserPage() {	
		ModelAndView modelAndView = new ModelAndView("adduser");	
		modelAndView.addObject("user", new User());	
		return modelAndView;

	}
	/*Response to add new user into the database*/
	@PostMapping(value="/adduser/process",produces="text/html")
	public ModelAndView addingUser(@ModelAttribute User user) {
		ModelAndView modelAndView = null;
		try{
			userService.addUser(user); 			
			modelAndView = new ModelAndView("user-list");
			List<User>   listUsers = userService.getAllUsers();
			modelAndView.addObject("listUsers", listUsers);

		}catch (Exception e) {
			modelAndView = new ModelAndView("adduser");
			modelAndView.addObject("message", "user NOt added");
			modelAndView.addObject("message", e.getCause());

			e.printStackTrace();	

		}
		return modelAndView;	 
	}

	/*All user list*/
	@GetMapping(value="/user/list",produces="text/html")
	public ModelAndView listOfUsers(){
		ModelAndView modelAndView = new ModelAndView("user-list");

		try{
			List<User>   listUsers = userService.getAllUsers();
			modelAndView.addObject("listUsers", listUsers);
		}catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;

	}

	/*Edit User request*/
	@GetMapping(value="/user/edit", produces="text/html")
	public ModelAndView editUserPage( @ModelAttribute User user,@RequestParam Integer userId) {
		ModelAndView modelAndView =null;
		try{ 
			modelAndView = new ModelAndView("user-edit");
			 user=userService.getUser(userId);
			modelAndView.addObject("user",user);

		}catch (Exception e) {
			e.printStackTrace();	
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("message","user editing failed to load");
		}
		return modelAndView;

	}

	/*Edit User response*/
	@PostMapping(value="/user/edit", produces="text/html")
	public ModelAndView edditingUser(@ModelAttribute User user) {
		ModelAndView modelAndView ;
		try{
			userService.updateUser(user);
			modelAndView=new ModelAndView("user-list");

			List<User>   listUsers = userService.getAllUsers();
			modelAndView.addObject("listUsers",listUsers);
		}
		catch (Exception e) {
			e.printStackTrace();
			modelAndView = new ModelAndView("user-edit");
			modelAndView.addObject("user",user);
			modelAndView.addObject("message","fail to update ");

		}

		return modelAndView;

	}
	
	/*delete user*/
	@GetMapping(value="/user/delete", produces="text/html")
	public ModelAndView deleteUser(@RequestParam Integer userId) {
		ModelAndView modelAndView = new ModelAndView("index");
			
		try{
			userService.deleteUser(userId);
			modelAndView.addObject("message","User Delete success ! ");

		}catch (Exception e) {
			modelAndView.addObject("message","fail to Delete ! ");
		}

		return modelAndView;

	}

	/*Search user request*/
@GetMapping(value="/user/search",produces="text/html")
	public ModelAndView searchUserPage(@ModelAttribute User user) {	
		ModelAndView modelAndView = new ModelAndView("searchuser");	
		modelAndView.addObject("user", new User());	
		return modelAndView;
	}

	/*Search user response*/
	@PostMapping(value = "/user/searchUser", produces="text/html")
	public ModelAndView searchUserProcess (@RequestParam(value="userName" ,required=false) String userName) {
		ModelAndView modelAndView = new ModelAndView("show-user");	 
		try{
			List<User> usersList = userService.searchUser(userName);
			modelAndView.addObject("user", new User());	

			modelAndView.addObject("usersList",usersList);	

			List<BookIssue> issuedBooks=bookIssueService.getUserIssueBook(usersList.get(0).getUserId(),userName);
		//System.err.println(issuedBooks.get(0));	
			modelAndView.addObject("issuedBooks", issuedBooks);

			if(issuedBooks==null) {
				modelAndView.addObject("message", "Book Issues NotFound");
			}


		}catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("message","User Not Found..");	

		}

		return modelAndView;	
	}

	/*sort a user list by userName*/
	@GetMapping(value="/user/sort/name",produces="text/html")
	public ModelAndView sortByName()  {
		ModelAndView modelAndView = new ModelAndView("user-list");

		count++;
		List<User> listUsers;
		try {
			listUsers = userService.getAllUsers();
			if(count%2!=0) {
				listUsers.sort(Comparator.comparing(User::getUserName));
				modelAndView.addObject("listUsers", listUsers);
			}else {
				listUsers.sort(Comparator.comparing(User::getUserName).reversed());

				modelAndView.addObject("listUsers", listUsers);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;

	}
}
