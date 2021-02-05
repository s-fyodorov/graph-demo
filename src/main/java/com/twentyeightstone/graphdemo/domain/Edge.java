package com.twentyeightstone.graphdemo.domain;

import lombok.AccessLevel;
import lombok.Getter;

class Edge {

    @Getter(AccessLevel.PACKAGE)
    private Long id;

    @Getter(AccessLevel.PACKAGE)
    private final String name;

    @Getter(AccessLevel.PACKAGE)
    private final Vertex directedToVertex;

    Edge(String name, Vertex destination) {
        this.name = name;
        this.directedToVertex = destination;
    }
}