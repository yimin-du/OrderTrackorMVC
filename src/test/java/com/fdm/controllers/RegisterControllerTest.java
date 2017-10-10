package com.fdm.controllers;

import org.junit.Test;
import org.springframework.ui.Model;

import com.fdm.model.Customer;
import com.fdm.model.CustomerService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RegisterControllerTest {
	
	private final RegisterController controller = new RegisterController();
	private final Model model = mock(Model.class);
	private final Customer customer = mock(Customer.class);
	private final PersistUtil persistUtil = mock(PersistUtil.class);
	private final CustomerService customerService = mock(CustomerService.class);
	
	@Test
	public void click_register_goto_register_jsp() {
		String nextPage = controller.gotoRegister(model);
		verify(model).addAttribute(eq("user"), anyObject());
		assertEquals("register", nextPage);
	}
	
	@Test
	public void register_post_user_form_when_username_not_exist() {
		// check if user exist
		when(customer.getUsername()).thenReturn("alex");
		when(customer.getPassword()).thenReturn("123");
		when(persistUtil.getCustomerService()).thenReturn(customerService);
		when(customerService.findCustomerByUsername("alex")).thenReturn(null);
		// if valid
		
	}
}
