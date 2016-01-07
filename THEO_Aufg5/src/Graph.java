/**
 * Interface with most frequently used operations for graphs. Vertices are
 * numbered continuously beginning with 0 (zero).
 * 
 * @author Thomas Umland
 */
public interface Graph {

	/**
	 * Return the number of vertices of this graph.
	 * 
	 * @return
	 */
	public int getP();

	/**
	 * Return the number of edges of this graph.
	 * 
	 * @return
	 */
	public int getQ();

	/**
	 * Return the degree of vertex v;
	 * 
	 * @param v
	 * @return grad(v)
	 */
	public int getDegree(int v);

	/**
	 * Returns true iff vertices u and v are adjacent in this graph
	 * 
	 * @param u
	 * @param v
	 * @return
	 */
	public boolean isAdjacent(int u, int v);

	/**
	 * Insert an edge between vertices u and v.
	 * 
	 * @param u
	 * @param v
	 */
	public void insertEdge(int u, int v);

	/**
	 * Remove an edge between vertices u and v.
	 * 
	 * @param u
	 * @param v
	 */
	public void removeEdge(int u, int v);

	/**
	 * Return an unordered linked list all neighbours of vertex v.
	 * 
	 * @param v
	 * @return
	 */
	public Neighbour getNeighbours(int v);

	/**
	 * Return a string representation of this graph (e.g. as adjacency matrix,
	 * as adjacency list, or some other useful structure).
	 * 
	 * @return
	 */
	public String toString();

}
