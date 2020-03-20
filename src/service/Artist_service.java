package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.Artist_entity;
import entity.Job_entity;
import entity.Media_entity;
import entity.User_entity;

public class Artist_service {

	public List<Artist_entity> getListArtist() {
		
		List<Artist_entity> artists = new ArrayList<Artist_entity>();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Artist_entity> q = session.createQuery("select a from artiste a order by a.p_id", Artist_entity.class);
			artists = q.getResultList();
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
		return artists;
	}
	
	public Artist_entity getArtistBySurName(String surName) {
		
		Artist_entity artist = new Artist_entity();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Artist_entity> q = session.createQuery("select a from artiste a where a.surName = ?0", Artist_entity.class);
			q.setParameter(0, surName);
			artist = q.getSingleResult();
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
		return artist;
	}

	public void editArtist(Integer id , String firstName, String lastName, String surName, Date dob) {
		
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Artist_entity artist = getArtistByIdSimple(id);
			artist.setFirstName(firstName);
			artist.setLastName(lastName);
			artist.setSurName(surName);
			artist.setDob(dob);
			session.persist(artist);
			tx.commit();
			
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
	}
 
	public Artist_entity getArtistByIdSimple(Integer id) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Artist_entity artist = session.get(Artist_entity.class, id);
		return artist;
	}
	
	public Artist_entity getArtistById(Integer id) {
		
		Artist_entity artist = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			artist = session.get(Artist_entity.class, id);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return artist;
	}
	
	
	public Artist_entity checkArtistExiste(Integer id, String surName) {
		
		Artist_entity artist = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Artist_entity> q = session.createQuery("select a from artiste a where a.surName = ?0 and a.p_id <> ?1", Artist_entity.class);
			q.setParameter(0, surName);
			q.setParameter(1, id);
			artist = q.getSingleResult();
			
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return artist;	
	}
	
	public Artist_entity checkArtistExisteBySurname(String surName) {
		
		Artist_entity artist = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Artist_entity> q = session.createQuery("select a from artiste a where a.surName = ?0", Artist_entity.class);
			q.setParameter(0, surName);
			artist = q.getSingleResult();
			
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return artist;	
	}
	
	public void removeArtist(Integer id) {
		
		Session session = null;
		Transaction tx = null;
		Artist_entity artist = null;
		
		 
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			artist = getArtistByIdSimple(id);
			 Set<Media_entity> medias = artist.getMedias();
			 for (Media_entity media : medias) {
				 System.out.println(media.getMedia_title());
			 }
			 medias.clear();
//	 		session.delete(artist);
			tx.commit();
			
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
		}
		finally {
			if (session != null) {
				session.close();
			}
		}	
	}
	
	
}
