package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.Aggregate;
import com.twentyeightstone.graphdemo.graph.entity.GraphDbEntity;
import com.twentyeightstone.graphdemo.repository.BaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
class GraphRepository implements BaseRepository<GraphAggregate> {

    private final GraphDAO graphDAO;
    private final EdgeDAO edgeDAO;
    private final DbEntityBuilder dbEntityBuilder;
    private final DomainBuilder domainBuilder;

    @Override
    public void save(GraphAggregate aggregate) {
        edgeDAO.saveAll(dbEntityBuilder.buildEntities(aggregate));
    }

    @Override
    public List<Aggregate> retrieveAll() {
        return graphDAO.findAll()
                .stream()
                .map(domainBuilder::buildAggregate)
                .collect(Collectors.toList());
    }

    @Override
    public Aggregate retrieveById(Long id) {
        GraphDbEntity dbEntity = graphDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("")); //todo proper exception
        return domainBuilder.buildAggregate(dbEntity);
    }



}
