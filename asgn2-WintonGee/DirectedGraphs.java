import java.io.*;
import java.util.ArrayList;

public class DirectedGraphs {

    public static int order = 0;

    // This program assumes that the contents of the file is valid
    public static void main(String[] args) throws IOException {
        if (args.length == 0)
            return;
        // Reading the input and file
        File file = new File(args[0]);

        // Setting the data into a list
        ArrayList<Vertex> list = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = fileReader.readLine()) != null) {
            String[] data = line.split(", ");
            int firstVal = Integer.parseInt(data[0]), secondVal = Integer.parseInt(data[1]);

            // Adding in new values
            boolean shouldAddFirst = list.stream().noneMatch(c -> c.key == firstVal);
            boolean shouldAddSecond = list.stream().noneMatch(c -> c.key == secondVal);
            if (shouldAddFirst)
                list.add(new Vertex(firstVal));
            if (shouldAddSecond)
                list.add(new Vertex(secondVal));

            // Setting the neighbors
            list.stream().filter(c -> c.key == firstVal).forEach(c -> c.addNeighbor(secondVal));
        }

        // Setting the vertices that each vertex can reach, used for determining the strong cycle
        for (Vertex v : list)
            setReachables(list, v);

        // Setting the post and pre-orders
        order = 0;
        dfs(list);

        // Determining the strong cycles, the order being checked with be based on the post order -> pre order
        new StronglyConnectedHandler(list).handle();
    }

    public static void setReachables(ArrayList<Vertex> list, Vertex vertex) {
        for (Vertex v : list)
            v.discovered = false;
        explore(list, vertex);

        for (Vertex v : list)
            if (v.discovered)
                vertex.addReachable(v);
    }

    // Input: A graph G = (V,E).
    // Output: A forest of connected components of G.
    public static void dfs(ArrayList<Vertex> list) {
        //1: for all v∈V do
        //2: discovered(v) = false
        for (Vertex vertex : list)
            vertex.discovered = false;

        //3: for all v∈V do
        //4: if discovered(v) = false then 5: explore(G , v )
        for (Vertex vertex : list) {
            if (!vertex.discovered) {
                explore(list, vertex);
            }
        }
    }

    // Input: A graph G and a vertex v.
    // Output: Vertices labeled “discovered” are vertices reachable from v
    public static void explore(ArrayList<Vertex> list, Vertex vertex) {
        // discovered(v) = true
        vertex.discovered = true;

        // previsit(v)
        preVisit(vertex);

        // for all outgoing neighbors of v, u do
        vertex.outgoingVertices.forEach(u -> {
            list.stream()
                    .filter(b -> b.key == u)
                    .forEach(a -> {
                        if (!a.discovered)
                            explore(list, a);
                    });
        });

        // postvisit(v)
        postVisit(vertex);

    }

    public static void preVisit(Vertex v) {
        v.preOrder = order;
        order++;
    }

    public static void postVisit(Vertex v) {
        v.postOrder = order;
        order++;
    }


}
