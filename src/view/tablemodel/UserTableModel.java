package view.tablemodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Genre_entity;
import entity.Media_entity;
import entity.User_entity;

public class UserTableModel extends AbstractTableModel{


	private static final long serialVersionUID = 1897460878493742727L;
	private List<String> columnsNames = new ArrayList<String>();
	private List<User_entity> list = new ArrayList<User_entity>();
	
	
	public UserTableModel(List<String> columnsNames, List< User_entity > values) {
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
            case 4: return String.class;
            case 5:return Boolean.class;
            default:
                return Boolean.class;
        }
    }
	
	@Override 
	public Object getValueAt ( int line, int column ) {
//		return values.get( line ).get( column );
		User_entity u = list.get(line);
		String s = new SimpleDateFormat("dd/MMMM//yyyy").format(u.getDate());
			switch (column) {
	        case 0: return u.getFirstName(); 
	        case 1: return u.getLastName();
	        case 2:return u.getUserEmail();
	        case 3: return u.getLevel().getLevelName();
	        case 4: return s;
	        case 5: return u.getBlocked() ;
	        }
        return null;
	}
	
}
