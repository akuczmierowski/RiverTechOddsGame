--liquibase formatted sql

--changeset root:UTF-8

CREATE TABLE IF NOT EXISTS player(
	id bigint AUTO_INCREMENT not null primary key,
	name varchar(50) not null,
	surname varchar(50) not null,
	username varchar(50) not null unique,
	balance decimal (10,2) not null default 1000

);

CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type ENUM('WIN', 'LOSE') NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    player_id BIGINT NOT NULL,
    FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE
);