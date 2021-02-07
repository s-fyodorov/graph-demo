package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.entities.BaseDbEntity;
import com.twentyeightstone.graphdemo.entities.EdgeDbEntity;
import com.twentyeightstone.graphdemo.entities.GraphDbEntity;
import com.twentyeightstone.graphdemo.entities.VertexDbEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Component
public class DbEntityBuilder {

    public GraphDbEntity buildEntity(GraphAggregate aggregate) {
        //todo throw exception if null graph
        Graph graph = aggregate.getGraph();
        var graphDbEntity = buildGraph(graph);

        Map<String, VertexDbEntity> verticesPerUniqName = graph.getVertices()
                .stream()
                .map(vertex -> buildAndAssignVertex(vertex, graphDbEntity))
                .collect(Collectors.toMap(BaseDbEntity::getName, identity()));

        graph.getVertices()
                .forEach(vertex -> buildAndAssignEdges(vertex, verticesPerUniqName));
        return graphDbEntity;
    }


    private VertexDbEntity buildAndAssignVertex(Vertex vertex, GraphDbEntity graph) {
        var vertexDbEntity = VertexDbEntity.builder()
                .id(vertex.getId())
                .name(vertex.getName())
                .build();
        graph.addVertex(vertexDbEntity);
        return vertexDbEntity;
    }

    private GraphDbEntity buildGraph(Graph graph) {
        return GraphDbEntity.builder()
                .id(graph.getGraphId())
                .name(graph.getGraphName())
                .build();
    }

    private void buildAndAssignEdges(Vertex vertex, Map<String, VertexDbEntity> dbVertices) {
        vertex.getEdges()
                .forEach(edge -> buildAndAssignEdge(edge, vertex, dbVertices));
    }

    private void buildAndAssignEdge(Edge edge, Vertex vertexFrom, Map<String, VertexDbEntity> dbVertices) {
        var edgeDbEntity = EdgeDbEntity.builder()
                .id(edge.getId())
                .name(edge.getName())
                .directedFromVertex(dbVertices.get(vertexFrom.getName()))
                .directedToVertex(dbVertices.get(edge.getDirectedToVertex().getName()))
                .build();
        dbVertices.get(vertexFrom.getName())
                .addOutComeEdge(edgeDbEntity);
        dbVertices.get(edge.getDirectedToVertex().getName())
                .addIncomeEdge(edgeDbEntity);
    }
}
