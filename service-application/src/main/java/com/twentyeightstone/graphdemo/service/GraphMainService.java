package com.twentyeightstone.graphdemo.service;

import com.twentyeightstone.graphdemo.dto.input.EdgeInputDTO;
import com.twentyeightstone.graphdemo.graph.OutputDtoBuilder;
import com.twentyeightstone.graphdemo.repository.GraphRepository;
import com.twentyeightstone.graphdemo.dto.output.GraphOutputDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class GraphMainService implements GraphService {

    private final GraphRepository repository;

    private final OutputDtoBuilder outputDtoBuilder;

    @Override
    public GraphOutputDTO getGraphStructure(Long graphId) {
        GraphAggregate aggregate = getGraphById(graphId);
        return outputDtoBuilder.buildFullGraphDTO(aggregate);
    }

    @Override
    public void createGraph(String graphName) {
        if(repository.isExistByName(graphName)) {
            throw new RuntimeException(""); //todo fixme
        }
        var aggregate = new GraphAggregate.GraphBuilder()
                .withName(graphName)
                .build();
        repository.save(aggregate);
    }

    @Override
    public void addVertex(String vertexName, Long graphId) {
        GraphAggregate aggregate = getGraphById(graphId);
        aggregate.addVertex(vertexName);
        repository.save(aggregate);
    }

    @Override
    public void addEdge(EdgeInputDTO dto, Long graphId) {
        GraphAggregate aggregate = getGraphById(graphId);
        aggregate.addEdge(dto.getEdgeName(), dto.getTailVertexName(), dto.getHeadVertexName());
        repository.save(aggregate);
    }

    @Override
    public boolean isGraphConnected(Long graphId) {
        return getGraphById(graphId).isConnected();
    }

    private GraphAggregate getGraphById(Long id) {
        return repository.retrieveById(id)
                .orElseThrow(() -> new RuntimeException("")); // todo fix
    }
}
