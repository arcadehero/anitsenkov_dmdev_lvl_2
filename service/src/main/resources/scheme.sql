CREATE TABLE IF NOT EXISTS users
(
    id       uuid PRIMARY KEY,
    name     VARCHAR(128) NOT NULL,
    surname  VARCHAR(128) NOT NULL,
    email    VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    role     VARCHAR(32)  NOT NULL
);

CREATE TABLE IF NOT EXISTS account
(
    id       uuid PRIMARY KEY,
    number   VARCHAR(128) UNIQUE,
    type     VARCHAR(128) NOT NULL,
    amount   BIGINT       NOT NULL,
    currency VARCHAR(3)   NOT NULL,
    status   VARCHAR(128) NOT NULL,
    users_id uuid REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS card
(
    id           uuid PRIMARY KEY,
    number       VARCHAR(128) UNIQUE,
    type         VARCHAR(128) NOT NULL,
    status       VARCHAR(128) NOT NULL,
    expired_date DATE         NOT NULL,
    account_id   uuid REFERENCES account (id)
);

CREATE TABLE IF NOT EXISTS payment
(
    id                   uuid PRIMARY KEY,
    sender_account_id    uuid REFERENCES account (id) NOT NULL,
    recipient_account_id uuid REFERENCES account (id) NOT NULL,
    amount               BIGINT                       NOT NULL,
    currency             VARCHAR(3)                   NOT NULL,
    description          TEXT                         NOT NULL,
    payment_date         DATE                         NOT NULL,
    status               VARCHAR(128)                 NOT NULL
);

CREATE INDEX IF NOT EXISTS account_card_idx ON card (account_id);

CREATE INDEX IF NOT EXISTS account_users_id_idx ON account (users_id);

CREATE INDEX IF NOT EXISTS users_email_idx ON users (email);

DROP table users cascade;
DROP table account CASCADE;
DROP table card CASCADE;
DROP table payment CASCADE;

SELECT * FROM users WHERE name = 'alex'