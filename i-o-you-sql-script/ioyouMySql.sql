/*******************************************************************************
   ioyou Database - Version 1.0
   Script: ioyouMySql.sql
   Description: Creates the ioyou database.
   DB Server: MySql
   Author: Kevin Aofia
********************************************************************************/

/*******************************************************************************
   Drop database if it exists
********************************************************************************/

DROP DATABASE IF EXISTS `ioyou`;

/*******************************************************************************
   Create database and use database
********************************************************************************/

CREATE DATABASE `ioyou`;

USE `ioyou`;

/*******************************************************************************
   Create Tables
********************************************************************************/
CREATE TABLE `Expense`
(
    `ExpenseId` INT NOT NULL AUTO_INCREMENT,
    `FirstName` NVARCHAR(30) NOT NULL,
    `LastName` NVARCHAR(30) NOT NULL,
    `Date` NVARCHAR(20) NOT NULL,
    `Amount` NVARCHAR(20) NOT NULL,
    `Reason` NVARCHAR(400) NOT NULL,
    `ReimbursementStatusId` INT NOT NULL,
    CONSTRAINT `PK_Expense` PRIMARY KEY  (`ExpenseId`)
);

CREATE TABLE `ReimbursementStatus`
(
    `ReimbursementStatusId` INT NOT NULL AUTO_INCREMENT,
    `Status` NVARCHAR(120) NOT NULL,
    CONSTRAINT `PK_ReimbursementStatus` PRIMARY KEY  (`ReimbursementStatusId`)
);

/*******************************************************************************
   Create Foreign Keys and Create Primary Key Unique Indexes
********************************************************************************/
ALTER TABLE `Expense` ADD CONSTRAINT `FK_ReimbursementStatusId`
    FOREIGN KEY (`ReimbursementStatusId`) REFERENCES `ReimbursementStatus` (`ReimbursementStatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

CREATE INDEX `IFK_ReimbursementStatusId` ON `Expense` (`ReimbursementStatusId`);

/*******************************************************************************
   Populate Tables
********************************************************************************/

INSERT INTO `ReimbursementStatus` (`Status`) VALUES (N'Pending');
INSERT INTO `ReimbursementStatus` (`Status`) VALUES (N'Under Review');
INSERT INTO `ReimbursementStatus` (`Status`) VALUES (N'Approved');
INSERT INTO `ReimbursementStatus` (`Status`) VALUES (N'Denied');
INSERT INTO `ReimbursementStatus` (`Status`) VALUES (N'Completed');

INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Kevin',N'Aofia',N'04-01-2022',N'$29.98',N'Brunch for stormers',1);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Kevin',N'Aofia',N'05-01-2022',N'$4.88',N'Snacks for stormers',1);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Kevin',N'Aofia',N'06-01-2022',N'$9.05',N'More snacks for stormers',1);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Kevin',N'Aofia',N'07-01-2022',N'$.99',N'A snack for a stormer is a snack for a stormer',1);

INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Kevin',N'Aofia',N'04-10-2022',N'$67.76',N'Lunch for stormers',1);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Lykasha',N'Lysongtseng',N'02-21-2022',N'$54.85',N'Breakfast for stormers',1);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Philip',N'Johnson',N'09-13-2021',N'$99.99',N'Dinner for stormers',1);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Alina',N'Korsunska',N'06-12-2022',N'$39.59',N'Lunch for stormers',2);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Berhe',N'Amare',N'01-19-2022',N'$10.45',N'Breakfast for stormers',2);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Breanna',N'Ransom',N'04-22-2022',N'$50.22',N'Dinner for stormers',2);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Daniel',N'Livingston',N'05-13-2021',N'$74.33',N'Lunch for stormers',3);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'David',N'Youngblood',N'02-23-2022',N'$19.29',N'Breakfast for stormers',3);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Dillon',N'Shomaker',N'05-11-2022',N'$137.56',N'Dinner for stormers',3);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Garth',N'Bates',N'06-10-2022',N'$77.91',N'Lunch for stormers',4);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Gary',N'Andrew',N'06-11-2022',N'$37.88',N'Breakfast for stormers',4);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Kevin',N'Garcia',N'07-05-2021',N'$70.24',N'Dinner for stormers',4);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Michael',N'Teeple',N'03-07-2021',N'$100.2',N'Lunch for stormers',5);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Sung',N'Baek',N'02-06-2022',N'$77.1',N'Breakfast for stormers',5);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Zachariah',N'Kelley',N'07-16-2022',N'$171.21',N'Dinner for stormers',5);
INSERT INTO `Expense` (`FirstName`, `LastName`, `Date`, `Amount`, `Reason`, `ReimbursementStatusId`) VALUES (N'Sara',N'Ifourah',N'07-12-2022',N'$171.21',N'Dinner for stormers',5);

