package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.graph.entity.GraphDbEntity;
import org.springframework.stereotype.Component;

@Component
public class DomainBuilder {

    GraphAggregate buildAggregate(GraphDbEntity graphDbEntity) {
        var graphAggregate = new GraphAggregate.GraphBuilder()
                .withId(graphDbEntity.getId())
                .withName(graphDbEntity.getName())
                .build();
        addVertices(graphDbEntity, graphAggregate);
        addEdges(graphDbEntity, graphAggregate);
        return graphAggregate;
    }

    private void addVertices(GraphDbEntity graphDbEntity, GraphAggregate aggregate) {
        graphDbEntity.getVertices()
                .forEach(vertex -> aggregate.addVertex(vertex.getId(), vertex.getName()));
    }

    private void addEdges(GraphDbEntity graphDbEntity, GraphAggregate aggregate) {
        graphDbEntity.getVertices()
                .stream()
                .flatMap(vertex -> vertex.getOutcomeEdges().stream())
                .forEach(edge -> aggregate.addEdge(
                        edge.getName(),
                        edge.getId(),
                        edge.getDirectedFromVertex().getName(),
                        edge.getDirectedToVertex().getName()
                ));
    }
}
