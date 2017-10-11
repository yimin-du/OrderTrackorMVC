package com.fdm.model;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class CourierService {
	
	@Resource(name="emf")
	private EntityManagerFactory emf;
	
	@Resource(name="orderService")
	private OrderService orderService;

	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void persistCourier(Courier courier) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(courier);
		et.commit();
		em.close();
	}
	
	public void removeCourier(Long id) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		Courier courier = em.find(Courier.class, id);
		try {
			if (courier != null) {
				et.begin();
				em.remove(courier);
				for(Order order : courier.getOrders()) {
					orderService.removeOrder(order.getOrderId());
				}
				et.commit();
			}
		} finally {
			em.close();
		}
	}
	
	public Courier findCourierByID(Long id) {
		Courier courier = new Courier();
		EntityManager em = getEntityManager();
		try {
			courier = em.find(Courier.class, id);
		} finally {
			em.close();
		}
		return courier;
	}
	
	public List<Courier> findAllOrders() {

		TypedQuery<Courier> query = 
				getEntityManager().createQuery("SELECT o from Courier o", Courier.class);

		return query.getResultList();
	}


	public Courier findAvailableCourier() {

		List<Courier> couriers = findAllOrders(); 
//		for(Courier courier : couriers) {
//			if(courier.getStatus().equals(CourierStatus.WAITING)) {
//				return courier;
//			}
//		}
		
		Random r = new Random();
		int courierIndex =  r.nextInt(4);
		
		return couriers.get(courierIndex);
	}
	
}
