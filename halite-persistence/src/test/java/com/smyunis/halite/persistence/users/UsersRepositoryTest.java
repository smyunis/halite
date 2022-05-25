package com.smyunis.halite.persistence.users;

import com.smyunis.halite.application.users.User;
import com.smyunis.halite.application.users.UserRepository;
import com.smyunis.halite.application.users.UserRole;
import com.smyunis.halite.domain.shared.PhoneNumber;
import com.smyunis.halite.persistence.DatabaseFixture;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersRepositoryTest {
    private static final JdbcTemplate jdbcTemplate = DatabaseFixture.createJdbcTemplate();
    private final UserRepository repository = new UserRepositoryImpl(jdbcTemplate);

    @Test
    void canSaveThenFetchUser() {
        var userPhone = new PhoneNumber("+251912096265");
        User user = new User()
                .setPhoneNumber(userPhone)
                .setPassword("secret123$$$")
                .setRole(UserRole.CATERER);

        repository.save(user);

        User fetchedUser = repository.get(userPhone);

        assertEquals(user.getPhoneNumber(),fetchedUser.getPhoneNumber());
        assertEquals(user.getPassword(),fetchedUser.getPassword());
        assertEquals(user.getRole(),fetchedUser.getRole());
    }


}
