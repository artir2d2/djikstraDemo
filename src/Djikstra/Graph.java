package Djikstra;

import java.util.List;

public class Graph {
    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertices = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

	public void initGraph(int nodeCount) {
		for (int i = 0; i < nodeCount; i++) {
			Vertex location = new Vertex("Node_" + i, "Node_" + i);
			vertices.add(location);
		}
	}
	
	
}