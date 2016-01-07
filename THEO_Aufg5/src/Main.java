public class Main {

    public static void main(String[] args) {

	// write your code here
        GraphAdjacencyMatrix graphAdjacencyMatrix = new GraphAdjacencyMatrix(4);
        graphAdjacencyMatrix.insertEdge(0, 2);
        graphAdjacencyMatrix.insertEdge(0, 3);
        graphAdjacencyMatrix.insertEdge(1, 2);
        graphAdjacencyMatrix.insertEdge(1, 3);
        
        System.out.println(graphAdjacencyMatrix.getNeighbours(0));
        
        System.out.println(graphAdjacencyMatrix.toString());
        
        
     
    }
}
