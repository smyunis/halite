package com.smyunis.halite.application.caterer;

import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.caterer.CatererRepository;

public class CatererService {
    private final CatererRepository catererRepository;
    public CatererService(CatererRepository catererRepository) {
        this.catererRepository = catererRepository;
    }


    public Caterer getCaterer(CatererId catererId) {
        return catererRepository.get(catererId);
    }
}
