package com.twentyeightstone.graphdemo.graph.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GraphDbEntity extends BaseDbEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "graph", orphanRemoval = true)
    private List<VertexDbEntity> vertices = new ArrayList<>();

    @Builder
    public GraphDbEntity(Long id, String name) {
        super(id, name);
    }

    public void addVertex(VertexDbEntity vertex) {
        vertices.add(vertex);
        vertex.setGraph(this);
    }
}
