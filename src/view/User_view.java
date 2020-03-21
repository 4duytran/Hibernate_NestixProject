package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import controller.Book_controller;
import controller.User_controller;

public class User_view extends JPanel {

	private static final long serialVersionUID = -4239316744366660136L;

	private JPanel u_panel = new JPanel(new BorderLayout());
	private JPanel u_content = new JPanel(new GridBagLayout());
	private JPanel u_tablecontent = new JPanel();
	
	private JLabel labelUserFirstName = new JLabel("First Name: ");
	private JLabel labelUserLastName = new JLabel("Last Name: ");
	private JLabel labelUserEmail = new JLabel("Email:");
	private JLabel labelUserPassword = new JLabel("Password:");
	private JLabel labelUserLevel = new JLabel("Level:");
	
	private  JTextField textUserFirstName = new JTextField(20);
	private JTextField textUserLastName = new JTextField(20);
	private JTextField textUserEmail = new JTextField(20);
	private  JPasswordField  textUserPassword = new JPasswordField(20);
	
	private JButton u_add = new JButton("Add new user");
	private JButton u_edit = new JButton("Edit");
	private JButton u_delete = new JButton("Delete");
	
	private JComboBox<Object> leveltList = new JComboBox<Object>();
	
	private JTable u_table = new JTable();
	private JScrollPane u_scroll = new JScrollPane(u_table);
	
	private Integer userId;

	private JCheckBox checkbox = new JCheckBox();
	
	private User_controller user_controller;
	
	public User_view() {
		user_controller = new User_controller(this);
		
		leveltList.setModel(new DefaultComboBoxModel<Object>(user_controller.userLevelList()));
		textInsertModel();
		initTableContent();
		u_table.addMouseListener(new User_controller(this)); 
		u_edit.addActionListener((e)->user_controller.updateUser(e));
		u_add.addActionListener((e)->user_controller.creatNewUser(e));
		u_delete.addActionListener((e)->user_controller.removeUser(e));
		u_content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.PINK),"USER Panel ", TitledBorder.RIGHT, TitledBorder.TOP));
		u_panel.add(u_content, BorderLayout.CENTER);
		u_panel.add(u_tablecontent, BorderLayout.EAST);
		
		add(u_panel);
	}
	
	public void initTableContent() {
		u_scroll.setPreferredSize(new Dimension(650, 550));
		u_table.setFillsViewportHeight(true);
		u_table.setPreferredScrollableViewportSize(u_scroll.getPreferredSize());
		u_table.setModel(user_controller.tableModel());
		u_tablecontent.add(u_scroll);
	}
	
	private GridBagConstraints textInsertModel() {
		
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 1;     
        
		u_content.add(labelUserFirstName, constraints);
 
        constraints.gridx = 1;
        u_content.add(textUserFirstName, constraints);
        
        constraints.gridx = 2;
        u_content.add(checkbox, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 2;     
        u_content.add(labelUserLastName, constraints);
         
        constraints.gridx = 1;
        u_content.add(textUserLastName, constraints);
  
        constraints.gridx = 0;
        constraints.gridy = 3;     
        u_content.add(labelUserEmail, constraints);
        
        constraints.gridx = 1;
        u_content.add(textUserEmail, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 4; 
        u_content.add(labelUserPassword, constraints);
        
        constraints.gridx = 1;  
        u_content.add(textUserPassword, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 5; 
        u_content.add(labelUserLevel, constraints);
         
        constraints.gridx = 1;
        u_content.add(leveltList, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        u_content.add(u_edit, constraints);
        
        constraints.gridx = 1;
        u_content.add(u_delete, constraints);
        
        constraints.gridx = 2;
        u_content.add(u_add, constraints);
        
        
		return constraints;

	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public JTextField getTextUserFirstName() {
		return textUserFirstName;
	}


	public JTextField getTextUserLastName() {
		return textUserLastName;
	}

	public JTextField getTextUserEmail() {
		return textUserEmail;
	}

	public JPasswordField getTextUserPassword() {
		return textUserPassword;
	}


	public JComboBox<Object> getLeveltList() {
		return leveltList;
	}

	public JCheckBox getCheckbox() {
		return checkbox;
	}
	
}
