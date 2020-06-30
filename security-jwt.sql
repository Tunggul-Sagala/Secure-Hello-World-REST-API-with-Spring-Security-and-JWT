DROP SCHEMA IF EXISTS `security-jwt`;

CREATE SCHEMA `security-jwt`;

USE `security-jwt`;

SET FOREIGN_KEY_CHECKS =0;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(100) NOT NULL,
    `password` VARCHAR(250) NOT NULL,
    `email` VARCHAR(250) NOT NULL,
    `enabled` BOOLEAN,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET =Latin1;