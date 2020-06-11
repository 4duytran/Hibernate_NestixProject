package view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import config.Login;
import controller.Main_controller;
import controller.Search_controller;
import utile.TextPlaceHolder;

public class Main_view extends JFrame implements ActionListener{

	private static final long serialVersionUID = 6956063381416880156L;

	private JPanel main = new JPanel();
	private CardLayout content = new CardLayout();

	private TextPlaceHolder textSearch = new TextPlaceHolder(10);
	
	private Media_view mediaPanel = new Media_view();
	private Film_view filmPanel = new Film_view();
	private Book_view bookPanel = new Book_view();
	private Music_view musicPanel = new Music_view();
	private User_view userPanel = new User_view();
	private Artist_view artistPanel = new Artist_view();
	
	private Main_controller main_controller ;
	
	
	public Main_view() {
		// init window pricipal
		super("Administrator System");
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		main_controller = new Main_controller(this);
		
		textSearch.addKeyListener(new Search_controller(this));
		// Add main window
		
		main.setLayout(content);
		// Here we will set name for each Panel , its using for get name from Search
		mediaPanel.setName("Media");
		filmPanel.setName("Film");
		bookPanel.setName("Book");
		musicPanel.setName("Music");
		userPanel.setName("User");
		artistPanel.setName("Artist");
		
		// Add all panel into the CardLayout
		
		main.add(mediaPanel, "Media Panel");
		main.add(filmPanel, "Film Panel");
		main.add(bookPanel, "Book Panel");
		main.add(musicPanel, "Music Panel");
		main.add(userPanel, "User Panel");
		main.add(artistPanel, "Artist Panel");
		
		content.show(main, "Media Panel");
		
		// init main app
		this.getContentPane().add(toolBar(), BorderLayout.NORTH); // add tool bar 
		this.getContentPane().add(main, BorderLayout.CENTER);
		
	}
	

	public Media_view getMediaPanel() {
		return mediaPanel;
	}

	public Film_view getFilmPanel() {
		return filmPanel;
	}
	
	public Music_view getMusicPanel() {
		return musicPanel;
	}
	
	public User_view getUserPanel() {
		return userPanel;
	}

	
	public Artist_view getArtistPanel() {
		return artistPanel;
	}

	public JPanel getMain() {
		return main;
	}


	public TextPlaceHolder getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(TextPlaceHolder textSearch) {
		this.textSearch = textSearch;
	}

	public JToolBar toolBar() {
		JToolBar toolBar = new JToolBar();
		JButton disConnectBtn = new JButton(new ImageIcon(getClass().getResource("/disconnect.png")));
		disConnectBtn.addActionListener(e -> {
			disconnect(e);
		});
		disConnectBtn.setToolTipText("DisConnect");
		toolBar.add(disConnectBtn);

		toolBar.addSeparator(new Dimension(20, 10));
		JButton showAllBtn = new JButton(new ImageIcon(getClass().getResource("/seo.png")));
		showAllBtn.setToolTipText("Show All");
		showAllBtn.addActionListener((e) -> main_controller.showMediaView(e));
		toolBar.add(showAllBtn);
		
		toolBar.addSeparator(new Dimension(20, 10));
		JButton filmBtn = new JButton(new ImageIcon(getClass().getResource("/film.png")));
		filmBtn.setToolTipText("List Film");
		filmBtn.addActionListener((e) -> main_controller.showFilmView(e));
		toolBar.add(filmBtn);
		
		toolBar.addSeparator(new Dimension(20, 10));
		JButton bookBtn = new JButton(new ImageIcon(getClass().getResource("/book.png")));
		bookBtn.setToolTipText("List Book");
		bookBtn.addActionListener((e) -> main_controller.showBookView(e));
		toolBar.add(bookBtn);
		
		toolBar.addSeparator(new Dimension(20, 10));
		JButton musicBtn = new JButton(new ImageIcon(getClass().getResource("/musical.png")));
		musicBtn.setToolTipText("List Music");
		musicBtn.addActionListener((e)->main_controller.showMusicView(e));
		toolBar.add(musicBtn);
		
		
		
		toolBar.addSeparator(new Dimension(20, 10));
		JButton userBtn = new JButton(new ImageIcon(getClass().getResource("/user.png")));
		userBtn.setToolTipText("Users");
		userBtn.addActionListener((e)->main_controller.showUserView(e));
		toolBar.add(userBtn);
		
		toolBar.addSeparator(new Dimension(20, 10));
		JButton artistBtn = new JButton(new ImageIcon(getClass().getResource("/artist.png")));
		artistBtn.setToolTipText("Users");
		artistBtn.addActionListener((e)->main_controller.showArtistView(e));
		toolBar.add(artistBtn);

		toolBar.addSeparator(new Dimension(50, 10));
		JButton buttonSearch = new JButton(new ImageIcon(getClass().getResource("/loupe.png")));
		toolBar.add(textSearch);
		toolBar.add(buttonSearch);
		

		
		return toolBar;

	}
	
	private void disconnect(ActionEvent e) {
		Login_view interfaceApp = new Login_view();
		interfaceApp.setVisible(true);
		this.dispose();	
	}
	
	
	public CardLayout getContent() {
		return content;
	}


	public void setContent(CardLayout content) {
		this.content = content;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	public Book_view getBookPanel() {
		return bookPanel;
	}

}
