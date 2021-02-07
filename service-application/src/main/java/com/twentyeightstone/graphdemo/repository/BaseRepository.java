package com.twentyeightstone.graphdemo.repository;

import com.twentyeightstone.graphdemo.Aggregate;

import java.util.Optional;

/**
 * Repository is suitable for storing and retrieving aggregates only
 *
 * @param <T>
 */
public interface BaseRepository<T extends Aggregate> {

    void save(T aggregate);

    Optional<T> retrieveById(Long id);

    void delete(Long id);
}
