package user_interface;

import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableCellRenderer;

import Djikstra.Algorithm;
import Djikstra.Edge;
import Djikstra.Graph;
import Djikstra.Vertex;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

public class MainWindow {
	private static final int HEADER_HEIGHT = 25;
	private JFrame frmDjikstraAlgorithmDemo;
	private JTable edgeTable;
	private final Action actionAddRow = new SwingAction();
	private final Action actionStartSimulation = new StartSimulationAction();
	JComboBox<String> comboStartVertex;
	JComboBox<String> comboEndVertex;
	private JTextField numberOfVerticlesTxt;
	JPanel tablePanel;
	DrawPanel drawPanel;
	private int numberOfVerticles;
	private final Action actionRandomData = new ActionRandomData();
	private JTextField minRandomTxt;
	private JTextField maxRandomTxt;
	private JCheckBox randomNoEdgeCB;

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
		gridBagLayout.columnWidths = new int[] { 200, 0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		frmDjikstraAlgorithmDemo.getContentPane().setLayout(gridBagLayout);

		JPanel menuPanel = new JPanel();
		GridBagConstraints gbc_menuPanel = new GridBagConstraints();
		gbc_menuPanel.insets = new Insets(5, 5, 5, 5);
		gbc_menuPanel.fill = GridBagConstraints.BOTH;
		gbc_menuPanel.gridx = 0;
		gbc_menuPanel.gridy = 0;

		GridBagLayout gbl_menuPanel = new GridBagLayout();
		gbl_menuPanel.columnWidths = new int[] { 0, 0 };
		gbl_menuPanel.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_menuPanel.columnWeights = new double[] { 1.0, 1.0 };
		gbl_menuPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		menuPanel.setLayout(gbl_menuPanel);
		frmDjikstraAlgorithmDemo.getContentPane().add(menuPanel, gbc_menuPanel);

		JLabel lblIloKrawdzi = new JLabel("Ilo\u015B\u0107 Kraw\u0119dzi:");
		GridBagConstraints gbc_lblIloKrawdzi = new GridBagConstraints();
		gbc_lblIloKrawdzi.anchor = GridBagConstraints.EAST;
		gbc_lblIloKrawdzi.insets = new Insets(0, 0, 5, 5);
		gbc_lblIloKrawdzi.gridx = 0;
		gbc_lblIloKrawdzi.gridy = 0;
		menuPanel.add(lblIloKrawdzi, gbc_lblIloKrawdzi);

		numberOfVerticlesTxt = new JTextField();
		numberOfVerticlesTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				numberOfVerticles = Integer.parseInt(numberOfVerticlesTxt.getText());
				edgeTable.setModel(new TableModel(numberOfVerticles));
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(JLabel.CENTER);
				centerRenderer.setVerticalAlignment(JLabel.CENTER);
				centerRenderer.setBackground(edgeTable.getTableHeader().getBackground());
				edgeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

				for (int i = 0; i < edgeTable.getColumnModel().getColumnCount(); i++) {
					edgeTable.getColumnModel().getColumn(i).setMaxWidth(25);
				}
				edgeTable.setRowHeight(25);
				edgeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

				DefaultComboBoxModel comboStartVertexModel = (DefaultComboBoxModel) comboStartVertex.getModel();
				DefaultComboBoxModel comboEndVertexModel = (DefaultComboBoxModel) comboEndVertex.getModel();
				comboStartVertexModel.removeAllElements();
				comboEndVertexModel.removeAllElements();
				for (int i = 0; i < Integer.parseInt(numberOfVerticlesTxt.getText()); i++) {
					comboStartVertexModel.addElement("Wierzcho³ek " + (i + 1));
					comboEndVertexModel.addElement("Wierzcho³ek " + (i + 1));
				}

			}
		});
		GridBagConstraints gbc_numberOfVerticlesTxt = new GridBagConstraints();
		gbc_numberOfVerticlesTxt.insets = new Insets(0, 0, 5, 0);
		gbc_numberOfVerticlesTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_numberOfVerticlesTxt.gridx = 1;
		gbc_numberOfVerticlesTxt.gridy = 0;
		menuPanel.add(numberOfVerticlesTxt, gbc_numberOfVerticlesTxt);
		numberOfVerticlesTxt.setColumns(10);

		JLabel lblStartVertex = new JLabel("Wierzcho\u0142ek pocz\u0105tkowy:");
		GridBagConstraints gbc_lblStartVertex = new GridBagConstraints();
		gbc_lblStartVertex.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartVertex.anchor = GridBagConstraints.NORTH;
		gbc_lblStartVertex.gridx = 0;
		gbc_lblStartVertex.gridy = 1;
		menuPanel.add(lblStartVertex, gbc_lblStartVertex);

		JLabel lblEndVertex = new JLabel("Wierzcho\u0142ek ko\u0144cowy:");
		GridBagConstraints gbc_lblEndVertex = new GridBagConstraints();
		gbc_lblEndVertex.insets = new Insets(0, 0, 5, 0);
		gbc_lblEndVertex.gridx = 1;
		gbc_lblEndVertex.gridy = 1;
		menuPanel.add(lblEndVertex, gbc_lblEndVertex);

		comboStartVertex = new JComboBox<String>();
		comboStartVertex.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboStartVertex.getItemCount() > 1
						&& comboStartVertex.getSelectedIndex() == comboEndVertex.getSelectedIndex()) {
					if (comboStartVertex.getSelectedIndex() == comboStartVertex.getItemCount() - 1) {
						comboStartVertex.setSelectedIndex(comboStartVertex.getSelectedIndex() - 1);
					} else {
						comboStartVertex.setSelectedIndex(comboStartVertex.getSelectedIndex() + 1);
					}
				}
			}
		});
		GridBagConstraints gbc_comboStartVertex = new GridBagConstraints();
		gbc_comboStartVertex.insets = new Insets(0, 0, 5, 5);
		gbc_comboStartVertex.anchor = GridBagConstraints.NORTH;
		gbc_comboStartVertex.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboStartVertex.gridx = 0;
		gbc_comboStartVertex.gridy = 2;
		menuPanel.add(comboStartVertex, gbc_comboStartVertex);

		comboEndVertex = new JComboBox<String>();
		comboEndVertex.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboEndVertex.getItemCount() > 1
						&& comboEndVertex.getSelectedIndex() == comboStartVertex.getSelectedIndex()) {
					if (comboEndVertex.getSelectedIndex() == comboEndVertex.getItemCount() - 1) {
						comboEndVertex.setSelectedIndex(comboEndVertex.getSelectedIndex() - 1);
					} else {
						comboEndVertex.setSelectedIndex(comboEndVertex.getSelectedIndex() + 1);
					}
				}
			}
		});
		GridBagConstraints gbc_comboEndVertex = new GridBagConstraints();
		gbc_comboEndVertex.insets = new Insets(0, 0, 5, 0);
		gbc_comboEndVertex.anchor = GridBagConstraints.NORTH;
		gbc_comboEndVertex.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboEndVertex.gridx = 1;
		gbc_comboEndVertex.gridy = 2;
		menuPanel.add(comboEndVertex, gbc_comboEndVertex);

		tablePanel = new JPanel();
		GridBagConstraints gbc_tablePanel = new GridBagConstraints();
		gbc_tablePanel.gridwidth = 2;
		gbc_tablePanel.insets = new Insets(0, 0, 5, 0);
		gbc_tablePanel.fill = GridBagConstraints.BOTH;
		gbc_tablePanel.gridx = 0;
		gbc_tablePanel.gridy = 3;
		menuPanel.add(tablePanel, gbc_tablePanel);
		GridBagLayout gbl_tablePanel = new GridBagLayout();
		gbl_tablePanel.columnWidths = new int[] { 0, 0 };
		gbl_tablePanel.rowHeights = new int[] { 20, 0, 0 };
		gbl_tablePanel.columnWeights = new double[] { 1.0, 1.0 };
		gbl_tablePanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		tablePanel.setLayout(gbl_tablePanel);

		JLabel lblEdgeTable = new JLabel("Tabela kraw\u0119dzi:");
		GridBagConstraints gbc_lblEdgeTable = new GridBagConstraints();
		gbc_lblEdgeTable.gridwidth = 2;
		gbc_lblEdgeTable.insets = new Insets(0, 0, 5, 0);
		gbc_lblEdgeTable.gridx = 0;
		gbc_lblEdgeTable.gridy = 0;
		tablePanel.add(lblEdgeTable, gbc_lblEdgeTable);

		edgeTable = new JTable(new TableModel(0));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		centerRenderer.setVerticalAlignment(JLabel.CENTER);
		centerRenderer.setBackground(edgeTable.getTableHeader().getBackground());
		edgeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		for (int i = 0; i < edgeTable.getColumnModel().getColumnCount(); i++) {
			edgeTable.getColumnModel().getColumn(i).setMaxWidth(25);
		}
		edgeTable.setRowHeight(25);
		edgeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		GridBagConstraints gbc_edgeTable = new GridBagConstraints();
		gbc_edgeTable.gridwidth = 2;
		gbc_edgeTable.insets = new Insets(5, 5, 0, 0);
		gbc_edgeTable.fill = GridBagConstraints.BOTH;
		gbc_edgeTable.gridx = 0;
		gbc_edgeTable.gridy = 1;

		JScrollPane scrollEdgeTable = new JScrollPane(edgeTable);
		scrollEdgeTable.setColumnHeader(new JViewport() {
			@Override
			public Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				d.height = HEADER_HEIGHT;
				return d;
			}
		});

		tablePanel.add(scrollEdgeTable, gbc_edgeTable);

		JPanel randomDataPanel = new JPanel();
		GridBagConstraints gbc_randomDataPanel = new GridBagConstraints();
		gbc_randomDataPanel.gridwidth = 2;
		gbc_randomDataPanel.insets = new Insets(0, 0, 5, 0);
		gbc_randomDataPanel.fill = GridBagConstraints.BOTH;
		gbc_randomDataPanel.gridx = 0;
		gbc_randomDataPanel.gridy = 7;
		menuPanel.add(randomDataPanel, gbc_randomDataPanel);
		GridBagLayout gbl_randomDataPanel = new GridBagLayout();
		gbl_randomDataPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_randomDataPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_randomDataPanel.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_randomDataPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		randomDataPanel.setLayout(gbl_randomDataPanel);

		JLabel lblMinRandom = new JLabel("Losuj od:");
		GridBagConstraints gbc_lblMinRandom = new GridBagConstraints();
		gbc_lblMinRandom.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinRandom.gridx = 0;
		gbc_lblMinRandom.gridy = 0;
		randomDataPanel.add(lblMinRandom, gbc_lblMinRandom);

		JLabel lblMaxRandom = new JLabel("Do:");
		GridBagConstraints gbc_lblMaxRandom = new GridBagConstraints();
		gbc_lblMaxRandom.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxRandom.gridx = 1;
		gbc_lblMaxRandom.gridy = 0;
		randomDataPanel.add(lblMaxRandom, gbc_lblMaxRandom);

		minRandomTxt = new JTextField();
		minRandomTxt.setText("0");
		minRandomTxt.setColumns(3);
		GridBagConstraints gbc_minRandomTxt = new GridBagConstraints();
		gbc_minRandomTxt.insets = new Insets(0, 0, 5, 5);
		gbc_minRandomTxt.gridx = 0;
		gbc_minRandomTxt.gridy = 1;
		randomDataPanel.add(minRandomTxt, gbc_minRandomTxt);

		maxRandomTxt = new JTextField();
		maxRandomTxt.setText("10");
		maxRandomTxt.setColumns(3);
		GridBagConstraints gbc_maxRandomTxt = new GridBagConstraints();
		gbc_maxRandomTxt.insets = new Insets(0, 0, 5, 5);
		gbc_maxRandomTxt.gridx = 1;
		gbc_maxRandomTxt.gridy = 1;
		randomDataPanel.add(maxRandomTxt, gbc_maxRandomTxt);

		randomNoEdgeCB = new JCheckBox("Bez po\u0142\u0105cze\u0144");
		randomNoEdgeCB.setToolTipText(
				"Gdy pole zaznaczone, nie wszystkie wierzcho\u0142ki b\u0119d\u0105 po\u0142\u0105czone ze sob\u0105");
		GridBagConstraints gbc_randomNoEdgeCB = new GridBagConstraints();
		gbc_randomNoEdgeCB.insets = new Insets(0, 0, 5, 0);
		gbc_randomNoEdgeCB.gridx = 2;
		gbc_randomNoEdgeCB.gridy = 1;
		randomDataPanel.add(randomNoEdgeCB, gbc_randomNoEdgeCB);

		JButton btnRandomData = new JButton("Losuj dane");
		btnRandomData.setAction(actionRandomData);
		btnRandomData.setText("Losuj dane");
		GridBagConstraints gbc_btnRandomData = new GridBagConstraints();
		gbc_btnRandomData.insets = new Insets(0, 0, 0, 5);
		gbc_btnRandomData.gridwidth = 2;
		gbc_btnRandomData.gridx = 0;
		gbc_btnRandomData.gridy = 2;
		randomDataPanel.add(btnRandomData, gbc_btnRandomData);

		JButton btnStartSimulation = new JButton("Rozpocznij symulacj\u0119");
		btnStartSimulation.setAction(actionStartSimulation);
		btnStartSimulation.setText("Rozpocznij symulacj\u0119");
		GridBagConstraints gbc_btnStartSimulation = new GridBagConstraints();
		gbc_btnStartSimulation.gridx = 1;
		gbc_btnStartSimulation.gridy = 8;
		menuPanel.add(btnStartSimulation, gbc_btnStartSimulation);

		drawPanel = new DrawPanel();
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

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private class StartSimulationAction extends AbstractAction {
		public StartSimulationAction() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			/*
			 * SEKCJA DO WYEKSPORTOWANIA DO ZEWNETRZNEJ METODY/KLASY
			 */
			// initialize graph data with values from table
			Graph graph = Algorithm.initAlgorithm(numberOfVerticles, edgeTable);
			// create an algorithm object
			Algorithm algorithm = new Algorithm(graph);
			System.out.println("start " + comboStartVertex.getSelectedIndex());
			System.out.println("end " + comboEndVertex.getSelectedIndex());
			// execute algorithm with the start node
			algorithm.execute(algorithm.nodes.get(comboStartVertex.getSelectedIndex()));
			// get path from start node to end node
			/*
			 * TUTAJ MASZ LISTE WIERZCHOLKOW KTORE SA NAJKROTSZA SCIEZKA OD
			 * WIERZCHOLKA POCZATKOWEGO DO KONCOWEGO
			 */
			LinkedList<Vertex> path = algorithm.getPath(algorithm.nodes.get(comboEndVertex.getSelectedIndex()));
			try {
				for (Vertex vertex : path) {
					System.out.println(vertex);
					drawPanel.drawNode(path.get(0), 20, 20);
				}
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(frmDjikstraAlgorithmDemo, "Wierzcho³ek koñcowy nieosi¹galny!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			}

			// TU GDYBYS CHCIAL RYSOWAC TO SAM - PRZYKLAD RYSOWANIA WIERZCHOLKA
			// NA DRAWPANELU

		}
	}

	private class ActionRandomData extends AbstractAction {
		public ActionRandomData() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			TableModel.losujDane(Integer.parseInt(minRandomTxt.getText()), Integer.parseInt(maxRandomTxt.getText()),
					randomNoEdgeCB.isSelected(), numberOfVerticles, edgeTable);
		}
	}
}
