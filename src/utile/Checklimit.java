package utile;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import view.Book_view;
import view.Film_view;
import view.Media_view;
import view.Music_view;

public class Checklimit extends PlainDocument {

	private static final long serialVersionUID = 2967526196981748424L;

	private int limit;
	/**
	 * Constructor
	 * @param limit Int
	 */
	public Checklimit(int limit) {
		   super();
		   this.limit = limit;
		   
		   }
	/**
	 * Check insert input limit
	 */
	public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
	    if (str == null) return;
	    if ((getLength() + str.length()) <= limit) {
	      super.insertString(offset, str, attr);
	    }
	    // if more than 4 caracteres we will try to test ISBN only number accept
	    if(limit > 4)
	    {
	    	try
		    {
		    	if (str.equals("Null")) {
		    		Book_view.getTextBookIsbn().setText("");
		    	} else {
		    		 Long.parseLong(str);
		    	}
		       
		    } catch(Exception e){
		    	System.out.println("Incorrect format"); // show error
		        JOptionPane.showMessageDialog(null, "Incorrect format, Only DIGITAL format");
		        Book_view.getTextBookIsbn().setText(Book_view.getTextBookIsbn().getText().substring(0, Book_view.getTextBookIsbn().getText().length() - 1)); // Delete the wrong format
		    }
	    } else 
	    {
	    	 try
	 	    {
	 	        Integer.parseInt(str);
	 	    } catch(Exception e){
	 	    	System.out.println("Incorrect format");
	 	    	JOptionPane.showMessageDialog(null, "Incorrect format, Only DIGITAL format");
	 	    	if (Media_view.getTextMediaYear().getText().length() != 0 ) {
		 	    	Media_view.getTextMediaYear().setText(Media_view.getTextMediaYear().getText().substring(0, Media_view.getTextMediaYear().getText().length() - 1)); 
	 	    	}
	 	    	if (Film_view.getTextFilmYear().getText().length() != 0) {
		 	    	Film_view.getTextFilmYear().setText(Film_view.getTextFilmYear().getText().substring(0, Film_view.getTextFilmYear().getText().length() - 1)); 
	 	    	}
	 	    	if (Book_view.getTextBookYear().getText().length() != 0) {
	 	    		Book_view.getTextBookYear().setText(Book_view.getTextBookYear().getText().substring(0, Book_view.getTextBookYear().getText().length() - 1)); 
	 	    	}
	 	    	if (Music_view.getTextMusicYear().getText().length() != 0) {
	 	    		Music_view.getTextMusicYear().setText(Music_view.getTextMusicYear().getText().substring(0, Music_view.getTextMusicYear().getText().length() - 1)); 
	 	    	}
	 	       
	 	    }
	    }
//	        Interface.textMediaYear.setText(Interface.textMediaYear.getText().substring(0, Interface.textMediaYear.getText().length() - 1));     
	  
	}
     
}
