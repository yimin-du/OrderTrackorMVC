package com.fdm.controllers;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.model.Customer;
import com.fdm.model.CustomerService;

@Controller
public class RegisterController {

	@Resource(name="persistUtil")
	private PersistUtil persistUtil;
	
	@RequestMapping(value="/register")
	public String gotoRegister(Model model) {

		model.addAttribute("customer", new Customer());
		return "register";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(Customer customer, HttpServletRequest req, Model model) {
		//check if customer already exist
		String username = customer.getUsername();
		CustomerService customerService = persistUtil.getCustomerService();
		Customer retrievedCustomer = customerService.findCustomerByUsername(username);
		
		if(retrievedCustomer == null) {
			System.out.println(customer);
			customerService.persistCustomer(customer);
			req.setAttribute("regSucceed", true);
			model.addAttribute("customer", new Customer());
			return "index";
		} else {
			req.setAttribute("userExisted", true);
			model.addAttribute("customer", new Customer());
			return "register";
		}
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginUser(Model model, HttpServletRequest req, @RequestParam String username, @RequestParam String password) {
		if(persistUtil.checkLogin(username, password)) {
			HttpSession session = req.getSession();
			Customer loginUser = persistUtil.getCustomerService().findCustomerByUsername(username);
			session.setAttribute("loginUser", loginUser );
			return "home";
		} else {
			req.setAttribute("loginFail", true);
			req.setAttribute("username", username);
			model.addAttribute("customer", new Customer());
			return "index";
		}

	}
}
