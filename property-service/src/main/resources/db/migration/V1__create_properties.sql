CREATE TABLE properties (
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(120)   NOT NULL,
    city       VARCHAR(80)    NOT NULL,
    price      NUMERIC(14, 2) NOT NULL CHECK (price > 0),
    created_at TIMESTAMPTZ    NOT NULL DEFAULT now()
);

CREATE INDEX idx_properties_city ON properties (city);
