package user_interface;
import java.util.Random;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

final class TableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Integer.class };

	TableModel(int rows) {
		super(createTableModel(rows), getColumns());
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
        return column == 0 ? false : true;
    }
	
	private static Object[][] createTableModel(int rows) {
		Object[][] table;
		table = new Object[rows+1][4];
		for (int col = 1; col < 4; col++) {
			for (int row = 0; row < rows + 1; row++) {
				table[row][0] = row;
				table[row][col] = "";
			}
		}
		return table;
	}

	public static String[] getColumns() {
		return new String[] { "No.", "V1", "V2", "W",};
	}

	public static void losujDane(int machines, int tasks, int maxVal, JTable table) {
		Random random = new Random();
		for (int m = 1; m < machines + 1; m++) {
			for (int z = 0; z < tasks; z++) {
				table.getModel().setValueAt(random.nextInt(tasks - 1) + 1, z, m);
			}
		}
	}

	public Class<? extends String> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}
}
