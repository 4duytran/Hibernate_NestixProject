package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateUtil;
import entity.MediaType_entity;
import entity.Media_entity;

public class TestMedia {

	
	/**
	 * Test connecion SQL
	 * @return Boolean
	 */
	public static boolean testConnectionSql() {
		Boolean reponse = false; // Init reponse
		Session session = null; // Init Hibernate session
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession(); // Get connection from Hibernate class
			// check if we get the connection or not
			if (session != null)  
			{
				reponse = true;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		// Close the connection after executed
		finally {
			if (session != null) {
				session.close();
			}
		}
		return reponse;
		
	}
	
	/**
	 * Test get media object by its ID
	 * @param id
	 * @return media entity
	 */
	public static String testGetMediaById(Integer id) {
		// Init media entity
			Media_entity media = null;
			String reponse = "";
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
				if (media != null)
				{
					reponse = "media name: " + media.getMedia_title() + " media id: " +media.getMedia_id();
				}
				
			} catch (Exception e) {		
				e.printStackTrace();
			}
			finally {
				if(session != null) {
					session.close();
				}
			}
			return reponse;
		}

	/**
	 * Test Create one new media ( add to sql)
	 * @param media object
	 */
	public static void creatMedia() {	
			// Init Hibernate session
			Session session = null;
			// Init Hibernate transaction
			Transaction tx = null;		
			// Try to add the new media object to database if get error will rollback and final will close the hibernate session
			try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				Media_entity media = new Media_entity();
				MediaType_entity mediaType = new MediaType_entity();
				mediaType.setMediaType_id(3);
				media.setMedia_title("testCreationMedia2");
				media.setMedia_year(0000);
				media.setMedia_type(mediaType); // Init media type
				session.persist(media); // Add media to object persistant
				tx.commit();
				System.out.println("Media created successfully");
				
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
					fail("Got error , cancel all traitement");
				}
				e.printStackTrace();
			}
			// Close the connection
			finally {
				if(session != null) {
					session.close();
				}
			}
			
		}
	
	
	/**
	 * Test Rollback using Delete media from database by its ID unique
	 * @param id Integer
	 */
	public static void removeMedia() {
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
			 Media_entity media = getMediaByIdSimple(138);
			 Media_entity media2 = getMediaByIdSimple(139); 
			 // Get a list of gerne of this media
			 
			 session.delete(media);  // Delete media
			 session.delete(media2);  // Delete media
			 tx.commit();  // Send commit 
			 System.out.println("Delete 2 media successfully");
			 
		 } catch (Exception e) {
			 if (tx != null) {
				 tx.rollback(); // Rollback if get error with any traitement
				 fail("Got error , cancel all traitement");
			 }
			 e.printStackTrace();
		 }
		finally {
			if (session != null) {
				session.close(); // Close the session at the end
			}
		}
	}
	
	public static Media_entity getMediaByIdSimple(Integer id) {
		Media_entity media = null;
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		media = session.get(Media_entity.class, id);
		return media;
	}
	
}
