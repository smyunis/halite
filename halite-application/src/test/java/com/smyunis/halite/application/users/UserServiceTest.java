package com.smyunis.halite.application.users;

import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostRepository;
import com.smyunis.halite.domain.shared.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService userService;
    private CateringEventHostRepository cateringEventHostRepository;
    private CatererRepository catererRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        catererRepository = mock(CatererRepository.class);
        cateringEventHostRepository = mock(CateringEventHostRepository.class);
        userRepository = mock(UserRepository.class);

        userService = new UserService(userRepository, catererRepository, cateringEventHostRepository);
    }

    @Test
    void canSaveCatererUser() {
        User newUser = new User()
                .setPhoneNumber(new PhoneNumber("+251912096265"))
                .setRole(UserRole.CATERER)
                .setPassword("123456789");

        CatererData catererData = new CatererData();

        userService.signUpUserAsCaterer(newUser,catererData);

        verify(userRepository).save(any(User.class));
        verify(catererRepository).save(any(Caterer.class));
    }


}
