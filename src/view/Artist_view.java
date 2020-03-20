package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.Artist_controller;
import controller.Book_controller;

public class Artist_view extends JPanel {

	private static final long serialVersionUID = 3186378284049566873L;

	private JPanel a_panel = new JPanel(new BorderLayout());
	private JPanel a_content = new JPanel(new GridBagLayout());
	private JPanel a_tablecontent = new JPanel();
	
	private JLabel labelArtistFirstName = new JLabel("First Name: ");
	private JLabel labelArtistLastName = new JLabel("Last Name: ");
	private JLabel labelArtistSurName = new JLabel("Sur Name:");
	private JLabel labelArtistDob = new JLabel("BirthDay(dd-MM-yyyy):");

	
	private  JTextField textArtistFirstName = new JTextField(20);
	private JTextField textArtistLastName = new JTextField(20);
	private JTextField textArtistSurName = new JTextField(20);
	private JTextField textArtistDob = new JTextField(9);
	
	
	private JButton a_add = new JButton("Add new artist");
	private JButton a_edit = new JButton("Edit");
	private JButton a_delete = new JButton("Delete");
	

	
	private JTable a_table = new JTable();
	private JScrollPane a_scroll = new JScrollPane(a_table);
	
	private Integer artistId;

	private Artist_controller artist_controller;
	
	public Artist_view() {
		artist_controller = new Artist_controller(this);
		textInsertModel();
		initTableContent();
		
		a_table.addMouseListener(new Artist_controller(this)); 
		
		a_edit.addActionListener((e)->artist_controller.editArtist(e));
		a_delete.addActionListener((e)->artist_controller.removeArtist(e));
		
		a_content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.PINK),"ARTIST Panel ", TitledBorder.RIGHT, TitledBorder.TOP));
		a_panel.add(a_content, BorderLayout.CENTER);
		a_panel.add(a_tablecontent, BorderLayout.EAST);
		
		add(a_panel);
	}
	
	public void initTableContent() {
		a_scroll.setPreferredSize(new Dimension(650, 550));
		a_table.setFillsViewportHeight(true);
		a_table.setPreferredScrollableViewportSize(a_scroll.getPreferredSize());
		a_table.setModel(artist_controller.tableModel());
		a_tablecontent.add(a_scroll);
	}
	
	private GridBagConstraints textInsertModel() {
		
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 1;     
        
		a_content.add(labelArtistFirstName, constraints);
 
        constraints.gridx = 1;
        a_content.add(textArtistFirstName, constraints);
          
        constraints.gridx = 0;
        constraints.gridy = 2;     
        a_content.add(labelArtistLastName, constraints);
         
        constraints.gridx = 1;
        a_content.add(textArtistLastName, constraints);
  
        constraints.gridx = 0;
        constraints.gridy = 3;     
        a_content.add(labelArtistSurName, constraints);
        
        constraints.gridx = 1;
        a_content.add(textArtistSurName, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 4;     
        a_content.add(labelArtistDob, constraints);
        
        constraints.gridx = 1;
        a_content.add(textArtistDob, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.CENTER;
        a_content.add(a_edit, constraints);
        
        constraints.gridx = 1;
        a_content.add(a_delete, constraints);
        
        constraints.gridx = 2;
        a_content.add(a_add, constraints);
        
		return constraints;

	}

	public JTextField getTextArtistFirstName() {
		return textArtistFirstName;
	}

	public void setTextArtistFirstName(JTextField textArtistFirstName) {
		this.textArtistFirstName = textArtistFirstName;
	}

	public JTextField getTextArtistLastName() {
		return textArtistLastName;
	}

	public void setTextArtistLastName(JTextField textArtistLastName) {
		this.textArtistLastName = textArtistLastName;
	}

	public JTextField getTextArtistSurName() {
		return textArtistSurName;
	}

	public void setTextArtistSurName(JTextField textArtistSurName) {
		this.textArtistSurName = textArtistSurName;
	}

	public Integer getArtistId() {
		return artistId;
	}

	public void setArtistId(Integer artistId) {
		this.artistId = artistId;
	}

	public JTextField getTextArtistDob() {
		return textArtistDob;
	}

	public void setTextArtistDob(JTextField textArtistDob) {
		this.textArtistDob = textArtistDob;
	}
	
	
	
}
