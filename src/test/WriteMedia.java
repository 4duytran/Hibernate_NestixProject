package test;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import config.HibernateUtil;
import entity.MediaType_entity;
import entity.Media_entity;

class WriteMedia {

	/**
	 * Test Create one new media ( add to sql)
	 * @param media object
	 */
	@Test
	public void creatMedia() {	
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
				media.setMedia_title("test Creation Media JUnit");
				media.setMedia_year(2020);
				media.setMedia_type(mediaType); // Init media type
				session.persist(media); // Add media to object persistant
				tx.commit();
				System.out.println("Media created successfully with");
				
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
}
