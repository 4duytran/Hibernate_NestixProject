package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class InfoMedia_view extends JFrame {

	
	private static final long serialVersionUID = 5288686245352987889L;
	
	
	private JPanel panelImage = new JPanel();
	private JPanel image = new JPanel();
	private JPanel panelInfo= new JPanel();

	public InfoMedia_view()  {
		super("Info Media");
		
		
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		
		this.setVisible(true);
		
		panelImage.add(image, BorderLayout.CENTER);
		image.setPreferredSize(new Dimension(350, 400));
		panelInfo.setPreferredSize(new Dimension(200, 400));
		
		image.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(10, 10,10, 10, Color.PINK), "Picture", TitledBorder.RIGHT, TitledBorder.TOP));
		
		this.getContentPane().add(panelImage, BorderLayout.CENTER);
		this.getContentPane().add(panelInfo, BorderLayout.EAST);
		
	}
	
	

	

	
	
	
	
}
