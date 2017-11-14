package user_interface;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class MainWindow {

	private JFrame frmDjikstraAlgorithmDemo;
	private JTable edgeTable;
	private final Action actionAddRow = new SwingAction();
	private final Action actionStartSimulation = new SwingAction_1();
	JComboBox comboStartVertex;
	JComboBox comboEndVertex;
//	private JTable edgeTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmDjikstraAlgorithmDemo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDjikstraAlgorithmDemo = new JFrame();
		frmDjikstraAlgorithmDemo.setTitle("Djikstra algorithm demo");
		frmDjikstraAlgorithmDemo.setBounds(100, 100, 800, 600);
		frmDjikstraAlgorithmDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		frmDjikstraAlgorithmDemo.getContentPane().setLayout(gridBagLayout);

		JPanel menuPanel = new JPanel();
		GridBagConstraints gbc_menuPanel = new GridBagConstraints();
		gbc_menuPanel.anchor = GridBagConstraints.WEST;
		gbc_menuPanel.insets = new Insets(0, 0, 5, 0);
		gbc_menuPanel.fill = GridBagConstraints.VERTICAL;
		gbc_menuPanel.gridx = 0;
		gbc_menuPanel.gridy = 0;
		
		GridBagLayout gbl_menuPanel = new GridBagLayout();
		gbl_menuPanel.columnWidths = new int[]{0,0};
		gbl_menuPanel.rowHeights = new int[]{20, 0, 0, 0, 0};
		gbl_menuPanel.columnWeights = new double[]{1.0,0};
		gbl_menuPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0};
		menuPanel.setLayout(gbl_menuPanel);
		JScrollPane scrollMenuPanel = new JScrollPane(menuPanel);
		frmDjikstraAlgorithmDemo.getContentPane().add(scrollMenuPanel, gbc_menuPanel);
		
		JPanel tablePanel = new JPanel();
		GridBagConstraints gbc_tablePanel = new GridBagConstraints();
		gbc_tablePanel.gridheight = 4;
		gbc_tablePanel.anchor = GridBagConstraints.WEST;
		gbc_tablePanel.insets = new Insets(0, 0, 5, 5);
		gbc_tablePanel.fill = GridBagConstraints.VERTICAL;
		gbc_tablePanel.gridx = 0;
		gbc_tablePanel.gridy = 0;
		menuPanel.add(tablePanel, gbc_tablePanel);
		GridBagLayout gbl_tablePanel = new GridBagLayout();
		gbl_tablePanel.columnWidths = new int[]{0};
		gbl_tablePanel.rowHeights = new int[]{20, 0, 0, 0};
		gbl_tablePanel.columnWeights = new double[]{1.0};
		gbl_tablePanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		tablePanel.setLayout(gbl_tablePanel);
		
		JLabel lblEdgeTable = new JLabel("Tabela kraw\u0119dzi:");
		GridBagConstraints gbc_lblEdgeTable = new GridBagConstraints();
		gbc_lblEdgeTable.insets = new Insets(0, 0, 5, 0);
		gbc_lblEdgeTable.gridx = 0;
		gbc_lblEdgeTable.gridy = 0;
		tablePanel.add(lblEdgeTable, gbc_lblEdgeTable);
		
		edgeTable = new JTable(new TableModel(5));
		edgeTable.addPropertyChangeListener(new TableChangeListener(this.comboStartVertex, (TableModel) this.edgeTable.getModel()));
		GridBagConstraints gbc_edgeTable = new GridBagConstraints();
		gbc_edgeTable.insets = new Insets(0, 0, 5, 0);
		gbc_edgeTable.fill = GridBagConstraints.BOTH;
		gbc_edgeTable.gridx = 0;
		gbc_edgeTable.gridy = 1;
		JScrollPane scrolleEdgeTable = new JScrollPane(edgeTable);
		scrolleEdgeTable.setPreferredSize(new Dimension(150,Integer.MAX_VALUE));
		tablePanel.add(scrolleEdgeTable,gbc_edgeTable);
		
		JButton btnAddRow = new JButton("Dodaj wiersz");
		btnAddRow.setAction(actionAddRow);
		GridBagConstraints gbc_btnAddRow = new GridBagConstraints();
		gbc_btnAddRow.gridx = 0;
		gbc_btnAddRow.gridy = 2;
		tablePanel.add(btnAddRow, gbc_btnAddRow);
		
		JLabel lblStartVertex = new JLabel("Wierzcho\u0142ek pocz\u0105tkowy:");
		GridBagConstraints gbc_lblStartVertex = new GridBagConstraints();
		gbc_lblStartVertex.insets = new Insets(0, 0, 5, 0);
		gbc_lblStartVertex.anchor = GridBagConstraints.NORTH;
		gbc_lblStartVertex.gridx = 1;
		gbc_lblStartVertex.gridy = 0;
		menuPanel.add(lblStartVertex, gbc_lblStartVertex);
		
		comboStartVertex = new JComboBox();
		GridBagConstraints gbc_comboStartVertex = new GridBagConstraints();
		gbc_comboStartVertex.insets = new Insets(0, 0, 5, 0);
		gbc_comboStartVertex.anchor = GridBagConstraints.NORTH;
		gbc_comboStartVertex.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboStartVertex.gridx = 1;
		gbc_comboStartVertex.gridy = 1;
		menuPanel.add(comboStartVertex, gbc_comboStartVertex);
		
		JLabel lblEndVertex = new JLabel("Wierzcho\u0142ek ko\u0144cowy:");
		GridBagConstraints gbc_lblEndVertex = new GridBagConstraints();
		gbc_lblEndVertex.insets = new Insets(0, 0, 5, 0);
		gbc_lblEndVertex.gridx = 1;
		gbc_lblEndVertex.gridy = 2;
		menuPanel.add(lblEndVertex, gbc_lblEndVertex);
		
		comboEndVertex = new JComboBox();
		GridBagConstraints gbc_comboEndVertex = new GridBagConstraints();
		gbc_comboEndVertex.insets = new Insets(0, 0, 5, 0);
		gbc_comboEndVertex.anchor = GridBagConstraints.NORTH;
		gbc_comboEndVertex.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboEndVertex.gridx = 1;
		gbc_comboEndVertex.gridy = 3;
		menuPanel.add(comboEndVertex, gbc_comboEndVertex);
		
		JButton btnStartSimulation = new JButton("Rozpocznij symulacj\u0119");
		btnStartSimulation.setAction(actionStartSimulation);
		GridBagConstraints gbc_btnStartSimulation = new GridBagConstraints();
		gbc_btnStartSimulation.gridwidth = 2;
		gbc_btnStartSimulation.insets = new Insets(0, 0, 0, 5);
		gbc_btnStartSimulation.gridx = 0;
		gbc_btnStartSimulation.gridy = 4;
		menuPanel.add(btnStartSimulation, gbc_btnStartSimulation);
		
