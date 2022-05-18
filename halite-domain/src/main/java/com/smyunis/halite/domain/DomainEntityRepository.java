package com.smyunis.halite.domain;

public interface DomainEntityRepository<T, I extends DomainEntityId> {
    T get(I id);

    void save(T domainEntity);

    void remove(I id);
}
