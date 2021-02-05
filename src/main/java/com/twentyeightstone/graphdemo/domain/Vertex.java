package com.twentyeightstone.graphdemo.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Vertex {

    @Getter(AccessLevel.PACKAGE)
    private final Long id;

    @Getter(AccessLevel.PACKAGE)
    @EqualsAndHashCode.Include
    private final String name;

    @Getter(AccessLevel.PACKAGE)
    private final List<Edge> edges = new ArrayList<>();

    Vertex(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    void addEdge(String edgeName, Vertex destination) {
        edges.add(new Edge(edgeName, destination));
    }

    void removeDirectEdgesTo(Vertex directedTo) {
        edges.removeIf(edge -> edge.getDirectedToVertex().equals(directedTo));
    }

    void removeAllEdges() {
        edges.clear();
    }

    boolean isEqualsByName(String name) {
        return Objects.equals(this.name, name);
    }

}
