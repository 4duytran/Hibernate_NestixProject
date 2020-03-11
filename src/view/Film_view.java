package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.Film_controller;
import utile.Checklimit;

public class Film_view extends JPanel {

	private static final long serialVersionUID = -1869027863102559133L;

	private JPanel f_panel = new JPanel ();
	private JPanel f_content = new JPanel(new GridBagLayout());
	private JPanel f_tablecontent = new JPanel();
	
	private JLabel labelFilmTitle = new JLabel("Enter Media Titre: ");
	private JLabel labelFilmYear = new JLabel("Media Annee: ");
	private JLabel f_genre = new JLabel("Genre");
	private JLabel f_saga = new JLabel("Saga");
	private JLabel f_genre_add = new JLabel("Add new Genre");
	private JLabel f_saga_add = new JLabel("Add new Saga");
	
	private static JTextField textFilmYear = new JTextField(20);
	private JTextField textFilmTitle = new JTextField(20);
	private JTextField textFilmGenre = new JTextField(20);
	private JTextField textFilmSaga = new JTextField(20);
	
	private JButton f_edit = new JButton("Edit");
	private JButton f_delete = new JButton("Delete");
	
	private JButton f_addSaga = new JButton(new ImageIcon(getClass().getResource("/button_plus.png")));
	private JButton f_addGenre = new JButton(new ImageIcon(getClass().getResource("/button_plus.png")));
	
	private List f_listGenre = new List(10, true);
	private List f_listSaga = new List(10, false);

	private JTable f_table = new JTable();

	private JScrollPane f_scroll = new JScrollPane(f_table);

	private  Film_controller film_controller;
	
	private Integer filmId;
	

	public Film_view() {
	
		film_controller = new Film_controller(this);
		textFilmYear.setDocument(new Checklimit(4));
		film_controller.genreList();
		film_controller.sagaList();
	
		f_addSaga.addActionListener((e)->film_controller.addNewSaga(e));
		f_edit.addActionListener((e)->film_controller.updateFilm(e));
			
		//Init table
		initTableContent() ;
		f_table.addMouseListener(new Film_controller(this)); 
		// ADD PANEL TO INTERFACE
		f_content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.PINK),
				" FILM Panel  ", TitledBorder.RIGHT, TitledBorder.TOP));
		textInsertModel();
		f_panel.add(f_content);
		f_panel.add(f_tablecontent);
		f_panel.setVisible(true);
		add(f_panel);
		
	}
	
	public Integer getFilmId() {
		return filmId;
	}
	
	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}
	public JTable getF_table() {
		return f_table;
	}
	
	public List getF_listGenre() {
		return f_listGenre;
	}
	
	public void setF_listGenre(List f_listGenre) {
		this.f_listGenre = f_listGenre;
	}
	
	public List getF_listSaga() {
		return f_listSaga;
	}

	public void setF_listSaga(List f_listSaga) {
		this.f_listSaga = f_listSaga;
	}
	
	public static JTextField getTextFilmYear() {
		return textFilmYear;
	}

	public JTextField getTextFilmTitle() {
		return textFilmTitle;
	}

	public JTextField getTextFilmGenre() {
		return textFilmGenre;
	}

	public void setTextFilmGenre(JTextField textFilmGenre) {
		this.textFilmGenre = textFilmGenre;
	}

	public JTextField getTextFilmSaga() {
		return textFilmSaga;
	}

	public void setTextFilmSaga(JTextField textFilmSaga) {
		this.textFilmSaga = textFilmSaga;
	}
	
	//Model insert
	private GridBagConstraints textInsertModel() {
		
			GridBagConstraints constraints = new GridBagConstraints();
	        constraints.anchor = GridBagConstraints.LINE_START;
	        constraints.insets = new Insets(10, 10, 10, 10);
	        constraints.gridx = 0;
	        constraints.gridy = 1;     
	        f_content.add(labelFilmTitle, constraints);
	 
	        constraints.gridx = 1;
	        f_content.add(textFilmTitle, constraints);
	         
	        constraints.gridx = 0;
	        constraints.gridy = 2;     
	        f_content.add(labelFilmYear, constraints);
	         
	        constraints.gridx = 1;
	        f_content.add(textFilmYear, constraints);
	  
	        
	        constraints.gridx = 0;
	        constraints.gridy = 4;     
	        f_content.add(f_genre, constraints);
	        
	        constraints.gridx = 1;
	        f_content.add(f_listGenre, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 5; 
	        f_content.add(f_genre_add, constraints);
	        
	        constraints.gridx = 1;  
	        f_content.add(textFilmGenre, constraints);
	        
	        constraints.gridx = 2;
	        f_content.add(f_addGenre, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 6;     
	        f_content.add(f_saga, constraints);
	        
	        constraints.gridx = 1;
	        f_content.add(f_listSaga, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 7;   
	        f_content.add(f_saga_add, constraints);
	        
	        constraints.gridx = 1; 
	        f_content.add(textFilmSaga, constraints);
	        
	        constraints.gridx = 2;
	        f_content.add(f_addSaga, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 8;
	        
	        constraints.anchor = GridBagConstraints.CENTER;
	        f_content.add(f_edit, constraints);
	        
	        constraints.gridx = 2;
	        f_content.add(f_delete, constraints);
	        
			return constraints;

	}
	
	public void initTableContent() {
		f_scroll.setPreferredSize(new Dimension(650, 550));
		f_table.setFillsViewportHeight(true);
		f_table.setPreferredScrollableViewportSize(f_scroll.getPreferredSize());
		f_table.setModel(film_controller.tableModel());
		f_tablecontent.add(f_scroll);
	}
	
	
}
