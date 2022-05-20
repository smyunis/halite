package com.smyunis.halite.persistence.cateringmenuitem;

import com.smyunis.halite.application.applicationexceptions.EntityNotFoundException;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringmenuitem.*;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CateringMenuItemRepositoryImpl implements CateringMenuItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public CateringMenuItemRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CateringMenuItem get(CateringMenuItemId id) {
        try {
            return tryGetCateringMenuItem(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(id.toString(), exception);
        }
    }

    private CateringMenuItem tryGetCateringMenuItem(CateringMenuItemId id) {
        CateringMenuItemData cateringMenuItemData = getCateringMenuItemData(id);
        fetchCateringMenuItemIngredients(cateringMenuItemData);
        fetchCateringMenuItemImages(cateringMenuItemData);
        return new CateringMenuItem(cateringMenuItemData);
    }

    private void fetchCateringMenuItemImages(CateringMenuItemData data) {
        String imagesFetchSql = "SELECT url FROM catering_menu_item_images WHERE catering_menu_item_id = ?;";
        List<String> imageLinks = jdbcTemplate.queryForList(imagesFetchSql, String.class, data.getId().toString());
        List<URL> imageUrls = imageLinks.stream().map(i -> {
            try {
                return new URL(i);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        data.setImages(imageUrls);
    }

    private void fetchCateringMenuItemIngredients(CateringMenuItemData data) {
        String ingredientsFetchSql = "SELECT ingredient FROM catering_menu_item_ingredients WHERE catering_menu_item_id = ?;";
        List<String> ingredients = jdbcTemplate.queryForList(ingredientsFetchSql, String.class, data.getId().toString());
        data.setIngredients(new HashSet<>(ingredients));
    }

    private CateringMenuItemData getCateringMenuItemData(CateringMenuItemId id) {
        String menuItemFetchSql = "SELECT * FROM catering_menu_item WHERE catering_menu_item_id = ?;";
        return jdbcTemplate.queryForObject(menuItemFetchSql, this::mapCateringMenuItem, id.toString());
    }

    private CateringMenuItemData mapCateringMenuItem(ResultSet resultSet, int row) throws SQLException {
        return new CateringMenuItemData()
                .setId(new CateringMenuItemId(resultSet.getString("catering_menu_item_id")))
                .setCatererId(new CatererId(resultSet.getString("caterer_id")))
                .setName(resultSet.getString("name"))
                .setPrice(new MonetaryAmount(resultSet.getDouble("price")));
    }

    @Override
    public void save(CateringMenuItem entity) {
        var data = entity.getDataReadOnlyProxy();
        var namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        upsertCateringMenuItem(data);
        upsertCateringMenuItemIngredients(data, namedParameterJdbcTemplate);
        upsertCateringMenuItemImages(data, namedParameterJdbcTemplate);
    }

    private void upsertCateringMenuItem(CateringMenuItemDataReadOnlyProxy data) {
        String upsertCateringMenuItemSql = """
                INSERT INTO catering_menu_item
                VALUES (?,?,?,?)
                ON CONFLICT (catering_menu_item_id) DO UPDATE
                SET caterer_id = excluded.caterer_id,
                    name = excluded.name,
                    price = excluded.price;
                """;

        jdbcTemplate.update(upsertCateringMenuItemSql,
                data.getId().toString(),
                data.getCatererId().toString(),
                data.getName(),
                data.getPrice().amount());
    }

    private void upsertCateringMenuItemImages(CateringMenuItemDataReadOnlyProxy data,
                                              NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        String imagesUpsertSql = """
                INSERT INTO catering_menu_item_images
                SELECT :id AS catering_menu_item_id, :url AS url
                WHERE NOT EXISTS(
                    SELECT *
                    FROM catering_menu_item_images
                    WHERE catering_menu_item_id = :id AND url = :url
                )
                LIMIT 1;
                """;
        MapSqlParameterSource[] imagesSqlParameters = data.getImages().stream()
                .map(url -> new MapSqlParameterSource()
                        .addValue("id", data.getId().toString())
                        .addValue("url", url.toString()))
                .toArray(MapSqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(imagesUpsertSql, imagesSqlParameters);
    }

    private void upsertCateringMenuItemIngredients(CateringMenuItemDataReadOnlyProxy data,
                                                   NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        String ingredientsUpsertSql = """
                INSERT INTO catering_menu_item_ingredients
                SELECT :id AS catering_menu_item_id, :ingredient AS ingredient
                WHERE NOT EXISTS(
                	SELECT *
                	FROM catering_menu_item_ingredients
                	WHERE catering_menu_item_id = :id AND ingredient = :ingredient
                )
                LIMIT 1;
                """;
        MapSqlParameterSource[] ingredientsSqlParameters = data.getIngredients().stream()
                .map(i -> new MapSqlParameterSource()
                        .addValue("id", data.getId().toString())
                        .addValue("ingredient", i))
                .toArray(MapSqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(ingredientsUpsertSql, ingredientsSqlParameters);
    }

    @Override
    public void remove(CateringMenuItemId id) {
        String removeMenuItemSql = "DELETE FROM catering_menu_item WHERE catering_menu_item_id = ?;";
        jdbcTemplate.update(removeMenuItemSql, id.toString());
    }
}
