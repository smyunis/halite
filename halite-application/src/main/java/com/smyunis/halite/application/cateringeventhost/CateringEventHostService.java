package com.smyunis.halite.application.cateringeventhost;

import com.smyunis.halite.domain.cateringeventhost.CateringEventHost;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostData;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostRepository;

public class CateringEventHostService {
    private final CateringEventHostRepository cateringEventHostRepository;

    public CateringEventHostService(CateringEventHostRepository cateringEventHostRepository) {
        this.cateringEventHostRepository = cateringEventHostRepository;
    }

    public void signUpAsCateringEventHost(CateringEventHostData cateringEventHostData) {
        CateringEventHost cateringEventHost = new CateringEventHost(cateringEventHostData);
        cateringEventHostRepository.save(cateringEventHost);
    }
}
