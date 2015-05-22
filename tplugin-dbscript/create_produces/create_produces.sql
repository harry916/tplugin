USE `tplugin`;
DROP procedure IF EXISTS `LOGINPROCEDURES`;

DELIMITER $$
USE `tplugin`$$
CREATE PROCEDURE `LOGINPROCEDURES` (IN userName2 varchar(40), IN password2 varchar(64),
IN ipAdress2 varchar(512), IN powerCode2 varchar(20))
BEGIN
	declare cnt int;
	set @cnt = 0;
	
	select count(*)  into @cnt from LOGIN where `USERNAME` = userName2 and `PASSWORD` = password2;
	if @cnt > 0 then
		update LOGIN set `IPADRESS` = ipAdress2, `POWERCODE`= powerCode2
		where `USERNAME` = userName2 and `PASSWORD` = password2;
	else
		insert into LOGIN values(userName2, password2, ipAdress2, powerCode2);
	end if;
END;

$$

DELIMITER ;

USE `tplugin`;
DROP procedure IF EXISTS `PRODUCTPROCEDURES`;

DELIMITER $$
USE `tplugin`$$
CREATE PROCEDURE `PRODUCTPROCEDURES` (IN proId2 varchar(64), IN proName2 varchar(60),
IN proType2 varchar(64), IN unstandard2 varchar(10))
BEGIN
	declare cnt int;
	set @cnt = 0;
	
	select count(*)  into @cnt from PRODUCT where `PROID` = proId2;
	if @cnt > 0 then
		update PRODUCT set `PRONAME` = proName2, `PROTYPE`= proType2, `UNSTANDARD` = unstandard2
		where `PROID` = proId2;
	else
		insert into PRODUCT values(proId2, proName2, proType2, unstandard2);
	end if;
END;

$$

DELIMITER ;



USE `tplugin`;
DROP procedure IF EXISTS `SENDALLOWPROCEDURES`;

DELIMITER $$
USE `tplugin`$$
CREATE PROCEDURE `SENDALLOWPROCEDURES` (IN state2 varchar(10), IN proType2 varchar(30),
IN storeJson2 varchar(526))
BEGIN
	declare cnt int;
	set @cnt = 0;
	
	select count(*)  into @cnt from SENDALLOW where `STATE` = state2 and `PROTYPE` = proType2;
	if @cnt > 0 then
		update SENDALLOW set `STOREJSON` = storeJson2
		where `STATE` = state2 and `PROTYPE` = proType2;
	else
		insert into SENDALLOW values(state2, proType2, storeJson2);
	end if;
END;

$$

DELIMITER ;


USE `tplugin`;
DROP procedure IF EXISTS `SENDORDERPROCEDURES`;

DELIMITER $$
USE `tplugin`$$
CREATE PROCEDURE `SENDORDERPROCEDURES` (IN state2 varchar(10), IN proType2 varchar(30),
IN sendIndex2 varchar(128))
BEGIN
	declare cnt int;
	set @cnt = 0;
	
	select count(*)  into @cnt from SENDORDER where `STATE` = state2 and `PROTYPE` = proType2;
	if @cnt > 0 then
		update SENDORDER set `SENDINDEX` = sendIndex2
		where `STATE` = state2 and `PROTYPE` = proType2;
	else
		insert into SENDORDER values(state2, proType2, sendIndex2);
	end if;
END;

$$

DELIMITER ;



USE `tplugin`;
DROP procedure IF EXISTS `STOCKPROCEDURES`;

DELIMITER $$
USE `tplugin`$$
CREATE PROCEDURE `STOCKPROCEDURES` (IN proId2 varchar(64), IN storeId2 varchar(10), IN number2 int,
IN preNumber2 int)
BEGIN
	declare cnt int;
	set @cnt = 0;
	
	select count(*)  into @cnt from STOCK where `PROID` = proId2 and `STOREID` = storeId2;
	if @cnt > 0 then
		update STOCK set `NUMBER` = number2,`PRENUMBER` = preNumber2
		where `PROID` = proId2 and `STOREID` = storeId2;
	else
	insert into STOCK values(proId2, storeId2, number2, preNumber2);
	end if;
END;

$$

DELIMITER ;