package com.twentyeightstone.graphdemo.dao;

import com.twentyeightstone.graphdemo.entities.EdgeDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdgeDAO extends JpaRepository<EdgeDbEntity, Long> {
}
