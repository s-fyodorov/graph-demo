package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.graph.entity.BaseDbEntity;
import com.twentyeightstone.graphdemo.graph.entity.EdgeDbEntity;
import com.twentyeightstone.graphdemo.graph.entity.GraphDbEntity;
import com.twentyeightstone.graphdemo.graph.entity.VertexDbEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Component
class DbEntityBuilder {

    List<EdgeDbEntity> buildEntities(GraphAggregate aggregate) {
        //todo throw exception if null graph
        Graph graph = aggregate.getGraph();
        Map<String, VertexDbEntity> dbVertices = graph.getVertices()
                .stream()
                .map(vertex -> buildVertex(vertex, graph))
                .collect(Collectors.toMap(BaseDbEntity::getName, identity()));

        return graph.getVertices().stream()
                .map(vertex -> buildEdges(vertex, dbVertices))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private VertexDbEntity buildVertex(Vertex vertex, Graph graph) {
        return VertexDbEntity.builder()
                .id(vertex.getId())
                .name(vertex.getName())
                .graph(buildGraph(graph))
                .build();
    }

    private GraphDbEntity buildGraph(Graph graph) {
        return GraphDbEntity.builder()
                .id(graph.getGraphId())
                .name(graph.getGraphName())
                .build();
    }

    private List<EdgeDbEntity> buildEdges(Vertex vertex, Map<String, VertexDbEntity> dbVertices) {
        return vertex.getEdges().stream()
                .map(edge -> buildEdge(edge, vertex, dbVertices))
                .collect(Collectors.toList());
    }

    private EdgeDbEntity buildEdge(Edge edge, Vertex vertexFrom, Map<String, VertexDbEntity> dbVertices) {
        return EdgeDbEntity.builder()
                .directedFromVertex(dbVertices.get(vertexFrom.getName()))
                .directedToVertex(dbVertices.get(edge.getDirectedToVertex().getName()))
                .build();
    }
}
