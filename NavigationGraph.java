import java.util.ArrayList;
import java.util.List;

public class NavigationGraph implements GraphADT<Location, Path> {

	//TODO: Implement all methods of GraphADT
	
	private ArrayList<GraphNode<Location, Path>> nodes;
	private int numVertices;
	private int id = 0;
	private String[] propName;
	
	public NavigationGraph(String[] edgePropertyNames) 
	{
		this.nodes = new ArrayList<GraphNode<Location, Path>>();
		this.numVertices = 0;
		this.propName = edgePropertyNames;
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
		for (GraphNode<Location, Path> node : nodes) {
			if ( node.getVertexData().equals(src)) {
				srcNode = node;
			}
			if ( node.getVertexData().equals(destNode)) {
				destNode = node;
			}
		}
		if (srcNode == null) throw new IllegalArgumentException();
		if (destNode == null) throw new IllegalArgumentException();
		
		srcNode.addOutEdge(edge);
	}
	
	public List<Location> getVertices() 
	{
		ArrayList<String> list = new ArrayList<String>();
		boolean t = ture;
		for (GraphNode<Location, Path> node : nodes) {
			t = true;
			for (String s : list)
				if (s.equals(node.getVertexData().getname()))
					t = false;
			if (t) list.add(node.getVertexData().getname())
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
		for (GraphNode<Location, Path> node : nodes) {
			if ( node.getVertexData().equals(src)) {
				srcNode = node;
			}
		}
		if (srcNode == null) throw new IllegalArgumentException();
		
		return srcNode.getOutEdges();
	}
	
	public List<Location> getNeighbors(Location vertex) {
//		  int numVertices = getNumVertices();
//		  if (numV <= v) 
//		  {
//			  throw new IndexOutOfBoundsException();
//		  }
//		  else
//		  {
//			  List<Integer> neighbors = new ArrayList<Integer>();
//			  for (int x : adjListsMap.get(vertex)) 
//			  {
//				  neighbors.add(x);
//			  }
//		  }
//		  
//		    }
//		    
//		    return neighbors;
//		
	}
	
	public List<Path> getShortestRoute(Location src, Location dest, String edgePropertyName) {
		
	}
	
	public String[] getEdgePropertyNames()
	{
		return propName;
	}
	
	public int getNumVertices() 
	{
		return numVertices;
	}
	public String toString() 
	{
		
	}
	
	/**
	 * Returns a Location object given its name
	 * 
	 * @param name
	 *            name of the location
	 * @return Location object
	 */
	public Location getLocationByName(String name) {
		return null; //TODO: implement correctly. 
	}

}
