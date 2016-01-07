public class GraphAdjacencyMatrix implements Graph {

    private boolean[][] adjacencyMatrix;
    private int q, p;

    public GraphAdjacencyMatrix(int p) {
        this.p = p;
        this.q = 0;
        adjacencyMatrix = new boolean[p][p];

    }

    @Override
    public int getQ() {
        return this.q;
    }

    @Override
    public int getP() {
        return this.p;
    }

    @Override
    public int getDegree(int v) {
        if(v < this.q) {
            int c = 0;
            for (int u = 0; u < this.q; u++) {
                if(adjacencyMatrix[v][u]) {
                    ++c;
                }
            }
            return c;
        }
        return -1;
    }

    @Override
    public boolean isAdjacent(int u, int v) {
        if(u < this.q && v < this.q && u != v) {
            return adjacencyMatrix[u][v];
        }
        return false;
    }

    @Override
    public void insertEdge(int u, int v) {
        if(u < this.p && v < this.p && u != v) {
            if(!adjacencyMatrix[u][v]) 
            {
                adjacencyMatrix[u][v] = true;
                adjacencyMatrix[v][u] = true;
                ++q;
            }
        }
    }

    @Override
    public void removeEdge(int u, int v) {
        if(u < this.p && v < this.p && u != v) {
            if(adjacencyMatrix[u][v]) 
            {
                adjacencyMatrix[u][v] = false;
                adjacencyMatrix[v][u] = false;
                --q;
            }
        }
    }

    @Override
    public Neighbour getNeighbours(int v) 
    {
        Neighbour nextN = null;
        for (int u = this.p-1; u >= 0; --u) 
        {
            if(adjacencyMatrix[v][u]) 
            {
                nextN = new Neighbour(u, nextN);
            }
        }
        return new Neighbour(v, nextN);
    }

    @Override
    public String toString() {
        String s = "";
        for (int u = 0; u < this.p; u++) {
            for (int v = 0; v < this.p; v++) {
                s += (adjacencyMatrix[u][v]? 1 : 0)+"\t";
            }
            s+= "\n";
        }
        return s;
    }
}
