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
import ui.Interface;
import utile.Checklimit;

public class User_view extends Media_view {

	private static final long serialVersionUID = 7706042524359417112L;

	private JTextField u_textTitre = new JTextField(25);
	private JTextField u_textDate = new JTextField(25);
	private JTextField u_textGenre = new JTextField(15);
	private JTextField u_textSaga = new JTextField(15);
	private String[] u_genrelist;
	private String u_sagalist;
	private List u_listGenre;
	private List u_listSaga;
	
	private Integer u_id;

	private JButton u_modifier = new JButton();
	private JButton u_Supprimer = new JButton();
	private JButton u_addSaga = new JButton("Add New Saga");
	private JButton u_addGenre = new JButton("Add New Genre");

	public User_view() throws Exception {
		// CONSTRUCTOR

		super();
		mediaModel();
//		dbquerry = new DBquerry();
//		dbquerry.getConnection();

		u_textTitre = super.getM_textTitre();
		u_textDate = super.getM_textDate();
		u_textGenre = super.getM_textGenre();
		u_textSaga = super.getM_textSaga();
		u_id = super.getM_id();

		u_modifier = super.getM_modifier();
		u_Supprimer = super.getM_Supprimer();

		u_textGenre = super.getM_textGenre();
		u_textSaga = super.getM_textSaga();

		u_addSaga = super.getM_addSaga();
		u_addGenre = super.getM_addGenre();

		u_listGenre = super.getM_listGenre();
		u_listSaga = super.getM_listSaga();

		// List Genre

		u_listGenre.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				u_genrelist = u_listGenre.getSelectedItems();
			}
		});

		// List Saga
		u_listSaga.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				u_sagalist = u_listSaga.getSelectedItem();

			}
		});

		Interface.getTableFilm().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				u_textTitre.setText(target.getValueAt(row, 1).toString());
				u_textDate.setText(target.getValueAt(row, 5).toString());
				u_id = (Integer) target.getValueAt(row, 0);

			}
		});

//		u_modifier.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					if (u_genrelist != null) {
//						updateFilm(u_textTitre.getText(), u_textDate.getText(), u_sagalist, u_genrelist);
//					} else if (u_genrelist == null) {
//						updateFilm(u_textTitre.getText(), u_textDate.getText(), u_sagalist);
//					}
//					Interface frame = (Interface) u_modifier.getTopLevelAncestor();
//					frame.showFilm(e);
//					u_textTitre.setText("");
//					u_textDate.setText("");
//					u_listGenre.deselect(u_listGenre.getSelectedIndex());
//				} catch (SQLException e1) {
//
//					e1.printStackTrace();
//				}
//
//			}
//		});
//
//		u_Supprimer.addActionListener(e -> {
//			try {
//				deleteMedia(e);
//				Interface frame = (Interface) u_Supprimer.getTopLevelAncestor();
//				frame.showFilm(e);
//				u_textTitre.setText("");
//				u_textDate.setText("");
//			} catch (SQLException | NullPointerException e1) {
//
//				e1.printStackTrace();
//				JOptionPane.showMessageDialog(null, "Nothing selected !");
//			}
//		});

	}

	protected GridBagConstraints mediaModel() {
		JPanel f_panelBanner = new JPanel();
	JLabel f_jlabelBanner = new JLabel("ARTIST PANEL");
		f_jlabelBanner.setFont(new Font("Verdana", 1, 20));
		f_panelBanner.add(f_jlabelBanner);
		f_panelBanner.setBorder(new LineBorder(Color.MAGENTA, 4));
		GridBagConstraints constraints = super.mediaModel();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 1;
		constraints.gridy = 0;
		getM_panelData().add(f_panelBanner, constraints);

		return constraints;
	}


}
