package com.smyunis.halite.application.users;

import com.smyunis.halite.domain.shared.PhoneNumber;

public interface UserRepository {
    User get(PhoneNumber phoneNumber);
    void save(User user);
}
