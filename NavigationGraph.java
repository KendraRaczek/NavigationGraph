import java.util.ArrayList;
import java.util.List;

public class NavigationGraph implements GraphADT<Location, Path> {

	//TODO: Implement all methods of GraphADT
	
	private ArrayList<GraphNode<Location, Path>> nodes;
	private int numVertices;
	private int id = 0;
	private String[] propNames;
	
	public NavigationGraph(String[] edgePropertyNames) 
	{
		this.nodes = new ArrayList<GraphNode<Location, Path>>();
		this.numVertices = 0;
		this.propNames = edgePropertyNames;
	}
	
	
	
	public void addVertex(Location vertex) 
	{
		GraphNode<Location, Path> nodeToAdd = new GraphNode<Location, Path>(vertex, id);
		nodes.add(nodeToAdd);
		this.numVertices++;
		this.id++;
	}

	public void addEdge(Location src, Location dest, Path edge)
	{
		
		// search through Graph's list
		GraphNode<Location, Path> srcNode = null;
		GraphNode<Location, Path> destNode = null;
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
		if (srcNode == null) throw new IllegalArgumentException();
		if (destNode == null) throw new IllegalArgumentException();
		
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
			neighbors.add(p.getDestinations());
		for (GraphNode<Location, Path> node : nodes) 
			{
				for (Path p : node.getOutEdges())
					if (p.getDestination().equals(vertex))
						neighbors.add(p.getSource());
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
