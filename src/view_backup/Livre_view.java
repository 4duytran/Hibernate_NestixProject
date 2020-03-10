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
import service.LivreService;
import ui.Interface;
import utile.Checklimit;

public class Livre_view extends Media_view {

	private static final long serialVersionUID = 7706042524359417112L;

	private JLabel l_isbn = new JLabel("Num ISBN");

	private JTextField l_textTitre = new JTextField(25);
	private JTextField l_textDate = new JTextField(25);
	private static JTextField l_textIsbn = new JTextField(25);
//	private JTextField l_textGenre = new JTextField(15);
//	private JTextField l_textSaga = new JTextField(15);
	private String[] l_genrelist;
	private String l_sagalist;
	private List l_listGenre;
	private List l_listSaga;
	
	private Integer l_id;

	private JButton l_modifier = new JButton();
	private JButton l_Supprimer = new JButton();
	private JButton l_addSaga = new JButton("Add New Saga");
	private JButton l_addGenre = new JButton("Add New Genre");

	public Livre_view() throws Exception {
		// CONSTRUCTOR

		super();
		mediaModel();
		LivreService livreService = new LivreService();

		getL_textIsbn().setDocument(new Checklimit(13));
		l_textTitre = super.getM_textTitre();
		l_textDate = super.getM_textDate();
//		l_textGenre = super.getM_textGenre();
//		l_textSaga = super.getM_textSaga();
		l_id = super.getM_id();

		l_modifier = super.getM_modifier();
		l_Supprimer = super.getM_Supprimer();

		l_addSaga = super.getM_addSaga();
		l_addGenre = super.getM_addGenre();

		l_listGenre = super.getM_listGenre();
		l_listSaga = super.getM_listSaga();

		// List Genre

		l_listGenre.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				l_genrelist = l_listGenre.getSelectedItems();
			}
		});

		// List Saga
		l_listSaga.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				l_sagalist = l_listSaga.getSelectedItem();

			}
		});

		Interface.getTableLivre().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				l_textTitre.setText(target.getValueAt(row, 1).toString());
				l_textDate.setText(target.getValueAt(row, 6).toString());

				if (target.getValueAt(row, 5) != null) {
					getL_textIsbn().setText(target.getValueAt(row, 5).toString());

				} else {
					getL_textIsbn().setText("");
				}
				l_id = (Integer) target.getValueAt(row, 0);

			}
		});

		l_modifier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (l_genrelist != null) {
						livreService.updateLivre(l_id, l_textTitre.getText(), l_textDate.getText(), l_sagalist, getL_textIsbn().getText(),
								l_genrelist);
					} else if (l_genrelist == null) {
						livreService.updateLivre(l_id, l_textTitre.getText(), l_textDate.getText(), l_sagalist, getL_textIsbn().getText());
					}
					Interface frame = (Interface) l_modifier.getTopLevelAncestor();
					frame.showLivre(e);
					l_textTitre.setText("");
					l_textDate.setText("");
					getL_textIsbn().setText("");
					l_listGenre.deselect(l_listGenre.getSelectedIndex());
					l_listSaga.deselect(l_listSaga.getSelectedIndex());

				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});

		l_Supprimer.addActionListener(e -> {
			try {
				livreService.deleteMedia(l_id);
				JOptionPane.showMessageDialog(null,
						"The media title " + l_textTitre.getText().toUpperCase() + " has been deleted successfully");
				Interface frame = (Interface) l_Supprimer.getTopLevelAncestor();
				frame.showLivre(e);
				l_textTitre.setText("");
				l_textDate.setText("");
				getL_textIsbn().setText("");
			} catch (SQLException | NullPointerException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Nothing selected !");
			}
		});

	}

	protected GridBagConstraints mediaModel() {

		JPanel l_panelBanner = new JPanel();
		JLabel l_jlabelBanner = new JLabel("BOOK PANEL");
		l_jlabelBanner.setFont(new Font("Verdana", 1, 20));
		l_panelBanner.add(l_jlabelBanner);
		l_panelBanner.setBorder(new LineBorder(Color.yellow, 4));
		GridBagConstraints constraints = super.mediaModel();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 1;
		constraints.gridy = 0;
		getM_panelData().add(l_panelBanner, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		getM_panelData().add(l_isbn, constraints);

		constraints.gridx = 1;
		getM_panelData().add(getL_textIsbn(), constraints);
		return constraints;
	}

	public static JTextField getL_textIsbn() {
		return l_textIsbn;
	}
}
