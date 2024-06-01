package com.jsp.hotelmanagementsystem.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.hotelmanagementsystem.entities.FoodOrder;

@Repository
public class FoodOrderDao {
	@Autowired
	EntityManagerFactory emf;

	@Autowired
	EntityManager em;

	@Autowired
	EntityTransaction et;

	public void saveFoodOrder(FoodOrder FoodOrder) {
		et.begin();
		em.persist(FoodOrder);
		et.commit();
	}

	public FoodOrder findById(int id) {
		return em.find(FoodOrder.class, id);
	}

	public void updateFoodOrder(FoodOrder FoodOrder) {
		et.begin();
		em.merge(FoodOrder);
		et.commit();
	}

	public void deleteById(int id) {
		et.begin();
		em.remove(em.find(FoodOrder.class, id));
		et.commit();
	}
}
