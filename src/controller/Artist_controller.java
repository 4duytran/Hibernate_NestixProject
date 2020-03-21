package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.text.JTextComponent;

import entity.Artist_entity;
import service.Artist_service;
import utile.Capitalize;
import view.Artist_view;
import view.tablemodel.ArtistTableModel;

public class Artist_controller extends MouseAdapter{

	private Artist_view artist_view ;
	private Artist_service artist_service;
	
	
	public Artist_controller(Artist_view artist_view) {
		artist_service = new Artist_service();
		this.artist_view = artist_view;
	}
	
	public ArtistTableModel tableModel() {
		List<String> columnsNames = new ArrayList<String>();
		columnsNames.add("First Name");
		columnsNames.add("Last Name");
		columnsNames.add("Surname");
		columnsNames.add("Date of birthday");
		
		List<Artist_entity> artists = new ArrayList<Artist_entity>();
		artists = artist_service.getListArtist();
		return new ArtistTableModel(columnsNames, artists);
	}
	
	public void editArtist(ActionEvent e) {
		Integer id = artist_view.getArtistId();
		String firstName = null;
		String lastName = null;
		String surName = null;
		String dob = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date dateDob = null;
		java.sql.Date sqlDob  = null;
		
		if (!artist_view.getTextArtistFirstName().getText().isEmpty() && !artist_view.getTextArtistLastName().getText().isEmpty() && !artist_view.getTextArtistSurName().getText().isEmpty() ) {
			firstName = Capitalize.upperCaseAllFirst(artist_view.getTextArtistFirstName().getText());
			lastName = Capitalize.upperCaseAllFirst(artist_view.getTextArtistLastName().getText());
			surName = Capitalize.upperCaseAllFirst(artist_view.getTextArtistSurName().getText());
			
			if (!artist_view.getTextArtistDob().getText().isEmpty()) {
				dob = artist_view.getTextArtistDob().getText();
				
				try {
					String regexDob = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
					Pattern patternDob= Pattern.compile(regexDob);
					Matcher matcherDob = patternDob.matcher(dob);
					if (matcherDob.matches()) {
						dateDob = formatter.parse(dob);
						sqlDob = new java.sql.Date(dateDob.getTime());
					} else {
						JOptionPane.showMessageDialog(this.artist_view, "Date of birth is wrong format (dd-mm-yyyy) !", "Error", JOptionPane.ERROR_MESSAGE);
					}
						
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			String regexName = "^[a-zA-Z ']+$";
			Pattern patternName = Pattern.compile(regexName);
			
			Matcher matcherFirstName = patternName.matcher(firstName);
			Matcher matcherLastName = patternName.matcher(lastName);
			Matcher matcherSurName = patternName.matcher(surName);
			
			if (matcherFirstName.matches() && matcherLastName.matches() && matcherSurName.matches()) {
				
				Artist_entity artist = artist_service.checkArtistExiste(id, surName);
				
				if (artist != null) {
					
					JOptionPane.showMessageDialog(this.artist_view, "The sur name is already existed  !", "Error", JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					artist_service.editArtist(id, firstName, lastName, surName, sqlDob);
					artist_view.initTableContent();
					JOptionPane.showMessageDialog(this.artist_view, "The user edited successfully");
					
				}
				
			} else {
				
				JOptionPane.showMessageDialog(this.artist_view, "The case first name or last name or sur name is wrong format, accept only letters !", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
		} else {
			
			JOptionPane.showMessageDialog(this.artist_view, "Those cases first name, last name and sur name are required !", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	public void removeArtist(ActionEvent e) {
		Integer id = artist_view.getArtistId();
		if (id != null) {
			Artist_entity artist = artist_service.getArtistById(id);
			int reponse = JOptionPane.showConfirmDialog(this.artist_view, "Do you want to delete the artist  "+artist.getSurName().toUpperCase()+" ?", "Delete media confirmation", JOptionPane.YES_NO_OPTION);
			if (reponse == 0) {
				artist_service.removeArtist(id);
				artist_view.initTableContent();
				artist_view.getTextArtistFirstName().setText("");
				artist_view.getTextArtistLastName().setText("");
				artist_view.getTextArtistSurName().setText("");
				artist_view.getTextArtistDob().setText("");
				JOptionPane.showMessageDialog(this.artist_view, "The artist deleted successfully");
			}
			
		} else {
			JOptionPane.showMessageDialog(this.artist_view, "There is an error occurred! can not found this artist", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void creatNewArtist(ActionEvent e) {
		Artist_entity artist = null;
		String firstName = null;
		String lastName = null;
		String surName = null;
		String dob = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date dateDob = null;
		java.sql.Date sqlDob  = null;
		
		if (!artist_view.getTextArtistFirstName().getText().isEmpty() && !artist_view.getTextArtistLastName().getText().isEmpty() && !artist_view.getTextArtistSurName().getText().isEmpty() && !artist_view.getTextArtistDob().getText().isEmpty() ) {
			firstName = Capitalize.upperCaseAllFirst(artist_view.getTextArtistFirstName().getText());
			lastName = Capitalize.upperCaseAllFirst(artist_view.getTextArtistLastName().getText());
			surName = Capitalize.upperCaseAllFirst(artist_view.getTextArtistSurName().getText());
			dob = artist_view.getTextArtistDob().getText();
			try {
				String regexDob = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
				Pattern patternDob= Pattern.compile(regexDob);
				Matcher matcherDob = patternDob.matcher(dob);
				if (matcherDob.matches()) {
					dateDob = formatter.parse(dob);
					sqlDob = new java.sql.Date(dateDob.getTime());
				} else {
					JOptionPane.showMessageDialog(this.artist_view, "Date of birth is wrong format (dd-mm-yyyy) !", "Error", JOptionPane.ERROR_MESSAGE);
				}
					
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String regexName = "^[a-zA-Z ']+$";
			Pattern patternName = Pattern.compile(regexName);
			
			Matcher matcherFirstName = patternName.matcher(firstName);
			Matcher matcherLastName = patternName.matcher(lastName);
			Matcher matcherSurName = patternName.matcher(surName);
			
			if (matcherFirstName.matches() && matcherLastName.matches() && matcherSurName.matches()) {
				
				artist = artist_service.getArtistBySurName(surName);
				
				if (artist == null) {
					
					artist = new Artist_entity();
					artist.setFirstName(firstName);
					artist.setLastName(lastName);
					artist.setSurName(surName);
					artist.setDob(sqlDob);
					artist_service.creatNewArtist(artist);
					artist_view.initTableContent();
					artist_view.getTextArtistFirstName().setText("");
					artist_view.getTextArtistLastName().setText("");
					artist_view.getTextArtistSurName().setText("");
					artist_view.getTextArtistDob().setText("");
					JOptionPane.showMessageDialog(this.artist_view, "The user created successfully");
					
				} else {
					
					JOptionPane.showMessageDialog(this.artist_view, "The sur name is already existed  !", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				
			} else {
				
				JOptionPane.showMessageDialog(this.artist_view, "The case first name or last name or sur name is wrong format, accept only letters !", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
		} else {
			
			JOptionPane.showMessageDialog(this.artist_view, "Those cases first name, last name and sur name are required !", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		JTable target = (JTable) e.getSource();
		int row = target.getSelectedRow();
		Artist_entity artist = artist_service.getArtistBySurName(target.getValueAt(row, 2).toString());
		artist_view.setArtistId(artist.getP_id());
		String date =  (null == artist.getDob()) ? "" : new SimpleDateFormat("dd-MM-yyyy").format(artist.getDob());
		Date date1 = null;
		artist_view.getTextArtistFirstName().setText(target.getValueAt(row, 0).toString());
		artist_view.getTextArtistLastName().setText(target.getValueAt(row, 1).toString());
		artist_view.getTextArtistSurName().setText(target.getValueAt(row, 2).toString());
		artist_view.getTextArtistDob().setText(date);
		
	}
	
	
}
