package com.company.api;

import java.util.Map;

public interface Repository<T, M> {
    void save(M object);
    M findByName(T name);
    M update(M object);
    boolean removeByName(T name);
    Map<T, M> getMap();
}