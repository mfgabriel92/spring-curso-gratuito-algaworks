CREATE TABLE IF NOT EXISTS comments (
    id TINYINT NOT NULL AUTO_INCREMENT,
    service_order_id TINYINT NOT NULL,
    content TEXT NOT NULL,
    created_at DATETIME NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_comment_service_order FOREIGN KEY (service_order_id) REFERENCES service_orders (id)
);