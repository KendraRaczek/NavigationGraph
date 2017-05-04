/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          p5
// FILE:             NavigationGraph.java
// TEAM:    72
// Author: Jonathan Nelson, jnelson33@wisc.edu, jnelson, Lec 001
// Author: David Zhu, dzhu46@wisc.edu, zhu, Lec 002
// Author: Kendra Raczek, raczek@wisc.edu, raczek, Lec 001
// Author: Xinhui Yu, xyu269@wisc.edu, Xinhui Yu, Lec 002
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A class that allows the code to use information from graphNodes and vertices 
 * in the main class in order to more effectively traverse through, retrieve information,
 * and set up the data structure. 
 *
 * @author Jonathan, Harry, Kendra, David
 */
public class NavigationGraph implements GraphADT<Location, Path> 
{	
	public ArrayList<GraphNode<Location, Path>> nodes; // ArrayList that stores the graphNode data
	private int numVertices; // A value representing the total number of vertices
	private int id = 0; // Used in getShortestRoute in order to mark as visited
	private String[] propNames; // Stores information that vertices hold
	
	/**
	 * This is the constructor for the data members above. Allows code to update information.
	 *
	 * @param String[] edgePropertyNames This initializes the ArrayList
	 */
	public NavigationGraph(String[] edgePropertyNames) 
	{
		this.nodes = new ArrayList<GraphNode<Location, Path>>(); // Initializes ArrayList
		this.numVertices = 0;
		this.propNames = edgePropertyNames;
	}
	
	/**
	 * This method adds a vertex in the code by creating a new GraphNode. This then adds to the list
	 * and  increments numVertices and ID 
	 *
	 * @param Location vertex This is the vertex that we are adding to the linked list
	 * @return void 
	 */	
	public void addVertex(Location vertex) 
	{
		// GraphNode representing Location
		GraphNode<Location, Path> nodeToAdd = new GraphNode<Location, Path>(vertex, id);
		nodes.add(nodeToAdd);
		this.numVertices++;
		this.id++;
	}
	
	/**
	 * This method adds an edge in the code by searching and finding the correct nodes.
	 * After, It adds the edge for the path.
	 *
	 * @param Location src This is the origin of the edge that we are adding
	 * @param Location dest This is the end of the edge we are adding
	 * @param Path edge //This is the Path of the edge
	 * @return void 
	 */	
	public void addEdge(Location src, Location dest, Path edge)
	{
		if (src.equals(dest)) throw new IllegalArgumentException();
		if ((edge == null) || (src == null) || (dest == null)) throw new IllegalArgumentException();
		
		GraphNode<Location, Path> srcNode = null;
		GraphNode<Location, Path> destNode = null;
		// Searches for the correct vertices
		for (GraphNode<Location, Path> node : nodes) 
		{
			if ( node.getVertexData().equals(src)) 
			{
				srcNode = node;
			}
			if ( node.getVertexData().equals(dest))
			{
				destNode = node;
			}
		}
		// Ensures that vertices are in the structure
		if (srcNode == null) throw new IllegalArgumentException();
		if (destNode == null) throw new IllegalArgumentException();
		// Adds the edge
		srcNode.addOutEdge(edge);
	}
	
	/**
     * This method traverses the graph for all of the vertices (nodes),
     * adds them to an ArrayList, and returns the ArrayList.
     *
     * @return list, the ArrayList containing the vertices
     */
	public List<Location> getVertices() 
	{
		// list of Locations rather than GraphNodes
		ArrayList<Location> list = new ArrayList<Location>();
		for (GraphNode<Location, Path> node : nodes) 
		{
				list.add(node.getVertexData());
		}
		return list;
	}
	
	/**
	 * Returns a path of the edge that is being checked if it has existence. 
	 *
	 * @param Location src The first node that checks 
	 * @param Location dest The last node that is being checked
	 * @return Path A list of all locations
	 */	
	public Path getEdgeIfExists(Location src, Location dest) 
	{
		if (dest == null) throw new IllegalArgumentException();
		
		GraphNode<Location, Path> srcNode = null;
		for (GraphNode<Location, Path> node : nodes)
		{
			if ( node.getVertexData().equals(src)) 
			{
				srcNode = node;
			}
		}
		if (srcNode == null) throw new IllegalArgumentException();		
		
		for (Path edge : srcNode.getOutEdges()) 
		{
			if ( edge.getDestination() == dest)
			{
				return edge;
			}
		}
		return null;
	}
	
	/**
	 * Returns a list of out edges that are coming out of the vertices. 
	 *
	 * @param Location src The first node that the edges are coming out of
	 * @return List<Path> A list of all edges coming out
	 */	
	public List<Path> getOutEdges(Location src) 
	{
		GraphNode<Location, Path> srcNode = null;
		for (GraphNode<Location, Path> node : nodes)
		{
			if ( node.getVertexData().equals(src)) 
			{
				srcNode = node;
			}
		}
		if (srcNode == null) throw new IllegalArgumentException();
		
		return srcNode.getOutEdges();
	}
	
