import java.util.ArrayList;
import java.util.stream.Collectors;

public class ExactGreedyVertexCover {

    /**
     * Task - Brute force
     * - Generate subsets
     * - Determine if subset is a vertex cover
     * - Compare vertex covers and determine the min based on the least number of vertices
     * - Sort in ascending order
     */

    // Implement a brute force (exact) algorithm for vertex cover.
    public static ArrayList<Integer> get(ArrayList<Edge> graph) {
        ArrayList<VertexSubset> potentialSubsets = getPotentialSubsets(graph);
        VertexSubset best = getBestSubset(potentialSubsets);
        return best == null ? new ArrayList<>() : best.vertices;
    }

    // Return: Vertex subset that contains the minimum number of vertices
    private static VertexSubset getBestSubset(ArrayList<VertexSubset> potentialSubsets) {
        if (potentialSubsets.size() == 0)
            return null;
        VertexSubset bestVertexSubset = potentialSubsets.get(0);

        for (int i = 1; i < potentialSubsets.size(); i++) {
            bestVertexSubset = getMinSubset(bestVertexSubset, potentialSubsets.get(i));
        }

        return bestVertexSubset;
    }

    // Return: All vertex subsets of graph that are vertex covers of g
    public static ArrayList<VertexSubset> getPotentialSubsets(ArrayList<Edge> g) {
        // Load the vertices used to generate subsets
        ArrayList<Integer> vertices = new ArrayList<>();
        for (Edge e : g) {
            vertices.add(e.u);
            vertices.add(e.v);
        }

        // Remove duplicate vertices
        vertices = vertices
                .stream()
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));

        // Generate a list of vertex subsets, add to return list if vertices is a valid vertex cover
        ArrayList<VertexSubset> vertexSubsets = new ArrayList<>();
        int n = vertices.size();
        for (int i = 0; i < (1 << n); i++) {
            VertexSubset vertexSubset = new VertexSubset();
            for (int j = 0; j < n; j++)
                if ((i & (1 << j)) > 0)
                    vertexSubset.addVertex(vertices.get(j));

            // Only add into the list of vertex subsets if vertexSubset is a valid vertex cover
            if (vertexSubset.isValidVertexCover(g))
                vertexSubsets.add(vertexSubset);
        }

        return vertexSubsets;
    }

    // Return: Vertex Subset with the smallest number of vertices, defaulting to v1 on equal
    public static VertexSubset getMinSubset(VertexSubset v1, VertexSubset v2) {
        return v1.vertices.size() <= v2.vertices.size() ? v1 : v2;
    }

}
