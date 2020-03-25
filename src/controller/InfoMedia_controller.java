package controller;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.Genre_entity;
import entity.Media_Artist_Role_R;
import entity.Media_entity;
import service.InfoMedia_service;
import service.Media_Artist_Role_R_service;
import service.Media_service;
import view.InfoMedia_view;

public class InfoMedia_controller {

	private InfoMedia_view infoMedia_view;
	private Media_service media_service;
	private InfoMedia_service infoMedia_service ;
	private Media_controller media_controller = new Media_controller(null);
	private Media_Artist_Role_R_service mar;
	private Integer id;
	

	public InfoMedia_controller (InfoMedia_view infoMedia_view) {
		this.infoMedia_view = infoMedia_view;
		media_service = new Media_service();
		infoMedia_service = new InfoMedia_service();
		mar = new Media_Artist_Role_R_service();
		
	}
	
	public InfoMedia_controller (Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void deselectList(ActionEvent e) {
		infoMedia_view.getListArtist().deselect(infoMedia_view.getListArtist().getSelectedIndex());
	}
	
	public void listArtist() {
		infoMedia_service.getArtistList(infoMedia_view.getListArtist());
	}
	
	public Object[] jobList() {
		Object[] o = infoMedia_service.getListJob();
		return o;
	}
	
	public void addArtistJob(ActionEvent e) {
		Integer id = Integer.parseInt(infoMedia_view.getLabelId().getText());
		String [] artistList ;
		Media_entity media = media_service.getMediaById(id);
		artistList = infoMedia_view.getListArtist().getSelectedItems();
		String jobName = (String) infoMedia_view.getJobList().getSelectedItem();
		infoMedia_service.addMediaJob(id, artistList, jobName);
		reloadPanel ();
	}
	
	public void reloadPanel () {
		infoMedia_view.getPanelInfo().removeAll();
		Integer id = Integer.parseInt(infoMedia_view.getLabelId().getText());
		Media_entity media = infoMedia_service.getMediaWithGenreById(id);
		List<Media_Artist_Role_R> medias = mar.getListArtistJobWithId(id);
		final String html = "<html><body style='width: %1spx'>%1s";
		List<String> director = new ArrayList<>();
		List<String> editor = new ArrayList<>();
		List<String> autor = new ArrayList<>();
		List<String> actor = new ArrayList<>();
		List<String> singer = new ArrayList<>();
		String isbn = null;
		GridBagConstraints constraints = infoMedia_view.getConstraints();
		
		for (Media_Artist_Role_R elt  : medias) {
			
			List<String> genres = new ArrayList<>();
			for (Genre_entity g : media.getGenre()) {
				genres.add(g.getGenre_name());
			}
			String title = elt.getMedias().getMedia_title();
			String year = Integer.toString(elt.getMedias().getMedia_year());
			String genre = (genres.isEmpty()) ? "Null" : String.format(html, 200,String.join(",", genres));
			
			infoMedia_view.getLabelTitleContent().setText(title);
            infoMedia_view.getLabelYearContent().setText(year);
            infoMedia_view.getLabelGenreContent().setText(genre);
			
        	if (elt.getMedias().getIsbn() != null) {
        		isbn = Long.toString(elt.getMedias().getIsbn());
        	}
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
		
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelTitle(), constraints);

		constraints.gridx = 1;
		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelTitleContent(), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelYear(), constraints);
		
		constraints.gridx = 1;
		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelYearContent(), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelGenre(), constraints);
		
		constraints.gridx = 1;
		infoMedia_view.getPanelInfo().add(infoMedia_view.getLabelGenreContent(), constraints);
    	
    	int i =3;
    	if (isbn != null) {
    		constraints.gridx = 0;
    		constraints.gridy = i;
    		infoMedia_view.getLabelIsbn().setText("Book Isbn");
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
    	infoMedia_view.getPanelInfo().revalidate();
    	infoMedia_view.getPanelInfo().repaint();			
	}
	
	
	
}
