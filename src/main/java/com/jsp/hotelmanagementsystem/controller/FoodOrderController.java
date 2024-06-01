package com.jsp.hotelmanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.hotelmanagementsystem.dao.CustomerDao;
import com.jsp.hotelmanagementsystem.dao.FoodOrderDao;
import com.jsp.hotelmanagementsystem.entities.Customer;
import com.jsp.hotelmanagementsystem.entities.FoodOrder;
import com.jsp.hotelmanagementsystem.entities.Item;

@Controller
public class FoodOrderController {

    @Autowired
    private FoodOrderDao foodOrderDao;

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping("/addfoodorder")
    public ModelAndView addFoodOrder(HttpSession session) {
        FoodOrder foodOrder = new FoodOrder();
        ModelAndView mav = new ModelAndView();
        mav.addObject("foodOrderObj", foodOrder);
        mav.setViewName("foodorderform");
        return mav;
    }

    @RequestMapping("/saveFoodOrder")
    public ModelAndView saveFoodOrder(@ModelAttribute("foodOrderObj") FoodOrder foodOrder, HttpSession session) {
        @SuppressWarnings("unchecked")
		List<Item> items = (List<Item>) session.getAttribute("itemslist");

        if (items == null || items.isEmpty()) {
            ModelAndView mav = new ModelAndView("foodorderform");
            mav.addObject("message", "No items in the order.");
            return mav;
        }

        foodOrder.setItems(items);
        double totalPrice = items.stream().mapToDouble(Item::getCost).sum();
        foodOrder.setTotalprice(totalPrice);

        Integer customerId = (Integer) session.getAttribute("customerinfo");
        if (customerId == null) {
            ModelAndView mav = new ModelAndView("foodorderform");
            mav.addObject("message", "Customer information is missing.");
            return mav;
        }

        Customer customer = customerDao.findCustomerById(customerId);
        if (customer == null) {
            ModelAndView mav = new ModelAndView("foodorderform");
            mav.addObject("message", "Customer not found.");
            return mav;
        }

        List<FoodOrder> foodOrders = customer.getFoodOrders();
        if (foodOrders == null) {
            foodOrders = new ArrayList<>();
        }
        foodOrders.add(foodOrder);
        customer.setFoodOrders(foodOrders);

        try {
            foodOrderDao.saveFoodOrder(foodOrder);
            customerDao.updateCustomer(customer);
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("foodorderform");
            mav.addObject("message", "Error saving order: " + e.getMessage());
            return mav;
        }

        session.removeAttribute("itemslist");

        ModelAndView mav = new ModelAndView("redirect:/orderConfirmation");
        mav.addObject("message", "Order placed successfully");
        mav.addObject("foodOrderInfo", foodOrder);
        return mav;
    }

    @RequestMapping("/orderConfirmation")
    public ModelAndView orderConfirmation(HttpSession session) {
        ModelAndView mav = new ModelAndView("displaybilltocustomer");
        mav.addObject("message", session.getAttribute("message"));
        mav.addObject("foodOrderInfo", session.getAttribute("foodOrderInfo"));
        session.removeAttribute("message");
        session.removeAttribute("foodOrderInfo");
        return mav;
    }
}

