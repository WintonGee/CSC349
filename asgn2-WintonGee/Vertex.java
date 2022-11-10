import java.util.ArrayList;

public class Vertex {
    public int key;
    public boolean discovered;
    public int preOrder, postOrder;

    // Neighbors (Edges created)
    public ArrayList<Integer> outgoingVertices = new ArrayList<>();

    // Vertices that are reachable, used for determining
    public ArrayList<Integer> reachableVertices = new ArrayList<>();


    public Vertex(int newFirstVertex) {
        this.key = newFirstVertex;
    }

    public void addNeighbor(int vertex) {
        outgoingVertices.add(vertex);
    }

    public void addReachable(Vertex v) {
        reachableVertices.add(v.key);
    }
}
