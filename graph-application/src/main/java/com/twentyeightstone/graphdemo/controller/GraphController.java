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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<BaseOutputDTO>> retrieveGraphHeaderList() {
        var graphList = graphService.retrieveGraphHeaderList();
        return ResponseEntity.ok(graphList);
    }

    @DeleteMapping("/{graphId}")
    public ResponseEntity<Void> deleteGraph(@PathVariable Long graphId) {
        graphService.deleteGraph(graphId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{graphId}")
    public ResponseEntity<GraphOutputDTO> getGraphStructure(@PathVariable Long graphId) {
        return ResponseEntity.ok(graphService.getGraphStructure(graphId));
    }

    @GetMapping("/{graphName}")
    public ResponseEntity<GraphOutputDTO> getGraphStructure(@PathVariable String graphName) {
        return ResponseEntity.ok(graphService.getGraphStructure(graphName));
    }

    @GetMapping("/{graphId}/isConnected")
    public ResponseEntity<Boolean> isGraphConnected(@PathVariable Long graphId) {
        return ResponseEntity.ok(graphService.isGraphConnected(graphId));
    }


}
