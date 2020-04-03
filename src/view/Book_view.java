package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import controller.Book_controller;
import controller.Film_controller;
import utile.Checklimit;

public class Book_view extends JPanel {

	private static final long serialVersionUID = -1869027863102559133L;

	private JPanel b_panel = new JPanel ();
	private JPanel b_content = new JPanel(new GridBagLayout());
	private JPanel b_tablecontent = new JPanel();
	
	private JLabel labelBookTitle = new JLabel("Enter Media Titre: ");
	private JLabel labelBookYear = new JLabel("Media Annee: ");
	private JLabel labelBookIsbn = new JLabel("ISBN: ");
	private JLabel b_genre = new JLabel("Genre");
	private JLabel b_saga = new JLabel("Saga");
	private JLabel b_genre_add = new JLabel("Add new Genre");
	private JLabel b_saga_add = new JLabel("Add new Saga");
	private JLabel labelBookValid = new JLabel("Valid ");
	
	private static JTextField textBookYear = new JTextField(20);
	private JTextField textBookTitle = new JTextField(20);
	private JTextField textBookGenre = new JTextField(20);
	private JTextField textBookSaga = new JTextField(20);
	private static JTextField textBookIsbn = new JTextField(13);

	private JButton b_edit = new JButton("Edit");
	private JButton b_delete = new JButton("Delete");
	private JButton b_deselect = new JButton("reset list");
	
	private JButton b_addSaga = new JButton(new ImageIcon(getClass().getResource("/button_plus.png")));
	private JButton b_addGenre = new JButton(new ImageIcon(getClass().getResource("/button_plus.png")));
	
	private List b_listGenre = new List(10, true);
	private List b_listSaga = new List(10, false);
	
	private JTable b_table = new JTable();

	private JScrollPane b_scroll = new JScrollPane(b_table);

	private  Book_controller book_controller;
	
	private Integer bookId;
	
	private JCheckBox checkbox = new JCheckBox();
	

	public Book_view() {
	
		book_controller= new Book_controller(this);
		textBookYear.setDocument(new Checklimit(4));
		textBookIsbn.setDocument(new Checklimit(13));
		book_controller.genreList();
		book_controller.sagaList();
	
		b_edit.addActionListener((e)->book_controller.updateBook(e));
		b_addSaga.addActionListener((e)->book_controller.addNewSaga(e));	
		b_delete.addActionListener((e) -> book_controller.removeMedia(e));
		b_deselect.addActionListener((e)->book_controller.deselectList(e));
		
		//Init table
		initTableContent() ;
		b_table.addMouseListener(new Book_controller(this)); 
		// ADD PANEL TO INTERFACE
		b_content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.PINK),
				" BOOK Panel  ", TitledBorder.RIGHT, TitledBorder.TOP));
		textInsertModel();
		b_panel.add(b_content);
		b_panel.add(b_tablecontent);
		b_panel.setVisible(true);
		add(b_panel);
		
	}
	
	
	
	//Model insert
	private GridBagConstraints textInsertModel() {
		
			GridBagConstraints constraints = new GridBagConstraints();
	        constraints.anchor = GridBagConstraints.LINE_START;
	        constraints.insets = new Insets(10, 10, 10, 10);
	        constraints.gridx = 0;
	        constraints.gridy = 1;     
	        b_content.add(labelBookTitle, constraints);
	 
	        constraints.gridx = 1;
	        b_content.add(textBookTitle, constraints);
	        
	        constraints.gridx = 2;
	        b_content.add(checkbox, constraints);
	         
	        constraints.gridx = 0;
	        constraints.gridy = 2;     
	        b_content.add(labelBookYear, constraints);
	         
	        constraints.gridx = 1;
	        b_content.add(textBookYear, constraints);
	  
	        constraints.gridx = 0;
	        constraints.gridy = 3;     
	        b_content.add(labelBookIsbn, constraints);
	        
	        constraints.gridx = 1;
	        b_content.add(textBookIsbn, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 4;     
	        b_content.add(b_genre, constraints);
	        
	        constraints.gridx = 1;
	        b_content.add(b_listGenre, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 5; 
	        b_content.add(b_genre_add, constraints);
	        
	        constraints.gridx = 1;  
	        b_content.add(textBookGenre, constraints);
	        
	        constraints.gridx = 2;
	        b_content.add(b_addGenre, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 6;     
	        b_content.add(b_saga, constraints);
	        
	        constraints.gridx = 1;
	        b_content.add(b_listSaga, constraints);
	        
	        constraints.gridx = 2;
	        b_content.add(b_deselect, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 7;   
	        b_content.add(b_saga_add, constraints);
	        
	        constraints.gridx = 1; 
	        b_content.add(textBookSaga, constraints);
	        
	        constraints.gridx = 2;
	        b_content.add(b_addSaga, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 8;
	        
	        constraints.anchor = GridBagConstraints.CENTER;
	        b_content.add(b_edit, constraints);
	        
	        constraints.gridx = 2;
	        b_content.add(b_delete, constraints);
	        
			return constraints;

	}
	
	public void initTableContent() {
		b_scroll.setPreferredSize(new Dimension(650, 550));
		b_table.setFillsViewportHeight(true);
		b_table.setPreferredScrollableViewportSize(b_scroll.getPreferredSize());
		b_table.setModel(book_controller.tableModel());
		b_tablecontent.add(b_scroll);
	}

	public static JTextField getTextBookYear() {
		return textBookYear;
	}

	public static void setTextBookYear(JTextField textBookYear) {
		Book_view.textBookYear = textBookYear;
	}

	public JTextField getTextBookTitle() {
		return textBookTitle;
	}

	public void setTextBookTitle(JTextField textBookTitle) {
		this.textBookTitle = textBookTitle;
	}

	public static JTextField getTextBookIsbn() {
		return textBookIsbn;
	}

	public static void setTextBookIsbn(JTextField textBookIsbn) {
		Book_view.textBookIsbn = textBookIsbn;
	}

	public JTextField getTextBookGenre() {
		return textBookGenre;
	}

	public void setTextBookGenre(JTextField textBookGenre) {
		this.textBookGenre = textBookGenre;
	}

	public JTextField getTextBookSaga() {
		return textBookSaga;
	}

	public void setTextBookSaga(JTextField textBookSaga) {
		this.textBookSaga = textBookSaga;
	}

	public void setB_delete(JButton b_delete) {
		this.b_delete = b_delete;
	}

	public List getB_listGenre() {
		return b_listGenre;
	}

	public void setB_listGenre(List b_listGenre) {
		this.b_listGenre = b_listGenre;
	}

	public List getB_listSaga() {
		return b_listSaga;
	}

	public void setB_listSaga(List b_listSaga) {
		this.b_listSaga = b_listSaga;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	public JCheckBox getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(JCheckBox checkbox) {
		this.checkbox = checkbox;
	}
	
	
}
