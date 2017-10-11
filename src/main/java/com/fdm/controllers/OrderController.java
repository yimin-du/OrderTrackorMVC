package com.fdm.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.model.Order;
import com.fdm.model.OrdersWrapper;
import com.fdm.model.Customer;
import com.fdm.model.LatLng;

@Controller
public class OrderController {
	
	@Resource(name="persistUtil")
	private PersistUtil persistUtil;
	
	@RequestMapping(value="/neworder")
	public String goToNewOrderPage(Model model) {
		model.addAttribute("order", new Order());
		return "neworder";
	}
	
	@RequestMapping(value="/neworder", method=RequestMethod.POST)
	public String processNewOrder(HttpServletRequest req, Order order, Model model) {
		persistUtil.newOrder(order,req);
		return "home";
	}
	
	@RequestMapping(value="/editorder")
	public String goToEditOrderPage(Model model, HttpServletRequest req){
		Long orderId = Long.parseLong(req.getParameter("orderId"));
		Order order = persistUtil.getOrderService().findOrderByID(orderId);

		//req.setAttribute("order", order);
		model.addAttribute("order", order);
		return "editorder";
	}
	
	@RequestMapping(value="/editorder", method=RequestMethod.POST)
	public String updateOrder(HttpServletRequest req, Order order) {
		persistUtil.updateOrder(req, order);
		Long orderId = Long.parseLong(req.getParameter("orderId"));
		Order retrievedOrder = persistUtil.getOrderService().findOrderByID(orderId);
		Customer customer = retrievedOrder.getSender();
		HttpSession session = req.getSession();
		session.setAttribute("loginUser", customer);
		return "home";
	}
	
	@RequestMapping(value="/ordersdata", headers="Accept=application/json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<LatLng> getOrderListJson(HttpSession session) {

		Customer customer = (Customer)(session.getAttribute("loginUser"));
		List<LatLng> result = new ArrayList<>();
		for(Order order : customer.getOrders()){
			String lat = order.getCourier().getLat() + "";
			String lng = order.getCourier().getLng() + "";
			LatLng  latlng = new LatLng(lat, lng);
			
			result.add(latlng);
		}
		return result;

	}

		
}
