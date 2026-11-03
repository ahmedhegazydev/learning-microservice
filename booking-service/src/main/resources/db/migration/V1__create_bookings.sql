-- Lets a GiST index compare plain columns like property_id with =
CREATE EXTENSION IF NOT EXISTS btree_gist;

CREATE TABLE bookings (
    id          BIGSERIAL PRIMARY KEY,
    -- An id owned by property-service: no foreign key, since that table lives in another database
    property_id BIGINT       NOT NULL,
    guest_name  VARCHAR(120) NOT NULL,
    check_in    DATE         NOT NULL,
    check_out   DATE         NOT NULL,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT now(),
    CHECK (check_out > check_in),
    -- The database itself refuses two bookings of one property on overlapping dates,
    -- even when two requests arrive at the same moment
    CONSTRAINT no_overlapping_bookings
        EXCLUDE USING gist (property_id WITH =, daterange(check_in, check_out) WITH &&)
);
