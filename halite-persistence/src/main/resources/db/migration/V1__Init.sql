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

CREATE TABLE caterer_review(
    caterer_review_id VARCHAR PRIMARY KEY,
    reviewer_id VARCHAR,
    reviewed_caterer_id VARCHAR,
    rating INTEGER,
    content TEXT
);


CREATE TABLE catering_event(
    catering_event_id VARCHAR PRIMARY KEY,
    catering_event_host_id VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    venue VARCHAR,
    description TEXT,
    expected_number_of_attendees INTEGER,
    event_start_time TIMESTAMP,
    event_end_time TIMESTAMP
);


CREATE TABLE catering_menu_item(
    catering_menu_item_id VARCHAR PRIMARY KEY,
    caterer_id VARCHAR NOT NULL,
    name VARCHAR,
    price DECIMAL
);


CREATE TABLE catering_menu_item_ingredients(
    catering_menu_item_id VARCHAR NOT NULL,
    ingredient VARCHAR
);


CREATE TABLE catering_menu_item_images(
    catering_menu_item_id VARCHAR NOT NULL,
    url VARCHAR
);


CREATE TABLE catering_order(
    catering_order_id VARCHAR PRIMARY KEY,
    status VARCHAR,
    catering_event_id VARCHAR NOT NULL,
    catering_event_host_id VARCHAR NOT NULL,
    caterer_id VARCHAR NOT NULL
);


CREATE TABLE catering_order_menu_items(
    catering_order_id VARCHAR NOT NULL,
    catering_menu_item_id VARCHAR NOT NULL,
    quantity INTEGER
);


CREATE TABLE catering_order_bill(
    bill_id VARCHAR UNIQUE,
    catering_order_id VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    outstanding_amount DECIMAL NOT NULL,
    due_time TIMESTAMP,
    settlement_time TIMESTAMP,
    remark VARCHAR
);


