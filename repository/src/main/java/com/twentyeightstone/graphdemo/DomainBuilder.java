package com.twentyeightstone.graphdemo;

public interface DomainBuilder<AGG, DB> {

    AGG buildAggregate(DB dbEntity);
}
