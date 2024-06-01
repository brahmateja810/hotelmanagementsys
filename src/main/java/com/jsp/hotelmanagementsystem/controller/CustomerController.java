package com.jsp.hotelmanagementsystem.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.hotelmanagementsystem.dao.CustomerDao;
import com.jsp.hotelmanagementsystem.entities.Customer;

@Controller
public class CustomerController {
	@Autowired
	CustomerDao customerDao;

	@RequestMapping("/addcustomer")
	public ModelAndView addCustomer() {
		Customer customer = new Customer();
		ModelAndView mav = new ModelAndView();
		mav.addObject("customerobj", customer);
		mav.setViewName("customerform");
		return mav;
	}

	@RequestMapping("/savecustomer")
	public ModelAndView saveCustomer(@ModelAttribute("customerobj") Customer customer) {
		customerDao.saveCustomer(customer);

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "Registered successfully");
		mav.setViewName("customerlogin");
		return mav;
	}

	@RequestMapping("/customerloginvalidate")
	public ModelAndView customerLoginValidation(ServletRequest request, HttpSession session) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Customer customer = customerDao.login(email, password);
		if (customer != null) {
			session.setAttribute("customerinfo", customer.getId());
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", "logged in successfully");
			mav.setViewName("customeroptions");
			return mav;
		} else {
			ModelAndView mav = new ModelAndView();
			mav.addObject("message", "invalid credentials");
			mav.setViewName("customerlogin");
			return mav;
		}
	}

}
