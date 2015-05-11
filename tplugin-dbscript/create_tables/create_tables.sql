/*Create SYSMAIN table*/
DROP TABLE IF EXISTS `SYSMAIN`;
CREATE TABLE `SYSMAIN`(
	`SYSID` VARCHAR(50) NOT NULL PRIMARY KEY,
	`CONTENT` VARCHAR(50) NOT NULL,
	`CLASS` VARCHAR(50) NOT NULL,
	`COMMAND` VARCHAR(50) NULL DEFAULT NULL
);

/*Create LOGIN table*/
DROP TABLE IF EXISTS `LOGIN`;
CREATE TABLE `LOGIN`(
	`USERNAME` VARCHAR(40) NOT NULL  PRIMARY KEY,
	`PASSWORD` VARCHAR(64) NOT NULL,
	`IPADRESS` VARCHAR(40) NOT NULL,
	`POWERCODE` VARCHAR(20)  NOT NULL
);

/*Create PRODUCT TABLE*/
DROP TABLE IF EXISTS `PRODUCT`;
CREATE TABLE `PRODUCT`(
  `PROID` VARCHAR(64) NOT NULL PRIMARY KEY,
  `PRONAME` VARCHAR(60) NOT NULL,
  `PROTYPE` VARCHAR(64) NOT NULL,
  `UNSTANDARD` VARCHAR(10) NULL DEFAULT NULL
);

/*Create STORE TABLE*/
DROP TABLE IF EXISTS `STORE`;
CREATE TABLE `STORE`(
  `STOREID` VARCHAR(10) NOT NULL PRIMARY KEY,
  `NAME` VARCHAR(64) NOT NULL,
  `SENDSCOPE` VARCHAR(40) NOT NULL,
  `STOREINDEX` INT(11) NOT NULL
);

/*Create STOCK TABLE*/
DROP TABLE IF EXISTS `STOCK`;
CREATE TABLE `STOCK`(
	`PROID` VARCHAR(64) NOT NULL PRIMARY KEY,
	`STOREID` VARCHAR(10) NOT NULL,
	`NUMBER` INT(11) NOT NULL,
	`PRENUMBER` INT(11) NULL DEFAULT NULL;
);
