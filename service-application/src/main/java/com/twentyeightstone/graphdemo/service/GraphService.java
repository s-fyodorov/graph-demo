package com.twentyeightstone.graphdemo.service;

import com.twentyeightstone.graphdemo.dto.input.EdgeInputDTO;
import com.twentyeightstone.graphdemo.dto.output.GraphOutputDTO;

public interface GraphService {

    GraphOutputDTO getGraphStructure(Long graphId);

    void createGraph(String graphName);

    void addVertex(String vertexName, Long graphId);

    void addEdge(EdgeInputDTO dto, Long graphId);

    boolean isGraphConnected(Long graphId);
}
