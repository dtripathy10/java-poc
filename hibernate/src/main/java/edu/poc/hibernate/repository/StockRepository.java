package edu.poc.hibernate.repository;

import org.hibernate.Session;

import edu.poc.hibernate.entity.Stock;
import edu.poc.hibernate.util.HibernateUtil;

public class StockRepository {

	public void createStock(Stock stock) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(stock);
		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}
}
