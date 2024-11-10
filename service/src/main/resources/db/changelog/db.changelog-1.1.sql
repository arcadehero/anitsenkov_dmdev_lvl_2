-- liquibase formatted sql

-- changeset anitsenkov:1
ALTER TABLE  account
ALTER COLUMN amount TYPE NUMERIC;

-- changeset anitsenkov:2
ALTER TABLE payment
ALTER COLUMN amount TYPE NUMERIC;

-- changeset anitsenkov:3
ALTER TABLE users
ADD version VARCHAR(32);

ALTER TABLE account
ADD version VARCHAR(32);

ALTER TABLE card
ADD version VARCHAR(32);

ALTER TABLE payment
ADD version VARCHAR(32);