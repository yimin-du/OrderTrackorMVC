package com.fdm.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdm.model.Customer;
import com.fdm.model.CustomerService;

@Controller
public class RegisterController {

	@Resource(name="persistUtil")
	private PersistUtil persistUtil;
	
	@RequestMapping(value="/register")
	public String gotoRegister(Model model) {

		model.addAttribute("user", new Customer());
		return "register";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(Customer customer, Model model) {
		//check if customer already exist
		String username = customer.getUsername();
		CustomerService customerService = persistUtil.getCustomerService();
		Customer customer = customerService.findCustomerByUsername(username);

		return "";
	}
}
