public class TriestImpr implements DataStreamAlgo {
    double numTriangles = 0;
    double memorySize;
    double Time = 0.0;
    ListGraph graph;

    public TriestImpr(int samsize) {
        memorySize = samsize;
        graph = new ListGraph();

    } //TriestImpr

    public void handleEdge(Edge edge) {

        Time++;
        if (edge.u != edge.v) {
            graph.addEdge(edge);
            numTriangles = numTriangles + (getWeight() * graph.findTriangles(edge.u, edge.v));

        }

        if (Time >= memorySize) {
            double weight = memorySize / Time;
            if (Math.random() <= weight) {
                int index = (int) (Math.random() * graph.edges.size() - 1);
                Edge randomEdge = graph.edges.get(index);
                graph.removeEdge(randomEdge, index);

            } else {
                if(edge.u != edge.v) graph.removeEdge(edge, -1);
            }
        }
    } //handleEdge()

    public double getWeight() {
        return Math.max(1, ((Time - 1) / (memorySize - 1)) * ((Time - 2) / (memorySize)));

    } //getWeight()

    public int getEstimate() {
        return (int) numTriangles;
    } //getEstimate()
}
