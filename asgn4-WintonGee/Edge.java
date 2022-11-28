import java.util.ArrayList;

public class Edge {

    public int u, v;

    // Debug
    public String toString() {
        return "Edge: (" + u + ", " + v + ")";
    }

    public Edge(int newFirstVertex, int newSecondVertex) {
        this.u = newFirstVertex;
        this.v = newSecondVertex;
    }

    public static boolean isAnySharedVertex(Edge firstEdge, Edge secondEdge) {
        return firstEdge.u == secondEdge.u
                || firstEdge.u == secondEdge.v
                || firstEdge.v == secondEdge.u
                || firstEdge.v == secondEdge.v;
    }

    public static boolean isContainsVertex(Edge firstEdge, int vertex) {
        return firstEdge.u == vertex || firstEdge.v == vertex;
    }

}
