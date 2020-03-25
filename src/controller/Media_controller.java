package controller;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import entity.MediaType_entity;
import entity.Media_Artist_Role_R;
import entity.Media_entity;
import service.MediaType_service;
import service.Media_Artist_Role_R_service;
import service.Media_service;
import view.InfoMedia_view;
import view.Media_view;
import view.tablemodel.MediaTableModel;

public class Media_controller  extends MouseAdapter {

	private Media_service media_service;
	private MediaType_service mediaType_service;
	private Media_Artist_Role_R_service mar_service;
	private MediaType_entity mediaType;
	private Media_view media_view;
	
	public Media_controller(Media_view media_view) {
		mediaType_service = new MediaType_service();
		media_service = new Media_service();
		mar_service = new Media_Artist_Role_R_service();
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
		final String html = "<html><body style='width: %1spx'>%1s";
		String title = target.getValueAt(row, 0).toString();
		String year = target.getValueAt(row, 1).toString();
		String genre = String.format(html, 200, target.getValueAt(row, 2).toString());
		String isbn = null;
		String type = target.getValueAt(row, 4).toString();
		
		List<String> director = new ArrayList<>();
		List<String> editor = new ArrayList<>();
		List<String> autor = new ArrayList<>();
		List<String> actor = new ArrayList<>();
		List<String> singer = new ArrayList<>();
		List<Media_entity>medias = new ArrayList<Media_entity>();
		List<Media_Artist_Role_R> mar = new ArrayList<Media_Artist_Role_R>();
		
		if (e.getClickCount() == 2 && target.getSelectedRow() != -1) {
            InfoMedia_view infoMedia_view = new InfoMedia_view();
            GridBagConstraints constraints = infoMedia_view.getConstraints();
            infoMedia_view.getLabelTitleContent().setText(title);
            infoMedia_view.getLabelYearContent().setText(year);
            infoMedia_view.getLabelGenreContent().setText(genre);
            medias = media_service.getMedia(title, Integer.parseInt(year) , type);
            if (!medias.isEmpty()) {
            	
            	for (Media_entity elt : medias) {
            		mar = mar_service.getListArtistJobWithId(elt.getMedia_id());
            		infoMedia_view.getLabelId().setText(Integer.toString(elt.getMedia_id()));
            		if (elt.getIsbn() != null) {
                		isbn = Long.toString(elt.getIsbn());
                	}
            	}
            	
            	for (Media_Artist_Role_R elt  : mar) {

                	if (elt.getJobs().getJobName().equals("Acteur")) {
                		actor.add(elt.getArtists().getSurName());
                	}
                	if (elt.getJobs().getJobName().equals("Auteur")) {
                		autor.add(elt.getArtists().getSurName());
                	}
                	if (elt.getJobs().getJobName().equals("Realisateur")) {
                		director.add(elt.getArtists().getSurName());
                	}
                	if (elt.getJobs().getJobName().equals("Editeur")) {
                		editor.add(elt.getArtists().getSurName());
                	}
                	if (elt.getJobs().getJobName().equals("Chanteur")) {
                		singer.add(elt.getArtists().getSurName());
                	}
                }     
            	
	        	int i =3;
	        	if (isbn != null) {
	        		constraints.gridx = 0;
		    		constraints.gridy = i;
	        		infoMedia_view.getLabelIsbn().setText("ISBN");
	        		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelIsbn(), constraints);
	        		
	        		constraints.gridx = 1;
	        		infoMedia_view.getLabelIsbnContent().setText(isbn);
	        		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelIsbnContent(), constraints);
	        		i++;
	        	}
	        	if(!director.isEmpty()) {
	
	        		infoMedia_view.getLabelDirector().setText("Director");
		    		infoMedia_view.getLabelDirectorContent().setText(director.toString().replaceAll("\\[|\\]", ""));
		    		
		    		constraints.gridx = 0;
		    		constraints.gridy = i;
		    		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelDirector(), constraints);
		    		
		    		constraints.gridx = 1;
		    		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelDirectorContent(), constraints);
		    		i++;
		    	}
	        	if(!editor.isEmpty()) {
		    		infoMedia_view.getLabelEditor().setText("Editor");
		    		infoMedia_view.getLabelEditorContent().setText(editor.toString().replaceAll("\\[|\\]", ""));
		    		
		    		constraints.gridx = 0;
		    		constraints.gridy = i;
		    		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelEditor(), constraints);
		    		
		    		constraints.gridx = 1;
		    		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelEditorContent(), constraints);
		    		i++;
		    	}
	        	if(!autor.isEmpty()) {
		    		infoMedia_view.getLabelAutor().setText("Autor");
		    		infoMedia_view.getLabelAutorContent().setText(autor.toString().replaceAll("\\[|\\]", ""));
		    		constraints.gridx = 0;
		    		constraints.gridy = i;
		    		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelAutor(), constraints);
		    		
		    		constraints.gridx = 1;
		    		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelAutorContent(), constraints);
		    		i++;
		    	}
		    	if(!actor.isEmpty()) {
		    		infoMedia_view.getLabelActor().setText("Actor");
		    		infoMedia_view.getLabelActorContent().setText(String.format(html, 200,actor.toString().replaceAll("\\[|\\]", "")));
		    		constraints.gridx = 0;
		    		constraints.gridy = i;
		    		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelActor(), constraints);
		    		
		    		constraints.gridx = 1;
		    		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelActorContent(), constraints);
		    		i++;
		    	}
		    	if(!singer.isEmpty()) {
		    		infoMedia_view.getLabelSinger().setText("Singer");
		    		infoMedia_view.getLabelSingerContent().setText(singer.toString().replaceAll("\\[|\\]", ""));
		    		constraints.gridx = 0;
		    		constraints.gridy = i;
		    		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelSinger(), constraints);
		    		
		    		constraints.gridx = 1;
		    		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelSingerContent(), constraints);
		    		i++;
		    	}          
		    	infoMedia_view.setVisible(true);         
            }
		}
	}
	
}