	/**
	 * Returns a list of all neighbors from the main vertex
	 *
	 * @param Location vertex The first node that the neighbors are calculated
	 * @return List<Location> A list of all neighbors
	 **/
	public List<Location> getNeighbors(Location vertex)
	{
		if (vertex == null) throw new IllegalArgumentException();
		List<Location> neighbors = new ArrayList<Location>();
		List<Path> path = new ArrayList<Path>();

		GraphNode<Location, Path> mainNode = null;
		for (GraphNode<Location, Path> node : nodes) 
		{
			if ( node.getVertexData().equals(vertex)) 
			{
				mainNode = node;
			}
		}
		if (mainNode == null) throw new IllegalArgumentException();
		path = mainNode.getOutEdges();
		for (Path p : path) 
		{
			neighbors.add(p.getDestination());
		}
		return neighbors;
     }	
	/**
	 * This method calculates via the weights the shortest possible route than one can take.
	 * It then returns a list with the order of the vertices that use the least weight.
	 *
	 * @param Location src This is the origin, where we are starting from
	 * @param Location dest This is the end, the node we want to finish at
	 * @param String edgePropertyName The weights that are used to calculate shortest route
	 * @return List<Path> Returns a list of vertices that uses the least weight. 
	 */	
	public List<Path> getShortestRoute(Location src, Location dest, String edgePropertyName)
	{	
		PriorityQueue<NewGraphNode> pq = new PriorityQueue<NewGraphNode>(
			new Comparator<NewGraphNode>(){
			
				public int compare(NewGraphNode a, NewGraphNode b){
				if (a.weight > b.weight) 
					return 1;
				else if (a.weight == b.weight)
					return 0;
				else return -1;
			}
		});
		String[] propNames = getEdgePropertyNames();
		int colNum = 0;
		for (int i = 0; i < propNames.length;i++)
			if (propNames[i].equals(edgePropertyName))
				colNum = i;
		ArrayList<NewGraphNode> newNodes = new ArrayList<NewGraphNode>();
		
		for (GraphNode<Location,Path> a: nodes)
			newNodes.add(new NewGraphNode(a));
		
		for (NewGraphNode f: pq)
			if (f.location.equals(src))
				f.weight = 0.0;
		
		NewGraphNode curr = null;
		while (!pq.isEmpty()){
			curr = pq.remove();
			curr.visited = true;
			
			for (NewGraphNode a : newNodes)
				if (a.visited == false){
					List<Location> neighbors = getNeighbors(curr.location);
					for (Location c : neighbors)
						for (NewGraphNode d : pq)
							if (c.equals(d.location) && (d.visited == false)){
								Double sum = 0.0;
								sum = curr.weight + getEdgeIfExists(curr.location, c).getProperties().get(colNum);
								if (sum < d.weight){
									d.prede = curr;	
									d.weight = sum;
									if (!pq.contains(d))
										pq.add(d);
								}		
							}
					
				}	
		}
		
		NewGraphNode current = null;
		ArrayList<Path> path = new ArrayList<Path>();
		for (int i = 0; i < newNodes.size(); i++){
			if (newNodes.get(i).location.equals(dest)){
				current = newNodes.get(i);
			}
		}
		while (!(current.prede == null)) {
			path.add(getEdgeIfExists(current.prede.location, current.location));
			current = current.prede;
		}
		
		System.out.print(path.toString());
		return path;
		
	}
	
	/**
	 * Returns an String array of the property names of vertices
	 *
	 * @return String[] Edge property names array
	 */
	public String[] getEdgePropertyNames()
	{
		return propNames;
	}
	
	/**
	 * Returns an int of the total number of vertices.
	 *
	 * @return int A total of the number of vertices
	 */
	public int getNumVertices() 
	{
		return numVertices;
	}
	
	/**
	 * Returns a properly-formatted String of the graph information.
	 *
	 * @return String the information in nodes
	 */
	public String toString() 
	{
		String graphString = "";
		for (GraphNode<Location, Path> node : nodes)
		{
			for (Path edge : node.getOutEdges())
			{
				graphString += node.getVertexData() + " ";
				for ( Double property : edge.getProperties()) 
				{
					graphString += property + " ";
				}
				graphString += edge.getDestination() + ", ";
			}
			graphString = graphString.substring(0,graphString.length()-2);
			graphString += '\n';
		}
		graphString = graphString.substring(0,graphString.length()-1);
		graphString = graphString.toLowerCase();
		return graphString;
	}
	
	/**
	 * Returns a Location object given its name.
	 * 
	 * @param name
	 *            name of the location
	 * @return Location object
	 */
	public Location getLocationByName(String name) 
	{
		for (GraphNode<Location, Path> node : nodes)
			if (node.getVertexData().getName().equals(name))
				return node.getVertexData();
		return null;
	}
}

class NewGraphNode 
{
	public boolean visited;
	public double weight;
	public NewGraphNode prede;
	public GraphNode<Location,Path> graphNode;
	public Location location;
	
	NewGraphNode(GraphNode<Location,Path> node) 
	{
		this.location = node.getVertexData();
		this.graphNode = node;
		this.visited = false;
		this.weight = Double.MAX_VALUE;
		this.prede = null;
	}
}
