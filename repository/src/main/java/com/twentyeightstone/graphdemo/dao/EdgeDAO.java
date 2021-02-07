package com.twentyeightstone.graphdemo.dao;

import com.twentyeightstone.graphdemo.entities.EdgeDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EdgeDAO extends JpaRepository<EdgeDbEntity, Long> {

    void deleteAllByIdIsIn(Set<Long> ids);
}
