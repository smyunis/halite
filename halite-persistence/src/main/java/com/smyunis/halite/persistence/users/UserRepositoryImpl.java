package com.smyunis.halite.persistence.users;

import com.smyunis.halite.application.users.User;
import com.smyunis.halite.application.users.UserRepository;
import com.smyunis.halite.application.users.UserRole;
import com.smyunis.halite.domain.shared.PhoneNumber;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User get(PhoneNumber phoneNumber) {
        String fetchUserSql = "SELECT * FROM users WHERE phone_number = ?";
        return jdbcTemplate.queryForObject(fetchUserSql, this::mapUser, phoneNumber.phoneNumber());
    }

    private User mapUser(ResultSet resultSet, int row) throws SQLException {
        return new User()
                .setPhoneNumber(new PhoneNumber(resultSet.getString("phone_number")))
                .setPassword(resultSet.getString("password"))
                .setRole(UserRole.valueOf(resultSet.getString("role")));
    }

    @Override
    public void save(User user) {
        String upsertUserSql = """
                INSERT INTO users
                VALUES (?,?,?)
                ON CONFLICT (phone_number) DO UPDATE
                SET password = excluded.password,
                    role = excluded.role;
                """;

        jdbcTemplate.update(upsertUserSql,
                user.getPhoneNumber().phoneNumber(),
                user.getPassword(),
                user.getRole().toString()
        );
    }
}
