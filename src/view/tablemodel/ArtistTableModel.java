package view.tablemodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Artist_entity;

public class ArtistTableModel extends AbstractTableModel{

	
	private static final long serialVersionUID = -4252472805945219478L;
	private List<String> columnsNames = new ArrayList<String>();
	private List<Artist_entity> list = new ArrayList<Artist_entity>();
	
	
	public ArtistTableModel(List<String> columnsNames, List< Artist_entity > values) {
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
    public Class getColumnClass(int column) {
        switch (column) {
            case 0: return String.class;
            case 1: return String.class;
            case 2:return String.class;
            case 3: return String.class;
            default:
                return String.class;
        }
    }
	
	@Override 
	public Object getValueAt ( int line, int column ) {
//		return values.get( line ).get( column );
		Artist_entity a = list.get(line);
		String s = (null == a.getDob()) ? "Null" : new SimpleDateFormat("dd/MMMM/yyyy").format(a.getDob());
			switch (column) {
	        case 0: return a.getFirstName(); 
	        case 1: return a.getLastName();
	        case 2:return a.getSurName();
	        case 3: return s;
	        
	        }
        return null;
	}

}