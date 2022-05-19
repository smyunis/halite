CREATE TABLE caterer (
    caterer_id VARCHAR PRIMARY KEY,
    name VARCHAR,
    phone_number VARCHAR,
    recommendation_metric INTEGER
);

CREATE TABLE catering_event_host (
    catering_event_host_id VARCHAR PRIMARY KEY,
    name VARCHAR,
    phone_number VARCHAR
);

CREATE TABLE review(
    review_id VARCHAR PRIMARY KEY,
    reviewer_id VARCHAR,
    reviewed_caterer_id VARCHAR,
    rating INTEGER,
    content TEXT
--    FOREIGN KEY (reviewer_id) REFERENCES catering_event_host(catering_event_host_id),
--    FOREIGN KEY (reviewed_caterer_id) REFERENCES caterer(caterer_id)
);
