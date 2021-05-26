public class TriestBase implements DataStreamAlgo {
    double numTriangles = 0;
    int memorySize;
    int Time = 0;
    ListGraph graph;

    public TriestBase(int samsize) {
        memorySize = samsize;
        graph = new ListGraph();
    } //TriestBase()

    public void handleEdge(Edge edge) {
        if (Time < memorySize) {
            graph.addEdge(edge);
            Time++;
            if (edge.u != edge.v) {
                numTriangles = numTriangles + graph.findTriangles(edge.u, edge.v);
            }

        } else {
            Time++;
            double weight = (double) memorySize / Time;
            if (Math.random() <= weight) {
                int index = (int) (Math.random() * graph.edges.size() - 1);
                Edge randomEdge = graph.edges.get(index);
                int vertex1 = randomEdge.u;
                int vertex2 = randomEdge.v;
                if (vertex1 != vertex2) {
                    numTriangles = numTriangles - graph.findTriangles(vertex1, vertex2);
                }
                graph.removeEdge(randomEdge, index);
                graph.addEdge(edge);

                if (edge.u != edge.v) {
                    numTriangles = numTriangles + graph.findTriangles(edge.u, edge.v);
                }
            }
        }
    } // handleEdge()

    public double getWeight() {
        return ((double) memorySize / Time) * ((double) (memorySize - 1) / (Time - 1)) *
                ((double) (memorySize - 2) / (Time - 2));
    } //getWeight()

    public int getEstimate() {
        if (Time < memorySize) {
            return (int) numTriangles;
        }
        return (int) (numTriangles / getWeight());
    } //getEstimate()
}
