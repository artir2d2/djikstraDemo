package user_interface;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JPanel;

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
}
