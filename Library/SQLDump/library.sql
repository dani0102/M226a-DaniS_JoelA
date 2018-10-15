-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.30-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loans` (
  `ID_Loans` int(11) NOT NULL AUTO_INCREMENT,
  `lendDate` date DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  `FK_Medien` int(11) NOT NULL,
  `FK_User` int(11) NOT NULL,
  PRIMARY KEY (`ID_Loans`),
  KEY `FK_Medien` (`FK_Medien`),
  KEY `FK_User` (`FK_User`),
  CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`FK_Medien`) REFERENCES `medien` (`ID_Medien`),
  CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`FK_User`) REFERENCES `user` (`ID_User`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES (1,'2018-10-12',NULL,1,1),(2,'2018-10-12','2018-10-12',2,1);
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medien`
--

DROP TABLE IF EXISTS `medien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medien` (
  `ID_Medien` int(11) NOT NULL AUTO_INCREMENT,
  `Titel` varchar(50) NOT NULL,
  `available` tinyint(1) DEFAULT '1',
  `Author` varchar(50) NOT NULL,
  `FK_Sort` int(11) NOT NULL,
  PRIMARY KEY (`ID_Medien`),
  KEY `FK_Sort` (`FK_Sort`),
  CONSTRAINT `medien_ibfk_1` FOREIGN KEY (`FK_Sort`) REFERENCES `sort` (`ID_Sort`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medien`
--

LOCK TABLES `medien` WRITE;
/*!40000 ALTER TABLE `medien` DISABLE KEYS */;
INSERT INTO `medien` VALUES (1,'testbuch',0,'testauthor',1),(2,'testdvd',1,'testauthor',2),(3,'buch1',1,'author1',1),(4,'dvd1',1,'author1',2);
/*!40000 ALTER TABLE `medien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sort`
--

DROP TABLE IF EXISTS `sort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sort` (
  `ID_Sort` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(4) NOT NULL,
  PRIMARY KEY (`ID_Sort`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sort`
--

LOCK TABLES `sort` WRITE;
/*!40000 ALTER TABLE `sort` DISABLE KEYS */;
INSERT INTO `sort` VALUES (1,'Book'),(2,'DVD');
/*!40000 ALTER TABLE `sort` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID_User` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_User`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test','test');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-12 16:34:26
