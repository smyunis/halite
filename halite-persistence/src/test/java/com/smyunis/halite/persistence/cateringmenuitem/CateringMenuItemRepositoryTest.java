package com.smyunis.halite.persistence.cateringmenuitem;

import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.persistence.DatabaseFixture;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class CateringMenuItemRepositoryTest {
    private static final JdbcTemplate jdbcTemplate = DatabaseFixture.createJdbcTemplate();
    private final CateringMenuItemRepository repository = new CateringMenuItemRepositoryImpl(jdbcTemplate);

    @Test
    void canSaveThenGetCateringMenuItem() {
        String ingredientsUpsertSql = """
                INSERT INTO catering_menu_item_ingredients
                SELECT :id AS catering_menu_item_id, :ingredient AS ingredient
                FROM catering_menu_item_ingredients
                WHERE NOT EXISTS(
                	SELECT *
                	FROM catering_menu_item_ingredients
                	WHERE catering_menu_item_id = :id AND ingredient = :ingredient
                )
                LIMIT 1;
                """;
        SqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("id", "xyz")
                .addValue("ingredient", "egg");

        var b = new MapSqlParameterSource[]{
                new MapSqlParameterSource()
                        .addValue("id", "xyz")
                        .addValue("ingredient", "egg"),
                new MapSqlParameterSource()
                        .addValue("id", "xyz")
                        .addValue("ingredient", "bread"),
                new MapSqlParameterSource()
                        .addValue("id", "xyz")
                        .addValue("ingredient", "tomato")
        };

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(DatabaseFixture.createJdbcTemplate());
        namedParameterJdbcTemplate.batchUpdate(ingredientsUpsertSql, b);
    }
}
