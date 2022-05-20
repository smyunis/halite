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

--CREATE TYPE event_status AS ENUM('open','closed');
CREATE TABLE catering_event(
    catering_event_id VARCHAR PRIMARY KEY,
    catering_event_host_id VARCHAR NOT NULL,
--    status event_status,
    status VARCHAR,
    venue VARCHAR,
    description TEXT,
    expected_number_of_attendees INTEGER,
    event_start_time TIMESTAMP,
    event_end_time TIMESTAMP
--    FOREIGN KEY (catering_event_host_id) REFERENCES catering_event_host (catering_event_host_id)
);

CREATE TABLE catering_menu_item(
    catering_menu_item_id VARCHAR PRIMARY KEY,
    caterer_id VARCHAR NOT NULL,
    name VARCHAR,
    price DECIMAL
--    FOREIGN KEY (caterer_id) REFERENCES caterer (caterer_id)
);

CREATE TABLE catering_menu_item_ingredients(
    catering_menu_item_id VARCHAR NOT NULL,
    ingredient VARCHAR,
    FOREIGN KEY (catering_menu_item_id) REFERENCES catering_menu_item(catering_menu_item_id) ON DELETE CASCADE
);

CREATE TABLE catering_menu_item_images(
    catering_menu_item_id VARCHAR NOT NULL,
    url VARCHAR,
    FOREIGN KEY (catering_menu_item_id) REFERENCES catering_menu_item(catering_menu_item_id) ON DELETE CASCADE
);

