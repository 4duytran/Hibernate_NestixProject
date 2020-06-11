package test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import config.HibernateUtil;
import entity.Media_entity;

public class ReadMedia {

	
	/**
	 * Test get media object by its ID
	 * @param id
	 * @return media entity
	 */
	@Test
	public void testGetMediaById() {
		// Init media entity
			Media_entity media = null;
			String response = "";
			int id = 15;
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
					response = "media name: " + media.getMedia_title() + " media id: " +media.getMedia_id();
				}
				System.out.println(response);
				
			} catch (Exception e) {		
				e.printStackTrace();
				fail("Error connection SQL");
			}
			finally {
				if(session != null) {
					session.close();
				}
			}
		
		}
}
