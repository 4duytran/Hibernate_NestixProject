package controller;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import entity.Artist_entity;
import entity.MediaType_entity;
import entity.Media_Artist_Role_R;
import entity.Media_entity;
import service.MediaType_service;
import service.Media_Artist_Role_R_service;
import service.Media_service;
import view.InfoMedia_view;
import view.Main_view;
import view.Media_view;
import view.tablemodel.ArtistTableModel;
import view.tablemodel.FilmTableModel;
import view.tablemodel.MediaTableModel;

public class Search_controller  extends KeyAdapter {

	private Media_service media_service;
	private MediaType_service mediaType_service;
	private Media_Artist_Role_R_service mar_service;
	private MediaType_entity mediaType;
	private Media_view media_view;
	private Main_view main_view;
	private Media_controller media_controller;
	
	/**
	 * Cobstructor of media controller
	 * @param media_view
	 */
	public Search_controller(Main_view main_view) {
		//Init Media type service
		mediaType_service = new MediaType_service();
		// Init Media service
		media_service = new Media_service();
		// Init media artist role service
		mar_service = new Media_Artist_Role_R_service();
		
		this.main_view = main_view;
	}
	
	/**
	 * Search item
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// Here we will try to get Compoment of the main view
		Component card = null;
		for (Component comp : main_view.getMain().getComponents()) {
		    if (comp.isVisible() == true) {
		        card = comp;
		    }
		}
		String value = main_view.getTextSearch().getText();
		
		// Get name of Cardlayout then search item
		switch(card.getName()) {
		case "Media":
			main_view.getMediaPanel().initTableContent(value);
		case "Artist":
			main_view.getArtistPanel().initTableContent(value);
		case "User":
		main_view.getUserPanel().initTableContent(value);
		default:
			main_view.getMediaPanel().initTableContent(value);
		}
		
//		System.out.println(card.getName());
	}
	
	
	
}
