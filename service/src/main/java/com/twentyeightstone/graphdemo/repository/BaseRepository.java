package com.twentyeightstone.graphdemo.repository;

import com.twentyeightstone.graphdemo.Aggregate;

import java.util.List;
import java.util.Optional;

/**
 * Repository is suitable for storing and retrieving aggregates only
 * @param <T>
 */
public interface BaseRepository<T extends Aggregate> {

    void save(T aggregate);

    List<Aggregate> retrieveAll();

    Aggregate retrieveById(Long id);
}
