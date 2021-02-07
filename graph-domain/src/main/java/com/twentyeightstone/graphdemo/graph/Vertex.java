package com.twentyeightstone.graphdemo.graph;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter(AccessLevel.PACKAGE)
class Vertex {

    private final Long id;

    @EqualsAndHashCode.Include
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
            if(edge.getHeadToVertex().equals(directedTo)) {
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

}
