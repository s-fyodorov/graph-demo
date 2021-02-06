package com.twentyeightstone.graphdemo.graph;

import com.twentyeightstone.graphdemo.graph.entity.EdgeDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdgeDAO extends JpaRepository<EdgeDbEntity, Long> {
}
