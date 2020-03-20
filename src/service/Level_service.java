package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.Level_entity;

public class Level_service {

	public Object[] getLevelList() {
		
		List<Level_entity> levels = new ArrayList<Level_entity>();
		List<Object> list = new ArrayList<Object>();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Level_entity> q = session.createQuery("select l from level l", Level_entity.class);
			levels = q.getResultList();
			for (Level_entity elt : levels) {
				list.add(elt.getLevelName());
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return list.toArray();
	}
	
public Level_entity getLevelName(String levelName) {
		
		Level_entity level = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Level_entity> q = session.createQuery("select l from level l where l.levelName = ?0", Level_entity.class);
			q.setParameter(0, levelName);
			level = q.getSingleResult();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return level;
	}
	
	
}
