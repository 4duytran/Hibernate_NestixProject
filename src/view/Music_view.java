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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.Film_controller;
import controller.Music_controller;
import utile.Checklimit;

public class Music_view extends JPanel{
	

	private static final long serialVersionUID = -811492821653962414L;
	
	private JPanel mu_panel = new JPanel();
	private JPanel mu_content = new JPanel(new GridBagLayout());
	private JPanel mu_tablecontent = new JPanel();
	
	private JLabel labelMusicTitle = new JLabel("Enter Media Titre: ");
	private JLabel labelMusicYear = new JLabel("Media Annee: ");
	private JLabel mu_genre = new JLabel("Genre");
	private JLabel mu_genre_add = new JLabel("Add new Genre");
	
	private static JTextField textMusicYear = new JTextField(20);
	private JTextField textMusicTitle = new JTextField(20);
	private JTextField textMusicGenre = new JTextField(20);
	
	private JButton mu_edit = new JButton("Edit");
	private JButton mu_delete = new JButton("Delete");
	
	private JButton mu_addGenre = new JButton(new ImageIcon(getClass().getResource("/button_plus.png")));
	
	private List mu_listGenre = new List(10, true);
	
	private JTable mu_table = new JTable();

	private JScrollPane mu_scroll = new JScrollPane(mu_table);
	
	private  Music_controller music_controller;
	
	private Integer musicId;
	
	
	public Music_view() {
		
		music_controller = new Music_controller(this);
		textMusicYear.setDocument(new Checklimit(4));
		music_controller.genreList();
		
		mu_content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.PINK),
				"MUSIC Panel ", TitledBorder.RIGHT, TitledBorder.TOP));
		initTableContent() ;
		textInsertModel();
		mu_table.addMouseListener(new Music_controller(this)); 
		mu_edit.addActionListener((e)->music_controller.updateMusic(e));
		
		mu_panel.add(mu_content);
		mu_panel.add(mu_tablecontent);
		mu_panel.setVisible(true);
		add(mu_panel);
	}
	
	public void initTableContent() {
		mu_scroll.setPreferredSize(new Dimension(650, 550));
		mu_table.setFillsViewportHeight(true);
		mu_table.setPreferredScrollableViewportSize(mu_scroll.getPreferredSize());
		mu_table.setModel(music_controller.tableModel());
		mu_tablecontent.add(mu_scroll);
	}
	
	private GridBagConstraints textInsertModel() {
		
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 1;     
        
		mu_content.add(labelMusicTitle, constraints);
 
        constraints.gridx = 1;
        mu_content.add(textMusicTitle, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 2;     
        mu_content.add(labelMusicYear, constraints);
         
        constraints.gridx = 1;
        mu_content.add(textMusicYear, constraints);
  
        
        constraints.gridx = 0;
        constraints.gridy = 3;     
        mu_content.add(mu_genre, constraints);
        
        constraints.gridx = 1;
        mu_content.add(mu_listGenre, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 4; 
        mu_content.add(mu_genre_add, constraints);
        
        constraints.gridx = 1;  
        mu_content.add(textMusicGenre, constraints);
        
        constraints.gridx = 2;
        mu_content.add(mu_addGenre, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 5;
        
        constraints.anchor = GridBagConstraints.CENTER;
        mu_content.add(mu_edit, constraints);
        
        constraints.gridx = 2;
        mu_content.add(mu_delete, constraints);
        
		return constraints;

	}
	
	public List getMu_listGenre() {
		return mu_listGenre;
	}

	public void setMu_listGenre(List mu_listGenre) {
		this.mu_listGenre = mu_listGenre;
	}
	
	public Integer getMusicId() {
		return musicId;
	}
	
	public void setFilmId(Integer musicId) {
		this.musicId = musicId;
	}
	
	public static JTextField getTextMusicYear() {
		return textMusicYear;
	}

	public JTextField getTextMusicTitle() {
		return textMusicTitle;
	}

	public JTextField getTextFilmGenre() {
		return textMusicGenre;
	}

	public void setTextMusicGenre(JTextField textFilmGenre) {
		this.textMusicGenre = textFilmGenre;
	}

	
	
}
