package com.twentyeightstone.graphdemo.service;

import com.twentyeightstone.graphdemo.dto.input.DeleteEdgeDTO;
import com.twentyeightstone.graphdemo.dto.input.NewEdgeDTO;
import com.twentyeightstone.graphdemo.dto.input.VertexInputDTO;
import com.twentyeightstone.graphdemo.dto.output.BaseOutputDTO;
import com.twentyeightstone.graphdemo.dto.output.GraphOutputDTO;
import com.twentyeightstone.graphdemo.exception.ApplicationException;
import com.twentyeightstone.graphdemo.exception.EntityNotFoundException;
import com.twentyeightstone.graphdemo.exception.UniqueNameConstraintException;
import com.twentyeightstone.graphdemo.graph.GraphAggregate;
import com.twentyeightstone.graphdemo.repository.GraphRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Service
@AllArgsConstructor
class GraphMainService implements GraphService {

    private final GraphRepository repository;

    private final GraphStructureOutputBuilder outputDtoBuilder;

    @Override
    public List<BaseOutputDTO> retrieveGraphHeaderList() {
        return repository.retrieveGraphHeaderList();
    }

    @Override
    public GraphOutputDTO getGraphStructure(Long graphId) {
        GraphAggregate aggregate = getGraphById(graphId);
        return outputDtoBuilder.build(aggregate);
    }

    @Override
    public GraphOutputDTO getGraphStructure(String graphName) {
        GraphAggregate aggregate = repository.retrieveByName(graphName)
                .orElseThrow(() ->
                        new EntityNotFoundException(format("Graph with name %s is not found", graphName))
                );
        return outputDtoBuilder.build(aggregate);
    }

    @Override
    @Transactional
    public BaseOutputDTO createGraph(String graphName) {
        if (repository.isExistByName(graphName)) {
            throw new UniqueNameConstraintException(format("Graph with name %s already exists", graphName));
        }
        var aggregate = new GraphAggregate.GraphBuilder()
                .withName(graphName)
                .build();
        repository.save(aggregate);
        return repository.retrieveGraphHeaderByName(graphName)
                .orElseThrow(() -> new ApplicationException("Invalid application state"));
    }

    @Override
    public void addVertex(VertexInputDTO dto, Long graphId) {
        GraphAggregate aggregate = getGraphById(graphId);
        aggregate.addVertex(dto.getVertexName());
        repository.save(aggregate);
    }

    @Override
    public void addEdge(NewEdgeDTO dto, Long graphId) {
        GraphAggregate aggregate = getGraphById(graphId);
        aggregate.addEdge(dto.getEdgeName(), dto.getFromVertexName(), dto.getToVertexName());
        repository.save(aggregate);
    }

    @Override
    public boolean isGraphConnected(Long graphId) {
        return getGraphById(graphId).isConnected();
    }

    @Override
    public void deleteGraph(Long graphId) {
        repository.delete(graphId);
    }

    @Override
    public void deleteVertex(VertexInputDTO dto, Long graphId) {
        GraphAggregate aggregate = getGraphById(graphId);
        aggregate.removeVertex(dto.getVertexName());
        repository.save(aggregate);
    }

    @Override
    public void deleteAllVertices(Long graphId) {
        GraphAggregate aggregate = getGraphById(graphId);
        aggregate.removeAllVertices();
        repository.save(aggregate);
    }

    @Override
    public void deleteEdgesBetween(DeleteEdgeDTO dto, Long graphId) {
        GraphAggregate aggregate = getGraphById(graphId);
        aggregate.removeAllDirectEdgesBetween(dto.getFirstVertexName(), dto.getSecondVertexName());
        repository.save(aggregate);
    }

    private GraphAggregate getGraphById(Long id) {
        return repository.retrieveById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Graph with id %s is not found", id)));
    }
}
