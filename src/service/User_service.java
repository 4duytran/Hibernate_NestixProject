package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.Level_entity;
import entity.User_entity;

public class User_service {

	/**
	 * Show list of all users from SQL
	 * @return
	 */
	public List<User_entity> getListUser(String search) {
		List<User_entity> users = new ArrayList<User_entity>();
		Session session = null; // Init Hibernate session
		Transaction tx = null; // Init Hibernate transaction
		// we will try to get the connection and get list of user from SQL, if get error we will cancel the transaction and close the connection
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession(); // Get current session of Hibernate with the class HibernateUtil
			tx = session.beginTransaction(); // Open Hibernate's transaction for security connection Synchrone
			// Create Hibernate quey with java objet
			Query<User_entity> q = session.createQuery("select u from utilisateur u join fetch u.level where u.firstName like ?0 or u.lastName like ?0 or u.userEmail like ?0 order by u.p_id", User_entity.class);  
			q.setParameter(0, "%"+search+"%");
			users = q.getResultList(); // This Hibernate method will return 1 java list
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); 
			}
			e.printStackTrace();
		}
		// Here we will check if the connection is exist before close it
		finally {
			if (session != null) {
				session.close();  
			}
		}
		return users;
	}
	
	/**
	 * Modify user 
	 * @param id Integer
	 * @param firstName String
	 * @param lastName String
	 * @param email String
	 * @param password String
	 * @param levelName String
	 * @param banned Boolean
	 */
	public void updateUser(Integer id, String firstName, String lastName, String email, String password, String levelName, Boolean banned) {
		
		Session session = null; //Init Hibernate session
		Transaction tx = null; // Init Hibernate transaction
		Level_entity level = null;
		// we will try to get the connection and get list of user from SQL, if get error we will cancel the transaction and close the connection
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession(); // Get current session of Hibernate with the class HibernateUtil
			tx = session.beginTransaction(); // Open Hibernate's transaction for security connection Synchrone
			User_entity user = getUserByIdSimple(id);  // Get java objet by an internal method from this class
			// Create Hibernate quey with java objet
			Query<Level_entity> q = session.createQuery("select l from level l where l.levelName = ?0", Level_entity.class);
			q.setParameter(0, levelName); // Init param for prepare statement
			level = q.getSingleResult();  // The method getSingleResult() from Hibernate return 1 java object
			// Here we will initilize user object with new value
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUserEmail(email);
			user.setUsePassword(password);
			user.setLevel(level);
			user.setBlocked(banned);
			// Send the request to SQL
			tx.commit();	
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); // Cancel action if get error during traitement
			}
			e.printStackTrace();
		}
		// Here we will check if the connection is exist before close it
		finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * Show user by its ID unique
	 * @param id Integer
	 * @return User Object
	 */
	public User_entity getUserById(Integer id) {
		
		User_entity user = null; // Init user object
		Session session = null; // Init Hibernate session
		Transaction tx = null; // Init Hibernate transaction
		// here we will try to get SQL connection and do request, if have error we will cancel all action and close the connection
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();  // Get current session of Hibernate with the class HibernateUtil
			tx = session.beginTransaction(); // Open Hibernate's transaction for security connection Synchrone
			user = session.get(User_entity.class, id); // Method get() from Hibernate wil return 1 java object parse in param
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); // Cancel action if get error during traitement
			}
			e.printStackTrace();
		}
		// Here we will check if the connection is exist before close it
		finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
	}
	
public User_entity getUserByIdSimple(Integer id) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		User_entity user = session.get(User_entity.class, id);
		return user;
		
	}
	
	public User_entity getUserByEmail(String email) {
		
		User_entity user = null;
		Session session = null;
		Transaction tx = null;
		// we will try to get the connection and get list of user from SQL, if get error we will cancel the transaction and close the connection
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession(); // Get current session of Hibernate with the class HibernateUtil
			tx = session.beginTransaction();
			Query<User_entity> q = session.createQuery("select u from utilisateur u where u.userEmail = ?0", User_entity.class);
			q.setParameter(0, email);
			user = q.getSingleResult();
			
		} catch (NoResultException e) {
			System.out.println("No user found");
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
	}
	
	/**
	 * Create new user
	 * @param user Object
	 */
	public void creatUser(User_entity user) {
		
		Session session = null; //Init Hibernate session
		Transaction tx = null; // Init Hibernate Transaction
		// we will try to get the connection and get list of user from SQL, if get error we will cancel the transaction and close the connection
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession(); // Get current session of Hibernate with the class HibernateUtil
			tx = session.beginTransaction(); // Open Hibernate's transaction for security connection Synchrone
			session.persist(user); // Attach uer object to Hibernate object persitance
			tx.commit(); // Send request to SQL
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); // Cancel all action if get error during the traitement
			}
			e.printStackTrace();
		}
		// Here we will check if the connection is exist before close it
		finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	
	/**
	 * Delete user
	 * @param id Integer
	 */
	public void removeUser(Integer id) {
		
		Session session = null; //Init Hibernate session
		Transaction tx = null; // Init Hibernate Transaction
		// we will try to get the connection and get list of user from SQL, if get error we will cancel the transaction and close the connection
		try {			
			session = HibernateUtil.getSessionFactory().getCurrentSession(); // Get current session of Hibernate with the class HibernateUtil
			tx = session.beginTransaction();  // Open Hibernate's transaction for security connection Synchrone
			User_entity user = getUserByIdSimple(id); // Get uer object with an internal method from this class
			session.delete(user); // action delete object persistance of Hibernate
			tx.commit(); // Send SQL request
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); // Cancel all action if get error during the traitement
			}
			e.printStackTrace();
		}
		// Here we will check if the connection is exist before close it
		finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	public User_entity checkUserExiste(Integer id, String email) {	
		User_entity user = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<User_entity> q = session.createQuery("select u from utilisateur u where u.userEmail = ?0 and u.p_id <> ?1", User_entity.class);
			q.setParameter(0, email);
			q.setParameter(1, id);
			user = q.getSingleResult();
			
		} catch (NoResultException e) {
			System.out.println("No user found");
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
		
	}
	
	public User_entity checkEmailExiste(String email) {
		
		User_entity user = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<User_entity> q = session.createQuery("select u from utilisateur u where u.userEmail = ?0 ", User_entity.class);
			q.setParameter(0, email);
			user = q.getSingleResult();
			
		} catch (NoResultException e) {
			System.out.println("No user found");
		}
		finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
	}
	
	
	
}
