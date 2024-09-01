
CREATE TABLE IF NOT EXISTS redirect
(
    id    BINARY(16) PRIMARY KEY,
    url   TEXT         NOT NULL,
    alias VARCHAR(255) NOT NULL
);