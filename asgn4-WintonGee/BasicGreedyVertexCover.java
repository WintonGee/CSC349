import java.util.ArrayList;
import java.util.Collections;

// 2-Approximation Algorithm
public class BasicGreedyVertexCover {

    //Input: A graph G.
    //Output: A set of vertices that form a (not necessarily optimal) vertex cover.
    //1: C ←∅
    //2: while G has at least one edge do
    //3: (u, v) ← any edge in G
    //4: G ←G \{u, v} (This also removes all edges adjacent to u and v)
    //5: C ←C ∪{u, v}
    //6: return C

    public static ArrayList<Integer> get(ArrayList<Edge> graph) {
        ArrayList<Edge> duplicateGraph = new ArrayList<>(graph);

        //1: C ←∅
        ArrayList<Integer> list = new ArrayList<Integer>();
        //2: while G has at least one edge do
        while (duplicateGraph.size() > 0) {
            //3: (u, v) ← any edge in G
            Edge e = duplicateGraph.get(0);

            //4: G ←G \{u, v} (This also removes all edges adjacent to u and v)
            duplicateGraph.removeIf(next -> Edge.isAnySharedVertex(e, next));

            //5: C ←C ∪{u, v}
            list.add(e.u);
            list.add(e.v);
        }

        //6: return C
        return list;
    }

}
