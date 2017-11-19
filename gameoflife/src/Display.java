/*
 * Created on May 24, 2004
 *
 * Latest update on April 21, 2011
 */
 
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

// Note that the JComponent is set up to listen for mouse clicks
// and mouse movement.  To achieve this, the MouseListener and
// MousMotionListener interfaces are implemented and there is additional
// code in init() to attach those interfaces to the JComponent.


public class Display extends JComponent implements MouseListener, MouseMotionListener {
	public static final int ROWS = 60;
	public static final int COLS = 80;
	public static Cell[][] cell = new Cell[ROWS][COLS];
	private final int X_GRID_OFFSET = 25; // 25 pixels from left
	private final int Y_GRID_OFFSET = 40; // 40 pixels from top
	private final int CELL_WIDTH = 8;
	private final int CELL_HEIGHT = 8;

	// Note that a final field can be initialized in constructor
	private final int DISPLAY_WIDTH;   
	private final int DISPLAY_HEIGHT;
	private StartButton startStop;
	private NextButton nextButton;
	private ClearButton clearButton;
	private QuitButton quitButton;
	private boolean paintloop = false;


	public Display(int width, int height) {
		DISPLAY_WIDTH = width;
		DISPLAY_HEIGHT = height;
		init();
	}


	public void init() {
		setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		initCells(true);

		addMouseListener(this);
		addMouseMotionListener(this);

		// Example of setting up a button.
		// See the StartButton class nested below.
		startStop = new StartButton();
		startStop.setBounds(100, 620, 100, 36);
		add(startStop);
		startStop.setVisible(true);
		
		nextButton = new NextButton();
		nextButton.setBounds(200, 620, 100, 36);
		add(nextButton);
		nextButton.setVisible(true);
		
		clearButton = new ClearButton();
		clearButton.setBounds(300, 620, 100, 36);
		add(clearButton);
		clearButton.setVisible(true);
		
		quitButton = new QuitButton();
		quitButton.setBounds(400, 620, 100, 36);
		add(quitButton);
		quitButton.setVisible(true);
		repaint();
	}


	public void paintComponent(Graphics g) {
		final int TIME_BETWEEN_REPLOTS = 100; // change to your liking

		g.setColor(Color.BLACK);
		drawGrid(g);
		drawCells(g);
		drawButtons();

		if (paintloop) {
			try {
				Thread.sleep(TIME_BETWEEN_REPLOTS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nextWrapAroundGeneration();
			//nextGeneration();
			repaint();
		}
	}


	public void initCells(boolean showInitial) {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				cell[row][col] = new Cell(row, col);
			}
		}
		
		if (showInitial) {
			cell[36][22].setAlive(true); // sample use of cell mutator method
			cell[36][23].setAlive(true); // sample use of cell mutator method
			cell[36][24].setAlive(true); // sample use of cell mutator method
		}
	}


	public void togglePaintLoop() {
		paintloop = !paintloop;
	}


	public void setPaintLoop(boolean value) {
		paintloop = value;
	}


