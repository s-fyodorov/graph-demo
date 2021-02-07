package com.twentyeightstone.graphdemo.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EdgeDbEntity extends BaseDbEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tail_vertex_id")
    private VertexDbEntity tailFromVertex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_vertex_id")
    private VertexDbEntity headToVertex;

    @Builder
    public EdgeDbEntity(Long id, String name, VertexDbEntity tailFromVertex, VertexDbEntity headToVertex) {
        super(id, name);
        this.tailFromVertex = tailFromVertex;
        this.headToVertex = headToVertex;
    }
}
