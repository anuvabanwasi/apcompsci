/*
 * Created on Dec 1, 2004
 * Last update: June 24, 2010
 */

import java.awt.Color;
import java.awt.Graphics;
public class Cell {
	private int myX, myY; // x,y position on grid
	private boolean myAlive; // alive (true) or dead (false)
	private int myNeighbors; // count of neighbors with respect to x,y
	private boolean myAliveNextTurn; // Used for state in next iteration
	private Color myColor; // Based on alive/dead rules
	private final Color DEFAULT_ALIVE = Color.ORANGE;
	private final Color DEFAULT_DEAD = Color.GRAY;
	public static final int ROWS = 60;
	public static final int COLS = 80;

	public Cell(int x, int y) {
		this(x, y, false, Color.GRAY);
	}

	public Cell(int row, int col, boolean alive, Color color) {
		myAlive = alive;
		myColor = color;
		myX = col;
		myY = row;
	}

	public boolean getAlive() {
		return myAlive;
	}

	public int getX() {
		return myX;
	}

	public int getY() {
		return myY;
	}

	public Color getColor() {
		return myColor;
	}

	public void setAlive(boolean alive) {
		if (alive) {
			setAlive(true, DEFAULT_ALIVE);
		} else {
			setAlive(false, DEFAULT_DEAD);
		}
	}

	public void setAlive(boolean alive, Color color) {
		myColor = color;
		myAlive = alive;
	}

	public void setAliveNextTurn(boolean alive) {
		myAliveNextTurn = alive;
	}

	public boolean getAliveNextTurn() {
		return myAliveNextTurn;
	}

	public void setColor(Color color) {
		myColor = color;
	}

	public int getNeighbors() {
		return myNeighbors;
	}
	
	public Cell[] getNeighbors(Cell[][]cell){
		Cell[] neighborCells = new Cell[8];
		
		int col = getY();
		int row = getX();
		
		if (col > 0)
			neighborCells[0] = cell[col-1][row];
		if (col > 0 && row > 0)
			neighborCells[1] = cell[col-1][row-1];
		if (row > 0 && col < ROWS-1)
			neighborCells[2] = cell[col+1][row-1];
		if (row > 0)
			neighborCells[3] = cell[col][row-1];
		if (col > 0 && row < COLS-1)
			neighborCells[4] = cell[col-1][row+1];
		if(col < ROWS-1 && row < COLS-1)
			neighborCells[5] = cell[col+1][row+1];
		if (col < ROWS-1)
			neighborCells[6] = cell[col+1][row];
		if (row < COLS-1)
			neighborCells[7] =cell[col][row+1];
		
		return neighborCells;
		
	}
	
	
	public void calcNeighbors(Cell[][] cell) {
		myNeighbors = 0;
		int col = getY();
		int row = getX();
		
		if (col > 0)
			addNeighbor(cell[col-1][row] );
		if (col > 0 && row > 0)
			addNeighbor(cell[col-1][row-1] );
		if (row > 0 && col < ROWS-1)
			addNeighbor(cell[col+1][row-1] );
		if (row > 0)
			addNeighbor(cell[col][row-1] );
		if (col > 0 && row < COLS-1)
			addNeighbor(cell[col-1][row+1] );
		if(col < ROWS-1 && row < COLS-1)
			addNeighbor(cell[col+1][row+1] );
		if (col < ROWS-1)
			addNeighbor(cell[col+1][row] );
		if (row < COLS-1)
			addNeighbor(cell[col][row+1]);
		
	}
	
	public Cell[] getWrapAroundNeighbors(Cell[][]grid){
		Cell[] neighborCells = new Cell[8];
		
		int col = getY();
		int row = getX();
		
		try {
			neighborCells[0] = grid[(col-1+ROWS)%ROWS][(row-1+COLS)%COLS];
			neighborCells[1] = grid[(col-1+ROWS)%ROWS][row];
			neighborCells[2] = grid[(col-1+ROWS)%ROWS][(row+1+COLS)%COLS];
			
			neighborCells[3] = grid[col][(row-1+COLS)%COLS];
			neighborCells[4] = grid[col][(row+1+COLS)%COLS];
			
			neighborCells[5] = grid[(col+1+ROWS)%ROWS][(row-1+COLS)%COLS];
			neighborCells[6] = grid[(col+1+ROWS)%ROWS][row];
			neighborCells[7] = grid[(col+1+ROWS)%ROWS][ (row+1+COLS)%COLS];
		}
		catch(Exception e){
			System.out.println("\n\nerror - please wrap");
		}
	
		return neighborCells;
		
	}
	
	public void calcWrapAroundNeighbors(Cell[][] cell) {
		myNeighbors = 0;
		int col = getY();
		int row = getX();
		
		try {
			
			addNeighbor(cell[(col-1+ROWS)%ROWS][(row-1+COLS)%COLS]);
			addNeighbor(cell[(col-1+ROWS)%ROWS][row]);
			addNeighbor(cell[(col-1+ROWS)%ROWS][(row+1+COLS)%COLS]);
			
			addNeighbor(cell[col][(row-1+COLS)%COLS]);
			addNeighbor(cell[col][(row+1+COLS)%COLS]);
			
			addNeighbor(cell[(col+1+ROWS)%ROWS][(row-1+COLS)%COLS]);
			addNeighbor(cell[(col+1+ROWS)%ROWS][row]);
			addNeighbor(cell[(col+1+ROWS)%ROWS][ (row+1+COLS)%COLS]);
			
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.println("\n\nerror - please wrap");
		}
		
	}
	
	public void addNeighbor(Cell cell){
		if (cell.getAlive())
			myNeighbors++; 
	}

	public void draw(int x_offset, int y_offset, int width, int height,
			Graphics g) {
		// I leave this understanding to the reader
		int xleft = x_offset + 1 + (myX * (width + 1));
		int xright = x_offset + width + (myX * (width + 1));
		int ytop = y_offset + 1 + (myY * (height + 1));
		int ybottom = y_offset + height + (myY * (height + 1));
		Color temp = g.getColor();

		g.setColor(myColor);
		g.fillRect(xleft, ytop, width, height);
	}
}