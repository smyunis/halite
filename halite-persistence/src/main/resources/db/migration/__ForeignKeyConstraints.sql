ALTER TABLE caterer_review
    ADD FOREIGN KEY (reviewer_id) REFERENCES catering_event_host(catering_event_host_id),
    ADD FOREIGN KEY (reviewed_caterer_id) REFERENCES caterer(caterer_id);

ALTER TABLE catering_event
    ADD FOREIGN KEY (catering_event_host_id) REFERENCES catering_event_host (catering_event_host_id);


ALTER TABLE catering_menu_item
    ADD FOREIGN KEY (caterer_id) REFERENCES caterer (caterer_id);


ALTER TABLE catering_menu_item_ingredients
    ADD FOREIGN KEY (catering_menu_item_id) REFERENCES catering_menu_item(catering_menu_item_id) ON DELETE CASCADE;


ALTER TABLE catering_menu_item_images
    ADD FOREIGN KEY (catering_menu_item_id) REFERENCES catering_menu_item(catering_menu_item_id) ON DELETE CASCADE;


ALTER TABLE catering_order
    ADD  FOREIGN KEY (catering_event_id) REFERENCES catering_event(catering_event_id) ON DELETE CASCADE,
    ADD  FOREIGN KEY (catering_event_host_id) REFERENCES catering_event_host(catering_event_host_id),
    ADD  FOREIGN KEY (caterer_id) REFERENCES caterer(caterer_id);


ALTER TABLE catering_order_menu_items
    ADD  FOREIGN KEY (catering_order_id) REFERENCES catering_order(catering_order_id) ON DELETE CASCADE,
    ADD  FOREIGN KEY (catering_menu_item_id) REFERENCES catering_menu_item(catering_menu_item_id);

ALTER TABLE catering_order_bill
    ADD FOREIGN KEY (catering_order_id) REFERENCES catering_order(catering_order_id) ON DELETE CASCADE;

