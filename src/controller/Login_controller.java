package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import config.Login;
import view.Login_view;
import view.Main_view;

public class Login_controller implements ActionListener {

	Login_view login_view ;
	
	public Login_controller(Login_view login_view) {
		this.login_view = login_view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(is_logged(login_view.getTextUsername().getText(), login_view.getTextPassword().getPassword()))
		{
			Main_view main = new Main_view();
			main.setVisible(true);
			login_view.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Wrong username or password", "Warning message",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private static boolean is_logged(String username, char[] cs) {
		boolean reponse = false;
		Login auth = new Login();
		if(username.equals(auth.getUsername()) && Arrays.equals(auth.getPassword(), cs))
		{
			reponse = true;
		}
		return reponse;
	}
}
