package com.twentyeightstone.graphdemo.service;

import com.twentyeightstone.graphdemo.dto.output.GraphOutputDTO;
import com.twentyeightstone.graphdemo.graph.GraphAggregate;

public interface GraphStructureOutputBuilder {

    GraphOutputDTO build(GraphAggregate aggregate);
}
