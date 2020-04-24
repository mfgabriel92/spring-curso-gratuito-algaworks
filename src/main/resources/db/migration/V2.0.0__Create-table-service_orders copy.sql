CREATE TABLE IF NOT EXISTS service_orders (
    id TINYINT NOT NULL AUTO_INCREMENT,
    client_id TINYINT NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(15) NOT NULL,
    opening_date DATETIME NOT NULL,
    closing_date DATETIME,

    PRIMARY KEY (id),
    CONSTRAINT fk_service_order_client FOREIGN KEY (client_id) REFERENCES clients (id)
);