CREATE TABLE properties (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE bookings (
    id VARCHAR(36) PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    booking_status VARCHAR(255) NOT NULL,
    property_id VARCHAR(36) NOT NULL,
        FOREIGN KEY (property_id) REFERENCES properties(id)
);

CREATE TABLE guest_details (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    booking_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES bookings(id)
);

INSERT INTO properties (id, name) VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Example Property');
