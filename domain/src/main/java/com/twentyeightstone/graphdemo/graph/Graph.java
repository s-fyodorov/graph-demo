package com.twentyeightstone.graphdemo.graph;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

class Graph {

    @Getter(AccessLevel.PACKAGE)
    private final Long graphId;

    @Getter(AccessLevel.PACKAGE)
    private final String graphName;

    @Getter(AccessLevel.PACKAGE)
    private final List<Vertex> vertices = new ArrayList<>();

    public Graph(Long graphId, String graphName) {
        this.graphId = graphId;
        this.graphName = graphName;
    }

    void addVertex(String name) {
        //todo validate name
        vertices.add(new Vertex(null, name));
    }

    void addVertex(Long id, String name) {
        //todo validate name
        vertices.add(new Vertex(id, name));
    }

    void addEdge(String edgeName, Long edgeId, String sourceVertexName, String targetVertexName) {
        Vertex sourceVertex = findVertexByName(sourceVertexName);
        Vertex targetVertex = findVertexByName(targetVertexName);
        sourceVertex.addEdge(edgeId, edgeName, targetVertex);
    }

    void removeVertex(String name) {
        Vertex vertexToRemove = findVertexByName(name);
        vertices.remove(vertexToRemove);
        vertices.forEach(vertex -> vertex.removeDirectEdgesTo(vertexToRemove));
    }

    void removeAllVertices() {
        vertices.clear();
    }

    void removeAllDirectEdgesBetween(String firstVertexName, String secondVertexName) {
        Vertex first = findVertexByName(firstVertexName);
        Vertex second = findVertexByName(secondVertexName);
        first.removeDirectEdgesTo(second);
        second.removeDirectEdgesTo(first);
    }

    void removeAllOutComeEdgesForVertex(String vertexName) {
        findVertexByName(vertexName)
                .removeAllEdges();
    }

    void removeAllEdges() {
        vertices.forEach(Vertex::removeAllEdges);
    }

    private Vertex findVertexByName(String name) {
        return vertices.stream().filter(vertex -> vertex.isEqualsByName(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("")); //todo throw correct exception
    }

    boolean isConnected() {
        if(vertices.isEmpty()) { //todo return exception invalid state
            return false;
        }
        return breadthFirstSearch().entrySet()
                .stream()
                .allMatch(entry -> entry.getValue().containsAll(vertices));
    }

    //todo getFullyConnectedVertices()
    // -- List<Name>
    //todo getNonFullyConnectedVertices()
    // -- List<ReportPerVertex>
    // -- ReportPerVertex ->
    // ----- List<VertexName>

    private Map<Vertex, List<Vertex>> breadthFirstSearch() {
        return vertices.stream().collect(Collectors.toMap(identity(), this::breadthFirstSearch));
    }

    private List<Vertex> breadthFirstSearch(Vertex vertex) {
        Map<Vertex, Boolean> visited = vertices.stream()
                .collect(Collectors.toMap(identity(), v -> false));

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(vertex);
        visited.put(vertex, true);

        while (!queue.isEmpty()) {
            Vertex polledVertex = queue.poll();

            polledVertex.getEdges()
                    .stream()
                    .filter(edge -> !visited.get(edge.getDirectedToVertex()))
                    .forEach(edge -> {
                        visited.put(edge.getDirectedToVertex(), true);
                        queue.add(edge.getDirectedToVertex());
                    });
        }
        return visited.entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
