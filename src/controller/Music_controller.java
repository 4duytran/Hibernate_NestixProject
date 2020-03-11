package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import entity.Media_entity;
import service.Genre_service;
import service.Media_service;
import view.Music_view;
import view.tablemodel.MusicTableModel;

public class Music_controller  extends MouseAdapter {

	private Music_view music_view;
	private Genre_service genre_service;
	private Media_service media_service;

	private List<String> listGenre = new ArrayList<String>();

	
	public Music_controller(Music_view music_view) {
		genre_service = new Genre_service();
		media_service = new Media_service();
		listGenre = genre_service.getGenreListName();
		this.music_view = music_view;
	}
	
	public void genreList() {
		genre_service.getGenreList(music_view.getMu_listGenre());
	}
	
	public MusicTableModel tableModel() {
		List<String> columnsNames = new ArrayList<String>();
		columnsNames.add("Media Name");
		columnsNames.add("Year");
		columnsNames.add("Genre");
		columnsNames.add("Media type");
		columnsNames.add("Valid");
		List<Media_entity> medias = new ArrayList<Media_entity>();
		medias = media_service.getListMusic();
		return new MusicTableModel(columnsNames, medias);
	}
	
	public void updateMusic(ActionEvent e) {
		List<Media_entity> medias = new ArrayList<Media_entity>();
		Integer id = null;
		String title = music_view.getTextMusicTitle().getText();
		Integer year = Integer.parseInt(music_view.getTextMusicYear().getText());
		String type = "Musique";
		String[] genreList = music_view.getMu_listGenre().getSelectedItems();
		medias =  media_service.getMedia(title, year, type);
		for (Media_entity media : medias) {
			id = media.getMedia_id() ;
		}	 
		
		if (id == null || (medias.size() <= 1 && id.equals(music_view.getMusicId())) ) {
			media_service.removeGenre(music_view.getMusicId());
			media_service.editMusic(music_view.getMusicId(), title, year, genreList);	
			music_view.initTableContent();
			JOptionPane.showMessageDialog(this.music_view, "The media edited successfully");
		} else {
			JOptionPane.showMessageDialog(this.music_view, "This media is exist already in database!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		List<Media_entity> medias = new ArrayList<Media_entity>();
		String [ ] genreList = null;
		String [ ] sagaList = null;
		music_view.getMu_listGenre().deselect(music_view.getMu_listGenre().getSelectedIndex());
	
		JTable target = (JTable) e.getSource();
		int row = target.getSelectedRow();
		music_view.getTextMusicTitle().setText(target.getValueAt(row, 0).toString());
		music_view.getTextMusicYear().setText(target.getValueAt(row, 1).toString());
		String genre = target.getValueAt(row, 2).toString();
		if (genre != null && !genre.isEmpty()) {
			genreList  = genre.split(",");
			for (String str : genreList) {
				music_view.getMu_listGenre().select(listGenre.indexOf(str));
			}
		}
		
		String title = music_view.getTextMusicTitle().getText();
		Integer year = Integer.parseInt(music_view.getTextMusicYear().getText());
		String type = "Musique";
		medias =  media_service.getMedia(title, year, type);
		for (Media_entity media : medias) {
			music_view.setFilmId(media.getMedia_id()) ;
		}
	}
	
}
