package com.twentyeightstone.graphdemo.service;

import com.twentyeightstone.graphdemo.dto.input.EdgeInputDTO;
import com.twentyeightstone.graphdemo.dto.output.GraphOutputDTO;

public interface GraphService {

    GraphOutputDTO getGraphStructure(Long id);

    void addVertex(String vertexName, Long graphId);

    public void addEdge(EdgeInputDTO dto, Long graphId);
}
