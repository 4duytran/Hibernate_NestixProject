package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controller.Media_controller;
import entity.Media_entity;
import service.Media_service;
import service.TestHibernate;
import view.Login_view;

public class MainApplication {


	public static void main(String[] args) throws Exception {

		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		Login_view interfaceApp = new Login_view();
		interfaceApp.setVisible(true);
		
		
//		TestHibernate test = new TestHibernate();
//		System.out.println(test.getMediaById(35));
		
//		Media_service test = new Media_service();
//		test.getMedia("war", 2000,"Film");
		
//		List<Media_entity> medias = new ArrayList<Media_entity>();
//		List< ArrayList<Object> > values = new ArrayList<ArrayList<Object>>();
//		medias = test.getListMedia();
//		for (Media_entity media : medias) {
//			System.out.println(media.getMedia_title());
//		}
//	
		
		
		
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
