-- liquibase formatted sql

-- changeset anitsenkov:1
CREATE TABLE IF NOT EXISTS users
(
    id       uuid PRIMARY KEY,
    name     VARCHAR(128) NOT NULL,
    surname  VARCHAR(128) NOT NULL,
    email    VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    role     VARCHAR(32)  NOT NULL
);
-- rollback DROP TABLE users

-- changeset anitsenkov:2
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
-- rollback DROP TABLE account

-- changeset anitsenkov:3
CREATE TABLE IF NOT EXISTS card
(
    id           uuid PRIMARY KEY,
    number       VARCHAR(128) UNIQUE,
    type         VARCHAR(128) NOT NULL,
    status       VARCHAR(128) NOT NULL,
    expired_date DATE         NOT NULL,
    account_id   uuid REFERENCES account (id)
);
-- rollback DROP TABLE card

-- changeset anitsenkov:4
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
-- rollback DROP TABLE payment
