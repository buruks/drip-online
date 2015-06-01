CREATE TABLE IF NOT EXISTS `users` (
  `id` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  `business_name` VARCHAR(50) NULL,
  `email` VARCHAR(60) NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `zip_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `authorities` (
  `user_id` mediumint NULL,
  `authority` varchar(50) DEFAULT NULL,
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;