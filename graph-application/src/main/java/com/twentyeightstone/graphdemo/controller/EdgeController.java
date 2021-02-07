package com.twentyeightstone.graphdemo.controller;


import com.twentyeightstone.graphdemo.dto.input.DeleteEdgeDTO;
import com.twentyeightstone.graphdemo.dto.input.NewEdgeDTO;
import com.twentyeightstone.graphdemo.service.GraphService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/graph/{graphId}/edge")
@AllArgsConstructor
public class EdgeController {

    private final GraphService graphService;

    @PostMapping
    public ResponseEntity<Void> addEdge(
            @PathVariable Long graphId,
            @RequestBody @Valid NewEdgeDTO edge
    ) {
        graphService.addEdge(edge, graphId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEdgesBetween(
            @PathVariable Long graphId,
            @RequestBody @Valid DeleteEdgeDTO dto) {
        graphService.deleteEdgesBetween(dto, graphId);
        return ResponseEntity.ok().build();
    }
}
