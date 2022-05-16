package com.smyunis.halite.domain;

public interface DomainEntityRepository<T>{
    T get(DomainEntityId id);
    void save(T domainEntity);
    void remove(DomainEntityId id);
}
