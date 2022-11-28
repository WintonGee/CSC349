import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

// Important note: All graphs will begin at 0
// Edges are undirected
public class ApproximationAlgorithms {

    public static void main(String[] args) throws IOException {
        if (args.length == 0)
            return;
        // Reading the input and file
        File file = new File(args[0]);

        // Setting the data into a list
        ArrayList<Edge> edges = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = fileReader.readLine()) != null) {
            String[] data = line.split(" ");
            int firstVal = Integer.parseInt(data[0]), secondVal = Integer.parseInt(data[1]);
            int firstVertex = Math.min(firstVal, secondVal), secondVertex = Math.max(firstVal, secondVal);
            edges.add(new Edge(firstVertex, secondVertex));
            // edges.add(new Edge(firstVal, secondVal));
        }

        // Sort the edges into ascending order since graph starts at 0
        edges.sort(Comparator.comparing(val -> val.v));
        edges.sort(Comparator.comparing(val -> val.u));

        // Test / debug
//        for (Edge e : edges)
//            System.out.println(e);

        ArrayList<Integer> logApproxList = SmartGreedyVertexCover.get(edges);
        System.out.print("log-Approximation: ");
        printList(logApproxList);

        ArrayList<Integer> twoApproxList = BasicGreedyVertexCover.get(edges);
        System.out.print("2-Approximation: ");
        printList(twoApproxList);

        // Exact list
        ArrayList<Integer> exactList = ExactGreedyVertexCover.get(edges);
        Collections.sort(exactList);
        System.out.print("Exact Solution: ");
        printList(exactList);
    }

    public static void printList(ArrayList<Integer> list) {
        System.out.println(list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" ")));
    }

}
