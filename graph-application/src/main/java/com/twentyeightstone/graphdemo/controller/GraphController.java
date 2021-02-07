package com.twentyeightstone.graphdemo.controller;

import com.twentyeightstone.graphdemo.dto.output.BaseOutputDTO;
import com.twentyeightstone.graphdemo.dto.output.GraphOutputDTO;
import com.twentyeightstone.graphdemo.service.GraphService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graph")
@AllArgsConstructor
public class GraphController {

    private final GraphService graphService;

    @PostMapping
    public ResponseEntity<BaseOutputDTO> createGraph(@RequestParam String graphName) {
        var createdGraph = graphService.createGraph(graphName);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGraph);
    }

    @GetMapping
    public ResponseEntity<List<BaseOutputDTO>> retrieveGraphHeaderList() {
        var graphList = graphService.retrieveGraphHeaderList();
        return ResponseEntity.ok(graphList);
    }

    @GetMapping("/id/{graphId}")
    public ResponseEntity<GraphOutputDTO> getGraphStructure(@PathVariable Long graphId) {
        return ResponseEntity.ok(graphService.getGraphStructure(graphId));
    }

    @GetMapping("/name/{graphName}")
    public ResponseEntity<GraphOutputDTO> getGraphStructure(@PathVariable String graphName) {
        return ResponseEntity.ok(graphService.getGraphStructure(graphName));
    }

    @GetMapping("/id/{graphId}/isConnected")
    public ResponseEntity<Boolean> isGraphConnected(@PathVariable Long graphId) {
        return ResponseEntity.ok(graphService.isGraphConnected(graphId));
    }

    @DeleteMapping("/id/{graphId}")
    public ResponseEntity<Void> deleteGraph(@PathVariable Long graphId) {
        graphService.deleteGraph(graphId);
        return ResponseEntity.ok().build();
    }

}
