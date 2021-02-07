package com.twentyeightstone.graphdemo.service;

import com.twentyeightstone.graphdemo.dto.input.EdgeInputDTO;
import com.twentyeightstone.graphdemo.dto.output.GraphOutputDTO;
import com.twentyeightstone.graphdemo.graph.GraphAggregate;
import com.twentyeightstone.graphdemo.graph.OutputDtoBuilder;
import com.twentyeightstone.graphdemo.repository.GraphRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GraphMainService implements GraphService {

    private final GraphRepository repository;

    private final OutputDtoBuilder outputDtoBuilder;

    @Override
    public GraphOutputDTO getGraphStructure(Long graphId) {
        GraphAggregate aggregate = repository.retrieveById(graphId);
        return outputDtoBuilder.buildFullGraphDTO(aggregate);
    }

    @Override
    public void addVertex(String vertexName, Long graphId) {
        GraphAggregate aggregate = repository.retrieveById(graphId);
        aggregate.addVertex(vertexName);
        repository.save(aggregate);
    }

    @Override
    public void addEdge(EdgeInputDTO dto, Long graphId) {
        GraphAggregate aggregate = repository.retrieveById(graphId);
        aggregate.addEdge(dto.getEdgeName(), dto.getTailVertexName(), dto.getHeadVertexName());
        repository.save(aggregate);
    }
}
