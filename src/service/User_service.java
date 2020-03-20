package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.Level_entity;
import entity.User_entity;

public class User_service {

	public List<User_entity> getListUser() {
		
		List<User_entity> users = new ArrayList<User_entity>();
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<User_entity> q = session.createQuery("select u from utilisateur u join fetch u.level order by u.p_id", User_entity.class);
			users = q.getResultList();
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
		return users;
	}
	
	public void updateUser(Integer id, String firstName, String lastName, String email, String password, String levelName, Boolean banned) {
		
		Session session = null;
		Transaction tx = null;
		Level_entity level = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			User_entity user = getUserByIdSimple(id);
			Query<Level_entity> q = session.createQuery("select l from level l where l.levelName = ?0", Level_entity.class);
			q.setParameter(0, levelName);
			level = q.getSingleResult();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUserEmail(email);
			user.setUsePassword(password);
			user.setLevel(level);
			user.setBlocked(banned);
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
	
	public User_entity getUserById(Integer id) {
		
		User_entity user = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			user = session.get(User_entity.class, id);
			
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
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query<User_entity> q = session.createQuery("select u from utilisateur u where u.userEmail = ?0", User_entity.class);
			q.setParameter(0, email);
			user = q.getSingleResult();
			
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
		return user;
	}
	
	public void creatUser(User_entity user) {
		
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.persist(user);
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
	
public void removeUser(Integer id) {
		
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			User_entity user = getUserByIdSimple(id);
			session.delete(user);
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
		return user;
		
	}
	
	
	
}
