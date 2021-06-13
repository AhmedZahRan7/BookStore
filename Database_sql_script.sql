-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`author` (
  `Author_Id` INT NOT NULL AUTO_INCREMENT,
  `Author_Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Author_Id`),
  UNIQUE INDEX `Author_Name_UNIQUE` (`Author_Name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`catagory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`catagory` (
  `Catagory_Id` INT NOT NULL AUTO_INCREMENT,
  `Catagory_Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Catagory_Id`),
  UNIQUE INDEX `Catagory_Name_UNIQUE` (`Catagory_Name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`publisher` (
  `Publisher_Id` INT NOT NULL AUTO_INCREMENT,
  `Address` VARCHAR(245) NOT NULL,
  `Publisher_Name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Publisher_Id`),
  UNIQUE INDEX `Name_UNIQUE` (`Publisher_Name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`book` (
  `ISBN` CHAR(13) NOT NULL,
  `Title` VARCHAR(255) NOT NULL,
  `Price` DECIMAL(6,2) NOT NULL,
  `Publication_Year` CHAR(4) NOT NULL,
  `NOCopies` INT NOT NULL,
  `Threshold` INT NOT NULL,
  `Publisher_Id` INT NOT NULL,
  `Catagory_Id` INT NOT NULL,
  PRIMARY KEY (`ISBN`),
  INDEX `fk_Book_PUBLISHER1_idx` (`Publisher_Id` ASC) VISIBLE,
  INDEX `fk_Book_CATAGORY1_idx` (`Catagory_Id` ASC) VISIBLE,
  CONSTRAINT `fk_Book_CATAGORY1`
    FOREIGN KEY (`Catagory_Id`)
    REFERENCES `mydb`.`catagory` (`Catagory_Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Book_PUBLISHER1`
    FOREIGN KEY (`Publisher_Id`)
    REFERENCES `mydb`.`publisher` (`Publisher_Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`book_has_author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`book_has_author` (
  `ISBN` CHAR(13) NOT NULL,
  `Author_Id` INT NOT NULL,
  PRIMARY KEY (`ISBN`, `Author_Id`),
  INDEX `ISBN` (`ISBN` ASC) VISIBLE,
  INDEX `Author_Id` (`Author_Id` ASC) VISIBLE,
  CONSTRAINT `book_has_author_ibfk_1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `mydb`.`book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `book_has_author_ibfk_2`
    FOREIGN KEY (`Author_Id`)
    REFERENCES `mydb`.`author` (`Author_Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `User_Name` VARCHAR(16) NOT NULL,
  `Password` VARCHAR(32) NOT NULL,
  `Shipping_Address` VARCHAR(255) NOT NULL,
  `Last_Name` VARCHAR(45) NOT NULL,
  `First_Name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `Manager` INT NOT NULL,
  PRIMARY KEY (`User_Name`),
  UNIQUE INDEX `UserName_UNIQUE` (`User_Name` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`checkout`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`checkout` (
  `ISBN` CHAR(13) NOT NULL,
  `NOCopies` INT NOT NULL DEFAULT '1',
  `Date` DATE NOT NULL,
  `Checkout_ID` INT NOT NULL AUTO_INCREMENT,
  `User_Name` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`Checkout_ID`),
  INDEX `fk_USER_has_Book_Book1_idx` (`ISBN` ASC) VISIBLE,
  INDEX `fk_CHECKOUT_USER1_idx` (`User_Name` ASC) VISIBLE,
  CONSTRAINT `fk_CHECKOUT_USER1`
    FOREIGN KEY (`User_Name`)
    REFERENCES `mydb`.`user` (`User_Name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_USER_has_Book_Book1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `mydb`.`book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 42
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`credit_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`credit_card` (
  `Card_Id` CHAR(16) NOT NULL,
  `Exp_Date` DATE NOT NULL,
  `User_Name` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`Card_Id`, `User_Name`),
  UNIQUE INDEX `Card_ID_UNIQUE` (`Card_Id` ASC) VISIBLE,
  INDEX `fk_CREDIT CARD_USER1_idx` (`User_Name` ASC) VISIBLE,
  CONSTRAINT `fk_CREDIT CARD_USER1`
    FOREIGN KEY (`User_Name`)
    REFERENCES `mydb`.`user` (`User_Name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`orders` (
  `ISBN` CHAR(13) NOT NULL,
  `NOCopies` INT NOT NULL DEFAULT '1',
  `Orders_Id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Orders_Id`),
  INDEX `fk_Book_has_USER_Book1_idx` (`ISBN` ASC) VISIBLE,
  CONSTRAINT `fk_Book_has_USER_Book1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `mydb`.`book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8;

USE `mydb` ;

-- -----------------------------------------------------
-- procedure PlaceOrder
-- -----------------------------------------------------

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`zezo`@`%` PROCEDURE `PlaceOrder`(IN ReqISBN CHAR(13))
BEGIN
         Insert into ORDERS(ISBN,NOCopies) values (ReqISBN , ( Select Threshold from Book where Book.ISBN = ReqISBN));
       END$$

DELIMITER ;
USE `mydb`;

DELIMITER $$
USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`author_BEFORE_DELETE`
BEFORE DELETE ON `mydb`.`author`
FOR EACH ROW
BEGIN
  	delete from book_has_author as ba where ba.Author_id=OLD.Author_id;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`Book_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`book`
FOR EACH ROW
BEGIN
IF NEW.NOCopies < NEW.Threshold then 
    set @message_text = concat("enter a value more than the threshold");
     signal sqlstate '45000' set message_text = @message_text;
END If;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`after_update_copies`
AFTER UPDATE ON `mydb`.`book`
FOR EACH ROW
BEGIN
IF NEW.NOCopies < NEW.Threshold AND OLD.NOCopies >= NEW.Threshold then 
    CAll PlaceOrder(NEW.ISBN);
END If;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`before_update_copies`
BEFORE UPDATE ON `mydb`.`book`
FOR EACH ROW
BEGIN
declare ret INT;
if(NEW.NOCopies < 0) then
    set @message_text = concat("can't update with negative");
    signal sqlstate '45000' set message_text = @message_text;
end if;

select NOCopies into ret from Book where NEW.ISBN = ISBN;
IF NEW.NOCopies < 0 then 
    set @message_text = concat('out of stock, remaining is ', ret);
    signal sqlstate '45000' set message_text = @message_text;
END If;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`book_has_author_AFTER_DELETE`
AFTER DELETE ON `mydb`.`book_has_author`
FOR EACH ROW
BEGIN
     delete from book as b where b.isbn NOT IN(Select ba.ISBN from book_has_author as ba);
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`CHECKOUT_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`checkout`
FOR EACH ROW
BEGIN
declare ret INT;
select NOCopies into ret from Book where NEW.ISBN = ISBN;
if(ret < NEW.NOCopies) then
 set @message_text = concat('out of stock, remaining is ', ret);
    signal sqlstate '45000' set message_text = @message_text;
END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`UPDATE_AFTER_INSERT_IN_CHECKOUT`
AFTER INSERT ON `mydb`.`checkout`
FOR EACH ROW
BEGIN
update book set Book.NOCOpies = Book.NOCopies - NEW.NOCopies where NEW.ISBN = book.ISBN;
END$$

USE `mydb`$$
CREATE
DEFINER=`zezo`@`%`
TRIGGER `mydb`.`ORDERS_BEFORE_DELETE`
BEFORE DELETE ON `mydb`.`orders`
FOR EACH ROW
BEGIN
Update Book As B set  B.NOCopies = B.NOCopies + OLD.NOCopies  where OLD.ISBN = B.ISBN;  
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
