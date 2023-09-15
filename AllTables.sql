/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.29 : Database - spring
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `spring`;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  KEY `fk_authorities_users` (`username`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `authorities` */

insert  into `authorities`(`username`,`authority`) values 
('user','ROLE_SITEINCHARGE'),
('admin','ROLE_STOREINCHARGE');

/*Table structure for table `material_available` */

DROP TABLE IF EXISTS `material_available`;

CREATE TABLE `material_available` (
  `id_` int NOT NULL AUTO_INCREMENT,
  `material_name_` varchar(20) DEFAULT NULL,
  `material_qty_` int DEFAULT NULL,
  `material_unit_` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `material_available` */

insert  into `material_available`(`id_`,`material_name_`,`material_qty_`,`material_unit_`) values 
(1,'Plastic',500,'Kg'),
(2,'Steel',200,'Kg'),
(3,'Glass',500,'Kg'),
(4,'Fibre',300,'Kg'),
(5,'Wood',1000,'Kg'),
(6,'Iron',200,'Kg'),
(7,'Structural_Material',100,'Kg');

/*Table structure for table `material_info` */

DROP TABLE IF EXISTS `material_info`;

CREATE TABLE `material_info` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `material_name` varchar(30) DEFAULT NULL,
  `material_quantity` int DEFAULT NULL,
  `material_unit` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `material_info` */

insert  into `material_info`(`material_id`,`material_name`,`material_quantity`,`material_unit`) values 
(1,'Plastic',100,'Kg'),
(2,'Cement',30000,'Kg'),
(3,'Steel',10000,'Kg'),
(4,'Glass',5000,'Kg'),
(5,'Fibre',7000,'Kg'),
(6,'Wood',5000,'Kg'),
(7,'Iron',3000,'Kg'),
(8,'Texttile',5000,'Kg'),
(9,'Structural_Material',7000,'Kg'),
(10,'Raw Material',25000,'Kg'),
(11,'Straw',10000,'Kg');

/*Table structure for table `material_request` */

DROP TABLE IF EXISTS `material_request`;

CREATE TABLE `material_request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `material_name` varchar(30) DEFAULT NULL,
  `material_quantity` int DEFAULT NULL,
  `unit` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `material_request` */

insert  into `material_request`(`id`,`material_name`,`material_quantity`,`unit`) values 
(1,'Plastic',200,'Kg'),
(2,'Steel',100,'Kg'),
(3,'Fibre',300,'Kg'),
(4,'Glass',500,'Kg'),
(6,'Wood',100,'Kg'),
(8,'Plastic',0,'Kg');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`enabled`) values 
('admin','pass',1),
('user','pass',1);

/* Procedure structure for procedure `demo1` */

/*!50003 DROP PROCEDURE IF EXISTS  `demo1` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `demo1`(m_name VARCHAR(20), user_quantity INT)
BEGIN
              DECLARE EXIT HANDLER FOR SQLEXCEPTION
              BEGIN
              ROLLBACK;
              END;	
	
              start transaction;
              
              UPDATE material_info
              SET material_quantity = material_quantity - user_quantity
              WHERE material_name = m_name;           
             
             
             UPDATE material_available
             SET material_qty_ = material_qty_ + user_quantity
             WHERE material_name_ = m_name;

             UPDATE material_request
             SET material_quantity = material_quantity - user_quantity
             WHERE material_name = m_name;

            COMMIT;
	END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
