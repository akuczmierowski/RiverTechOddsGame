--liquibase formatted sql

--changeset root:UTF-8

ALTER TABLE player
MODIFY COLUMN balance DOUBLE (10, 2) NOT NULL DEFAULT 1000;

ALTER TABLE transaction
MODIFY COLUMN amount DOUBLE (10, 2) NOT NULL;