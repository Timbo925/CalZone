package com.vub.controller;

//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import com.vub.model.User;
//import com.vub.model.UserDao;

//@RequestMapping("/login")
@Controller
public class LoginController {

	// Serving Login Page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(ModelMap model) {

		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "login";
	}

}
