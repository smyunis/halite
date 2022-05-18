package com.smyunis.halite.persistence.caterer;

import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatererJdbcRepository extends CrudRepository<Caterer, CatererId> {

}
