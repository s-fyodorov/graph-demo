package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.Aggregate;
import lombok.AccessLevel;
import lombok.Getter;

public class GraphAggregate implements Aggregate {

    @Getter(AccessLevel.PACKAGE)
    private final Graph graph;

    private GraphAggregate(GraphBuilder builder) {
        graph = new Graph(builder.graphId, builder.graphName);
    }

    public void addVertex(String name) {
        graph.addVertex(name);
    }

    public void addEdge(String sourceVertexName, String targetVertexName, String edgeName) {
        graph.addEdge(sourceVertexName, targetVertexName, edgeName);
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
