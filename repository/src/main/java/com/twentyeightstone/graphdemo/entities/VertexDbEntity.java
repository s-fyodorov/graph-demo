package com.twentyeightstone.graphdemo.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class VertexDbEntity extends BaseDbEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graph_id")
    private GraphDbEntity graph;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tailFromVertex", orphanRemoval = true)
    private List<EdgeDbEntity> outcomeEdges = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "headToVertex", orphanRemoval = true)
    private List<EdgeDbEntity> incomeEdges = new ArrayList<>();

    @Builder
    public VertexDbEntity(Long id, String name, GraphDbEntity graph) {
        super(id, name);
        this.graph = graph;
    }

    public void addOutComeEdge(EdgeDbEntity edge) {
        outcomeEdges.add(edge);
        edge.setTailFromVertex(this);
    }

    public void addIncomeEdge(EdgeDbEntity edge) {
        incomeEdges.add(edge);
        edge.setHeadToVertex(this);
    }

}

