package com.twentyeightstone.graphdemo;

import com.twentyeightstone.graphdemo.Aggregate;
import com.twentyeightstone.graphdemo.dao.EdgeDAO;
import com.twentyeightstone.graphdemo.dao.GraphDAO;
import com.twentyeightstone.graphdemo.entities.GraphDbEntity;
import com.twentyeightstone.graphdemo.graph.DbEntityBuilder;
import com.twentyeightstone.graphdemo.graph.DomainBuilder;
import com.twentyeightstone.graphdemo.graph.GraphAggregate;
import com.twentyeightstone.graphdemo.repository.GraphRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
class GraphMainRepository implements GraphRepository {

    private final GraphDAO graphDAO;
    private final EdgeDAO edgeDAO;
    private final DbEntityBuilder dbEntityBuilder;
    private final DomainBuilder domainBuilder;

    @Override
    public void save(GraphAggregate aggregate) {
        edgeDAO.saveAll(dbEntityBuilder.buildEntities(aggregate));
    }

    @Override
    public List<GraphAggregate> retrieveAll() {
        return graphDAO.findAll()
                .stream()
                .map(domainBuilder::buildAggregate)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GraphAggregate> retrieveById(Long id) {
        return graphDAO.findById(id)
                .map(domainBuilder::buildAggregate);
    }

    @Override
    public boolean isExistByName(String name) {
        return graphDAO.findGraphDbEntityByName(name).isPresent();
    }


}
