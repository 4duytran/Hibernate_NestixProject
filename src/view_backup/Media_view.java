package view_backup;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import config.DBquerry;
import utile.Checklimit;
import utile.TextPlaceHolder;

public class Media_view extends JPanel {
		
		private static final long serialVersionUID = 7706042524359417112L;
		
		private JPanel m_panelData = new JPanel (new GridBagLayout());
		
		
		private JTextField m_textTitre = new JTextField(25);
		private JTextField m_textDate = new JTextField(25);
		private TextPlaceHolder m_textGenre = new TextPlaceHolder(15);
		private TextPlaceHolder m_textSaga = new TextPlaceHolder(15);
		private String [] m_genrelist;
		private String m_sagalist;
		private List m_listGenre;
		private List m_listSaga;
		private DBquerry dbquerry ;
		private Integer m_id;
			
		private JButton m_modifier = new JButton();
		private JButton m_Supprimer = new JButton();
		private JButton m_addSaga = new JButton(new ImageIcon(getClass().getResource("/button_plus.png")));
		private JButton m_addGenre = new JButton(new ImageIcon(getClass().getResource("/button_plus.png")));
		private JButton m_ajouter = new JButton();
		
		private JLabel m_titre = new JLabel();
		private JLabel m_date = new JLabel();
		private JLabel m_genre = new JLabel();
		private JLabel m_saga = new JLabel();
		
		
		
