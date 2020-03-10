package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.MediaType_entity;

public class MediaType_service {

	
	
	public Object[] getListMediaType() {
		
		List<MediaType_entity> mediaType = new ArrayList<MediaType_entity>();
		List<Object> typeList = new ArrayList<Object>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<MediaType_entity> query = session.createQuery("select j from type_media j", MediaType_entity.class);
			mediaType = query.getResultList();	
			for (MediaType_entity type : mediaType) {
				typeList.add(type.getMediaType_name());
			}
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
		return typeList.toArray();
	}
	
	public MediaType_entity getMediaTypeId(String typeName) {
		MediaType_entity mediaType = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<MediaType_entity> query = session.createQuery("select e from type_media e where e.mediaType_name=?0", MediaType_entity.class);
			query.setParameter(0, typeName);
			mediaType = query.getSingleResult();	
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
		return mediaType;
	}
	
}
