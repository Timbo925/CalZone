package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.db.DbTranslate;
//import com.vub.model.Credentials;
import com.vub.model.User;
import com.vub.model.UserDao;
import com.vub.db.*; 

//@RequestMapping("/login")
@Controller
public class LoginController {
	
	//DbTranslate db = new DbTranslate();
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(@ModelAttribute("user") User user) {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin(@ModelAttribute("user") User user) {
		System.out.println("POST request /login ");
		
		//db.openConnection();
		//db.showPersons();
		String userName = user.getUserName();
		String password = user.getPassword();
		
		UserDao userDao = new UserDao();
		User user2 =  userDao.findByUserName("ncarragg");
		System.out.println(user2);
		
		if (user.getUserName() != "" && user.getPassword() != "") {
			System.out.println(user.getUserName());
			System.out.println(user.getPassword());
			System.out.println("Login succesfull");
			//db.main(new String[1]);
			return "redirect:login/succesfull";
		} else {
			System.out.println("Login failure");
			return "redirect:login/create";
		}
	}
}
