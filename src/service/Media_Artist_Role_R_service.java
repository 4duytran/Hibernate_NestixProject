package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import config.HibernateUtil;
import entity.Media_Artist_Role_R;

public class Media_Artist_Role_R_service {

	public List<Media_Artist_Role_R> getListArtistJobWithId(Integer id) {
		List<Media_Artist_Role_R> medias = new ArrayList<Media_Artist_Role_R>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query<Media_Artist_Role_R> q = session.createQuery("select m from role_artiste m join fetch m.artists join fetch m.jobs join fetch m.medias where m.medias.media_id = ?0", Media_Artist_Role_R.class);
			q.setParameter(0, id);
			medias = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return medias;	
	}
	
	
}
