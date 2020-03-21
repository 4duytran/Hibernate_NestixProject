package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import entity.MediaType_entity;
import entity.Media_entity;
import service.MediaType_service;
import service.Media_service;
import view.InfoMedia_view;
import view.Media_view;
import view.tablemodel.MediaTableModel;

public class Media_controller  extends MouseAdapter {

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
		columnsNames.add("Valid");
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JTable target = (JTable) e.getSource();
		int row = target.getSelectedRow();
		String title = target.getValueAt(row, 0).toString();
		String year = target.getValueAt(row, 1).toString();
		String genre = target.getValueAt(row, 2).toString();
		String type = target.getValueAt(row, 4).toString();
		if (e.getClickCount() == 2 && target.getSelectedRow() != -1) {
            InfoMedia_view info = new InfoMedia_view();
            info.getLabelTitleContent().setText(title);
            info.getLabelYearContent().setText(year);
            info.getLabelGenreContent().setText(genre);
            Media_entity media = media_service.getSimpleMedia(title, Integer.parseInt(year) , type);
            if (media != null) {
            	media.getArtist_job().entrySet().forEach(entry->{
    			    System.out.println(entry.getKey().getSurName() + " " + entry.getValue().getJobName());  
    			 });
            }          
            info.setVisible(true);         
        }
	}

	
}
