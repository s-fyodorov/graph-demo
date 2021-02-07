package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.Aggregate;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.Set;

@Getter(AccessLevel.PACKAGE)
public class GraphAggregate implements Aggregate {

    private final Graph graph;

    private GraphAggregate(GraphBuilder builder) {
        graph = new Graph(builder.graphId, builder.graphName);
    }

    public void addVertex(String name) {
        graph.addVertex(name);
    }

    void addVertex(Long id, String name) {
        graph.addVertex(id, name);
    }

    public void addEdge(String edgeName, String sourceVertexName, String targetVertexName) {
        graph.addEdge(edgeName, null, sourceVertexName, targetVertexName);
    }

    void addEdge(String edgeName, Long edgeId, String tailFromVertexName, String headToVertexName) {
        graph.addEdge(edgeName, edgeId, tailFromVertexName, headToVertexName);
    }

    public void removeVertex(String name) {
        graph.removeVertex(name);
    }

    public void removeAllVertices() {
        graph.removeAllVertices();
    }

    public void removeAllDirectEdgesBetween(String firstVertexName, String secondVertexName) {
        graph.removeAllDirectEdgesBetween(firstVertexName, secondVertexName);
    }

    public void removeAllOutComeEdgesForVertex(String vertexName) {
        graph.removeAllOutComeEdgesForVertex(vertexName);
    }

    public void removeAllEdges() {
        graph.removeAllEdges();
    }

    public boolean isConnected() {
        return graph.isConnected();
    }

    public Set<Long> getRemovedVerticesIds() {
        return graph.getRemovedVerticesIds();
    }

    public Set<Long> getRemovedEdgesIds() {
        return graph.getRemovedEdgesIds();
    }

    public static class GraphBuilder {
        private Long graphId;
        private String graphName;

        public GraphBuilder withId(Long id) {
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
