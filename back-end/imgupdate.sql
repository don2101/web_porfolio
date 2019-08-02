-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ssafy
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafy` ;

-- -----------------------------------------------------
-- Table `ssafy`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy`.`post` (
  `Post_id` INT(11) NOT NULL,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `content` VARCHAR(1000) NULL,
  `count` INT(11) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`Post_id`),
  UNIQUE INDEX `ID_UNIQUE` (`Post_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy`.`comment` (
  `C_id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `content` VARCHAR(500) NULL DEFAULT NULL,
  `FK_Post_id` INT(11) NOT NULL,
  PRIMARY KEY (`C_id`),
  UNIQUE INDEX `ID_UNIQUE` (`C_id` ASC) VISIBLE,
  INDEX `fk_Comment_Post_idx` (`FK_Post_id` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_Post`
    FOREIGN KEY (`FK_Post_id`)
    REFERENCES `ssafy`.`post` (`Post_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy`.`member` (
  `mid` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `pw` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`mid`),
  UNIQUE INDEX `ID_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `M_id_UNIQUE` (`mid` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`portfolio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy`.`portfolio` (
  `Pf_id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `content` VARCHAR(1000) NULL DEFAULT NULL,
  `count` INT(11) NULL DEFAULT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`Pf_id`),
  UNIQUE INDEX `ID_UNIQUE` (`Pf_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`postImg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy`.`postImg` (
  `img_id` INT(11) NOT NULL AUTO_INCREMENT,
  `src` VARCHAR(45) NULL DEFAULT NULL,
  `FK_Post_id` INT(11) NOT NULL,
  PRIMARY KEY (`img_id`),
  UNIQUE INDEX `ID_UNIQUE` (`img_id` ASC) VISIBLE,
  INDEX `fk_postImg_post1_idx` (`FK_Post_id` ASC) VISIBLE,
  CONSTRAINT `fk_postImg_post1`
    FOREIGN KEY (`FK_Post_id`)
    REFERENCES `ssafy`.`post` (`Post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy`.`pfImg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy`.`pfImg` (
  `img_id` INT(11) NOT NULL AUTO_INCREMENT,
  `src` VARCHAR(45) NULL DEFAULT NULL,
  `FK_Pf_id` INT(11) NOT NULL,
  PRIMARY KEY (`img_id`),
  UNIQUE INDEX `ID_UNIQUE` (`img_id` ASC) VISIBLE,
  INDEX `fk_pfImg_portfolio1_idx` (`FK_Pf_id` ASC) VISIBLE,
  CONSTRAINT `fk_pfImg_portfolio1`
    FOREIGN KEY (`FK_Pf_id`)
    REFERENCES `ssafy`.`portfolio` (`Pf_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
