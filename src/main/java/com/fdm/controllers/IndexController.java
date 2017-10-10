package com.fdm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdm.model.Customer;

@Controller
public class IndexController {

	@RequestMapping(value="/")
	public String goToIndex(Model model) {
		model.addAttribute("customer", new Customer());
		return "index";
	}
	
	
}
