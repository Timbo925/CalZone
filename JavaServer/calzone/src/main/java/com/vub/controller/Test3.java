package com.vub.controller;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.datadump.ReadCSV;
import com.vub.model.ActivationKey;

@Controller 
public class Test3 {
	@RequestMapping(value = "/test3")
	public String sayHello(Model model) {
		model.addAttribute("greeting", "Hello World");
		return "hello";
	}
}
