package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entity.MediaType_entity;
import entity.Media_entity;
import service.MediaType_service;
import service.Media_service;
import view.Media_view;
import view.tablemodel.MediaTableModel;

public class Media_controller  {

	private Media_service media_service;
	private MediaType_service mediaType_service;
	private MediaType_entity mediaType;
	private Media_view media_view;
	
	public Media_controller(Media_view media_view) {
		mediaType_service = new MediaType_service();
		media_service = new Media_service();
		this.media_view = media_view;
	}
	
	public MediaTableModel tableModel() {
		List<String> columnsNames = new ArrayList<String>();
		columnsNames.add("Media Name");
		columnsNames.add("Year");
		columnsNames.add("Media Genres");
		columnsNames.add("Media Saga");
		columnsNames.add("Media type");
		columnsNames.add("Blocked");
		List<Media_entity> medias = new ArrayList<Media_entity>();
		medias = media_service.getListMedia();
		return new MediaTableModel(columnsNames, medias);
	}
	
	public Object[] mediaType() {
		Object[] o = mediaType_service.getListMediaType();
		return o;
	}
	
	public void creatMedia(ActionEvent e) {
		String title = media_view.getTextMediaTitle().getText();
		Integer year = Integer.parseInt(media_view.getTextMediaYear().getText());
		String type = media_view.getObjectList().getSelectedItem().toString();
		List<Media_entity> medias = new ArrayList<Media_entity>();
		if (title.length() > 0 && type.length() > 0 && !type.equals("--None--") && !title.equalsIgnoreCase("Title of new media")) {
			medias = media_service.getMedia(title, year, type);
			if (medias.isEmpty()) {
			Media_entity media = new Media_entity();
			mediaType = mediaType_service.getMediaTypeId(type);
			media.setMedia_title(title);
			media.setMedia_year(year);
			media.setMedia_type(mediaType);
			media.setMedia_valid(true);
			media_service.creatMedia(media);
			media_view.initTableContent();
			JOptionPane.showMessageDialog(this.media_view, "New media added successfully");
			} else {
				JOptionPane.showMessageDialog(this.media_view, "This media is exist already in database!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this.media_view,"The case is empty \n" + "Please complete the media type before retry!");
		}
	}

	
}
