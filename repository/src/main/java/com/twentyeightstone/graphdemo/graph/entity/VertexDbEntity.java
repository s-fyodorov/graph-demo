package com.twentyeightstone.graphdemo.graph.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class VertexDbEntity extends BaseDbEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graph_id")
    private GraphDbEntity graph;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "directedFromVertex", orphanRemoval = true)
    private List<EdgeDbEntity> outcomeEdges = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "directedToVertex", orphanRemoval = true)
    private List<EdgeDbEntity> incomeEdges = new ArrayList<>();

    @Builder
    public VertexDbEntity(Long id, String name, GraphDbEntity graph) {
        super(id, name);
        this.graph = graph;
    }

    public void addOutComeEdge(EdgeDbEntity edge) {
        outcomeEdges.add(edge);
        edge.setDirectedFromVertex(this);
    }

    public void addIncomeEdge(EdgeDbEntity edge) {
        incomeEdges.add(edge);
        edge.setDirectedToVertex(this);
    }

}

