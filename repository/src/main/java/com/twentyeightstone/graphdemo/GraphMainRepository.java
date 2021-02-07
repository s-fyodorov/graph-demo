package com.twentyeightstone.graphdemo;

import com.twentyeightstone.graphdemo.dao.EdgeDAO;
import com.twentyeightstone.graphdemo.dao.GraphDAO;
import com.twentyeightstone.graphdemo.dao.VertexDAO;
import com.twentyeightstone.graphdemo.dto.output.BaseOutputDTO;
import com.twentyeightstone.graphdemo.graph.DbEntityBuilder;
import com.twentyeightstone.graphdemo.graph.DomainBuilder;
import com.twentyeightstone.graphdemo.graph.GraphAggregate;
import com.twentyeightstone.graphdemo.repository.GraphRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
    private final DbEntityBuilder dbEntityBuilder;
    private final DomainBuilder domainBuilder;

    @Override
    @Transactional
    public void save(GraphAggregate aggregate) {
        graphDAO.save(dbEntityBuilder.buildEntities(aggregate));
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
                .map(domainBuilder::buildAggregate);
    }

    @Override
    public Optional<GraphAggregate> retrieveByName(String name) {
        return graphDAO.findGraphDbEntityByName(name)
                .map(domainBuilder::buildAggregate);
    }

    @Override
    public void delete(Long id) {
        graphDAO.deleteById(id);
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
