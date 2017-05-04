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

public class NavigationGraph implements GraphADT<Location, Path> {
	
	public ArrayList<GraphNode<Location, Path>> nodes;
	private int numVertices;
	private int id = 0;
	private String[] propNames = new String[2];
	
	public NavigationGraph(String[] edgePropertyNames) 
	{
		if (edgePropertyNames == null) throw new IllegalArgumentException();
		this.nodes = new ArrayList<GraphNode<Location, Path>>();
		this.numVertices = 0;
		this.propNames = edgePropertyNames;
	}
	
	public void addVertex(Location vertex) 
	{	
		if (vertex == null) throw new IllegalArgumentException();
		GraphNode<Location, Path> nodeToAdd = new GraphNode<Location, Path>(vertex, id);
		nodes.add(nodeToAdd);
		this.numVertices++;
		this.id++;
	}

	public void addEdge(Location src, Location dest, Path edge)
	{
		if (src == null) throw new IllegalArgumentException();
		if (dest == null) throw new IllegalArgumentException();
		
		// search through Graph's list
		GraphNode<Location, Path> srcNode = null;
//		GraphNode<Location, Path> destNode = null;
		for (GraphNode<Location, Path> node : nodes) 
		{
			if ( node.getVertexData().equals(src)) 
			{
				srcNode = node;
			}
		}
//			if ( node.getVertexData().equals(dest))
//			{
//				destNode = node;
//			}
//		}

		if (edge.getDestination().equals(dest) && edge.getSource().equals(src))
			srcNode.addOutEdge(edge);
	}
	
	public List<Location> getVertices() 
	{
		ArrayList<Location> list = new ArrayList<Location>();
		for (GraphNode<Location, Path> node : nodes) {
				list.add(node.getVertexData());
		}
		return list;
	}
	
	public Path getEdgeIfExists(Location src, Location dest) 
	{
		if (src == null || dest == null) throw new IllegalArgumentException();
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
			if ( edge.getDestination().equals(dest))
			{
				return edge;
			}
		}
		return null;
	}
	
	
	public List<Path> getOutEdges(Location src) 
	{
		if (src == null) throw new IllegalArgumentException();
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
		if (mainNode == null)
			throw new IllegalArgumentException();
		path = mainNode.getOutEdges();
		for (Path p : path) 
			neighbors.add(p.getDestination());
		return neighbors;
     	}	
	
	public List<Path> getShortestRoute(Location src, Location dest, String edgePropertyName) {
		if (src == null || dest == null || edgePropertyName == null) throw new IllegalArgumentException();
		PriorityQueue<NewGraphNode> pq = new PriorityQueue<NewGraphNode>(new Comparator<NewGraphNode>() {
			public int compare(NewGraphNode a, NewGraphNode b) {
				if (a.weight > b.weight)
					return 1;
				else if (a.weight == b.weight)
					return 0;
				else
					return -1;
			}
		});
		String[] propNames = getEdgePropertyNames();
		int colNum = 0;
		for (int i = 0; i < propNames.length; i++)
			if (propNames[i].equals(edgePropertyName))
				colNum = i;
		ArrayList<NewGraphNode> newNodes = new ArrayList<NewGraphNode>();

		for (GraphNode<Location, Path> a : nodes)
			newNodes.add(new NewGraphNode(a));

		for (NewGraphNode f : newNodes)
			if (f.location.equals(src)){
				f.weight = 0.0;
				pq.add(f);
			}

		NewGraphNode curr = null;
		while (!pq.isEmpty()) {
			curr = pq.remove();
			curr.visited = true;
			List<Location> neighbors = getNeighbors(curr.location);
			List<NewGraphNode> succNode = new ArrayList<NewGraphNode>();
			for (Location b : neighbors)
				for (NewGraphNode a : newNodes)
					if (a.location.equals(b))
						succNode.add(a);

			for (NewGraphNode d : succNode)
				if (!(d.visited)) {
					Double sum = 0.0;
					sum = curr.weight + getEdgeIfExists(curr.location, d.location).getProperties().get(colNum);
					if (sum < d.weight) {
						d.prede = curr;
						d.weight = sum;
						if (!pq.contains(d))
							pq.add(d);
					}
				}

		}

		NewGraphNode current = null;
		ArrayList<Path> path = new ArrayList<Path>();
		for (int i = 0; i < newNodes.size(); i++) {
			if (newNodes.get(i).location.equals(dest)) {
				current = newNodes.get(i);
			}
		}
		while (!(current.prede == null)) {
			path.add(getEdgeIfExists(current.prede.location, current.location));
			current = current.prede;
		}
		ArrayList<Path> path1 = new ArrayList<Path>();
		for (int i = path.size() - 1; i >= 0;i--)
			path1.add(path.get(i));
		return path1;
		
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
		String currName = "";
		currName = nodes.get(0).getVertexData().getName();
		for (GraphNode<Location, Path> node : nodes)
		{
			if (!node.getVertexData().getName().equals(currName)){
				graphString += '\n';
				currName = node.getVertexData().getName();
			}
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

class NewGraphNode {
    // now MyClass can create and use instances of WrapperClass as needed.
	
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
