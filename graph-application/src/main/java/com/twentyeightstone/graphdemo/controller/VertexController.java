package com.twentyeightstone.graphdemo.controller;

import com.twentyeightstone.graphdemo.dto.input.VertexInputDTO;
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
@RequestMapping("/graph/{graphId}/vertex")
@AllArgsConstructor
public class VertexController {

    private final GraphService graphService;

    @PostMapping
    public ResponseEntity<Void> addVertex(
            @PathVariable Long graphId,
            @RequestBody @Valid VertexInputDTO dto
    ) {
        graphService.addVertex(dto, graphId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVertex(
            @PathVariable Long graphId,
            @RequestBody @Valid VertexInputDTO dto
    ) {
        graphService.deleteVertex(dto, graphId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllVertices(@PathVariable Long graphId) {
        graphService.deleteAllVertices(graphId);
        return ResponseEntity.ok().build();
    }

}
