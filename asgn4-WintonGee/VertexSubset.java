import java.util.ArrayList;

// This class is used for the ExactGreedyVertexCover algorithm
public class VertexSubset {

    public ArrayList<Integer> vertices = new ArrayList<>();

    public String toString() {
        return vertices.toString();
    }

    // Return: If vertices create a vertex cover of g
    public boolean isValidVertexCover(ArrayList<Edge> g) {
        ArrayList<Edge> duplicateGraph = new ArrayList<>(g);
        vertices.forEach(vertex -> duplicateGraph.removeIf(edge -> Edge.isContainsVertex(edge, vertex)));
        return duplicateGraph.size() == 0;
    }

    public void addVertex(int newVertex) {
        vertices.add(newVertex);
    }

}
