-- -----------------------------------------------------
-- Schema inventory-app
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `inventory-app`;

CREATE SCHEMA `inventory-app`;
USE `inventory-app` ;

-- -----------------------------------------------------
-- Table `inventory-app`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory-app`.`employee` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) DEFAULT NULL,
  `age` INT(4) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;



-- -----------------------------------------------------
-- Add sample data
-- -----------------------------------------------------

INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME, AGE) VALUES ('JACK', 'JONES', 32);
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME, AGE) VALUES ('JILL', 'HIGH', 23);
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME, AGE) VALUES ('WILL', 'SMITH', 40);
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME, AGE) VALUES ('MARY', 'KOM', 30);
INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME, AGE) VALUES ('STEVE', 'MILLER', 50);


-- -----------------------------------------------------
-- Table `inventory-app`.`file_processing_task_dtls`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory-app`.`file_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `task_id` VARCHAR(255) NOT NULL,
  `file_name` VARCHAR(255) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

