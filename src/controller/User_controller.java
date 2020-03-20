package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import entity.Level_entity;
import entity.User_entity;
import service.Level_service;
import service.User_service;
import utile.BCrypt;
import utile.Capitalize;
import view.User_view;
import view.tablemodel.UserTableModel;

public class User_controller extends MouseAdapter{

	private User_view user_view ;
	private User_service user_service;
	private Level_service level_serive;
	
	public User_controller(User_view user_view) {
		user_service = new User_service();
		level_serive = new Level_service();
		this.user_view = user_view;
	}
	
	public Object[] userLevelList() {
		Object[] o = level_serive.getLevelList();
		return o;
	}
	
	public UserTableModel tableModel() {
		List<String> columnsNames = new ArrayList<String>();
		columnsNames.add("First Name");
		columnsNames.add("Last Name");
		columnsNames.add("Email");
		columnsNames.add("Rank");
		columnsNames.add("Date registered");
		columnsNames.add("Banned");
		List<User_entity> users = new ArrayList<User_entity>();
		users = user_service.getListUser();
		return new UserTableModel(columnsNames, users);
	}
	
	public void updateUser(ActionEvent e) {
		User_entity user = null;
		String firstName = null;
		String lastName = null;
		String email = null;
		String  password = null;
		Boolean banned = null;
		String level = null;
		
		if (!user_view.getTextUserFirstName().getText().isEmpty() && !user_view.getTextUserLastName().getText().isEmpty() && !user_view.getTextUserEmail().getText().isEmpty()) {
			firstName = Capitalize.upperCaseAllFirst(user_view.getTextUserFirstName().getText());
			lastName = Capitalize.upperCaseAllFirst(user_view.getTextUserLastName().getText());
			email = user_view.getTextUserEmail().getText();
			level = user_view.getLeveltList().getSelectedItem().toString();
			banned = user_view.getCheckbox().isSelected();
			String regexEmail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
			Pattern patternEmail = Pattern.compile(regexEmail);
			Matcher matcherEmail = patternEmail.matcher(email);
			
			String regexName = "^[a-zA-Z ']+$";
			Pattern patternName = Pattern.compile(regexName);
			Matcher matcherFirstName = patternName.matcher(firstName);
			Matcher matcherLastName = patternName.matcher(lastName);
			if (!matcherEmail.matches()) {
				JOptionPane.showMessageDialog(this.user_view, "The email is invalid format !", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (!matcherFirstName.matches() || !matcherLastName.matches()) {
				JOptionPane.showMessageDialog(this.user_view, "The First name or Last name is invalid format !", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				user = user_service.checkUserExiste(user_view.getUserId(), email);
				if (user != null) {
					JOptionPane.showMessageDialog(this.user_view, "The email is already used by other user !", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					user = user_service.getUserById(user_view.getUserId());
					if (user_view.getTextUserPassword().getPassword().length != 0) {
						password = new String(user_view.getTextUserPassword().getPassword());
						password = BCrypt.hashpw(password, BCrypt.gensalt(12));
						password = password.replaceFirst("2a", "2y");
					} else {
						password = user.getUsePassword();
					}
					user_service.updateUser(user_view.getUserId(), firstName, lastName, email, password, level, banned);	
					user_view.initTableContent();
					user_view.getTextUserFirstName().setText("");
					user_view.getTextUserLastName().setText("");
					user_view.getTextUserEmail().setText("");
					user_view.getTextUserPassword().setText("");
					JOptionPane.showMessageDialog(this.user_view, "The user edited successfully");
				}	
			}
		}
		
	}
	
	public void creatNewUser(ActionEvent e) {
		User_entity user = null;
		String firstName = null;
		String lastName = null;
		String email = null;
		String  password = null;
		String level = null;
		if (!user_view.getTextUserFirstName().getText().isEmpty() && !user_view.getTextUserLastName().getText().isEmpty() && !user_view.getTextUserEmail().getText().isEmpty() && user_view.getTextUserPassword().getPassword().length != 0) {
			firstName = Capitalize.upperCaseAllFirst(user_view.getTextUserFirstName().getText());
			lastName = Capitalize.upperCaseAllFirst(user_view.getTextUserLastName().getText());
			email = user_view.getTextUserEmail().getText();
			level = user_view.getLeveltList().getSelectedItem().toString();
			password = new String(user_view.getTextUserPassword().getPassword());
			password = BCrypt.hashpw(password, BCrypt.gensalt(12));
			password = password.replaceFirst("2a", "2y");
			
			String regexEmail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
			Pattern patternEmail = Pattern.compile(regexEmail);
			Matcher matcherEmail = patternEmail.matcher(email);
			
			String regexName = "^[a-zA-Z ']+$";
			Pattern patternName = Pattern.compile(regexName);
			Matcher matcherFirstName = patternName.matcher(firstName);
			Matcher matcherLastName = patternName.matcher(lastName);
			
			if (!matcherEmail.matches()) {
				JOptionPane.showMessageDialog(this.user_view, "The email is invalid format !", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (!matcherFirstName.matches() || !matcherLastName.matches()) {
				JOptionPane.showMessageDialog(this.user_view, "The First name or Last name is invalid format !", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				 user= user_service.checkEmailExiste(email);
				if (user == null) {
					user = new User_entity();
					Level_entity levelUser = level_serive.getLevelName(level);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setUserEmail(email);
					user.setUsePassword(password);
					user.setLevel(levelUser);
					user_service.creatUser(user);
					user_view.initTableContent();
					user_view.getTextUserFirstName().setText("");
					user_view.getTextUserLastName().setText("");
					user_view.getTextUserEmail().setText("");
					user_view.getTextUserPassword().setText("");
					JOptionPane.showMessageDialog(this.user_view, "The new user added successfully");
				} else {
					JOptionPane.showMessageDialog(this.user_view, "The email is already used by other user !", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}	
		} else {
			JOptionPane.showMessageDialog(this.user_view, "All cases are required for create new user !", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void removeUser(ActionEvent e) {
		Integer id = user_view.getUserId();
		User_entity user = user_service.getUserById(id);
		int reponse = JOptionPane.showConfirmDialog(this.user_view, "Do you want to delete the user:  " + user.getFirstName().toUpperCase() + " " + user.getLastName().toUpperCase() + " ?", "Delete user confirmation", JOptionPane.YES_NO_OPTION);
		if (reponse == 0) {
			user_service.removeUser(id);
			user_view.getTextUserFirstName().setText("");
			user_view.getTextUserLastName().setText("");
			user_view.getTextUserEmail().setText("");
			user_view.initTableContent();
			JOptionPane.showMessageDialog(this.user_view, "The user deleted successfully");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		JTable target = (JTable) e.getSource();
		int row = target.getSelectedRow();
		user_view.getTextUserFirstName().setText(target.getValueAt(row, 0).toString());
		user_view.getTextUserLastName().setText(target.getValueAt(row, 1).toString());
		user_view.getTextUserEmail().setText(target.getValueAt(row, 2).toString());
		user_view.getLeveltList().setSelectedItem(target.getValueAt(row, 3).toString());
		user_view.getCheckbox().setSelected((boolean) target.getValueAt(row, 5));
		User_entity user = user_service.getUserByEmail(target.getValueAt(row, 2).toString());
		user_view.setUserId(user.getP_id());
	}
}
