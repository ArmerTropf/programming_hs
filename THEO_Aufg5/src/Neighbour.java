/**
 * Helper class to build a linked list of vertices (e.g. a list of neighbours).
 * 
 * @author Thomas Umland
 */
public class Neighbour {
	public final int v;
	public final Neighbour next;

	public Neighbour(int v, Neighbour next) {
		this.v = v;
		this.next = next;
	}
}
