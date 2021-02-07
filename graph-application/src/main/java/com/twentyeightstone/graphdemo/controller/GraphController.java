package com.twentyeightstone.graphdemo.controller;

import com.twentyeightstone.graphdemo.dto.input.EdgeInputDTO;
import com.twentyeightstone.graphdemo.dto.input.VertexInputDTO;
import com.twentyeightstone.graphdemo.dto.output.GraphOutputDTO;
import com.twentyeightstone.graphdemo.service.GraphService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/graph")
@AllArgsConstructor
public class GraphController {

    private final GraphService graphService;

    @PostMapping
    public ResponseEntity<Void> createGraph(@RequestParam @NotBlank @Valid String graphName) {
        graphService.createGraph(graphName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{graphId}")
    public ResponseEntity<GraphOutputDTO> getGraphStructure(@PathVariable @NotNull Long graphId) {
        return ResponseEntity.ok(graphService.getGraphStructure(graphId));
    }

    @PostMapping("/{graphId}/vertex")
    public ResponseEntity<Void> addVertex(
            @RequestBody @Valid VertexInputDTO vertex,
            @PathVariable @NotNull @Valid Long graphId
    ) {
        graphService.addVertex(vertex.getVertexName(), graphId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{graphId}/edge")
    public ResponseEntity<Void> addEdge(
            @RequestBody @Valid EdgeInputDTO edge,
            @PathVariable @NotNull @Valid Long graphId
    ) {
        graphService.addEdge(edge, graphId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{graphId}/isConnected")
    public ResponseEntity<Boolean> isGraphConnected(@PathVariable @NotNull @Valid Long graphId) {
        return ResponseEntity.ok(graphService.isGraphConnected(graphId));
    }
}
