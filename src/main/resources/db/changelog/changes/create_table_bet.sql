--liquibase formatted sql

--changeset root:UTF-8

CREATE TABLE IF NOT EXISTS bet (
 id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
 bet_number INT NOT NULL CHECK (bet_number BETWEEN 1 AND 10),
 bet_amount double (10,2) not null,
 generated_number INT NOT NULL,
 result double (10,2)  NOT NULL,
 player_id bigint not null,
 FOREIGN KEY (player_id) REFERENCES player(id)
);