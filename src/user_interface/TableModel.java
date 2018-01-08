package user_interface;
import java.util.Random;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

final class TableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	
	TableModel(int dimention) {
		super(createTableModel(dimention), getColumns(dimention));
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
		if(row+1 == column) return false;
        return column == 0 ? false : true;
    }
	
	private static Object[][] createTableModel(int dimention) {
		Object[][] table;
		table = new Object[dimention][dimention];
		for (int col = 1; col < dimention; col++) {
			for (int row = 0; row < dimention; row++) {
				table[row][0] = "V"+(row+1);
				table[row][col] = "";
			}
		}
		return table;
	}

	public static String[] getColumns(int cols) {
		String columns[] = new String[cols+1];
		if(columns.length==0){
			return new String[1];
		}
		columns[0] = "";
		for(int i=1;i<cols+1;i++){
			columns[i] = "V"+i;
		}
		return columns;
	}

	public static void losujDane(int machines, int tasks, int maxVal, JTable table) {
		Random random = new Random();
		for (int m = 1; m < machines + 1; m++) {
			for (int z = 0; z < tasks; z++) {
				table.getModel().setValueAt(random.nextInt(tasks - 1) + 1, z, m);
			}
		}
	}

	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex==0) return String.class;
		return Integer.class;
		
	}
}
