-- MySQL dump 10.13  Distrib 5.5.27, for Win64 (x86)
--
-- Host: localhost    Database: miserver
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bus` (
  `BusId` int(11) NOT NULL AUTO_INCREMENT,
  `BusCapacity` int(11) DEFAULT '0',
  PRIMARY KEY (`BusId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0),(8,0),(9,0),(10,0),(11,0),(12,0),(13,0),(14,0),(15,0);
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `busstation`
--

DROP TABLE IF EXISTS `busstation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `busstation` (
  `BusStationID` int(11) NOT NULL AUTO_INCREMENT,
  `Distance` int(11) DEFAULT '3000',
  PRIMARY KEY (`BusStationID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `busstation`
--

LOCK TABLES `busstation` WRITE;
/*!40000 ALTER TABLE `busstation` DISABLE KEYS */;
INSERT INTO `busstation` VALUES (1,3000),(2,4000);
/*!40000 ALTER TABLE `busstation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car` (
  `CarId` int(11) NOT NULL AUTO_INCREMENT,
  `CarAction` varchar(255) DEFAULT 'Start',
  `Balance` int(11) DEFAULT '100',
  `CarSpeed` int(11) DEFAULT '60',
  PRIMARY KEY (`CarId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,'Start',100,60),(2,'Start',100,60),(3,'Start',100,60),(4,'Start',100,60),(5,'Start',100,60),(6,'Start',100,60),(7,'Start',100,60),(8,'Start',100,60),(9,'Start',100,60),(10,'Start',100,60),(11,'Start',100,60),(12,'Start',100,60),(13,'Start',100,60),(14,'Start',100,60),(15,'Start',100,60);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `RateType` varchar(255) DEFAULT 'Count',
  `Money` int(11) DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking`
--

LOCK TABLES `parking` WRITE;
/*!40000 ALTER TABLE `parking` DISABLE KEYS */;
INSERT INTO `parking` VALUES (1,'Count',20);
/*!40000 ALTER TABLE `parking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkingspaces`
--

DROP TABLE IF EXISTS `parkingspaces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parkingspaces` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) DEFAULT '1',
  `fk_parkingId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_parkingSpaces_parkingId` (`fk_parkingId`),
  CONSTRAINT `fk_parkingSpaces_parkingId` FOREIGN KEY (`fk_parkingId`) REFERENCES `parking` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkingspaces`
--

LOCK TABLES `parkingspaces` WRITE;
/*!40000 ALTER TABLE `parkingspaces` DISABLE KEYS */;
INSERT INTO `parkingspaces` VALUES (1,1,1),(2,1,1);
/*!40000 ALTER TABLE `parkingspaces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `road`
--

DROP TABLE IF EXISTS `road`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `road` (
  `RoadId` int(11) NOT NULL AUTO_INCREMENT,
  `Status` int(11) DEFAULT '1',
  PRIMARY KEY (`RoadId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `road`
--

LOCK TABLES `road` WRITE;
/*!40000 ALTER TABLE `road` DISABLE KEYS */;
INSERT INTO `road` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1);
/*!40000 ALTER TABLE `road` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roadlight`
--

DROP TABLE IF EXISTS `roadlight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roadlight` (
  `RoadLightId` int(11) NOT NULL AUTO_INCREMENT,
  `Status` varchar(255) DEFAULT 'Close',
  `ControlMode` varchar(255) DEFAULT 'Auto',
  PRIMARY KEY (`RoadLightId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roadlight`
--

LOCK TABLES `roadlight` WRITE;
/*!40000 ALTER TABLE `roadlight` DISABLE KEYS */;
INSERT INTO `roadlight` VALUES (1,'Close','Auto'),(2,'Close','Auto'),(3,'Close','Auto');
/*!40000 ALTER TABLE `roadlight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `signallight`
--

DROP TABLE IF EXISTS `signallight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `signallight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Time` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `fk_TrafficLightId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_signallight_TrafficLightId` (`fk_TrafficLightId`),
  CONSTRAINT `fk_signallight_TrafficLightId` FOREIGN KEY (`fk_TrafficLightId`) REFERENCES `trafficlight` (`TrafficLightId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `signallight`
--

LOCK TABLES `signallight` WRITE;
/*!40000 ALTER TABLE `signallight` DISABLE KEYS */;
INSERT INTO `signallight` VALUES (16,7,'Red','1'),(17,3,'Yellow','1'),(18,7,'Green','1'),(19,6,'Red','2'),(20,4,'Yellow','2'),(21,6,'Green','2'),(22,5,'Red','3'),(23,5,'Yellow','3'),(24,5,'Green','3'),(25,8,'Red','4'),(26,2,'Yellow','4'),(27,8,'Green','4'),(28,9,'Red','5'),(29,1,'Yellow','5'),(30,9,'Green','5'),(31,6,'Red','6'),(32,6,'Yellow','6'),(33,6,'Green','6'),(34,7,'Red','7'),(35,7,'Yellow','7'),(36,7,'Green','7');
/*!40000 ALTER TABLE `signallight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trafficlight`
--

DROP TABLE IF EXISTS `trafficlight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trafficlight` (
  `TrafficLightId` varchar(255) NOT NULL,
  `Status` varchar(255) DEFAULT 'Green',
  PRIMARY KEY (`TrafficLightId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trafficlight`
--

LOCK TABLES `trafficlight` WRITE;
/*!40000 ALTER TABLE `trafficlight` DISABLE KEYS */;
INSERT INTO `trafficlight` VALUES ('1','Green'),('2','Green'),('3','Green'),('4','Green'),('5','Green'),('6','Green'),('7','Green');
/*!40000 ALTER TABLE `trafficlight` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-24 14:39:44
