package service;

import java.awt.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.Genre_entity;
import entity.MediaType_entity;
import entity.Saga_entity;

public class Saga_service {

	public List getSagaList(List list) {
		java.util.List<Saga_entity> mediaSagaList = new ArrayList<Saga_entity>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Saga_entity> reg = session.createQuery("select s from saga s", Saga_entity.class);
			mediaSagaList = reg.getResultList();
			for (Saga_entity saga : mediaSagaList) {
				list.add(saga.getSaga_Name());
			}
		} catch ( Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	
	public java.util.List getSagaListName() {
		java.util.List<Saga_entity> mediaSagaList = new ArrayList<Saga_entity>();
		java.util.List<String> mediaSagaListName = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Saga_entity> reg = session.createQuery("select s from saga s", Saga_entity.class);
			mediaSagaList = reg.getResultList();
			for (Saga_entity s : mediaSagaList) {
					mediaSagaListName.add(s.getSaga_Name());
			}
		} catch ( Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return mediaSagaListName;
	}
	
	public Saga_entity getSagaId(String sagaName) {
		Saga_entity saga = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Saga_entity> query = session.createQuery("select s from saga s where s.saga_Name=?0", Saga_entity.class);
			query.setParameter(0, sagaName);
			saga = query.getSingleResult();	
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return saga;
	}
	
	
}
