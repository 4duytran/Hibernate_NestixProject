package controller;

import java.awt.event.ActionEvent;

import view.Main_view;

public class Main_controller {
	
	private Main_view main_view;
	
	public Main_controller( Main_view main_view ) {
		this.main_view = main_view;
	}

	public void showMediaView( ActionEvent e ) {
		main_view.getContent().show(main_view.getMain(), "Media Panel");
		main_view.getMediaPanel().initTableContent();
	}
	
	public void showFilmView( ActionEvent e ) {
		main_view.getContent().show(main_view.getMain(), "Film Panel");
		main_view.getFilmPanel().initTableContent();
	}

	public void showBookView( ActionEvent e ) {
		main_view.getContent().show(main_view.getMain(), "Book Panel");
		main_view.getBookPanel().initTableContent();
	}
	
}
