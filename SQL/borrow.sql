/*
SQLyog Professional v12.09 (64 bit)
MySQL - 8.0.32 : Database - mybatis
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mybatis` /*!40100 DEFAULT CHARACTER SET gbk */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `mybatis`;

/*Table structure for table `borrow` */

DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `borrow` (
  `borrow_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `borrow_time` varchar(50) DEFAULT NULL,
  `deadline` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`borrow_id`),
  KEY `book_id` (`book_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `borrow_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=gbk;

/*Data for the table `borrow` */

insert  into `borrow`(`borrow_id`,`student_id`,`book_id`,`borrow_time`,`deadline`) values (26,NULL,4,'2023-06-01','2025-10-02'),(27,NULL,10,'2023-06-01','2025-07-02'),(28,NULL,7,'2023-06-01','2023-06-02'),(32,20230101,7,'2023-06-03','2024-08-04'),(34,20230401,10,'2023-06-03','2023-06-04'),(36,20230401,7,'2023-06-03','2028-01-04'),(37,20230402,10,'2023-06-03','2023-06-04'),(38,20230402,4,'2023-06-03','2023-06-04'),(39,20230202,10,'2023-06-03','2023-06-04'),(41,20230101,4,'2023-06-04','2025-08-05');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
