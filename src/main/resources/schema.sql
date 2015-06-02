CREATE TABLE IF NOT EXISTS `users` (
  `id` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(500) NULL,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  `account_number` int NULL,
  `business_name` VARCHAR(50) NULL,
  `area_code` VARCHAR(3) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `zip_code` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));


CREATE TABLE IF NOT EXISTS `authorities` (
  `user_id` mediumint NULL,
  `authority` varchar(50) DEFAULT NULL,
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `drip_user` (
  `id` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  `account_number` int NULL,
  `business_name` VARCHAR(50) NULL,
  `area_code` VARCHAR(3) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `zip_code` VARCHAR(10) NOT NULL,
  `registered` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`));