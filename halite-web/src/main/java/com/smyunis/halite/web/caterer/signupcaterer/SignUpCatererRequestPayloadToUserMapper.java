package com.smyunis.halite.web.caterer.signupcaterer;

import com.smyunis.halite.application.users.User;
import com.smyunis.halite.application.users.UserRole;
import com.smyunis.halite.domain.shared.PhoneNumber;
import com.smyunis.halite.web.Mapper;

public class SignUpCatererRequestPayloadToUserMapper implements Mapper<SignUpCatererRequestPayload, User> {
    @Override
    public User map(SignUpCatererRequestPayload obj) {
        return new User()
                .setPhoneNumber(new PhoneNumber(obj.getPhoneNumber()))
                .setPassword(obj.getPassword());
    }
}
