package com.twentyeightstone.graphdemo.dao;

import com.twentyeightstone.graphdemo.entities.VertexDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface VertexDAO extends JpaRepository<VertexDbEntity, Long> {

    void deleteAllByIdIsIn(Set<Long> ids);
}
