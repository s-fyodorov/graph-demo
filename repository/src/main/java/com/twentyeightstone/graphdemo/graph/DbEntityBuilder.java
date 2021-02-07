package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.entities.BaseDbEntity;
import com.twentyeightstone.graphdemo.entities.EdgeDbEntity;
import com.twentyeightstone.graphdemo.entities.GraphDbEntity;
import com.twentyeightstone.graphdemo.entities.VertexDbEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Component
public class DbEntityBuilder {

    public GraphDbEntity buildEntities(GraphAggregate aggregate) {
        //todo throw exception if null graph
        Graph graph = aggregate.getGraph();
        var graphDbEntity = buildGraph(graph);

        Map<String, VertexDbEntity> verticesPerUniqName = buildAndAssignVertices(graph, graphDbEntity)
                .stream()
                .collect(Collectors.toMap(BaseDbEntity::getName, identity()));

        graph.getVertices()
                .forEach(vertex -> buildAndAssignEdges(vertex, verticesPerUniqName));
        return graphDbEntity;
    }

    GraphDbEntity buildGraph(Graph graph) {
        return GraphDbEntity.builder()
                .id(graph.getGraphId())
                .name(graph.getGraphName())
                .build();
    }

    private List<VertexDbEntity> buildAndAssignVertices(Graph graph, GraphDbEntity graphDbEntity) {
        return graph.getVertices()
                .stream()
                .map(vertex -> buildAndAssignVertex(vertex, graphDbEntity))
                .collect(Collectors.toList());
    }

    VertexDbEntity buildAndAssignVertex(Vertex vertex, GraphDbEntity graph) {
        var vertexDbEntity = VertexDbEntity.builder()
                .id(vertex.getId())
                .name(vertex.getName())
                .build();
        graph.addVertex(vertexDbEntity);
        return vertexDbEntity;
    }

    private void buildAndAssignEdges(Vertex vertex, Map<String, VertexDbEntity> dbVertices) {
        vertex.getEdges()
                .forEach(edge -> buildAndAssignEdge(edge, vertex, dbVertices));
    }

    private void buildAndAssignEdge(Edge edge, Vertex vertexFrom, Map<String, VertexDbEntity> dbVertices) {
        var edgeDbEntity = EdgeDbEntity.builder()
                .id(edge.getId())
                .name(edge.getName())
                .tailFromVertex(dbVertices.get(vertexFrom.getName()))
                .headToVertex(dbVertices.get(edge.getHeadToVertex().getName()))
                .build();
        dbVertices.get(vertexFrom.getName())
                .addOutComeEdge(edgeDbEntity);
        dbVertices.get(edge.getHeadToVertex().getName())
                .addIncomeEdge(edgeDbEntity);
    }
}
