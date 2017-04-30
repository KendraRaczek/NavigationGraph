import java.util.ArrayList;
import java.util.List;

public class NavigationGraph implements GraphADT<Location, Path> {

	//TODO: Implement all methods of GraphADT
	
	private ArrayList<GraphNode<Location, Path>> nodes;
	private int numVertices;
	private int id = 0;
	private String[] propName;
	
	public NavigationGraph(String[] edgePropertyNames) {
		
		this.nodes = new ArrayList<GraphNode<Location, Path>>();
		this.numVertices = 0;
		this.propName = edgePropertyNames;
	}
	
	
	
	public void addVertex(Location vertex) {
		
		
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
	
	public List<Location> getVertices() {
		
	}
	
	public Path getEdgeIfExists(Location src, Location dest) 
	{
		int numVertices = getNumVertices();
		if (numVertices > src || numVertices > dest)
		{
			throw  new IllegalArgumentException(); 
		}
		else 
		{
			GraphNode<Location, Path> srcNode;
			for (GraphNode<Location, Path> node : nodes)
			{
				if ( node.getVertexData().equals(src)) 
				{
					srcNode = node;
				}
			}
			if (srcNode == null) throw new IllegalArgumentException();		
			return (Path) srcNode.getOutEdges(); // Not yet correct, figuring out how to get the specific edge
		}
	
	}
	
	
	public List<Path> getOutEdges(Location src) 
	{
	    List list = new ArrayList();
		int numVertices = getNumVertices();
		for (int i = 0; i < numVertices; i++)
		{
			double temp = matrix[src][i];
			if (temp != 0) 
			{
				list.add(temp); //What is the list?
			}
		}
		return list;
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
