package com.twentyeightstone.graphdemo.graph;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter(AccessLevel.PACKAGE)
class Vertex {

    private final Long id;

    @EqualsAndHashCode.Include
    private final String name;

    private final List<Edge> edges = new ArrayList<>();

    Vertex(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    void addEdge(Long edgeId, String edgeName, Vertex directedTo) {
        edges.add(Edge.builder()
                .id(edgeId)
                .name(edgeName)
                .directedToVertex(directedTo)
                .build()
        );
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
