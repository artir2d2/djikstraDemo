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
		table = new Object[dimention][dimention+1];
		for (int col = 1; col < dimention; col++) {
			for (int row = 0; row < dimention; row++) {
				if(row == col-1){
					table[row][col] = "-";
				}else{
					table[row][col] = "";
				}
				table[row][0] = "V"+(row+1);
			}
			table[dimention-1][dimention] = "-";
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

	public static void losujDane(int minVal, int maxVal, Integer randNoEdge, int size, JTable table) {
		Random random = new Random();
		for (int y = 0; y < size; y++) {
			for (int x = 1; x < size + 1; x++) {
				if (x - 1 == y) {
					table.getModel().setValueAt("-", y, x);
				} else {
					table.getModel().setValueAt(random.nextInt(maxVal - minVal) + minVal, y, x);
				}
				
				if (x - 1 != y && random.nextInt(100) > randNoEdge) {
					table.getModel().setValueAt("", y, x);
				}
			}
		}
	}

	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex==0) return String.class;
		return Integer.class;
		
	}
}
