package user_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JApplet;

import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;

import Djikstra.Edge;
import Djikstra.Graph;
import Djikstra.Vertex;

public class DrawPanel extends JApplet {

	private static final long serialVersionUID = 3256444702936019250L;
	private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
	private static final Dimension DEFAULT_SIZE = new Dimension(600, 400);

	private JGraphModelAdapter<String, DefaultEdge> jgAdapter;

	private int paddingX = 15;
	private int paddingY = 15;

	public int getPaddingX() {
		return paddingX;
	}

	public void setPaddingX(int paddingX) {
		this.paddingX = paddingX;
	}

	public int getPaddingY() {
		return paddingY;
	}

	public void setPaddingY(int paddingY) {
		this.paddingY = paddingY;
	}

	private JGraphModelAdapter m_jgAdapter;


	public void init(LinkedList<Vertex> path, Graph graph, int numOfVertex) {
		ListenableGraph g = new ListenableUndirectedGraph(DefaultEdge.class);

		jgAdapter = new JGraphModelAdapter<>(g);
		
		JGraph jgraph = new JGraph(jgAdapter);

		adjustDisplaySettings(jgraph);
		getContentPane().add(jgraph);
		resize(DEFAULT_SIZE);

        for (Vertex vertex : graph.getVertexes()) {
        	g.addVertex(vertex.toString());
		}
        
        for (Edge edge : graph.getEdges()) {
        	String[] edges = new String[2];
        	edges = edge.toString().split(" ");
        	g.addEdge(edges[0], edges[1]);
		}

        int a = getWidth() / 2;
        int b = 50 + getHeight() / 2;
        
        for (int i = 0; i < numOfVertex; i++) {
            double t = 2 * Math.PI * i / numOfVertex;
            int x = (int) Math.round(a + 200 * Math.cos(t));
            int y = (int) Math.round(b + 200 * Math.sin(t));
            positionVertexAt("W" + i, x, y, path);
        }
	}

	private void adjustDisplaySettings(JGraph jg) {
		jg.setPreferredSize(DEFAULT_SIZE);

		Color c = DEFAULT_BG_COLOR;
		String colorStr = null;

		try {
			colorStr = "#FAFBFF";
			
		} catch (Exception e) {
		}

		if (colorStr != null) {
			c = Color.decode(colorStr);
		}

		jg.setBackground(c);
	}

	private void positionVertexAt(Object vertex, int x, int y, LinkedList<Vertex> path) {
		DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
		
		AttributeMap attr = cell.getAttributes();
		Rectangle2D bounds = GraphConstants.getBounds(attr);

		Rectangle2D newBounds = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());

	
		GraphConstants.setBounds(attr, newBounds);
		
        for (Vertex vrtx : path) {
        	if(vrtx.toString().equals(vertex.toString())){
        		GraphConstants.setGradientColor(attr, Color.decode("#FF0000"));
        	}
		}
        
		AttributeMap cellAttr = new AttributeMap();
		cellAttr.put(cell, attr);
		jgAdapter.edit(cellAttr, null, null, null);
	}
}
