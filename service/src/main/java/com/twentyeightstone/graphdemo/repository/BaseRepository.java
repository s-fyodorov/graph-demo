package com.twentyeightstone.graphdemo.repository;

import com.twentyeightstone.graphdemo.Aggregate;

import java.util.List;

/**
 * Repository is suitable for storing and retrieving aggregates only
 * @param <T>
 */
public interface BaseRepository<T extends Aggregate> {

    void save(T aggregate);

    List<T> retrieveAll();

    T retrieveById(Long id);
}
