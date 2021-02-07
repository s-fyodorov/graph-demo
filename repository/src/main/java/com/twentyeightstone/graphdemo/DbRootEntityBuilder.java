package com.twentyeightstone.graphdemo;

public interface DbRootEntityBuilder<ROOT, AGG> {

    ROOT build(AGG domainAggregate);
}
