CREATE DATABASE payments_repository;
CREATE SCHEMA payments_storage;

CREATE TABLE IF NOT EXISTS "user"
(
    id      uuid PRIMARY KEY,
    name    VARCHAR(128) NOT NULL,
    surname VARCHAR(128) NOT NULL,
    email   VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS account
(
    id           uuid PRIMARY KEY,
    number       VARCHAR(128) UNIQUE,
    type         VARCHAR(128) NOT NULL,
    minor_amount BIGINT       NOT NULL,
    currency     VARCHAR(3)   NOT NULL,
    status       VARCHAR(128),
    owner_id     uuid REFERENCES "user" (id)
);

CREATE TABLE IF NOT EXISTS card
(
    id         uuid PRIMARY KEY,
    number     VARCHAR(128) UNIQUE,
    type       VARCHAR(128) NOT NULL,
    status     VARCHAR(128) NOT NULL,
    account_id uuid REFERENCES account (id)
);

CREATE TABLE IF NOT EXISTS payment
(
    id                   uuid PRIMARY KEY,
    sender_account_id    uuid REFERENCES account (id) NOT NULL,
    recipient_account_id uuid REFERENCES account (id) NOT NULL,
    amount               NUMERIC                      NOT NULL,
    description          TEXT                         NOT NULL,
    payment_date         DATE                         NOT NULL,
    status               VARCHAR(128)                 NOT NULL
);

CREATE INDEX IF NOT EXISTS account_card_idx ON card (account_id);

CREATE INDEX IF NOT EXISTS account_owner_id_idx ON account (owner_id);

CREATE INDEX IF NOT EXISTS user_email_idx ON "user" (email);

DROP table "user";
DROP table card;
DROP table account;
DROP table payment;