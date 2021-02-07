package com.twentyeightstone.graphdemo.graph;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

class Graph {

    @Getter(AccessLevel.PACKAGE)
    private final Long graphId;

    @Getter(AccessLevel.PACKAGE)
    private final String graphName;

    @Getter(AccessLevel.PACKAGE)
    private final List<Vertex> vertices = new ArrayList<>();

    @Getter(AccessLevel.PACKAGE)
    private final List<Vertex> removedVertices = new ArrayList<>();

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

    void addEdge(String edgeName, Long edgeId, String tailFromVertexName, String headToVertexName) {
        Vertex sourceVertex = findVertexByName(tailFromVertexName);
        Vertex targetVertex = findVertexByName(headToVertexName);
        sourceVertex.addEdge(edgeId, edgeName, targetVertex);
    }

    void removeVertex(String name) {
        Vertex vertexToRemove = findVertexByName(name);
        removedVertices.add(vertexToRemove);
        vertices.remove(vertexToRemove);
        vertices.forEach(vertex -> vertex.removeDirectEdgesTo(vertexToRemove));
    }

    void removeAllVertices() {
        removedVertices.addAll(vertices);
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

    Set<Long> getRemovedVerticesIds() {
        return removedVertices
                .stream()
                .map(Vertex::getId)
                .collect(Collectors.toSet());
    }

    Set<Long> getRemovedEdgesIds() {
        return vertices
                .stream()
                .map(Vertex::getRemovedEdgesIds)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    boolean isConnected() {
        if(vertices.isEmpty()) { //todo return exception invalid state
            return false;
        }
        return breadthFirstSearchTraverse().entrySet()
                .stream()
                .allMatch(entry -> entry.getValue().containsAll(vertices));
    }

    //todo getFullyConnectedVertices()
    // -- List<Name>
    //todo getNonFullyConnectedVertices()
    // -- List<ReportPerVertex>
    // -- ReportPerVertex ->
    // ----- List<VertexName>

    private Map<Vertex, List<Vertex>> breadthFirstSearchTraverse() {
        return vertices.stream().collect(Collectors.toMap(identity(), this::breadthFirstSearchTraverse));
    }

    private List<Vertex> breadthFirstSearchTraverse(Vertex vertex) {
        Map<Vertex, Boolean> visited = vertices.stream()
                .collect(Collectors.toMap(identity(), v -> false));

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(vertex);
        visited.put(vertex, true);

        while (!queue.isEmpty()) {
            Vertex polledVertex = queue.poll();

            polledVertex.getEdges()
                    .stream()
                    .filter(edge -> !visited.get(edge.getHeadToVertex()))
                    .forEach(edge -> {
                        visited.put(edge.getHeadToVertex(), true);
                        queue.add(edge.getHeadToVertex());
                    });
        }
        return visited.entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
