package com.smyunis.halite.application.users;

import com.smyunis.halite.application.applicationexceptions.EntityNotFoundException;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostRepository;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;

public class UserService {

    private final UserRepository userRepository;
    private final CatererRepository catererRepository;
    private final CateringEventHostRepository cateringEventHostRepository;

    public UserService(UserRepository userRepository,
                       CatererRepository catererRepository,
                       CateringEventHostRepository cateringEventHostRepository) {
        this.userRepository = userRepository;
        this.catererRepository = catererRepository;
        this.cateringEventHostRepository = cateringEventHostRepository;
    }

    public void signUpUserAsCaterer(User newUser, CatererData catererData) {
        userRepository.save(newUser);
        assertUserRoleIsCaterer(newUser);
        createCatererIfDoesNotExist(catererData);
    }

    private void assertUserRoleIsCaterer(User newUser) {
        if (newUser.getRole() != UserRole.CATERER)
            throw new InvalidOperationException("User role is not Caterer");
    }

    private void createCatererIfDoesNotExist(CatererData catererData) {
        try {
            catererRepository.getByPhoneNumber(catererData.getPhoneNumber());
        } catch (EntityNotFoundException exception) {
            Caterer newCaterer = new Caterer(catererData);
            catererRepository.save(newCaterer);
        }
    }
}
