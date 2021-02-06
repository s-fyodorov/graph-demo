package com.twentyeightstone.graphdemo.graph.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Builder
@NoArgsConstructor
public class VertexDbEntity extends BaseDbEntity {

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "directedFromVertex", orphanRemoval = true)
//    private List<EdgeDbEntity> edges;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graph_id")
    private GraphDbEntity graph;

    @Builder
    public VertexDbEntity(Long id, String name, GraphDbEntity graph) {
        super(id, name);
        this.graph = graph;
    }
}
