package com.twentyeightstone.graphdemo.repository;

import com.twentyeightstone.graphdemo.dto.output.BaseOutputDTO;
import com.twentyeightstone.graphdemo.graph.GraphAggregate;

import java.util.List;
import java.util.Optional;

public interface GraphRepository extends BaseRepository<GraphAggregate> {

    Optional<BaseOutputDTO> retrieveGraphHeaderByName(String name);

    Optional<GraphAggregate> retrieveByName(String name);

    boolean isExistByName(String name);

    List<BaseOutputDTO> retrieveGraphHeaderList();
}
