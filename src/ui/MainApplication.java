package ui;

import java.util.List;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import entity.Media_entity;
import service.TestHibernate;
import view.Login_view;

public class MainApplication {


	public static void main(String[] args) throws Exception {

		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		Login_view interfaceApp = new Login_view();
		interfaceApp.setVisible(true);
		
	
		TestHibernate test = new TestHibernate();
		List<Media_entity> medias = test.getListArtistJob();
		
		for (Media_entity media : medias) {
			System.out.println(media.getMedia_title()+"  "+media.getMedia_type().getMediaType_name());
//			for (Artist_entity artist : media.getArtist()) {
//				System.out.println(artist.getSurName());
//			}
//			for (Job_entity job : media.getJob()) {
//				System.out.println(job.getJobName());
//			}
			media.getArtist_job().entrySet().forEach(entry->{
			    System.out.println(entry.getKey().getSurName() + " " + entry.getValue().getJobName());  
			 });
			System.out.println("-------------------------------");
		}
//		System.out.println(test.getMediaById(35));
		
//		Media_service test = new Media_service();
//		test.getMedia("war", 2000,"Film");
//		String z = null;
//		User_service test = new User_service();
//		List<User_entity> medias = new ArrayList<User_entity>();
//		List< ArrayList<Object> > values = new ArrayList<ArrayList<Object>>();
//		medias = test.getListUser();
//		for (User_entity media : medias) {
//			String s = new SimpleDateFormat("MMMM/dd/yyyy").format(media.getDate());
//			if (null != media.getDob()) {
//				z = new SimpleDateFormat("MMMM/dd/yyyy").format(media.getDob());
//			} 
//			System.out.println(media.getP_id() + "  " + media.getFirstName() + "  " + media.getLastName() + "  " +s + " ------" + z +  "  " + media.getLevel().getLevelName());
//		}
		
//		String pw_hash = "$2y$10$ISd90To1jfCxtNEFjI0FKOqaP6FKV6MkkB8PokexKformpQ0MwaTa".replaceFirst("2y", "2a");
//		System.out.println("jBCrypt Hash: " +pw_hash);
//		System.out.println(BCrypt.checkpw("test", pw_hash));
		
//		TestHibernate test = new TestHibernate();
//		List<Media_entity> medias = new ArrayList<Media_entity>();
//		medias = test.getListMedia();
//		System.out.println(Arrays.deepToString(medias.toArray()));
//		Iterator i = medias.iterator();
//		for (Media_entity media : medias) {
//			System.out.println(media.getMedia_title());
//		}
//        while(i.hasNext())
//        {
//    		Media_entity jpa1Obj = (Media_entity)i.next();
//            System.out.println("id="+jpa1Obj.getMedia_id());
//
//        }
		
//		Media_controller test = new Media_controller(null);
//		Object[] o = test.mediaType();
//		for (Object i : o) {
//			System.out.println(i);
//		}
		
//		Media_service test = new Media_service();
//		List<Media_entity> medias = new ArrayList<Media_entity>();
//		medias = test.getMedia("work", 2016, "musique");
//		System.out.println(medias);
//		for (Media_entity media : medias) {
//			System.out.println(media.getMedia_title());
//			System.out.println(media.getMedia_year());
//			System.out.println(media.getMedia_type().getMediaType_name());
//			System.out.println(media.getMedia_valid());
//		}
//		
//		System.out.println(medias.isEmpty());
			
	}
}
