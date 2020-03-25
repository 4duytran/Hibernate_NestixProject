package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.Media_Artist_Role_R;
import entity.Media_entity;

public class TestHibernate {

	
	public Media_entity getMedia(Integer id) {
		Media_entity media = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			media = session.get(Media_entity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return media;
		
	}
	
	public List<Media_entity> getListMedia() {
		List<Media_entity> medias = new ArrayList<Media_entity>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query<Media_entity> query = session.createQuery("select j from media j", Media_entity.class);
			medias = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return medias;	
	}
	
	public List<Media_entity> getListFilm() {
		List<Media_entity> medias = new ArrayList<Media_entity>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query<Media_entity> query = session.createQuery("select j from media j where j.mediaType.mediaType_name=?0", Media_entity.class);
			query.setParameter(0, "Film");
			medias = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return medias;	
	}
	
	
	public Media_entity getMediaById(Integer id) {
		Media_entity media = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			media = session.get(Media_entity.class, id);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return media;
	}
	
	public List<Media_Artist_Role_R> getListArtistJob() {
		List<Media_Artist_Role_R> medias = new ArrayList<Media_Artist_Role_R>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query<Media_Artist_Role_R> query = session.createQuery("select m from role_artiste m join fetch m.artists join fetch m.jobs join fetch m.medias where m.medias.media_id = ?0 and m.jobs.jobName= ?1", Media_Artist_Role_R.class);
			query.setParameter(0, 35);
			query.setParameter(1, "Acteur");
			medias = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return medias;	
	}
	
}
