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

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_name` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
  `borrow1_id` int DEFAULT NULL,
  `borrow2_id` int DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `borrow1_id` (`borrow1_id`),
  KEY `borrow2_id` (`borrow2_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`borrow1_id`) REFERENCES `borrow` (`borrow_id`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`borrow2_id`) REFERENCES `borrow` (`borrow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20230506 DEFAULT CHARSET=gbk;

/*Data for the table `student` */

insert  into `student`(`student_id`,`student_name`,`borrow1_id`,`borrow2_id`) values (20230101,'Miku',32,41),(20230103,'Rin',NULL,NULL),(20230201,'Gumi',NULL,NULL),(20230202,'IA',39,NULL),(20230301,'Renri',NULL,NULL),(20230401,'Ayase',34,36),(20230402,'Deco',37,38),(20230403,'Orangestar',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
