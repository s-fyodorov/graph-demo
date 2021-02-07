package com.twentyeightstone.graphdemo.service;

import com.twentyeightstone.graphdemo.dto.input.DeleteEdgeDTO;
import com.twentyeightstone.graphdemo.dto.input.NewEdgeDTO;
import com.twentyeightstone.graphdemo.dto.input.VertexInputDTO;
import com.twentyeightstone.graphdemo.dto.output.BaseOutputDTO;
import com.twentyeightstone.graphdemo.dto.output.GraphOutputDTO;

import java.util.List;

public interface GraphService {

    GraphOutputDTO getGraphStructure(Long graphId);

    GraphOutputDTO getGraphStructure(String graphName);

    BaseOutputDTO createGraph(String graphName);

    void addVertex(VertexInputDTO dto, Long graphId);

    void addEdge(NewEdgeDTO dto, Long graphId);

    boolean isGraphConnected(Long graphId);

    void deleteGraph(Long graphId);

    void deleteVertex(VertexInputDTO dto, Long graphId);

    void deleteAllVertices(Long graphId);

    void deleteEdgesBetween(DeleteEdgeDTO dto, Long graphId);

    List<BaseOutputDTO> retrieveGraphHeaderList();
}
