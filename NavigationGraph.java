import java.util.List;

public class NavigationGraph implements GraphADT<Location, Path> {

	//TODO: Implement all methods of GraphADT
	
	public NavigationGraph(String[] edgePropertyNames) {
	}
	
	
	
	public void addVertex(V vertex) {
		
	}

	public void addEdge(V src, V dest, E edge) {
		
	}
	
	public List<V> getVertices() {
		
	}
	
	public E getEdgeIfExists(V src, V dest) {
		
	}
	
	public List<E> getOutEdges(V src) {
		
	}
	
	public List<V> getNeighbors(V vertex) {
		
	}
	
	public List<E> getShortestRoute(V src, V dest, String edgePropertyName) {
		
	}
	
	public String[] getEdgePropertyNames() {
		
	}
	
	public String toString() {
		
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
