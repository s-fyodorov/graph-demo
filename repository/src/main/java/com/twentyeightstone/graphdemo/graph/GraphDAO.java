package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.graph.entity.GraphDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphDAO extends JpaRepository<GraphDbEntity, Long> {
}