	public Media_view() throws Exception {
		// CONSTRUCTOR
		dbquerry = new DBquerry();
		dbquerry.getConnection();
		
		m_ajouter.setText("Ajouter");
		m_Supprimer.setText("Supprimer");
		m_modifier.setText("Modifier");
		//LABEL 
	
		m_titre.setText("Media Titre");
		m_date.setText("Année de sortie");
		m_genre.setText("Genre");
		m_saga.setText("Saga");
		
		m_addSaga.setToolTipText("Add new SAGA");
		m_addGenre.setToolTipText("ADD new GENRE");
		Border emptyBorder = BorderFactory.createEmptyBorder();
		m_addGenre.setBorder(emptyBorder);
		m_addSaga.setBorder(emptyBorder);
		
		m_textDate.setDocument(new Checklimit(4));
		m_textGenre.setPlaceholder("Enter new media genre");
		m_textSaga.setPlaceholder("Enter new media Saga");

		// List Genre
		
		m_listGenre = new List(10, true);
		dbquerry.listGenre(m_listGenre);
		
		
		// List Saga
		m_listSaga = new List(10, false);
		dbquerry.listSaga(m_listSaga);
			

		// ADD PANEL TO INTERFACE
		m_panelData.setVisible(true);
		add(m_panelData);
		
		
		
		m_addGenre.addActionListener(e -> {
			try {
				addNewGenre(e);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		
		m_addSaga.addActionListener(e -> {
			try {
				addNewSaga(e);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		
		
	}

	public JPanel getM_panelData() {
		return m_panelData;
	}

	public JTextField getM_textDate() {
		return m_textDate;
	}


	public JTextField getM_textGenre() {
		return m_textGenre;
	}


	public JTextField getM_textSaga() {
		return m_textSaga;
	}


	public String[] getM_genrelist() {
		return m_genrelist;
	}


	public String getM_sagalist() {
		return m_sagalist;
	}


	public List getM_listGenre() {
		return m_listGenre;
	}


	public List getM_listSaga() {
		return m_listSaga;
	}


	public DBquerry getDbquerry() {
		return dbquerry;
	}


	public Integer getM_id() {
		return m_id;
	}


	public JButton getM_modifier() {
		return m_modifier;
	}


	public JButton getM_Supprimer() {
		return m_Supprimer;
	}


	public JButton getM_addSaga() {
		return m_addSaga;
	}


	public JButton getM_addGenre() {
		return m_addGenre;
	}


	public JButton getM_ajouter() {
		return m_ajouter;
	}


	public JLabel getM_titre() {
		return m_titre;
	}


	public JLabel getM_date() {
		return m_date;
	}


	public JLabel getM_genre() {
		return m_genre;
	}


	public JLabel getM_saga() {
		return m_saga;
	}


	public JTextField getM_textTitre() {
		return m_textTitre;
	}


	
	private void addNewGenre(ActionEvent e) throws SQLException {
		if (m_textGenre.getText().length() == 0 || m_textGenre.getText().equalsIgnoreCase("Enter new media genre"))
		{
			JOptionPane.showMessageDialog(null, "The case is empty , Nothing to add !");
		}
		else
			{
			String stringGenre = m_textGenre.getText().toString().toLowerCase();
			String update = "select genre.`genre_Nom` from genre where genre.`genre_Nom` = ?";
			PreparedStatement statement = dbquerry.getConnection().prepareStatement(update);
			statement.setString(1, stringGenre);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
			{
				JOptionPane.showMessageDialog(null, "Genre existed please try another one");
			}
			else
			{
				String update1 = "INSERT into genre (genre.`genre_Nom`) VALUES (?)";
				PreparedStatement statement1 = dbquerry.getConnection().prepareStatement(update1);
				statement1.setString(1, stringGenre);
				statement1.executeQuery();
				m_textGenre.setText("");
				JOptionPane.showMessageDialog(null, "New Genre added successfully");
				m_listGenre.removeAll();
				dbquerry.listGenre(m_listGenre);
				
			}
		}
	}
	
	
	private void addNewSaga(ActionEvent e) throws SQLException {
		
		if (m_textSaga.getText().length() == 0 || m_textSaga.getText().equalsIgnoreCase("Enter new media Saga"))
		{
			JOptionPane.showMessageDialog(null, "The case is empty , Nothing to add !");
		}
		else
		{
			String stringSaga = m_textSaga.getText().toString().toLowerCase();
			String update = "select saga.`saga_Nom` from saga where saga.`saga_Nom` = ?";
			PreparedStatement statement = dbquerry.getConnection().prepareStatement(update);
			statement.setString(1, stringSaga);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
			{
				JOptionPane.showMessageDialog(null, "Saga name existed please try another one");
			}
			else
			{
				String update1 = "INSERT into saga (saga.`saga_Nom`) VALUES (?)";
				PreparedStatement statement1 = dbquerry.getConnection().prepareStatement(update1);
				statement1.setString(1, stringSaga);
				statement1.executeQuery();
				m_textSaga.setText("");
				JOptionPane.showMessageDialog(null, "New Saga added successfully");
				m_listSaga.removeAll();
				dbquerry.listSaga(m_listSaga);
				
			}
		}
	}
	
	protected GridBagConstraints mediaModel() {
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 1;     
        m_panelData.add(m_titre, constraints);
 
        constraints.gridx = 1;
        m_panelData.add(m_textTitre, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 2;     
        m_panelData.add(m_date, constraints);
         
        constraints.gridx = 1;
        m_panelData.add(m_textDate, constraints);
  
        
        constraints.gridx = 0;
        constraints.gridy = 4;     
        m_panelData.add(m_genre, constraints);
        
        constraints.gridx = 1;
        m_panelData.add(m_listGenre, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 5;     
        m_panelData.add(m_textGenre, constraints);
        
        constraints.gridx = 2;
        m_panelData.add(m_addGenre, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 6;     
        m_panelData.add(m_saga, constraints);
        
        constraints.gridx = 1;
        m_panelData.add(m_listSaga, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 7;     
        m_panelData.add(m_textSaga, constraints);
        
        constraints.gridx = 2;
        m_panelData.add(m_addSaga, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 8;
        
        constraints.anchor = GridBagConstraints.CENTER;
        m_panelData.add(m_modifier, constraints);
        

        
        constraints.gridx = 2;
        m_panelData.add(m_Supprimer, constraints);
        
		return constraints;
		
		
	}
	


}
