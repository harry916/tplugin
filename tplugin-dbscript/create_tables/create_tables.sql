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

/*Create SENDALLOW TABLE*/
DROP TABLE IF EXISTS `SENDALLOW`;
CREATE TABLE `SENDALLOW`(
  `STATE` VARCHAR(10) NOT NULL,
  `PROTYPE` VARCHAR(30) NOT NULL,
  `STOREJSON` VARCHAR(526) NOT NULL,
  PRIMARY KEY(`STATE`,`PROTYPE`)
);

/*Create SENDORDER TABLE*/
DROP TABLE IF EXISTS `SENDORDER`;
CREATE TABLE `SENDORDER`(
  `STATE` VARCHAR(10) NOT NULL,
  `PROTYPE` VARCHAR(30) DEFAULT NULL,
  `SENDINDEX` VARCHAR(128) NOT NULL,
  PRIMARY KEY(`STATE`,`PROTYPE`)
);

/*Create STOCK TABLE*/
DROP TABLE IF EXISTS `STOCK`;
CREATE TABLE `STOCK`(
	`PROID` VARCHAR(64) NOT NULL PRIMARY KEY,
	`STOREID` VARCHAR(10) NOT NULL PRIMARY KEY,
	`NUMBER` INT(11) NOT NULL,
	`PRENUMBER` INT(11) NULL DEFAULT NULL
);

