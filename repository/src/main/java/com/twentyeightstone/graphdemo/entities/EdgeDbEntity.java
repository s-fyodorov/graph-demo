package com.twentyeightstone.graphdemo.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EdgeDbEntity extends BaseDbEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directed_from_vertex_id")
    private VertexDbEntity directedFromVertex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directed_to_vertex_id")
    private VertexDbEntity directedToVertex;

    @Builder
    public EdgeDbEntity(Long id, String name, VertexDbEntity directedFromVertex, VertexDbEntity directedToVertex) {
        super(id, name);
        this.directedFromVertex = directedFromVertex;
        this.directedToVertex = directedToVertex;
    }
}
