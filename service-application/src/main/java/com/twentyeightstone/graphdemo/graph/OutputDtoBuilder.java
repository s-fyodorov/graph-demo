package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.dto.output.EdgeOutputDTO;
import com.twentyeightstone.graphdemo.dto.output.VertexOutputDTO;
import com.twentyeightstone.graphdemo.dto.output.GraphOutputDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OutputDtoBuilder {

    public GraphOutputDTO buildFullGraphDTO(GraphAggregate aggregate) {
        Graph graph = aggregate.getGraph();
        return GraphOutputDTO.builder()
                .id(graph.getGraphId())
                .name(graph.getGraphName())
                .vertices(buildOutputVertexDTOs(graph.getVertices()))
                .build();

    }

    private List<VertexOutputDTO> buildOutputVertexDTOs(List<Vertex> vertices) {
        return vertices.stream()
                .map(this::buildOutputVertexDTO)
                .collect(Collectors.toList());
    }

    private VertexOutputDTO buildOutputVertexDTO(Vertex vertex) {
        return VertexOutputDTO.builder()
                .id(vertex.getId())
                .name(vertex.getName())
                .outcomeEdges(buildOutputEdgeDTOs(vertex.getEdges()))
                .build();
    }

    private List<EdgeOutputDTO> buildOutputEdgeDTOs(List<Edge> edges) {
        return edges.stream()
                .map(this::buildOutputEdgeDTO)
                .collect(Collectors.toList());
    }

    private EdgeOutputDTO buildOutputEdgeDTO(Edge edge) {
        return EdgeOutputDTO.builder()
                .id(edge.getId())
                .name(edge.getName())
                .headVertexId(edge.getHeadToVertex().getId())
                .headVertexName(edge.getHeadToVertex().getName())
                .build();
    }
}
