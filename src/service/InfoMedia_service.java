package service;

import java.awt.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.Artist_entity;
import entity.Job_entity;
import entity.Media_Artist_Role_R;
import entity.Media_entity;

public class InfoMedia_service {

	public List getArtistList(List list) {
		java.util.List<Artist_entity> artists = new ArrayList<Artist_entity>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Artist_entity> q = session.createQuery("select a from artiste a order by a.surName", Artist_entity.class);
			artists = q.getResultList();
			for (Artist_entity artist : artists) {
				list.add(artist.getSurName());
			}
		} catch ( Exception e ) {
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
	
public Object[] getListJob() {
		
	java.util.List<Job_entity> jobs = new ArrayList<Job_entity>();
	java.util.List<Object> jobList = new ArrayList<Object>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Job_entity> query = session.createQuery("select m from metier m", Job_entity.class);
			jobs = query.getResultList();	
			for (Job_entity job : jobs) {
				jobList.add(job.getJobName());
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
		return jobList.toArray();
	}

	public Media_entity getMediaWithGenreById(Integer id) {
		
		Media_entity media = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx =session.beginTransaction();
			Query<Media_entity> q = session.createQuery("select m from media m left join fetch m.genres where m.media_id=?0", Media_entity.class);
			q.setParameter(0, id);
			media = q.getSingleResult();
			
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
		return media;
	}

	public void addMediaJob(Integer id , String []artists, String jobName) {
		java.util.List<Media_Artist_Role_R> medias = new ArrayList<Media_Artist_Role_R>();
		Media_entity media = null;
		Job_entity job = null;
		Artist_entity artist = null;
		Session session = null;
		Transaction tx = null;
		
		try {		
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Media_Artist_Role_R> q = session.createQuery("select m from role_artiste m join fetch m.artists join fetch m.jobs join fetch m.medias where m.medias.media_id = ?0 and m.jobs.jobName = ?1 ", Media_Artist_Role_R.class);
			q.setParameter(0, id);
			q.setParameter(1,jobName);
			medias = q.getResultList();
			if (!medias.isEmpty()) {
				for (Media_Artist_Role_R elt : medias) {
					session.delete(elt);
				}
			} 
			
			for (String surname : artists) {
				Query<Artist_entity> q1 = session.createQuery("select a from artiste a where a.surName = ?0", Artist_entity.class);
				q1.setParameter(0, surname);
				artist = q1.getSingleResult();
				media = session.get(Media_entity.class, id);
				Query<Job_entity> q2 = session.createQuery("select j from metier j where j.jobName = ?0", Job_entity.class);
				q2.setParameter(0, jobName);
				job = q2.getSingleResult();
				Media_Artist_Role_R mar = new Media_Artist_Role_R();
				mar.setArtists(artist);
				mar.setMedias(media);
				mar.setJobs(job);
				session.persist(mar);
			}		
			tx.commit();
			
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
	}
	
	
}