	void drawGrid(Graphics g) {
		for (int row = 0; row <= ROWS; row++) {
			g.drawLine(X_GRID_OFFSET,
					Y_GRID_OFFSET + (row * (CELL_HEIGHT + 1)), X_GRID_OFFSET
					+ COLS * (CELL_WIDTH + 1), Y_GRID_OFFSET
					+ (row * (CELL_HEIGHT + 1)));
		}
		for (int col = 0; col <= COLS; col++) {
			g.drawLine(X_GRID_OFFSET + (col * (CELL_WIDTH + 1)), Y_GRID_OFFSET,
					X_GRID_OFFSET + (col * (CELL_WIDTH + 1)), Y_GRID_OFFSET
					+ ROWS * (CELL_HEIGHT + 1));
		}
	}

	
	void drawCells(Graphics g) {
		// Have each cell draw itself
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				// The cell cannot know for certain the offsets nor the height
				// and width; it has been set up to know its own position, so
				// that need not be passed as an argument to the draw method
				cell[row][col].draw(X_GRID_OFFSET, Y_GRID_OFFSET, CELL_WIDTH,
						CELL_HEIGHT, g);
			}
		}
	}


	private void drawButtons() {
		startStop.repaint();
		nextButton.repaint();
		clearButton.repaint();
	}


	private void nextGeneration() {
		for (int i = 0; i < ROWS ;i++){
			for (int j = 0; j < COLS ; j++){
				if (cell[i][j].getAlive()){
					//step 1
					cell[i][j].calcNeighbors(cell);
					if (cell[i][j].getNeighbors() != 3 && cell[i][j].getNeighbors() != 2)
						cell[i][j].setAliveNextTurn(false);
					else 
						cell[i][j].setAliveNextTurn(true);
					
					//step 2
					Cell[] neighborCells = cell[i][j].getNeighbors(cell);
					for (int k = 0; k < neighborCells.length; k++){
						if (neighborCells[k] != null){
							neighborCells[k].calcNeighbors(cell);
							if ( neighborCells[k].getNeighbors() == 3 )
								neighborCells[k].setAliveNextTurn(true);
						}
					}
					
				}
			}
		}
		for (int i = 0; i < ROWS ;i++){
			for (int j = 0; j < COLS ; j++){
				if (cell[i][j].getAliveNextTurn())
					cell[i][j].setAlive(true);
				else
					cell[i][j].setAlive(false);
			}
		}
		repaint();
	}
	
	private void nextWrapAroundGeneration() {
		for (int i = 0; i < ROWS ;i++){
			for (int j = 0; j < COLS ; j++){
				if (cell[i][j].getAlive()){
					//step 1
					cell[i][j].calcWrapAroundNeighbors(cell);
					if (cell[i][j].getNeighbors() != 3 && cell[i][j].getNeighbors() != 2)
						cell[i][j].setAliveNextTurn(false);
					else 
						cell[i][j].setAliveNextTurn(true);
					
					//step 2
					Cell[] neighborCells = cell[i][j].getWrapAroundNeighbors(cell);
					for (int k = 0; k < neighborCells.length; k++){
						if (neighborCells[k] != null){
							neighborCells[k].calcWrapAroundNeighbors(cell);
							if ( neighborCells[k].getNeighbors() == 3 )
								neighborCells[k].setAliveNextTurn(true);
						}
					}
					
				}
			}
		}
		for (int i = 0; i < ROWS ;i++){
			for (int j = 0; j < COLS ; j++){
				if (cell[i][j].getAliveNextTurn())
					cell[i][j].setAlive(true);
				else
					cell[i][j].setAlive(false);
			}
		}
		repaint();
	}
	
	

	private void clearGrid() {
		for (int i = 0; i < ROWS;i++){
			for (int j = 0; j < COLS; j++){
				cell[i][j].setAlive(false);
			}
		}
		repaint();
		initCells(false);
	}

	
	public void mouseClicked(MouseEvent arg0) {
		
	}


	public void mouseEntered(MouseEvent arg0) {
	
	}

	
	public void mouseExited(MouseEvent arg0) {

	}

	
	public void mousePressed(MouseEvent arg0) {
		int cursor_x = arg0.getX();
		int cursor_y = arg0.getY();
		int actual_x = (cursor_x - X_GRID_OFFSET)/(CELL_WIDTH + 1);
		int actual_y = (cursor_y - Y_GRID_OFFSET)/(CELL_HEIGHT + 1);
		
		if (actual_y >= 0 && actual_y < ROWS && actual_x >= 0 && actual_x < COLS) 
			if (cell[actual_y][actual_x].getAlive())
				cell[actual_y][actual_x].setAlive(false);
			else
				cell[actual_y][actual_x].setAlive(true);
		
		repaint();
	}

	
	public void mouseReleased(MouseEvent arg0) {	
		
	}

	
	public void mouseDragged(MouseEvent arg0) {
		int cursor_x = arg0.getX();
		int cursor_y = arg0.getY();
		int actual_x = (cursor_x - X_GRID_OFFSET)/(CELL_WIDTH + 1);
		int actual_y = (cursor_y - Y_GRID_OFFSET)/(CELL_HEIGHT + 1);
		
		if (actual_y >= 0 && actual_y < ROWS && actual_x >= 0 && actual_x < COLS) 
			cell[actual_y][actual_x].setAlive(true);
		
		repaint();
	}

	
	public void mouseMoved(MouseEvent arg0) {
	
	}
	
	private class StartButton extends JButton implements ActionListener {
		StartButton() {
			super("Start");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			nextGeneration(); // test the start button
			//nextWrapAroundGeneration(); // wrap around
			if (this.getText().equals("Start")) {
				togglePaintLoop();
				setText("Stop");
				nextButton.setEnabled(false);
				clearButton.setEnabled(false);
			} else {
				togglePaintLoop();
				setText("Start");
				nextButton.setEnabled(true);
				clearButton.setEnabled(true);
			}
			repaint();
		}
	}
	
	private class NextButton extends JButton implements ActionListener {
		NextButton() {
			super("Step");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			//nextGeneration(); 
			nextWrapAroundGeneration(); // wrap around
			repaint();
		}
	}
	
	private class ClearButton extends JButton implements ActionListener {
		ClearButton() {
			super("Clear");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			clearGrid();
			repaint();
		}
	}
	
	private class QuitButton extends JButton implements ActionListener {
		QuitButton() {
			super("Quit");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			
			for (Frame frame : Frame.getFrames())
			{
				if (frame.isActive())
				{
					WindowEvent windowClosing = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
					frame.dispatchEvent(windowClosing);
				}
			}
		}
	}
}
