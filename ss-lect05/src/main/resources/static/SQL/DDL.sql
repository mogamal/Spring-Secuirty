CREATE SCHEMA `spring06` ;
CREATE TABLE `spring06`.`user` (
  `id` INT NOT NULL,
  `username` VARCHAR(100) NULL,
  `password` VARCHAR(250) NULL,
  PRIMARY KEY (`id`));

  
  
  CREATE TABLE `spring06`.`otp` (
  `id` INT NOT NULL,
  `username` VARCHAR(100) NOT NULL,
  `otp` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
