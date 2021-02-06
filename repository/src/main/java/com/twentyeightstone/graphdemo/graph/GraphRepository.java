package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.Aggregate;
import com.twentyeightstone.graphdemo.repository.BaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
class GraphRepository implements BaseRepository<GraphAggregate> {

    private final GraphDAO graphDAO;
    private final DbEntityBuilder dbEntityBuilder;

    @Override
    public void save(GraphAggregate aggregate) {
        graphDAO.saveAll(dbEntityBuilder.buildEntities(aggregate));
    }

    @Override
    public List<Aggregate> retrieveAll() {
        return null;
    }

    @Override
    public Optional<Aggregate> retrieveById(Long id) {
        return Optional.empty();
    }



}
