package ui;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import view.Login_view;

public class MainApplication {


	public static void main(String[] args) throws Exception {

		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		Login_view interfaceApp = new Login_view();
		interfaceApp.setVisible(true);
		
	}
}
