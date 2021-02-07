package com.twentyeightstone.graphdemo.dao;

import com.twentyeightstone.graphdemo.entities.GraphDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GraphDAO extends JpaRepository<GraphDbEntity, Long> {

    Optional<GraphDbEntity> findGraphDbEntityByName(String name);
}
