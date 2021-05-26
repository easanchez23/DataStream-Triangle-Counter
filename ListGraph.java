import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ListGraph {
    HashMap<Integer, HashSet<Integer>> map;
    ArrayList<Edge> edges = new ArrayList<>();

    public ListGraph() {
        map = new HashMap<>();
    }

    public void addEdge(Edge edge) {
        int first = edge.u;
        int second = edge.v;
        if (!map.containsKey(first)) {
            map.put(first, new HashSet<>());
        }
        if (!map.containsKey(second)) {
            map.put(second, new HashSet<>());
        }
//        if (!map.get(first).contains(second) && !map.get(second).contains(first)) {
            edges.add(edge);
            map.get(first).add(second);
            map.get(second).add(first);
//        }
    } //addEdge()

    public void removeEdge(Edge edge, int index) {
        int first = edge.u;
        int second = edge.v;
        if (map.get(first).contains(second)) {
            map.get(first).remove(second);
            if (index != -1) {
                edges.remove(index);
            } else {
                edges.remove(edge);
            }
            if (first != second) {
                map.get(second).remove(first);
            }
            if (map.get(first).isEmpty()) {
                map.remove(first);
            }
            if (first != second && map.get(second).isEmpty()) {
                map.remove(second);
            }
        }
    } //removeEdge()

    public int findTriangles(int vertex1, int vertex2) {
        HashSet<Integer> v1 = new HashSet<>(map.get(vertex1));
        v1.remove(vertex2);
        HashSet<Integer> v2 = map.get(vertex2);
        v2.remove(vertex1);
        v1.retainAll(v2);
        v2.add(vertex1);
        return v1.size();
    } //findTriangles()
}

