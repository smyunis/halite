package com.smyunis.halite.domain;

import java.util.Optional;

public interface DomainEntityRepository<T, I extends DomainEntityId> {
    T get(I id);
    //Optional<T> get(I id);
    void save(T entity);

}
