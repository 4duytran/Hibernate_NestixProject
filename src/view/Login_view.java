package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import config.Login;
import controller.Login_controller;

public class Login_view extends JFrame{
	
	private static final long serialVersionUID = -6282904348896767670L;
	private JPanel loginPanel = new JPanel (new GridBagLayout());
	private JLabel labelUsername = new JLabel("Enter username: ");
	private JLabel labelPassword = new JLabel("Enter password: ");
	private JTextField textUsername = new JTextField(20);
	private JPasswordField textPassword = new JPasswordField(20);
	private JButton buttonLogin = new JButton("Login");

	public Login_view() {
		super("Administrator System");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// add components to the panel login
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 0;
		constraints.gridy = 0;
		loginPanel.add(labelUsername, constraints);

		constraints.gridx = 1;
		loginPanel.add(textUsername, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		loginPanel.add(labelPassword, constraints);

		constraints.gridx = 1;
		loginPanel.add(textPassword, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		loginPanel.add(buttonLogin, constraints);
		loginPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login Panel"));
		
		buttonLogin.addActionListener(new Login_controller(this));
		this.setContentPane(loginPanel);
	}
	
	
	public JTextField getTextUsername() {
		return textUsername;
	}

	public JPasswordField getTextPassword() {
		return textPassword;
	}
	
	
}
