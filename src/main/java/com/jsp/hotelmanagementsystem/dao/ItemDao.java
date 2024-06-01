package com.jsp.hotelmanagementsystem.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.hotelmanagementsystem.entities.Item;

@Repository
public class ItemDao {
	@Autowired
	EntityManagerFactory emf;

	@Autowired
	EntityManager em;

	@Autowired
	EntityTransaction et;

	public void saveItem(Item item) {
		et.begin();
		em.persist(item);
		et.commit();
	}

	public Item findById(int id) {
		return em.find(Item.class, id);
	}

	public void updateItem(Item Item) {
		et.begin();
		em.merge(Item);
		et.commit();
	}

	public void deleteById(int id) {
		et.begin();
		em.remove(em.find(Item.class, id));
		et.commit();
	}
	
	

}
