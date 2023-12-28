CREATE TABLE bookings (
    id VARCHAR(36) PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    booking_status VARCHAR(255) NOT NULL,
    guest_id VARCHAR(255) NOT NULL,
    property_id VARCHAR(255) NOT NULL
);