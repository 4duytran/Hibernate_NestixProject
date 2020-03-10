package view_backup;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import config.DBquerry;
import service.MusicService;
import ui.Interface;

public class Music_view extends Media_view {

	private static final long serialVersionUID = 7706042524359417112L;

	private JTextField music_textTitre = new JTextField(25);
	private JTextField music_textDate = new JTextField(25);
//	private JTextField music_textGenre = new JTextField(15);
//	private JTextField music_textSaga = new JTextField(15);
	private String[] music_genrelist;
	private String music_sagalist;
	private List music_listGenre;
	private List music_listSaga;

	private Integer music_id;

	private JButton music_modifier = new JButton();
	private JButton music_Supprimer = new JButton();
//	private JButton music_addSaga = new JButton("Add New Saga");
//	private JButton music_addGenre = new JButton("Add New Genre");

	public Music_view() throws Exception {
		// CONSTRUCTOR

		super();
		mediaModel();
		MusicService musicService = new MusicService();

//		dbquerry = new DBquerry();
//		dbquerry.getConnection();

		super.getM_panelData().remove(getM_saga());
		super.getM_panelData().remove(getM_textSaga());
		super.getM_panelData().remove(getM_addSaga());
		super.getM_panelData().remove(getM_listSaga());

		music_textTitre = super.getM_textTitre();
		music_textDate = super.getM_textDate();
//		music_textGenre = super.getM_textGenre();
//		music_textSaga = super.getM_textSaga();
		music_id = super.getM_id();

		music_modifier = super.getM_modifier();
		music_Supprimer = super.getM_Supprimer();

//		music_addSaga = super.getM_addSaga();
//		music_addGenre = super.getM_addGenre();

		music_listGenre = super.getM_listGenre();
		music_listSaga = super.getM_listSaga();

		// List Genre
		music_listGenre.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				music_genrelist = music_listGenre.getSelectedItems();
			}
		});

		// List Saga
		music_listSaga.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				music_sagalist = music_listSaga.getSelectedItem();

			}
		});

		Interface.getTableMusic().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				music_textTitre.setText(target.getValueAt(row, 1).toString());
				music_textDate.setText(target.getValueAt(row, 4).toString());

				music_id = (Integer) target.getValueAt(row, 0);

			}
		});

		music_modifier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (music_genrelist != null) {
						musicService.updateMusic(music_id, music_textTitre.getText(), music_textDate.getText(), music_sagalist,
								music_genrelist);
					} else if (music_genrelist == null) {
						musicService.updateMusic(music_id, music_textTitre.getText(), music_textDate.getText(), music_sagalist);
					}
					Interface frame = (Interface) music_modifier.getTopLevelAncestor();
					frame.showMusic(e);
					music_textTitre.setText("");
					music_textDate.setText("");
					music_listGenre.deselect(music_listGenre.getSelectedIndex());
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});

		music_Supprimer.addActionListener(e -> {
			try {
				musicService.deleteMedia(music_id);
				JOptionPane.showMessageDialog(null,
						"The media title " + music_textTitre.getText().toUpperCase() + " has been deleted successfully");
				Interface frame = (Interface) music_Supprimer.getTopLevelAncestor();
				frame.showMusic(e);
				music_textTitre.setText("");
				music_textDate.setText("");
			} catch (SQLException | NullPointerException e1) {

				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Nothing selected !");
			}
		});

	}

	protected GridBagConstraints mediaModel() {

		JPanel music_panelBanner = new JPanel();
		JLabel music_jlabelBanner = new JLabel("MUSIC PANEL");
		music_jlabelBanner.setFont(new Font("Verdana", 1, 20));
		music_panelBanner.add(music_jlabelBanner);
		music_panelBanner.setBorder(new LineBorder(Color.BLUE, 4));
		GridBagConstraints constraints = super.mediaModel();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 1;
		constraints.gridy = 0;
		getM_panelData().add(music_panelBanner, constraints);
		return constraints;
	}

}
