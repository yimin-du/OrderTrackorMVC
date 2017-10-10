package com.fdm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value="/")
	public String goToIndex(Model model) {
		return "index";
	}
	
	
}
