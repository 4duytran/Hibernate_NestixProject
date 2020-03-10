package view.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Genre_entity;
import entity.Media_entity;

public class MediaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 5618868029037443430L;
	
	private List<String> columnsNames = new ArrayList<String>();
	private List<Media_entity> list = new ArrayList<Media_entity>();
	
	
	public MediaTableModel(List<String> columnsNames, List< Media_entity > values) {
		this.columnsNames = columnsNames;
		this.list = values;
	}
	
	@Override 
	public String getColumnName(int i) {
		return columnsNames.get( i );
	}

	@Override 
	public int getColumnCount() {
		return columnsNames.size();
	}

	@Override 
	public int getRowCount() {
		return list.size();
	}

	@Override 
	public Object getValueAt( int line, int column ) {
//		return values.get( line ).get( column );
		Media_entity m = list.get(line);
		List<String> genre = new ArrayList<>();
		for (Genre_entity list : m.getGenre()) {
			genre .add(list.getGenre_name());
		}
			switch (column) {
	        case 0: return m.getMedia_title(); 
	        case 1: return m.getMedia_year();
	        case 2: return String.join(",", genre);
	        case 3:return (null == m.getSaga()) ? "Null" : m.getSaga().getSaga_Name();
	        case 4: return m.getMedia_type().getMediaType_name(); 
	        case 5:return ( m.getMedia_valid() == true) ?  "Yes" :   "No";
			}
        return null;
	}
	
	
}
