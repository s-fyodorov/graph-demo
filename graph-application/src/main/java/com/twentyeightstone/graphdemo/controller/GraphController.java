package com.twentyeightstone.graphdemo.controller;

import com.twentyeightstone.graphdemo.dto.output.GraphOutputDTO;
import com.twentyeightstone.graphdemo.service.GraphService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<GraphOutputDTO> getGraphStructure(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(graphService.getGraphStructure(id));
    }

    @PostMapping("/{id}/vertex")
    public ResponseEntity<Void> addVertex(
            @RequestParam @NotBlank @Valid String vertexName,
            @PathVariable @NotNull @Valid Long id
    ) {
        graphService.addVertex(vertexName, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}/edge")
    public ResponseEntity<Void> addEdge(
            @RequestParam @NotBlank @Valid String vertexName,
            @PathVariable @NotNull @Valid Long id
    ) {
        graphService.addVertex(vertexName, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/isConnected")
    public ResponseEntity<Boolean> isGraphConnected(@PathVariable @NotNull @Valid Long id) {
        return ResponseEntity.ok(graphService.isGraphConnected(id));
    }
}
