public class GraphAdjacencyList implements Graph {

    private Neighbour[] vertices;
    private int q, p;

    public GraphAdjacencyList(int q) {
        this.q = q;
        this.p = 0;
        vertices = new Neighbour[q];
    }

    @Override
    public int getP() {
        return this.p;
    }

    @Override
    public int getQ() {
        return this.q;
    }

    @Override
    public int getDegree(int v) {
        return 0;
    }

    @Override
    public boolean isAdjacent(int u, int v) {

        return false;
    }

    @Override
    public void insertEdge(int u, int v) {

        ++p;
    }

    @Override
    public void removeEdge(int u, int v) {

        --p;
    }

    @Override
    public Neighbour getNeighbours(int v) {
        return vertices[v];
    }
}
