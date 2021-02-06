package com.twentyeightstone.graphdemo.graph;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder
class Edge {

    @Getter(AccessLevel.PACKAGE)
    private Long id;

    @Getter(AccessLevel.PACKAGE)
    private final String name;

    @Getter(AccessLevel.PACKAGE)
    private final Vertex directedToVertex;

//    Edge(Long id, String name, Vertex destination) {
//        this.name = name;
//        this.directedToVertex = destination;
//    }
}
