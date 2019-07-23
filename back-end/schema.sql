-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssafy
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafy` DEFAULT CHARACTER SET utf8 ;
USE `ssafy` ;

-- -----------------------------------------------------
-- Table `ssafy`.`Member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy`.`Member` (
  `e-mail` VARCHAR(45) NOT NULL,
  `pw` VARCHAR(45) NOT NULL,
  `M_id` INT NOT NULL AUTO_INCREMENT,
  UNIQUE INDEX `ID_UNIQUE` (`e-mail` ASC) VISIBLE,
  PRIMARY KEY (`M_id`),
  UNIQUE INDEX `M_id_UNIQUE` (`M_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafy`.`Post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy`.`Post` (
  `Post_id` INT NOT NULL,
  `md_src` VARCHAR(100) NULL,
  `count` INT NULL,
  `date` DATE NULL,
  UNIQUE INDEX `ID_UNIQUE` (`Post_id` ASC) VISIBLE,
  PRIMARY KEY (`Post_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafy`.`Portfolio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy`.`Portfolio` (
  `Pf_id` INT NOT NULL,
  `img` VARCHAR(100) NULL,
  `title` VARCHAR(45) NULL,
  `content` VARCHAR(45) NULL,
  `count` INT NULL AUTO_INCREMENT,
  UNIQUE INDEX `ID_UNIQUE` (`Pf_id` ASC) VISIBLE,
  PRIMARY KEY (`Pf_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafy`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy`.`Comment` (
  `C_id` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `content` VARCHAR(500) NULL,
  `FK_Post_id` INT NOT NULL,
  `Member_M_id` INT NOT NULL,
  UNIQUE INDEX `ID_UNIQUE` (`C_id` ASC) VISIBLE,
  PRIMARY KEY (`C_id`),
  INDEX `fk_Comment_Post_idx` (`FK_Post_id` ASC) VISIBLE,
  INDEX `fk_Comment_Member1_idx` (`Member_M_id` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_Post`
    FOREIGN KEY (`FK_Post_id`)
    REFERENCES `ssafy`.`Post` (`Post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Member1`
    FOREIGN KEY (`Member_M_id`)
    REFERENCES `ssafy`.`Member` (`M_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
