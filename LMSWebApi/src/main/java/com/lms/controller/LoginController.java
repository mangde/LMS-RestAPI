package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lms.model.Login;
import com.lms.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;


	@GetMapping(value = "/login", produces="text/html")
	public ModelAndView showLogin() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("login", new Login());
		return modelAndView;
	}

	@PostMapping(value = "/loginProcess",produces="text/html")
	public ModelAndView loginProcess(@ModelAttribute("login") Login login) throws Exception {
		ModelAndView modelAndView = null;
		try {  
			System.out.println(login.getUserName());
			System.out.println(login.getPassword());

			  loginService.validateUser(login);
				modelAndView = new ModelAndView("index");
				modelAndView.addObject("userName", login.getUserName());
				modelAndView.addObject("message", "Welcome  !!"+login.getUserName());

			

		}catch (EmptyResultDataAccessException  e) {
			e.printStackTrace();
			
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("message", "Username or Password is wrong!!");

		}catch(NullPointerException nPointerException) {
			nPointerException.printStackTrace();
		}

		return modelAndView;
	}

}
