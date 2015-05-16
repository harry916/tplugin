CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWPRODUCT` AS
    select 
        `PRODUCT`.`PROID` AS `PROID`,
        `PRODUCT`.`PRONAME` AS `PRONAME`,
        `PRODUCT`.`PROTYPE` AS `PROTYPE`,
        `PRODUCT`.`UNSTANDARD` AS `UNSTANDARD`
    from
        `PRODUCT`;
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWALLOWTYPELIVE` AS
    select 
        `SENDALLOW`.`STATE` AS `STATE`,
        `SENDALLOW`.`PROTYPE` AS `PROTYPE`,
        `SENDALLOW`.`STOREJSON` AS `STOREJSON`
    from
        `SENDALLOW`
    where
        (`SENDALLOW`.`PROTYPE` = '活体');
        
  
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWALLOWTYPENORMAL` AS
    select 
        `SENDALLOW`.`STATE` AS `STATE`,
        `SENDALLOW`.`PROTYPE` AS `PROTYPE`,
        `SENDALLOW`.`STOREJSON` AS `STOREJSON`
    from
        `SENDALLOW`
    where
        (`SENDALLOW`.`PROTYPE` = '普通器材');
        
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWALLOWTYPEUNNORMAL` AS
    select 
        `SENDALLOW`.`STATE` AS `STATE`,
        `SENDALLOW`.`PROTYPE` AS `PROTYPE`,
        `SENDALLOW`.`STOREJSON` AS `STOREJSON`
    from
        `SENDALLOW`
    where
        (`SENDALLOW`.`PROTYPE` = '超标器材');
        
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWSTOCKSH` AS
    select 
        `STOCK`.`PROID` AS `PROID`,
        `STOCK`.`STOREID` AS `STOREID`,
        `STOCK`.`NUMBER` AS `NUMBER`,
        `STOCK`.`PRENUMBER` AS `PRENUMBER`
    from
        `STOCK`
    where
        (`STOCK`.`STOREID` = '021');
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWSTOCKBJ` AS
    select 
        `STOCK`.`PROID` AS `PROID`,
        `STOCK`.`STOREID` AS `STOREID`,
        `STOCK`.`NUMBER` AS `NUMBER`,
        `STOCK`.`PRENUMBER` AS `PRENUMBER`
    from
        `STOCK`
    where
        (`STOCK`.`STOREID` = '010');
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWSTOCKGZ` AS
    select 
        `STOCK`.`PROID` AS `PROID`,
        `STOCK`.`STOREID` AS `STOREID`,
        `STOCK`.`NUMBER` AS `NUMBER`,
        `STOCK`.`PRENUMBER` AS `PRENUMBER`
    from
        `STOCK`
    where
        (`STOCK`.`STOREID` = '021');
        
        
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWPRODUCT` AS
    select 
        `PRODUCT`.`PROID` AS `PROID`,
        `PRODUCT`.`PRONAME` AS `PRONAME`,
        `PRODUCT`.`PROTYPE` AS `PROTYPE`,
        `PRODUCT`.`UNSTANDARD` AS `UNSTANDARD`
    from
        `PRODUCT`;
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWALLOWTYPELIVE` AS
    select 
        `SENDALLOW`.`STATE` AS `STATE`,
        `SENDALLOW`.`PROTYPE` AS `PROTYPE`,
        `SENDALLOW`.`STOREJSON` AS `STOREJSON`
    from
        `SENDALLOW`
    where
        (`SENDALLOW`.`PROTYPE` = '活体');
        
  
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWALLOWTYPENORMAL` AS
    select 
        `SENDALLOW`.`STATE` AS `STATE`,
        `SENDALLOW`.`PROTYPE` AS `PROTYPE`,
        `SENDALLOW`.`STOREJSON` AS `STOREJSON`
    from
        `SENDALLOW`
    where
        (`SENDALLOW`.`PROTYPE` = '普通器材');
        
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWALLOWTYPEUNNORMAL` AS
    select 
        `SENDALLOW`.`STATE` AS `STATE`,
        `SENDALLOW`.`PROTYPE` AS `PROTYPE`,
        `SENDALLOW`.`STOREJSON` AS `STOREJSON`
    from
        `SENDALLOW`
    where
        (`SENDALLOW`.`PROTYPE` = '超标器材');
        
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWSTOCKSH` AS
    select 
        `STOCK`.`PROID` AS `PROID`,
        `STOCK`.`STOREID` AS `STOREID`,
        `STOCK`.`NUMBER` AS `NUMBER`,
        `STOCK`.`PRENUMBER` AS `PRENUMBER`
    from
        `STOCK`
    where
        (`STOCK`.`STOREID` = '021');
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWSTOCKBJ` AS
    select 
        `STOCK`.`PROID` AS `PROID`,
        `STOCK`.`STOREID` AS `STOREID`,
        `STOCK`.`NUMBER` AS `NUMBER`,
        `STOCK`.`PRENUMBER` AS `PRENUMBER`
    from
        `STOCK`
    where
        (`STOCK`.`STOREID` = '010');
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWSTOCKGZ` AS
    select 
        `STOCK`.`PROID` AS `PROID`,
        `STOCK`.`STOREID` AS `STOREID`,
        `STOCK`.`NUMBER` AS `NUMBER`,
        `STOCK`.`PRENUMBER` AS `PRENUMBER`
    from
        `STOCK`
    where
        (`STOCK`.`STOREID` = '021');
        
        
        
CREATE OR REPLACE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `VIEWSTOCKSUM` AS
    select 
	    `STOCK`.`PROID` AS `PROID`,
        SUM(`STOCK`.`NUMBER`) AS `NUMBER`,
        SUM(`STOCK`.`PRENUMBER`) AS `PRENUMBER`
    from
        `STOCK`
	GROUP by
		`STOCK`.`PROID`
        
        
        
        
        
        