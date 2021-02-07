package com.twentyeightstone.graphdemo.dao;

import com.twentyeightstone.graphdemo.entities.GraphDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphDAO extends JpaRepository<GraphDbEntity, Long> {
}
