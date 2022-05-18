package com.smyunis.halite.application.cateringeventhost;

import com.smyunis.halite.domain.cateringeventhost.CateringEventHost;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostData;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostRepository;
import com.smyunis.halite.domain.shared.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CateringEventHostServiceTest {
    private CateringEventHostService cateringEventHostService;
    private CateringEventHostRepository cateringEventHostRepository;

    @BeforeEach
    void setup() {
        cateringEventHostRepository = mock(CateringEventHostRepository.class);
        cateringEventHostService = new CateringEventHostService(cateringEventHostRepository);
    }

    @Test
    void signUpNewHost() {
        CateringEventHostData cateringEventHostData = new CateringEventHostData()
                .setName("Queen Elizabeth II")
                .setPhoneNumber(new PhoneNumber("+443031237334"));

        cateringEventHostService.signUpAsCateringEventHost(cateringEventHostData);

        verify(cateringEventHostRepository).save(any(CateringEventHost.class));
    }
}
