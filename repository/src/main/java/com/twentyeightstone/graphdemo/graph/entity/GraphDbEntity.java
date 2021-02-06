package com.twentyeightstone.graphdemo.graph.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Builder
@NoArgsConstructor
public class GraphDbEntity extends BaseDbEntity {

    @Builder
    public GraphDbEntity(Long id, String name) {
        super(id, name);
    }
}
