package user_interface;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javax.swing.JPanel;

import Djikstra.Edge;
import Djikstra.Graph;
import Djikstra.Vertex;

public class DrawPanel extends JPanel {
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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void drawNode(Vertex vertex,int x,int y){
		Graphics2D g2d = (Graphics2D) this.getGraphics();
	 	g2d.drawOval(x, y, 30, 30);
	 	g2d.setFont(new Font("Arial", Font.BOLD, 12));
	 	g2d.drawString(vertex.getName(), x+7, y+20);
	}
}
