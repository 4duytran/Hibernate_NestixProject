package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.Genre_entity;
import entity.Media_Artist_Role_R;
import entity.Media_entity;
import entity.Saga_entity;

public class Media_service {
	
	/**
	 * Show list of all media 
	 * @return Object array
	 */
	public List<Media_entity> getListMedia() {
		
		List<Media_entity> medias = new ArrayList<Media_entity>();
		// Init Hibernate session
		Session session = null;
		//Init Hibernate Transaction
		Transaction tx = null;
		// try connect to SQL and get list of media entity , Final will close the Hibernate session, The method Rollback here is not useful but its good pratice
		try {
			// Get the current session of Hibernate
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			// Starting the transaction
			tx =session.beginTransaction();
			// Hibernate Query method will return result of sql request and parse it into object 
			Query<Media_entity> query = session.createQuery("select distinct m from media m join fetch m.mediaType left join fetch m.genres left join fetch m.saga", Media_entity.class);
			// Hibernate getResultList() will return a list of object
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
	
	/**
	 * Show list of all media 
	 * @return Object array
	 */
	public List<Media_entity> getListMediaSearch(String search) {
		
		List<Media_entity> medias = new ArrayList<Media_entity>();
		// Init Hibernate session
		Session session = null;
		//Init Hibernate Transaction
		Transaction tx = null;
		// try connect to SQL and get list of media entity , Final will close the Hibernate session, The method Rollback here is not useful but its good pratice
		try {
			// Get the current session of Hibernate
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			// Starting the transaction
			tx =session.beginTransaction();
			// Hibernate Query method will return result of sql request and parse it into object 
			Query<Media_entity> query = session.createQuery("select distinct m from media m join fetch m.mediaType left join fetch m.genres left join fetch m.saga where m.media_title like ?0", Media_entity.class);
			// Hibernate getResultList() will return a list of object
			query.setParameter(0, "%"+search+"%");
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
			e.printStackTrace();
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
	
	/**
	 * Create one new media ( add to sql)
	 * @param media object
	 */
	public void creatMedia(Media_entity media) {	
			// Init Hibernate session
			Session session = null;
			// Init Hibernate transaction
			Transaction tx = null;		
			// Try to add the new media object to database if get error will rollback and final will close the hibernate session
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
		
	/**
	 * Method Update film
	 * @param id Integer
	 * @param mediaTitle String
	 * @param mediaYear Integer
	 * @param sagaName String
	 * @param listGenre Array of String
	 * @param valid Boolean
	 */
	public void editFilm(Integer id, String mediaTitle , Integer mediaYear, String sagaName, String[] listGenre, Boolean valid) {
		// Init Hibernate session
			Session session = null;
			// Init Hibernate transaction
			Transaction tx = null;
			Genre_entity genre = null;
			Saga_entity saga = null;
			// Try connect to sql and do sql update statement and will close the Hibernate session at the end with finally block
			try {
				// Get current hibernate session
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				// Start the transaction
				tx = session.beginTransaction();
				Media_entity media = getMediaByIdSimple(id);
				// Here we will add one or many genre to this media
				for (String str : listGenre) {
					// Do Hibernate Query method, It will return a query such as prepare statement with parametter
					Query<Genre_entity> q = session.createQuery("select g from genre g where g.genre_name = ?0", Genre_entity.class);
					q.setParameter(0, str); // Parse parametter in prepare statement query
					genre = q.getSingleResult(); // Hibernate getSingleResult() return an Object ( here is Genre entity)
					genre.setGenre_name(str);
					media.addGenre(genre);	
				}
				// Check if Saga in parametter is not null to do the traitement , if null we will passe
				if (sagaName != null) {
					Query<Saga_entity> q = session.createQuery("select s from saga s where s.saga_Name=?0", Saga_entity.class);
					q.setParameter(0, sagaName);
					saga = q.getSingleResult();	
					saga.setSaga_Name(sagaName);			
				} 
				media.setMedia_title(mediaTitle);
				media.setMedia_year(mediaYear);
				media.setMedia_valid(valid);
				media.setSaga(saga);
				// Once edit all info of our media , we will do commit , Hibernate will send all those news info to the database
				tx.commit();
				
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback(); // If have error in any traitement , Hibernate will cancel all SQL requests through this method RollBack
				}
				e.printStackTrace();
			}
			finally {
				if(session != null) {
					session.close(); // Close the session Hibernate at the end of traitement
				}
			}	
		}
	
	public void editMusic(Integer id, String mediaTitle , Integer mediaYear, String[] listGenre, Boolean valid) {
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
			media.setMedia_valid(valid);
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
	
	public void editBook(Integer id, String mediaTitle , Integer mediaYear, Long isbn, String sagaName, String[] listGenre, Boolean valid) {
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
			if (sagaName != null) {
				Query<Saga_entity> q = session.createQuery("select s from saga s where s.saga_Name=?0", Saga_entity.class);
				q.setParameter(0, sagaName);
				saga = q.getSingleResult();	
				saga.setSaga_Name(sagaName);
				
			}
			
			media.setMedia_title(mediaTitle);
			media.setMedia_year(mediaYear);
			media.setIsbn(isbn);
			media.setMedia_valid(valid);
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
	
	/**
	 * Delete media from database by its ID unique
	 * @param id Integer
	 */
	public void removeMedia(Integer id) {
		// Init Hibernate session
		Session session = null;
		// Init Hibernate transaction
		Transaction tx = null;
		// Try connect to sql and do the traitement, will close the session at the end with finally block
		 try {
			 // Get current session of Hibernate
			 session = HibernateUtil.getSessionFactory().getCurrentSession();
			 // Start Hibernate transaction
			 tx = session.beginTransaction();
			 Media_entity media = getMediaByIdSimple(id);
			 // Get a list of gerne of this media
			 Set<Genre_entity> genres = media.getGenre();
			 genres.clear();  // Delete all gernes of this media
			 session.delete(media);  // Delete media
			 tx.commit();  // Send commit 
			 
		 } catch (Exception e) {
			 if (tx != null) {
				 tx.rollback(); // Rollback if get error with any traitement
			 }
			 e.printStackTrace();
		 }
		finally {
			if (session != null) {
				session.close(); // Close the session at the end
			}
		}
	}
	
	public void removeMediaGenre(Integer id ) {
		
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
	
	/**
	 * The method to get media object by its ID
	 * @param id
	 * @return media entity
	 */
	public Media_entity getMediaById(Integer id) {
		// Init media entity
			Media_entity media = null;
			// init hibernate session
			Session session = null;
			// Init Hibernate Transaction
			Transaction tx = null;
			// Try to get the media object by its id , Rollback here is not important because we do any traitement but its good pratice. Final close Hibernate session
			try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				// Hibernate find method will return object
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
