package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.InfoMedia_controller;
import controller.Media_controller;

public class InfoMedia_view extends JFrame {

	
	private static final long serialVersionUID = 5288686245352987889L;
	
	private JPanel panel = new JPanel();
	private JPanel panelInfo = new JPanel(new GridBagLayout());
	private GridBagConstraints constraints ;
	private GridBagConstraints constraintsEdit ;

	private JPanel panelEdit= new JPanel(new GridBagLayout());

	private JLabel labelId = new JLabel();

	private JLabel labelTitle = new JLabel("Title");
	private JLabel labelTitleContent = new JLabel();
	
	private JLabel labelType = new JLabel("Media type");
	private JLabel labelTypeContent = new JLabel();

	private JLabel labelYear = new JLabel("Year");
	private JLabel labelYearContent = new JLabel();
	
	private JLabel labelGenre = new JLabel("Genre");
	private JLabel labelGenreContent = new JLabel();
	
	private JLabel labelIsbn = new JLabel("");
	private JLabel labelIsbnContent = new JLabel();

	private JLabel labelAutor = new JLabel("");
	private JLabel labelAutorContent = new JLabel();
	
	private JLabel labelActor = new JLabel("");
	private JLabel labelActorContent = new JLabel();
	
	private JLabel labelDirector = new JLabel("");
	private JLabel labelDirectorContent = new JLabel();
	
	private JLabel labelSinger = new JLabel("");
	private JLabel labelSingerContent = new JLabel();
	
	private JLabel labelEditor = new JLabel("");
	private JLabel labelEditorContent = new JLabel();

	private Integer id;
	
	private JButton info_add = new JButton("Update");
	private JButton info_deselect = new JButton("Reset list");
	
	private List listArtist= new List(10, true);

	private JComboBox<Object> jobList = new JComboBox<Object>();

	private InfoMedia_controller infoMedia_controller = new InfoMedia_controller(this);
	
	public InfoMedia_view()  {
		super("Info Media");
		
		
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		
		contentModel();
		editModel();
		
		labelId.setVisible(false);
		
		infoMedia_controller.listArtist();
		jobList.setModel(new DefaultComboBoxModel<Object>(infoMedia_controller.jobList()));
		
		info_add.addActionListener((e) -> infoMedia_controller.addArtistJob(e));
		info_deselect.addActionListener((e)->infoMedia_controller.deselectList(e));
		
		panelInfo.setPreferredSize(new Dimension(350, 450));
		panelEdit.setPreferredSize(new Dimension(300, 450));
		
		panel.add(panelInfo,BorderLayout.EAST);
		panel.add(panelEdit, BorderLayout.WEST);
		panelInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,5, 5, Color.PINK), "Media Information", TitledBorder.RIGHT, TitledBorder.TOP));
		panelEdit.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Edit Panel"));
		
		this.getContentPane().add(panel);
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public JLabel getLabelTitle() {
		return labelTitle;
	}


	public JLabel getLabelYear() {
		return labelYear;
	}

	public JLabel getLabelTypeContent() {
		return labelTypeContent;
	}

	public JLabel getLabelGenre() {
		return labelGenre;
	}

	public JLabel getLabelIsbn() {
		return labelIsbn;
	}

	public JLabel getLabelIsbnContent() {
		return labelIsbnContent;
	}

	public JLabel getLabelAutor() {
		return labelAutor;
	}


	public JLabel getLabelActor() {
		return labelActor;
	}


	public JLabel getLabelDirector() {
		return labelDirector;
	}


	public JLabel getLabelDirectorContent() {
		return labelDirectorContent;
	}


	public JLabel getLabelSinger() {
		return labelSinger;
	}


	public JLabel getLabelSingerContent() {
		return labelSingerContent;
	}


	public JLabel getLabelTitleContent() {
		return labelTitleContent;
	}

	public JLabel getLabelYearContent() {
		return labelYearContent;
	}


	public Integer getId() {
		return id;
	}

	public JLabel getLabelId() {
		return labelId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JLabel getLabelGenreContent() {
		return labelGenreContent;
	}

	public JLabel getLabelAutorContent() {
		return labelAutorContent;
	}

	public JLabel getLabelActorContent() {
		return labelActorContent;
	}

	public GridBagConstraints getConstraints() {
		return constraints;
	}
	
	public GridBagConstraints getConstraintsEdit() {
		return constraintsEdit;
	}
	
	public JPanel getPanelInfo() {
		return panelInfo;
	}
	
	public JPanel getPanelEdit() {
		return panelEdit;
	}
	
	public JLabel getLabelEditor() {
		return labelEditor;
	}


	public JLabel getLabelEditorContent() {
		return labelEditorContent;
	}
	
	public JButton getInfo_add() {
		return info_add;
	}
	
	public List getListArtist() {
		return listArtist;
	}

	public JComboBox<Object> getJobList() {
		return jobList;
	}

	private GridBagConstraints contentModel() {
		
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		panelInfo.add(labelId, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		panelInfo.add(labelTitle, constraints);

		constraints.gridx = 1;
		panelInfo.add(labelTitleContent, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		panelInfo.add(labelYear, constraints);
		
		constraints.gridx = 1;
		panelInfo.add(labelYearContent, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		panelInfo.add(labelGenre, constraints);
		
		constraints.gridx = 1;
		panelInfo.add(labelGenreContent, constraints);
	
		return constraints;

	}
	
	private GridBagConstraints editModel() {
		
		constraintsEdit = new GridBagConstraints();
		constraintsEdit.anchor = GridBagConstraints.WEST;
		constraintsEdit.insets = new Insets(10, 10, 10, 10);
		
		constraintsEdit.gridx = 0;
		constraintsEdit.gridy = 0;
		panelEdit.add(listArtist, constraintsEdit);
		
		constraintsEdit.gridx = 0;
		constraintsEdit.gridy = 1;
		panelEdit.add(jobList, constraintsEdit);
		
		constraintsEdit.gridx = 0;
		constraintsEdit.gridy = 2;
		panelEdit.add(info_add, constraintsEdit);
		
		constraintsEdit.gridx = 1;
		panelEdit.add(info_deselect, constraintsEdit);

		return constraintsEdit;

	}

	
}
