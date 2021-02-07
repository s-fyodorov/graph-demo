package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.exception.UniqueNameConstraintException;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Getter(AccessLevel.PACKAGE)
class Vertex {

    private final Long id;

    private final String name;

    private final List<Edge> edges = new ArrayList<>();

    private final List<Edge> removedEdges = new ArrayList<>();

    Vertex(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    void addEdge(Long edgeId, String edgeName, Vertex directedTo) {
        edges.add(Edge.builder()
                .id(edgeId)
                .name(edgeName)
                .headToVertex(directedTo)
                .build()
        );
    }

    void removeDirectEdgesTo(Vertex directedTo) {
        var iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.getHeadToVertex().equals(directedTo)) {
                removedEdges.add(edge);
                iterator.remove();
            }
        }
    }

    void removeAllEdges() {
        removedEdges.addAll(edges);
        edges.clear();
    }

    Set<Long> getRemovedEdgesIds() {
        return removedEdges
                .stream()
                .map(Edge::getId)
                .collect(Collectors.toSet());
    }

    boolean isEqualsByName(String name) {
        return Objects.equals(this.name, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(id, vertex.id) &&
                name.equals(vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
