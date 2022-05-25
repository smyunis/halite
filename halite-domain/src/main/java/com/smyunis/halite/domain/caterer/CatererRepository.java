package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.DomainEntityRepository;
import com.smyunis.halite.domain.shared.PhoneNumber;

public interface CatererRepository extends DomainEntityRepository<Caterer,CatererId> {
    Caterer getByPhoneNumber(PhoneNumber phoneNumber);
}
