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
import service.Saga_service;
import view.Book_view;
import view.tablemodel.BookTableModel;

public class Book_controller extends MouseAdapter  {

	private Book_view book_view;
	private Genre_service genre_service;
	private Media_service media_service;
	private Saga_service saga_service;
	private List<String> listGenre = new ArrayList<String>();
	private List<String> listSaga = new ArrayList<String>();
	
	
	public Book_controller(Book_view book_view) {
		genre_service = new Genre_service();
		media_service = new Media_service();
		saga_service = new Saga_service();
		listGenre = genre_service.getGenreListName();
		listSaga = saga_service.getSagaListName();
		this.book_view = book_view;
	}
	
	public void genreList() {
		genre_service.getGenreList(book_view.getB_listGenre());
	}
	
	public void sagaList() {
		saga_service.getSagaList(book_view.getB_listSaga());
	}
	
	public BookTableModel tableModel() {
		List<String> columnsNames = new ArrayList<String>();
		columnsNames.add("Media Name");
		columnsNames.add("Year");
		columnsNames.add("ISBN");
		columnsNames.add("Genre");
		columnsNames.add("Media Saga");
		columnsNames.add("Media type");
		columnsNames.add("Valid");
		List<Media_entity> medias = new ArrayList<Media_entity>();
		medias = media_service.getListBook();
		return new BookTableModel(columnsNames, medias);
	}
	
	public void updateBook(ActionEvent e) {
		List<Media_entity> medias = new ArrayList<Media_entity>();
		Integer id = null;
		Long isbn = null;
		Integer year = null;
		String title = null;
		Boolean valid = null;
		
		if (!book_view.getTextBookIsbn().getText().equals("")) {
			isbn = Long.parseLong(book_view.getTextBookIsbn().getText());
		}
		String type = "Livre";
		String saga = book_view.getB_listSaga().getSelectedItem();
		String[] genreList = book_view.getB_listGenre().getSelectedItems();
		if (book_view.getTextBookTitle().getText().isEmpty() || book_view.getTextBookYear().getText().isEmpty()) {
			JOptionPane.showMessageDialog(this.book_view, "The title and year case are required !", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			title = book_view.getTextBookTitle().getText();
			year = Integer.parseInt(book_view.getTextBookYear().getText());
			valid = book_view.getCheckbox().isSelected();
			medias =  media_service.getMedia(title, year, type);
			for (Media_entity media : medias) {
				id = media.getMedia_id() ;
			}	 
			
			if (id == null || (medias.size() <= 1 && id.equals(book_view.getBookId())) ) {
				media_service.removeGenre(book_view.getBookId());
				media_service.editBook(book_view.getBookId(), title, year, isbn, saga, genreList, valid);	
				book_view.initTableContent();
				JOptionPane.showMessageDialog(this.book_view, "The media edited successfully");
			} else {
				JOptionPane.showMessageDialog(this.book_view, "This media is exist already in database!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void removeMedia(ActionEvent e) {
		Media_entity media = new Media_entity();
		Integer id = book_view.getBookId();
		if ( id != null ) {
			media = media_service.getMediaById(id);
			String title = media.getMedia_title();
			int reponse = JOptionPane.showConfirmDialog(this.book_view, "Do you want to delete the media "+title.toUpperCase()+" ?", "Delete media confirmation", JOptionPane.YES_NO_OPTION);
			if (reponse == 0) {
				media_service.removeMedia(book_view.getBookId());
				book_view.initTableContent();
				book_view.getTextBookTitle().setText(null);
				Book_view.getTextBookYear().setText(null);
				Book_view.getTextBookIsbn().setText(null);
				book_view.getB_listGenre().deselect(book_view.getB_listGenre().getSelectedIndex());
				book_view.getB_listSaga().deselect(book_view.getB_listSaga().getSelectedIndex());
				book_view.setBookId(null);
				JOptionPane.showMessageDialog(this.book_view, "The media deleted successfully");
			}
			
		} else {
			JOptionPane.showMessageDialog(this.book_view, "There is an error occurred! Can not found the media or the media is empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addNewSaga(ActionEvent e) {
			
			boolean find = false;
			String sagaTitle = book_view.getTextBookSaga().getText();
			
			if (sagaTitle.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this.book_view,"The case is empty \n" + "Please complete  before retry!");
			} else {
				List<String> sagaList = saga_service.getSagaListName();
				for (String saga : sagaList) {
					if (sagaTitle.equalsIgnoreCase(saga)) {
						find = true;
						break;
					} else {
						find = false;
					}	
				}
				if (find) {
					JOptionPane.showMessageDialog(this.book_view,"This saga name " + "\"" +sagaTitle.toUpperCase() + "\"" + " is already existed in database", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					saga_service.addSaga(sagaTitle);	
					book_view.getB_listSaga().removeAll();
					sagaList();
					book_view.getTextBookSaga().setText("");
					JOptionPane.showMessageDialog(this.book_view, "The new saga added successfully");
				}
				
			}
		}
	
	public void addNewGenre(ActionEvent e) {
		
		boolean find = false;
		String genreTitle = book_view.getTextBookGenre().getText();
		
		if (genreTitle.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this.book_view,"The case is empty \n" + "Please complete  before retry!");
		} else {
			List<String> genreList = genre_service.getGenreListName();
			for (String genre : genreList) {
				if (genreTitle.equalsIgnoreCase(genre)) {
					find = true;
					break;
				} else {
					find = false;
				}	
			}
			if (find) {
				JOptionPane.showMessageDialog(this.book_view,"This genre name " + "\"" +genreTitle.toUpperCase() + "\"" + " is already existed in database", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				genre_service.addNewGenre(genreTitle);
				book_view.getB_listGenre().removeAll();
				genreList();
				book_view.getTextBookGenre().setText("");
				JOptionPane.showMessageDialog(this.book_view, "The new genre added successfully");
			}
			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		List<Media_entity> medias = new ArrayList<Media_entity>();
		String [ ] genreList = null;
		String [ ] sagaList = null;
		book_view.getB_listGenre().deselect(book_view.getB_listGenre().getSelectedIndex());
		book_view.getB_listSaga().deselect(book_view.getB_listSaga().getSelectedIndex());
		JTable target = (JTable) e.getSource();
		int row = target.getSelectedRow();
		book_view.getTextBookTitle().setText(target.getValueAt(row, 0).toString());
		book_view.getTextBookYear().setText(target.getValueAt(row, 1).toString());
		book_view.getTextBookIsbn().setText(target.getValueAt(row, 2).toString());
		book_view.getCheckbox().setSelected((boolean) target.getValueAt(row, 6));
		String genre = target.getValueAt(row, 3).toString();
		if (genre != null && !genre.isEmpty() && !genre.equals("Null")) {
			genreList  = genre.split(",");
			for (String str : genreList) {
				book_view.getB_listGenre().select(listGenre.indexOf(str));
			}
		}
		String saga = target.getValueAt(row, 4).toString();
		if (saga != null && !saga.isEmpty()) {
			if (listSaga.contains(saga)) {
				book_view.getB_listSaga().select(listSaga.indexOf(saga));
			}
		}
		String title = book_view.getTextBookTitle().getText();
		Integer year = Integer.parseInt(book_view.getTextBookYear().getText());
		String type = "Livre";
		medias =  media_service.getMedia(title, year, type);
		for (Media_entity media : medias) {
			book_view.setBookId(media.getMedia_id()) ;
		}
	}
	
}
