package service;



import java.awt.List;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.Genre_entity;

	public class Genre_service {
	
		public List getGenreList(List list) {
			java.util.List<Genre_entity> mediaGenreList = new ArrayList<Genre_entity>();
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				Query<Genre_entity> reg = session.createQuery("select g from genre g", Genre_entity.class);
				mediaGenreList = reg.getResultList();
				for (Genre_entity genres : mediaGenreList) {
					list.add(genres.getGenre_name());
				}
			} catch ( Exception e) {
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
		
		@SuppressWarnings("rawtypes")
		public java.util.List getGenreListName() {
			java.util.List<Genre_entity> mediaGenreList = new ArrayList<Genre_entity>();
			java.util.List<String> mediaGenreListName = new ArrayList<>();
			Session session = null;
			Transaction tx = null;
			try {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				Query<Genre_entity> reg = session.createQuery("select g from genre g", Genre_entity.class);
				mediaGenreList = reg.getResultList();
				for (Genre_entity g : mediaGenreList) {
						mediaGenreListName.add(g.getGenre_name());
				}
			} catch ( Exception e) {
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
			return mediaGenreListName;
		}
		
	
}
