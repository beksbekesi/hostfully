# hostfully

This REST API is designed to fulfill the following requirements:

## Requirements
The REST API should allow users to:
- **Create a Booking**
    - Endpoint: `POST /bookings`

- **Update Booking Dates and Guest Details**
    - Endpoint: `PUT /bookings/{bookingId}`
- **Cancel a Booking**
    - Endpoint: `PUT /bookings/{bookingId}`
    - Request Body: Set the `bookingStatus` variable to `CANCELLED`.
    - Note: Every booking has a `bookingStatus` (ACTIVE, CANCELLED, DELETED). To cancel a booking, set this variable to `CANCELLED`.

- **Rebook a Canceled Booking**
    - Endpoint: `PUT /bookings/{bookingId}`
    - Request Body: Set the `bookingStatus` variable to `ACTIVE`.
    - Note: Every booking has a `bookingStatus` (ACTIVE, CANCELLED, DELETED). To rebook a canceled booking, set this variable to `ACTIVE`.

- **Delete a Booking**
    - Endpoint: `DELETE /bookings/{bookingId}`

- **Get a Booking**
    - Endpoint: `GET /bookings/{bookingId}`

- **Create a Block**
    - Endpoint: `POST /blocks`

- **Update a Block**
    - Endpoint: `PUT /blocks/{blockId}`

- **Delete a Block**
    - Endpoint: `DELETE /blocks/{blockId}`

## Validation

- [x] Implement proper validation to ensure data integrity.
- [x] Provide logic to prevent bookings from overlapping (in terms of dates and property) with non-canceled bookings or blocks.