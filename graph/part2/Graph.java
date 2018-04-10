
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

/**
 * Draw a directed graph with nodes and edges given only distances between nodes but NOT coordinates
 * Reference -  https://www.math.uh.edu/~jiwenhe/Math1432/lectures/lecture12_handout.pdf
 *  			http://www1.cs.columbia.edu/~bert/courses/3137/hw3_files/GraphDraw.java
 *  			https://stackoverflow.com/questions/2027613/how-to-draw-a-directed-arrow-line-in-java
 * @author Anuva
 */
public class Graph extends JFrame {

	private static final int origin_x = 250;
	private static final int origin_y = 250;
	//required since this class extends javax.swing.JFrame
	private static final long serialVersionUID = -3339127201103743348L;
	int width = 50;
	int height = 50;
	boolean inverse;

	ArrayList<Node> nodes;
	ArrayList<Edge> edges;
	Map<Integer, List<Edge>> prevEdges;						// track edges which have int i as destination

	public Graph(String str, boolean inverse) { 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(str);
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		prevEdges = new HashMap<Integer, List<Edge>>();
		prevEdges.put(0, new ArrayList<Edge>());
	
		this.inverse = inverse;
	}

	/**
	 * Add a node with identifier id 
	 * @param id unique identifier of the node
	 */
	public void addNode(int id) {
		nodes.add(new Node(id));
	}
	
	/**
	 * Add an edge between nodes i and j
	 * @param i source node
	 * @param j destination node
	 * @param weight assigned to edge connecting source and destination
	 */
	public void addEdge(int i, int j, int weight) {
		// add an edge between nodes i and j
		edges.add(new Edge(nodes.get(i), nodes.get(j), weight));

		// store previous edge to node j
		List<Edge> pe = prevEdges.get(j);
		if (pe == null)
			pe = new ArrayList<Edge>();
		pe.add(new Edge(nodes.get(i), nodes.get(j), weight));
		prevEdges.put(j, pe);
	}

	/**
	 * Paint the frame to draw the graph
	 */
	public void paint(Graphics g) { 
		System.out.println();

		int xStart = origin_x;
		int yStart = origin_y;

		g.drawString("Directed Graph with Weights (Distance between Nodes)", getWidth()/3 , getHeight()/2 );
		
		if (nodes.size() > 0) {
		
			// draw first node
			Node nodeZero = nodes.get(0);							
			drawNode(g, nodeZero, xStart, yStart);

			for (Edge e : edges) {
				System.out.print(e.getSource().identifier + " -> " + e.getDestination().identifier);
				Node src = e.getSource();
				Node dest = e.getDestination();

				int x1 = getXCoordinates(src);
				int y1 = getYCoordinates(src);

				int x2 = getXCoordinates(dest);
				int y2 = getYCoordinates(dest);

				System.out.print(" (" + x1 + ", " + y1 + " ) ");
				System.out.print(" -> ");
				System.out.println(" (" + x2 + ", " + y2 + " ) ");

				int thickness = calculateThickness(e.getWeight(), inverse);

				drawNode(g, e.getDestination(), x2, y2);
				drawEdge(g, x1, y1, x2, y2, 15, thickness, thickness, e.getWeight());
			}
		}
	}

	/**
	 * Calculate y-coordinate of node n based on distances
	 * Follows previous nodes till it finds a node connected to node 0
	 * @param n Node
	 * @return y-coordinate of node n
	 */
	private int getYCoordinates(Node n) {
		if (n.identifier == 0)							// if node is node0, return the origin_x
			return origin_y;
		List<Edge> prevEdges = getPrevEdges(n);			// get list of edges with node n as destination
		for (Edge e : prevEdges) {						// iterate thru edges in above list 
			if (e.getSource().identifier == 0)			// if source node of an edge is node0, return edge weight
				return e.getWeight();

			return getYCoordinates(e.getSource());		// else recurse till find an edge where the source is node0
		}

		return -1;
	}

	/**
	 * Calculate x coordinate of a node n based on distances
	 * @param n node
	 * @return x coordinate of the node
	 */
	private int getXCoordinates(Node n) {
		if (n.identifier == 0)						// if node is node0, return the origin_x
			return origin_x;						

		List<Edge> prevEdges = getPrevEdges(n);		// get list of edges with node n as destination

		Edge e = prevEdges.get(0);					// get the first edge
		if (e.getSource().identifier == 0)			// if source node of the edge is node0, return origin_x
			return origin_x;
		return prevEdges.get(0).getWeight();			// else return the weight of the edge

	}

	/**
	 * Draw a node with given (x,y) coordinates
	 * @param g
	 * @param node
	 * Reference - http://www1.cs.columbia.edu/~bert/courses/3137/hw3_files/GraphDraw.java
	 */
	private void drawNode(Graphics g,  Node node, int x, int y) {
		
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
		//g.fillOval(x - xAdjustment, y - yAdjustment, nodeWidth, nodeHeight);
		
		// Draw boundary of node
		g.setColor(Color.red);
		g.drawOval(x - xAdjustment, y - yAdjustment, nodeWidth, nodeHeight);
		
		// Draw label of node
		g.drawString(name, x - 5 - stringWidth / divisionFactor, y - 40 + f.getHeight() / divisionFactor);
	}

	/**
	 * Draw an arrow line between two points.
	 * 
	 * @param g   the graphics component.
	 * @param x1  x-position of first point.
	 * @param y1  y-position of first point.
	 * @param x2  x-position of second point.
	 * @param y2  y-position of second point.
	 * @param d   the width of the arrow.
	 * @param h   the height of the arrow.
	 * @param thickness the thickness of the line
	 * 
	 * Reference - https://stackoverflow.com/questions/2027613/how-to-draw-a- directed-arrow-line-in-java
	 * 
	 */
	private void drawEdge(Graphics g, int x1, int y1, int x2, int y2, int depth, int height, int thickness, int weight) {
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
		g2.setColor(Color.lightGray);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawLine(x1, y1, x2, y2);  
	    
	    g2.setColor(Color.darkGray);
	    g2.fillPolygon(xpoints, ypoints, 3);					// Draw triangle at end point of line
	    
	    int x_mid = (x1+x2)/2;
	    int y_mid = (y1+y2)/2;
	    
	    // Draw weight of edge
	 	g.drawString(String.valueOf(weight), x_mid, y_mid);
	    
	}

	/**
	 * Get list of edges that have node n as destination
	 * @param n node
	 * @return list of edges that lead to node n
	 */
	private List<Edge> getPrevEdges(Node n) {
		return prevEdges.get(n.identifier);
	}

	/**
	 * Calculate thickness of the line as proportional or inversely proportional to the weight of the edge
	 * Uses log normalization
	 * @param weight weight of edge
	 * @param inverse if true then thickness is inversely proportional to the edge weight
	 * @return
	 */
	private int calculateThickness(int weight, boolean inverse) {
		if (inverse) {
			int k = 10;
			return (int) Math.ceil(k / Math.log1p(weight) / Math.log1p(1.1));
		} else
			return (int) Math.ceil(Math.log1p(weight) / Math.log1p(2.5));
	}
}
