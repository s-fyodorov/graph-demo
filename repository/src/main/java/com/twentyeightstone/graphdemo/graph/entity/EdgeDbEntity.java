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
public class EdgeDbEntity extends BaseDbEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directed_from_vertex_id")
    private VertexDbEntity directedFromVertex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directed_to_vertex_id")
    private VertexDbEntity directedToVertex;
}
