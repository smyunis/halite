package com.smyunis.halite.application.caterer;

import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.shared.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CatererServiceTest {
    private CatererService catererService;
    private CatererRepository catererRepository;

    @BeforeEach
    void setup() {
        catererRepository = mock(CatererRepository.class);
        catererService = new CatererService(catererRepository);
    }




}
