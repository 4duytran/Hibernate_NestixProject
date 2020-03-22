package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class InfoMedia_view extends JFrame {

	
	private static final long serialVersionUID = 5288686245352987889L;
	
	private JPanel panel = new JPanel();
	private JPanel panelInfo = new JPanel(new GridBagLayout());
	private JPanel panelEdit= new JPanel();
	
	private JLabel labelTitle = new JLabel("Title");
	private JLabel labelTitleContent = new JLabel();
	
	private JLabel labelYear = new JLabel("Year");
	private JLabel labelYearContent = new JLabel();
	
	private JLabel labelGenre = new JLabel("Genre");
	private JLabel labelGenreContent = new JLabel();
	
	private JLabel labelAutor = new JLabel("");
	private JLabel labelAutorContent = new JLabel();
	
	private JLabel labelActor = new JLabel("");
	private JLabel labelActorContent = new JLabel();
	
	private JLabel labelDirector = new JLabel("");
	private JLabel labelDirectorContent = new JLabel();
	
	private JLabel labelSinger = new JLabel("");
	private JLabel labelSingerContent = new JLabel();


	private Integer id;

	public InfoMedia_view()  {
		super("Info Media");
		
		
		this.setSize(650,450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		contentModel();
		panelInfo.setPreferredSize(new Dimension(350, 400));
		panelEdit.setPreferredSize(new Dimension(250, 400));
		panel.add(panelInfo,BorderLayout.WEST);
		panel.add(panelEdit, BorderLayout.EAST);
		panelInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(5, 5,5, 5, Color.PINK), "Media Information", TitledBorder.RIGHT, TitledBorder.TOP));
		
		this.getContentPane().add(panel);
		
	}
	
	
	public JLabel getLabelTitle() {
		return labelTitle;
	}


	public JLabel getLabelYear() {
		return labelYear;
	}


	public JLabel getLabelGenre() {
		return labelGenre;
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


	public void setId(Integer id) {
		this.id = id;
	}

	public JLabel getLabelGenreContent() {
		return labelGenreContent;
	}


	public void setLabelGenreContent(JLabel labelGenreContent) {
		this.labelGenreContent = labelGenreContent;
	}


	public JLabel getLabelAutorContent() {
		return labelAutorContent;
	}


	public void setLabelAutorContent(JLabel labelAutorContent) {
		this.labelAutorContent = labelAutorContent;
	}


	public JLabel getLabelActorContent() {
		return labelActorContent;
	}


	private GridBagConstraints contentModel() {
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
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

		constraints.gridx = 0;
		constraints.gridy = 3;
		panelInfo.add(labelDirector, constraints);
		
		constraints.gridx = 1;
		panelInfo.add(labelDirectorContent, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		panelInfo.add(labelAutor, constraints);
		
		constraints.gridx = 1;
		panelInfo.add(labelAutorContent, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		panelInfo.add(labelActor, constraints);

		constraints.gridx = 1;
		panelInfo.add(labelActorContent, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		panelInfo.add(labelSinger, constraints);
		
		constraints.gridx = 1;
		panelInfo.add(labelSingerContent, constraints);
		
		return constraints;

	}
	

	
	
	
	
}
