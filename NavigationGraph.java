import java.util.ArrayList;
import java.util.List;

public class NavigationGraph implements GraphADT<Location, Path> {

	//TODO: Implement all methods of GraphADT
	
	private ArrayList<GraphNode<Location, Path>> nodes;
	private int size;
	private int id = 0;
	private String[] propName;
	
	public NavigationGraph(String[] edgePropertyNames) {
		
		this.nodes = new ArrayList<GraphNode<Location, Path>>();
		this.size = 0;
		propName = edgePropertyNames;
	}
	
	
	
	public void addVertex(Location vertex) {
		
		
		GraphNode<Location, Path> nodeToAdd = new GraphNode<Location, Path>(vertex, id);
		nodes.add(nodeToAdd);
		this.size++;
		this.id++;
		
		
	}

	public void addEdge(V src, V dest, E edge)
	{
		int numVertices = getNumVertices();
		if (numVertices > src || numVertices > dest) 
		{
			throw  new IllegalArgumentException(); //Not sure which exception to throw
		}
		matrix[src][dest] = edge; //Find Agacency matrix
	}
	
	public List<V> getVertices() {
		
	}
	
	public E getEdgeIfExists(V src, V dest) 
	{
		int numVertices = getNumVertices();
		if (numVertices > src || numVertices > dest)
		{
			throw  new IllegalArgumentException(); 
		}
		else 
		{
			return matrix[src][dest] != 0;
		}
	
	}
	
	public List<E> getOutEdges(V src) 
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
	
	public List<V> getNeighbors(V vertex) {
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
	
	public List<E> getShortestRoute(V src, V dest, String edgePropertyName) {
		
	}
	
	public String[] getEdgePropertyNames()
	{
		
	}
	
	public int getNumVertices() 
	{
		
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
