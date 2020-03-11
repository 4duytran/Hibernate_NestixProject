package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.Genre_entity;
import entity.Media_entity;
import entity.Saga_entity;

public class Media_service {
	
	public List<Media_entity> getListMedia() {
		
		List<Media_entity> medias = new ArrayList<Media_entity>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx =session.beginTransaction();
			Query<Media_entity> query = session.createQuery("select j from media j join fetch j.mediaType left join fetch j.genres left join fetch j.saga", Media_entity.class);
			medias = query.getResultList();	
			
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
		return medias;
	}
	
	public List<Media_entity> getListFilm() {
			
			List<Media_entity> medias = new ArrayList<Media_entity>();
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				Query<Media_entity> query = session.createQuery("select distinct j from media j join fetch j.mediaType left join fetch j.genres left join fetch j.saga where j.mediaType.mediaType_name = ?0 order by j.media_id", Media_entity.class);
				query.setParameter(0, "Film");
				medias = query.getResultList();		
				
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
			return medias;
	}
	
	public List<Media_entity> getListMusic() {
		
		List<Media_entity> musics = new ArrayList<>();
		Session session = null;
		 Transaction tx = null;
		 
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Media_entity> q = session.createQuery("select distinct m from media m join fetch m.mediaType left join fetch m.genres where m.mediaType.mediaType_name = ?0 order by m.media_id", Media_entity.class);
			q.setParameter(0, "Musique");
			musics = q.getResultList();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		
		return musics;
	}
	
	public List<Media_entity> getListBook() {
		
		List<Media_entity> medias = new ArrayList<Media_entity>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Media_entity> query = session.createQuery("select distinct j from media j join fetch j.mediaType left join fetch j.genres left join fetch j.saga where j.mediaType.mediaType_name = ?0 order by j.media_id", Media_entity.class);
			query.setParameter(0, "Livre");
			medias = query.getResultList();		
			
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
		return medias;
}
	
	public List<Media_entity> getMedia(String title , Integer year , String type) {
		List<Media_entity> medias = new ArrayList<Media_entity>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<Media_entity> query = session.createQuery("select m from media m join fetch m.mediaType where m.media_title=?0 and m.media_year=?1 and m.mediaType.mediaType_name=?2", Media_entity.class);
			query.setParameter(0, title);
			query.setParameter(1, year);
			query.setParameter(2, type);
			medias = query.getResultList();
			
		}  catch (Exception e) {
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
		return medias;
	}
	
	public void creatMedia(Media_entity media) {
			
			Session session = null;
			Transaction tx = null;
			
			try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				session.persist(media);
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
		
	public void editFilm(Integer id, String mediaTitle , Integer mediaYear, String sagaName, String[] listGenre) {
			Session session = null;
			Transaction tx = null;
			Genre_entity genre = null;
			Saga_entity saga = null;
			try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				Media_entity media = getMediaByIdSimple(id);
				for (String str : listGenre) {
					Query<Genre_entity> q = session.createQuery("select g from genre g where g.genre_name = ?0", Genre_entity.class);
					q.setParameter(0, str);
					genre = q.getSingleResult();
					genre.setGenre_name(str);
					media.addGenre(genre);	
				}
				Query<Saga_entity> q = session.createQuery("select s from saga s where s.saga_Name=?0", Saga_entity.class);
				q.setParameter(0, sagaName);
				saga = q.getSingleResult();	
				saga.setSaga_Name(sagaName);
				media.setMedia_title(mediaTitle);
				media.setMedia_year(mediaYear);
				media.setSaga(saga);
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
	
	public void editMusic(Integer id, String mediaTitle , Integer mediaYear, String[] listGenre) {
		Session session = null;
		Transaction tx = null;
		Genre_entity genre = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Media_entity media = getMediaByIdSimple(id);
			for (String str : listGenre) {
				Query<Genre_entity> q = session.createQuery("select g from genre g where g.genre_name = ?0", Genre_entity.class);
				q.setParameter(0, str);
				genre = q.getSingleResult();
				genre.setGenre_name(str);
				media.addGenre(genre);	
			}
			
			media.setMedia_title(mediaTitle);
			media.setMedia_year(mediaYear);
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
	
	public void editBook(Integer id, String mediaTitle , Integer mediaYear, Long isbn, String sagaName, String[] listGenre) {
		Session session = null;
		Transaction tx = null;
		Genre_entity genre = null;
		Saga_entity saga = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Media_entity media = getMediaByIdSimple(id);
			for (String str : listGenre) {
				Query<Genre_entity> q = session.createQuery("select g from genre g where g.genre_name = ?0", Genre_entity.class);
				q.setParameter(0, str);
				genre = q.getSingleResult();
				genre.setGenre_name(str);
				media.addGenre(genre);	
			}
			Query<Saga_entity> q = session.createQuery("select s from saga s where s.saga_Name=?0", Saga_entity.class);
			q.setParameter(0, sagaName);
			saga = q.getSingleResult();	
			saga.setSaga_Name(sagaName);
			media.setMedia_title(mediaTitle);
			media.setMedia_year(mediaYear);
			media.setIsbn(isbn);
			media.setSaga(saga);
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
	
	public void removeGenre(Integer id ) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Media_entity media = getMediaByIdSimple(id);
			Set<Genre_entity> genres = media.getGenre();
			genres.clear();
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
	
	public Media_entity getMediaById(Integer id) {
			Media_entity media = null;
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				media = session.find(Media_entity.class, id);
				
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
	
	public Media_entity getMediaByIdSimple(Integer id) {
		Media_entity media = null;
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		media = session.get(Media_entity.class, id);
		return media;
	}

}
