package test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import config.HibernateUtil;

public class TestConnection {

	/**
	 * Test connecion SQL
	 */
	@Test
	public void testConnectionSql() {
		Session session = null; // Init Hibernate session
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession(); // Get connection from Hibernate class
			// check if we get the connection or not
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error connection SQL"); // Show error
		}
		// Close the connection after executed
		finally {
			if (session != null) {
				session.close();
			}
		}	
	}
	

	

}
