import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// log(n)-Approximation Algorithm
public class SmartGreedyVertexCover {

    //Input: A graph G.
    //Output: A set of vertices that form a (not necessarily optimal) vertex cover.
    public static ArrayList<Integer> get(ArrayList<Edge> graph) {
        ArrayList<Edge> duplicateGraph = new ArrayList<>(graph);

        //1: C ←∅
        ArrayList<Integer> list = new ArrayList<>();
        //2: while G has at least one edge do
        while (duplicateGraph.size() > 0) {
            //3: v ← vertex in G with maximum degree
            int maxVertex = getMaxDegreeVertex(duplicateGraph);

            //4: G ←G \v (This also removes all edges adjacent to v)
            duplicateGraph.removeIf(next -> Edge.isContainsVertex(next, maxVertex));

            //5: C ←C ∪v
            list.add(maxVertex);
        }
        //6: return C
        return list;
    }

    // PreCondition: size of edges must be at least 1
    // Return: the vertex with maximum degree
    public static int getMaxDegreeVertex(ArrayList<Edge> edges) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Edge edge : edges) {
            map.put(edge.u, map.getOrDefault(edge.u, 1) + 1);
            map.put(edge.v, map.getOrDefault(edge.v, 1) + 1);
        }
        int maxKey = -1, maxValue = -1;
        for (Map.Entry<Integer, Integer> val : map.entrySet()) {
            if (val.getValue() > maxValue) {
                maxKey = val.getKey();
                maxValue = val.getValue();
            }
        }
        return maxKey;
    }

}






