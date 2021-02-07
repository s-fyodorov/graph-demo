package com.twentyeightstone.graphdemo.graph;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Builder
@EqualsAndHashCode
@Getter(AccessLevel.PACKAGE)
class Edge {

    private final Long id;

    private final String name;

    private final Vertex headToVertex;

}
