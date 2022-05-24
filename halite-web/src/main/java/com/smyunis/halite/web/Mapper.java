package com.smyunis.halite.web;

public interface Mapper<T,V> {
    V map(T obj);
}
