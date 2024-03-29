package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;

	private Timer timer;

	// 1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] cells;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		// 2. Calculate the cell size.
		this.cellSize = w / cpr;
		// 3. Initialize the cell array to the appropriate size.
		cells = new Cell[this.cellsPerRow][this.cellsPerRow];
		// 3. Iterate through the array and initialize each cell.
		// Don't forget to consider the cell's dimensions when
		// passing in the location.
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(i * this.cellSize, j * this.cellSize, this.cellSize);
			}
		}
	}

	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive memeber to true of false
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				Random randy = new Random();
				
				int rand = randy.nextInt(1);
				if (rand == 0) {
					cells[i][j].isAlive = true;
				} 
				if(rand == 1)
				{
					cells[i][j].isAlive = false;
				}
			}
		}
		repaint();
	}

	public void clearCells() {
		// 5. Iterate through the cells and set them all to dead.
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j].isAlive = false; 
			}
		}
		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		// 6. Iterate through the cells and draw them all
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j].draw(g);
			}
		}
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	// advances world one step
	public void step() {
		// 7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for (int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				livingNeighbors[i][j] =  getLivingNeighbors(i, j);
			}
		
		}
		// 8. check if each cell should live or die

		repaint();
	}

	// 9. Complete the method.
	// It returns an int of 8 or less based on how many
	// living neighbors there are of the
	// cell identified by x and y
	
	public int getLivingNeighbors(int x, int y) {
		int neighborCount = 0;
		int iVal = x;
		int jVal = y;
				
		if(iVal+1 < cells.length) {
			if(cells[iVal+1][jVal].isAlive == true) {
				neighborCount++;
			}
		}
		if(iVal+1 < cells.length && jVal+1 < cells[iVal].length) {
			if(cells[iVal+1][jVal+1].isAlive == true) {
				neighborCount++;
			}
		}
		if(jVal+1 < cells[iVal].length) {
			if(cells[iVal][jVal+1].isAlive == true) {
				neighborCount++;
			}
		}
		
		if(iVal-1 >= 0) {
			if(cells[iVal-1][jVal].isAlive == true) {
				neighborCount++;
			}
		}
		if(iVal-1 >= 0 && jVal+1 < cells[iVal].length) {
			if(cells[iVal-1][jVal+1].isAlive == true) {
				neighborCount++;
			}
		}
		
		if(iVal-1 >= 0 && jVal-1 >= 0) {
			if(cells[iVal-1][jVal-1].isAlive == true) {
				neighborCount++;
			}
		}
		if(jVal-1 >= 0) {
			if(cells[iVal][jVal-1].isAlive == true) {
				neighborCount++;
			}
		}
		if(iVal+1 < cells.length && jVal-1 >= 0) {
			if(cells[iVal+1][jVal-1].isAlive == true) {
				neighborCount++;
			}

		}
				
		return neighborCount;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 10. Use e.getX() and e.getY() to determine
		// which cell is clicked. Then toggle
		// the isAlive variable for that cell.
		int posX = e.getX();
		int posY = e.getY();
		int tempX = posX/cellSize;
		int tempY = posY/cellSize;
		cells[tempX][tempY].isAlive =! (cells[tempX][tempY].isAlive);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
