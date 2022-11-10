import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

// This class will be used for determining the strongly connected components
public class StronglyConnectedHandler {

    ArrayList<Vertex> list;

    public StronglyConnectedHandler(ArrayList<Vertex> newList) {
        this.list = newList;
    }

    // Determining the strong cycles, the order being checked with be based on the post order -> pre order
    // Strongly Connected: When each vertex can reach each other
    public void handle() {
        // Decide the order of which vertex to check by using post and pre order
        ArrayList<Vertex> sortedByPostOrderList = getSortedListByPostOrder();

        ArrayList<StronglyConnected> stronglyConnectedComponents = new ArrayList<>();
        StronglyConnected component = new StronglyConnected();
        for (Vertex vertex : sortedByPostOrderList) {
            if (component.canConnect(vertex)) {
                component.connect(vertex);
                continue;
            }

            stronglyConnectedComponents.add(component);

            // Next Strongly Connected Component
            component = new StronglyConnected();
            component.connect(vertex);
        }

        if (component.connections.size() > 0)
            stronglyConnectedComponents.add(component);

        // Printing out
        System.out.println(stronglyConnectedComponents.size() + " Strongly Connected Component(s):");
        stronglyConnectedComponents.forEach(StronglyConnected::print);
    }

    public ArrayList<Vertex> getSortedListByPostOrder() {
        if (this.list.size() == 0)
            return this.list;

        ArrayList<Vertex> newList = new ArrayList<>(this.list);
        newList.sort(Comparator.comparing(o -> o.postOrder));
        Collections.reverse(newList);
//        newList.forEach(c -> System.out.println(c.key + " Pre: " + c.preOrder + " , Post: "+ c.postOrder));
        return newList;
    }

    public static class StronglyConnected {
        ArrayList<Integer> connections = new ArrayList<>();

        public StronglyConnected() {

        }

        public boolean canConnect(Vertex v) {
            return connections.size() == 0 || v.reachableVertices.containsAll(connections);
        }

        public void connect(Vertex v) {
            connections.add(v.key);
        }

        public void print() {
            Collections.sort(connections);
            String s = connections.stream().
                    map(Object::toString).
                    collect(Collectors.joining(", "));
            System.out.println(s);
        }


    }


}