//		JLabel lblTabelaKrawdzi = new JLabel("Tabela kraw\u0119dzi:");
//		GridBagConstraints gbc_lblTabelaKrawdzi = new GridBagConstraints();
//		gbc_lblTabelaKrawdzi.anchor = GridBagConstraints.WEST;
//		gbc_lblTabelaKrawdzi.insets = new Insets(0, 0, 0, 5);
//		gbc_lblTabelaKrawdzi.gridx = 0;
//		gbc_lblTabelaKrawdzi.gridy = 0;
//		tablePanel.add(lblTabelaKrawdzi, gbc_lblTabelaKrawdzi);
//		
//		edgeTable = new JTable(new TableModel(30));
//		JScrollPane scrollTable = new JScrollPane(edgeTable);
//		GridBagConstraints gbc_scrollTable = new GridBagConstraints();
//		gbc_scrollTable.fill = GridBagConstraints.BOTH;
//		gbc_scrollTable.gridx = 0;
//		gbc_scrollTable.gridy = 1;
//		tablePanel.add(scrollTable, gbc_scrollTable);
//		scrollTable.setPreferredSize(new Dimension(10, 20));

		DrawPanel drawPanel = new DrawPanel();
		GridBagConstraints gbc_drawPanel = new GridBagConstraints();
		gbc_drawPanel.insets = new Insets(0, 0, 5, 0);
		gbc_drawPanel.fill = GridBagConstraints.BOTH;
		gbc_drawPanel.gridx = 1;
		gbc_drawPanel.gridy = 0;
		JScrollPane scrollDrawPanel = new JScrollPane(drawPanel);
		frmDjikstraAlgorithmDemo.getContentPane().add(scrollDrawPanel, gbc_drawPanel);

		JPanel footerPanel = new JPanel();
		GridBagConstraints gbc_footerPanel = new GridBagConstraints();
		gbc_footerPanel.gridwidth = 2;
		gbc_footerPanel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_footerPanel.gridx = 0;
		gbc_footerPanel.gridy = 2;
		frmDjikstraAlgorithmDemo.getContentPane().add(footerPanel, gbc_footerPanel);

		JLabel lblFooter = new JLabel("Artur Kapu\u015Bniak, Krzysztof P\u0119zio\u0142 - 2017");
		footerPanel.add(lblFooter);

		JMenuBar menuBar = new JMenuBar();
		frmDjikstraAlgorithmDemo.setJMenuBar(menuBar);
		
		JMenu mnPlik = new JMenu("Plik");
		menuBar.add(mnPlik);
		
		JMenuItem mntmWczytajGraf = new JMenuItem("Wczytaj graf");
		mnPlik.add(mntmWczytajGraf);
		
		JMenuItem mntmZapiszGraf = new JMenuItem("Zapisz graf");
		mnPlik.add(mntmZapiszGraf);
	}

	private class TableChangeListener implements PropertyChangeListener {
		JComboBox comboBox;
		TableModel tableModel;
		public TableChangeListener(JComboBox comboBox, TableModel tableModel){
			this.tableModel = tableModel;
			this.comboBox = comboBox;
		}
		
		public void setCombo(JComboBox comboBox){
			this.comboBox = comboBox;
		}
		
		public JComboBox getCombo(){
			return this.comboBox;
		}
		
		public void setTableModel(TableModel tableModel){
			this.tableModel =  tableModel;
		}
		
		public void propertyChange(PropertyChangeEvent evt) {
			setDistinctVerticles();
		}
		
		public void setDistinctVerticles(){
			for(int row =0; row<tableModel.getRowCount();row++){
				//if(comboBox.getModel().getElementAt(index))
			}
		}
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
