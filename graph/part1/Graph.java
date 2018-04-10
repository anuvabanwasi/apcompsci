
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * Draw a directed graph with nodes and edges given the coordinates of each node
 * Reference -  https://www.math.uh.edu/~jiwenhe/Math1432/lectures/lecture12_handout.pdf
 *  			http://www1.cs.columbia.edu/~bert/courses/3137/hw3_files/GraphDraw.java
 *  			https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java
 * @author Anuva
 *
 */
public class Graph extends JFrame {
	
	//needed since this class extends javax.swing.JFrame
	private static final long serialVersionUID = -3339127201103743348L; 
	
	private final int width = 50;
	private final int height = 50;
	
	ArrayList<Node> nodes;
	ArrayList<Edge> edges;

	public Graph(String str) { // Constructor
		this.setTitle(str);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}

	/**
	 * Add a node with identifier id at location (x, y)
	 * @param id unique identifier of the node
	 * @param x x-coordinate of node
	 * @param y y-coordinate of node
	 */
	public void addNode(int id, int x, int y) {
		nodes.add(new Node(id, x, y));
	}

	/**
	 * Add an edge between nodes i and j
	 * @param i source node
	 * @param j destination node
	 * @param weight assigned to edge connecting source and destination
	 */
	public void addEdge(int i, int j, int weight) {
		edges.add(new Edge(nodes.get(i), nodes.get(j), weight));
	}

	/**
	 * Paint the frame to draw the graph
	 */
	public void paint(Graphics g) { 
		FontMetrics f = g.getFontMetrics();

		g.drawString("Directed Graph", getWidth()/2 - 5- f.stringWidth("Directed Graph") / 2, getHeight()/2 - 40 + f.getHeight() / 2);
		
		// Iterate through list of nodes and draw each node
		for (Node n : nodes) {
			drawNode(g, n);
		}
		
		// Iterate through list of edges and draw each edge
		for (Edge e : edges) {
			g.setColor(Color.lightGray);

			int x1 = nodes.get(e.getSource().identifier).x;
			int y1 = nodes.get(e.getSource().identifier).y;
			
			int x2 = nodes.get(e.getDestination().identifier).x;
			int y2 = nodes.get(e.getDestination().identifier).y;
			
			drawEdge(g, x1, y1, x2, y2, 15, 2, e.getWeight());
		}
	}

	/**
	 * Draw a node with given (x,y) coordinates
	 * @param g
	 * @param node
	 * Reference - http://www1.cs.columbia.edu/~bert/courses/3137/hw3_files/GraphDraw.java
	 */
	private void drawNode(Graphics g,  Node node) {
		
		FontMetrics f = g.getFontMetrics();
		int divisionFactor = 2;
		
		String name = String.valueOf(node.identifier);
		int stringWidth = f.stringWidth(name);
		
		int nodeWidth = Math.max(width, stringWidth + width / divisionFactor);
		int nodeHeight = Math.max(height, f.getHeight());

		int xAdjustment = nodeWidth / divisionFactor;
		int yAdjustment = nodeHeight / divisionFactor;
		
		// Draw filled node
		g.setColor(Color.white);
		g.fillOval(node.x - xAdjustment, node.y - yAdjustment, nodeWidth, nodeHeight);
		
		// Draw boundary of node
		g.setColor(Color.red);
		g.drawOval(node.x - xAdjustment, node.y - yAdjustment, nodeWidth, nodeHeight);
		
		// Draw label of node
		g.drawString(name, node.x - 5 - stringWidth / divisionFactor, node.y - 40 + f.getHeight() / divisionFactor);
	}
	
	/**
	 * Draw an arrow line between two points.
	 * @param g the graphics component.
	 * @param x1 x-position of first point.
	 * @param y1 y-position of first point.
	 * @param x2 x-position of second point.
	 * @param y2 y-position of second point.
	 * @param depth  the width of the arrow.
	 * @param height  the height of the arrow.
	 * @param thickness thickness of line
	 * https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java
	 */
	private void drawEdge(Graphics g, int x1, int y1, int x2, int y2, int depth, int height, int weight) {
	    int deltax = x2 - x1;
	    int deltay = y2 - y1;
	    double distance = Math.sqrt(deltax*deltax + deltay*deltay);
	    
	    double normx = deltax / distance;
	    double normy = deltay / distance;
	    
	    double xvertex1 = distance - depth;
	    double xvertex2 = xvertex1;
	    double yvertex1 = height;
	    double yvertex2 = -height;

	    double x = xvertex1*normx - yvertex1*normy + x1;
	    yvertex1 = xvertex1*normy + yvertex1*normx + y1;
	    xvertex1 = x;

	    x = xvertex2*normx - yvertex2*normy + x1;
	    yvertex2 = xvertex2*normy + yvertex2*normx + y1;
	    xvertex2 = x;

	    int[] xpoints = {x2, (int) xvertex1, (int) xvertex2};	// xcoords of triangle
	    int[] ypoints = {y2, (int) yvertex1, (int) yvertex2};	// ycoords of triangle

	    Graphics2D g2 = (Graphics2D) g;
        //g2.setStroke(new BasicStroke(thickness));
        g2.drawLine(x1, y1, x2, y2);  
	    
	    g2.setColor(Color.darkGray);
	    g2.fillPolygon(xpoints, ypoints, 3);					// Draw triangle at end point of line
	    
	    //int x_mid = (x1+x2)/2;
	    //int y_mid = (y1+y2)/2;
	    
	    // Draw weight of edge
	 	//g.drawString(String.valueOf(weight), x_mid, y_mid);
	    
	}
}
