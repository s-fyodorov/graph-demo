package com.twentyeightstone.graphdemo.domain;

import lombok.AccessLevel;
import lombok.Getter;

public class GraphAggregate {

    @Getter(AccessLevel.PACKAGE)
    private final Graph graph;

    private GraphAggregate(GraphBuilder builder) {
        graph = new Graph(builder.graphId, builder.graphName);
    }

    void addVertex(String name) {
        graph.addVertex(name);
    }

    void addEdge(String sourceVertexName, String targetVertexName, String edgeName) {
        graph.addEdge(sourceVertexName, targetVertexName, edgeName);
    }

    void removeVertex(String name) {
        graph.removeVertex(name);
    }

    void removeAllVertices() {
        graph.removeAllVertices();
    }

    void removeAllDirectEdgesBetween(String firstVertexName, String secondVertexName) {
        graph.removeAllDirectEdgesBetween(firstVertexName, secondVertexName);
    }

    void removeAllOutComeEdgesForVertex(String vertexName) {
        graph.removeAllOutComeEdgesForVertex(vertexName);
    }

    void removeAllEdges() {
        graph.removeAllEdges();
    }

    boolean isConnected() {
        return graph.isConnected();
    }

    public static class GraphBuilder {
        private long graphId;
        private String graphName;

        public GraphBuilder withId(long id) {
            graphId = id;
            return this;
        }

        public GraphBuilder withName(String name) {
            graphName = name;
            return this;
        }

        public GraphAggregate build() {
            return new GraphAggregate(this);
        }
    }
}
