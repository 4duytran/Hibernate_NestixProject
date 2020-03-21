package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import controller.Media_controller;
import entity.Media_entity;
import service.Media_service;
import utile.Checklimit;
import utile.TextPlaceHolder;
import view.tablemodel.MediaTableModel;

public class Media_view extends JPanel {
	

	private static final long serialVersionUID = -8527291664869225715L;
	private JPanel m_panel = new JPanel ();
	private JPanel m_content = new JPanel(new GridBagLayout());
	private JPanel m_tablecontent = new JPanel();
	private static TextPlaceHolder textMediaYear = new TextPlaceHolder(20);
	private JLabel labelMediaTitle = new JLabel("Enter Media Titre: ");
	private JLabel labelMediaYear = new JLabel("Media Annee: ");
	private TextPlaceHolder textMediaTitle = new TextPlaceHolder(20);
	private JLabel labelMediaType = new JLabel("Media Type: ");
	private JButton buttonInsert = new JButton("Ajouter");
	
	
	private JComboBox<Object> objectList = new JComboBox<Object>();

	private JTable m_table = new JTable();
	private JScrollPane scrollAll = new JScrollPane(m_table);

	private  Media_controller media_controller;
	
	
	
	public Media_view() {
	
		media_controller = new Media_controller(this);
		textMediaYear.setDocument(new Checklimit(4));
		textMediaYear.setPlaceholder("2000");
		textMediaTitle.setPlaceholder("Title of new media");

		
		objectList.setModel(new DefaultComboBoxModel<Object>(media_controller.mediaType()));
		objectList.insertItemAt("--None--", 0);
		objectList.setSelectedIndex(0);
		
		buttonInsert.addActionListener((e) -> media_controller.creatMedia(e));
			
		//Init table
		initTableContent() ;
		m_table.addMouseListener(new Media_controller(this));
		// ADD PANEL TO INTERFACE
		m_content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.PINK),
				" MEDIA Panel  ", TitledBorder.RIGHT, TitledBorder.TOP));
		textInsertModel();
		m_panel.add(m_content);
		m_panel.add(m_tablecontent);
		m_panel.setVisible(true);
		add(m_panel);
		
	}
	
	public static TextPlaceHolder getTextMediaYear() {
		return textMediaYear;
	}
	
	public  void  setTextMediaYear(TextPlaceHolder textMediaYear) {
		this.textMediaYear = textMediaYear;
	}

	public TextPlaceHolder getTextMediaTitle() {
		return textMediaTitle;
	}

	public void setTextMediaTitle(TextPlaceHolder textMediaTitle) {
		this.textMediaTitle = textMediaTitle;
	}

	public JComboBox getObjectList() {
		return objectList;
	}

	public void setObjectList(JComboBox objectList) {
		this.objectList = objectList;
	}
	
	public JTable getM_table() {
		return m_table;
	}
	
	public JScrollPane getScrollAll() {
		return scrollAll;
	}
	
	//Model insert
	private GridBagConstraints textInsertModel() {
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 0;
		constraints.gridy = 0;
		m_content.add(labelMediaTitle, constraints);

		constraints.gridx = 1;
		m_content.add(textMediaTitle, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		m_content.add(labelMediaYear, constraints);

		constraints.gridx = 1;
		m_content.add(textMediaYear, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		m_content.add(labelMediaType, constraints);

		constraints.gridx = 1;
		m_content.add(objectList, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		m_content.add(buttonInsert, constraints);
		return constraints;

	}
	
	public void initTableContent() {
		scrollAll.setPreferredSize(new Dimension(650, 550));
		m_table.setFillsViewportHeight(true);
		m_table.setPreferredScrollableViewportSize(scrollAll.getPreferredSize());
		m_table.setModel(media_controller.tableModel());
		m_tablecontent.add(scrollAll);
	}
	
	
}
