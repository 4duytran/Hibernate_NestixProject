package controller;

import java.awt.event.ActionEvent;

import view.Main_view;

public class Main_controller {
	
	private Main_view main_view;
	
	/**
	 * Constructor of Main view
	 * @param main_view
	 */
	public Main_controller( Main_view main_view ) {
		this.main_view = main_view;
	}

	/**
	 * Show media view
	 * @param e
	 */
	public void showMediaView( ActionEvent e ) {
		main_view.getContent().show(main_view.getMain(), "Media Panel");
		main_view.getMediaPanel().initTableContent();
	}
	
	/**
	 * Show film view
	 * @param e
	 */
	public void showFilmView( ActionEvent e ) {
		main_view.getContent().show(main_view.getMain(), "Film Panel");
		main_view.getFilmPanel().initTableContent();
	}

	/**
	 * Show book view
	 * @param e
	 */
	public void showBookView( ActionEvent e ) {
		main_view.getContent().show(main_view.getMain(), "Book Panel");
		main_view.getBookPanel().initTableContent();
	}
	
	/**
	 * Show music View
	 * @param e
	 */
	public void showMusicView( ActionEvent e ) {
		main_view.getContent().show(main_view.getMain(), "Music Panel");
		main_view.getMusicPanel().initTableContent();
	}
	
	/**
	 * Show user view
	 * @param e
	 */
	public void showUserView( ActionEvent e ) {
		main_view.getContent().show(main_view.getMain(), "User Panel");
		main_view.getUserPanel().initTableContent();
	}
	
	/**
	 * Show artist view
	 * @param e
	 */
	public void showArtistView( ActionEvent e ) {
		main_view.getContent().show(main_view.getMain(), "Artist Panel");
		main_view.getArtistPanel().initTableContent();
	}
	
}
