package com.fdm.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

import com.fdm.model.*;

public class PersistUtil {
	@Resource(name="cutomerService")
	private CustomerService customerService;
	@Resource(name="orderService")
	private OrderService orderService;
	@Resource(name="emf")
	private EntityManagerFactory emf;
	@Resource(name="courierService")
	private CourierService courierService;
	@Resource(name="employeeService")
	private EmployeeService employeeService;
	

	public CustomerService getCustomerService() {
		return customerService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public CourierService getCourierService() {
		return courierService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public boolean checkLogin(String username, String password) {
		Customer retrievedCustomer = customerService.findCustomerByUsername(username);
		if(retrievedCustomer != null && retrievedCustomer.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
		
	}

	public void newOrder(HttpServletRequest req) {
		String receiverName = req.getParameter("receivername");
		String receiverAddress = req.getParameter("receiveraddress");
		Order order = new Order();
		Customer customer = (Customer) req.getSession().getAttribute("loginUser");
		Courier courier = this.getCourierService().findAvailableCourier();
		order.setSender(customer);
		order.setCost(10);
		order.setCourier(courier);
		Date date = new Date();
		order.setEstimateDeliveryDate(addDays(date, 3));
		order.setOrderDate(date);
		order.setReceiverAddress(receiverAddress);
		order.setReceiverName(receiverName);
		order.setStatus(OrderStatus.DISPATCHED);
		this.getOrderService().persistOrder(order);
		this.getCustomerService().addOrder(customer, order);
	}
	
	public Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}

	public void updateOrder(HttpServletRequest req) {
		OrderService orderService = getOrderService();
		Long orderId = Long.parseLong(req.getParameter("orderId"));
		Order order = orderService.findOrderByID(orderId);
		Order newOrder = new Order();
		newOrder.setOrderId(orderId);
		newOrder.setReceiverName(req.getParameter("receivername"));
		newOrder.setReceiverAddress(req.getParameter("receiveraddress"));
		orderService.updateOrder(newOrder);
	}

	public Customer getLoginUser() {
		
		return null;
	}

	
}
