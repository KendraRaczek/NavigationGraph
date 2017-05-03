/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          p5
// FILE:             NavigationGraph.java
// TEAM:    72
// Author: Jonathan Nelson, jnelson33@wisc.edu, jnelson, Lec 001
// Author: Add
// Author: Add
// Author: Add
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.List;

/**
 * A class that allows the code to use information from graphNodes and vertices 
 * in the main class in order to more effectively traverse through, retrieve information,
 * and set up the data structure. 
 *
 * @author Jonathan, Harry, Kendra, David
 */
public class NavigationGraph implements GraphADT<Location, Path> 
{	
	private ArrayList<GraphNode<Location, Path>> nodes; // ArrayList that stores the graphNode data
	private int numVertices; //A value representing the total number of vertices
	private int id = 0; //Used in getShortestRoute in order to mark as visited
	private String[] propNames; //Stores information that vertices hold
	
	/**
	 * This is the constructer for the data members above. Allows code to update information.
	 *
	 * @param String[] edgePropertyNames This initializes the ArrayList
	 */
	public NavigationGraph(String[] edgePropertyNames) 
	{
		this.nodes = new ArrayList<GraphNode<Location, Path>>(); //Initializes ArrayList
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
		
		// search through Graph's list
		GraphNode<Location, Path> srcNode = null;
		GraphNode<Location, Path> destNode = null;
		//Searches for the correct vertices
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
		//Ensures that vertices are in the structure
		if (srcNode == null) throw new IllegalArgumentException();
		if (destNode == null) throw new IllegalArgumentException();
		//Adds the edge
		srcNode.addOutEdge(edge);
	}
	
	public List<Location> getVertices() 
	{
		ArrayList<Location> list = new ArrayList<Location>();
		for (GraphNode<Location, Path> node : nodes) 
		{
				list.add(node.getVertexData());
		}
		return list;
	}
	
	public Path getEdgeIfExists(Location src, Location dest) 
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
		
		for (Path edge : srcNode.getOutEdges()) 
		{
			if ( edge.getDestination() == dest)
			{
				return edge;
			}
		}
		return null;
	}
	
	
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
	
	public List<Location> getNeighbors(Location vertex)
	{
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
		path = mainNode.getOutEdges();
		for (Path p : path) 
		{
			neighbors.add(p.getDestination());
		}
		return neighbors;
     	}	
	
	public List<Path> getShortestRoute(Location src, Location dest, String edgePropertyName)
	{
		//This method left, this should be able to be found Online
	}
	
	public String[] getEdgePropertyNames()
	{
		return propNames;
	}
	
	public int getNumVertices() 
	{
		return numVertices;
	}
	
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
		}
		graphString = graphString.toLowerCase();
		return graphString;
	}
	
	/**
	 * Returns a Location object given its name
	 * 
	 * @param name
	 *            name of the location
	 * @return Location object
	 */
	public Location getLocationByName(String name) {
		for (GraphNode<Location, Path> node : nodes)
			if (node.getVertexData().getName().equals(name))
				return node.getVertexData();
		return null;
	}

}
