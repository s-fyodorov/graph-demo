package com.twentyeightstone.graphdemo;

import com.twentyeightstone.graphdemo.dao.EdgeDAO;
import com.twentyeightstone.graphdemo.dao.GraphDAO;
import com.twentyeightstone.graphdemo.dao.VertexDAO;
import com.twentyeightstone.graphdemo.dto.output.BaseOutputDTO;
import com.twentyeightstone.graphdemo.entities.GraphDbEntity;
import com.twentyeightstone.graphdemo.exception.EntityNotFoundException;
import com.twentyeightstone.graphdemo.graph.GraphAggregate;
import com.twentyeightstone.graphdemo.repository.GraphRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
class GraphMainRepository implements GraphRepository {

    private final GraphDAO graphDAO;
    private final VertexDAO vertexDAO;
    private final EdgeDAO edgeDAO;
    private final DbRootEntityBuilder<GraphDbEntity, GraphAggregate> dbEntityBuilder;
    private final DomainBuilder<GraphAggregate, GraphDbEntity> aggregateBuilder;

    @Override
    @Transactional
    public void save(GraphAggregate aggregate) {
        graphDAO.save(dbEntityBuilder.build(aggregate));
        deleteRemovedVertices(aggregate);
        deleteRemovedEdges(aggregate);
    }

    @Override
    public List<BaseOutputDTO> retrieveGraphHeaderList() {
        return graphDAO.findAll()
                .stream()
                .map(graph -> new BaseOutputDTO(graph.getId(), graph.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BaseOutputDTO> retrieveGraphHeaderByName(String name) {
        return graphDAO.findGraphDbEntityByName(name)
                .map(graph -> new BaseOutputDTO(graph.getId(), graph.getName()));
    }

    @Override
    public Optional<GraphAggregate> retrieveById(Long id) {
        return graphDAO.findById(id)
                .map(aggregateBuilder::buildAggregate);
    }

    @Override
    public Optional<GraphAggregate> retrieveByName(String name) {
        return graphDAO.findGraphDbEntityByName(name)
                .map(aggregateBuilder::buildAggregate);
    }

    @Override
    public void delete(Long id) {
        try {
            graphDAO.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(String.format("Graph with id %s is not exist", id));
        }
    }

    @Override
    public boolean isExistByName(String name) {
        return graphDAO.findGraphDbEntityByName(name).isPresent();
    }

    private void deleteRemovedVertices(GraphAggregate aggregate) {
        Set<Long> verticesToDelete = aggregate.getRemovedVerticesIds();
        vertexDAO.deleteAllByIdIsIn(verticesToDelete);
    }

    private void deleteRemovedEdges(GraphAggregate aggregate) {
        Set<Long> idsToDelete = aggregate.getRemovedEdgesIds();
        edgeDAO.deleteAllByIdIsIn(idsToDelete);
    }

}
