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
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import config.DBquerry;
import service.FilmService;
import ui.Interface;
import utile.Checklimit;

public class Film_view extends Media_view {

	private static final long serialVersionUID = 7706042524359417112L;

	private JTextField f_textTitre = new JTextField(25);
	private JTextField f_textDate = new JTextField(25);
	private JTextField f_textGenre = new JTextField(15);
	private JTextField f_textSaga = new JTextField(15);
	private String[] f_genrelist;
	private String f_sagalist;
	private List f_listGenre;
	private List f_listSaga;

	private Integer f_id;

	private JButton f_modifier = new JButton();
	private JButton f_Supprimer = new JButton();
	private JButton f_addSaga = new JButton("Add New Saga");
	private JButton f_addGenre = new JButton("Add New Genre");

	public Film_view() throws Exception {
		// CONSTRUCTOR
		super();
		mediaModel();
		FilmService filmService = new FilmService();
//		dbquerry = new DBquerry();
//		dbquerry.getConnection();

		f_textTitre = super.getM_textTitre();
		f_textDate = super.getM_textDate();
		f_textGenre = super.getM_textGenre();
		f_textSaga = super.getM_textSaga();
		f_id = super.getM_id();

		f_modifier = super.getM_modifier();
		f_Supprimer = super.getM_Supprimer();

		f_textGenre = super.getM_textGenre();
		f_textSaga = super.getM_textSaga();

		f_addSaga = super.getM_addSaga();
		f_addGenre = super.getM_addGenre();

		f_listGenre = super.getM_listGenre();
		f_listSaga = super.getM_listSaga();

		// List Genre
		
		f_listGenre.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				f_genrelist = f_listGenre.getSelectedItems();
			}
		});

		// List Saga
		f_listSaga.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				f_sagalist = f_listSaga.getSelectedItem();

			}
		});

		Interface.getTableFilm().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				f_textTitre.setText(target.getValueAt(row, 1).toString());
				f_textDate.setText(target.getValueAt(row, 5).toString());
				f_id = (Integer) target.getValueAt(row, 0);

			}
		});

		f_modifier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (f_genrelist != null) {
						filmService.updateFilm(f_id, f_textTitre.getText(), f_textDate.getText(), f_sagalist, f_genrelist);
					} else if (f_genrelist == null) {
						filmService.updateFilm(f_id, f_textTitre.getText(), f_textDate.getText(), f_sagalist);
					}
					Interface frame = (Interface) f_modifier.getTopLevelAncestor();
					frame.showFilm(e);
					f_textTitre.setText("");
					f_textDate.setText("");
					f_listGenre.deselect(f_listGenre.getSelectedIndex());
					f_listSaga.deselect(f_listSaga.getSelectedIndex());
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});

		f_Supprimer.addActionListener(e -> {
			try {
				filmService.deleteMedia(f_id);
				JOptionPane.showMessageDialog(null,
						"The media title " + f_textTitre.getText().toUpperCase() + " has been deleted successfully");
				Interface frame = (Interface) f_Supprimer.getTopLevelAncestor();
				frame.showFilm(e);
				f_textTitre.setText("");
				f_textDate.setText("");
			} catch (SQLException | NullPointerException e1) {

				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Nothing selected !");
			}
		});

	}

	protected GridBagConstraints mediaModel() {
		JPanel f_panelBanner = new JPanel();
		JLabel f_jlabelBanner = new JLabel("FILM PANEL");
		f_jlabelBanner.setFont(new Font("Verdana", 1, 20));
		f_panelBanner.add(f_jlabelBanner);
		f_panelBanner.setBorder(new LineBorder(Color.red, 4));
		GridBagConstraints constraints = super.mediaModel();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 1;
		constraints.gridy = 0;
		getM_panelData().add(f_panelBanner, constraints);

		return constraints;
	}

}
