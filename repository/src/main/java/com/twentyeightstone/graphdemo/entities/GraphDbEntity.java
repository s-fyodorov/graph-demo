package com.twentyeightstone.graphdemo.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GraphDbEntity extends BaseDbEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "graph", orphanRemoval = true)
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
