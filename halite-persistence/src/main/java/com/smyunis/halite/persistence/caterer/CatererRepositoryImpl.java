package com.smyunis.halite.persistence.caterer;

import com.smyunis.halite.domain.DomainEntityId;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.caterer.CatererRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

public class CatererRepositoryImpl implements CatererRepository {

    private final CatererJdbcRepository catererJdbcRepository;

    @Autowired
    public CatererRepositoryImpl(CatererJdbcRepository catererJdbcRepository) {
        this.catererJdbcRepository = catererJdbcRepository;
    }

    @Override
    public Caterer get(DomainEntityId id) {
        CatererId catererId = (CatererId) id;
        var caterer = catererJdbcRepository.findById(catererId);
        if (caterer.isEmpty())
            throw new NoSuchElementException();
        return caterer.get();
    }

    @Override
    public void save(Caterer domainEntity) {

    }

    @Override
    public void remove(DomainEntityId id) {

    }
}
