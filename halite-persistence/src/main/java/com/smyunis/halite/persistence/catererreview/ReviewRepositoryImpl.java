package com.smyunis.halite.persistence.catererreview;

import com.smyunis.halite.application.applicationexceptions.EntityNotFoundException;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.catererreview.*;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReviewRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Review get(ReviewId id) {
        try {
            return tryGetReview(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(id.toString(), exception);
        }
    }

    private Review tryGetReview(ReviewId id) {
        String sql = "SELECT * FROM review WHERE review_id = ?";
        ReviewData reviewData = jdbcTemplate.queryForObject(sql, this::mapReview, id.toString());
        return new Review(reviewData);
    }

    private ReviewData mapReview(ResultSet resultSet, int row) throws SQLException {
        return new ReviewData()
                .setId(new ReviewId(resultSet.getString("review_id")))
                .setReviewerId(new CateringEventHostId(resultSet.getString("reviewer_id")))
                .setReviewedCatererId(new CatererId(resultSet.getString("reviewed_caterer_id")))
                .setRating(new Rating(resultSet.getInt("rating")))
                .setContent(resultSet.getString("content"));
    }

    @Override
    public void save(Review entity) {
        var data = entity.getDataReadOnlyProxy();
        String upsertSql = """
                INSERT INTO review
                VALUES (?,?,?,?,?)
                ON CONFLICT (review_id) DO UPDATE
                SET reviewer_id = excluded.reviewer_id,
                    reviewed_caterer_id = excluded.reviewed_caterer_id,
                    rating = excluded.rating,
                    content = excluded.content;
                """;
        jdbcTemplate.update(upsertSql,
                data.getId().toString(),
                data.getReviewerId().toString(),
                data.getReviewedCatererId().toString(),
                data.getRating().toInt(),
                data.getContent()
        );
    }
}
