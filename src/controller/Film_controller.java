package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import entity.Media_entity;
import service.Genre_service;
import service.Media_service;
import service.Saga_service;
import view.Film_view;
import view.tablemodel.FilmTableModel;

public class Film_controller extends MouseAdapter  {

	private Film_view film_view;
	private Genre_service genre_service;
	private Media_service media_service;
	private Saga_service saga_service;
	private List<String> listGenre = new ArrayList<String>();
	private List<String> listSaga = new ArrayList<String>();
	
	
	public Film_controller(Film_view film_view) {
		genre_service = new Genre_service();
		media_service = new Media_service();
		saga_service = new Saga_service();
		listGenre = genre_service.getGenreListName();
		listSaga = saga_service.getSagaListName();
		this.film_view = film_view;
	}
	
	public void genreList() {
		genre_service.getGenreList(film_view.getF_listGenre());
	}
	
	public void sagaList() {
		saga_service.getSagaList(film_view.getF_listSaga());
	}
	
	public FilmTableModel tableModel() {
		List<String> columnsNames = new ArrayList<String>();
		columnsNames.add("Media Name");
		columnsNames.add("Year");
		columnsNames.add("Genre");
		columnsNames.add("Media Saga");
		columnsNames.add("Media type");
		columnsNames.add("Blocked");
		List<Media_entity> medias = new ArrayList<Media_entity>();
		medias = media_service.getListFilm();
		return new FilmTableModel(columnsNames, medias);
	}
	
	public void updateFilm(ActionEvent e) {
		List<Media_entity> medias = new ArrayList<Media_entity>();
		Integer id = null;
		String title = film_view.getTextFilmTitle().getText();
		Integer year = Integer.parseInt(film_view.getTextFilmYear().getText());
		String type = "Film";
		String saga = film_view.getF_listSaga().getSelectedItem();
		String[] genreList = film_view.getF_listGenre().getSelectedItems();
		medias =  media_service.getMedia(title, year, type);
		for (Media_entity media : medias) {
			id = media.getMedia_id() ;
		}	 
		
		if (id == null || (medias.size() <= 1 && id.equals(film_view.getFilmId())) ) {
			media_service.removeGenre(film_view.getFilmId());
			media_service.editFilm(film_view.getFilmId(), title, year, saga, genreList);	
			film_view.initTableContent();
			JOptionPane.showMessageDialog(this.film_view, "The media edited successfully");
		} else {
			JOptionPane.showMessageDialog(this.film_view, "This media is exist already in database!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		List<Media_entity> medias = new ArrayList<Media_entity>();
		String [ ] genreList = null;
		String [ ] sagaList = null;
		film_view.getF_listGenre().deselect(film_view.getF_listGenre().getSelectedIndex());
		film_view.getF_listSaga().deselect(film_view.getF_listSaga().getSelectedIndex());
		JTable target = (JTable) e.getSource();
		int row = target.getSelectedRow();
		film_view.getTextFilmTitle().setText(target.getValueAt(row, 0).toString());
		film_view.getTextFilmYear().setText(target.getValueAt(row, 1).toString());
		String genre = target.getValueAt(row, 2).toString();
		if (genre != null && !genre.isEmpty()) {
			genreList  = genre.split(",");
			for (String str : genreList) {
				film_view.getF_listGenre().select(listGenre.indexOf(str));
			}
		}
		String saga = target.getValueAt(row, 3).toString();
		if (saga != null && !saga.isEmpty()) {
			if (listSaga.contains(saga)) {
				film_view.getF_listSaga().select(listSaga.indexOf(saga));
			}
		}
		String title = film_view.getTextFilmTitle().getText();
		Integer year = Integer.parseInt(film_view.getTextFilmYear().getText());
		String type = "Film";
		medias =  media_service.getMedia(title, year, type);
		for (Media_entity media : medias) {
			film_view.setFilmId(media.getMedia_id()) ;
		}
	}
	
}
