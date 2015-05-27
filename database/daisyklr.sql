CREATE DATABASE  IF NOT EXISTS `DaisyKLR` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `DaisyKLR`;
-- MySQL dump 10.13  Distrib 5.6.22, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: DaisyKLR
-- ------------------------------------------------------
-- Server version	5.6.24

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
-- Table structure for table `Books`
--

DROP TABLE IF EXISTS `Books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Books` (
  `isbn` bigint(13) NOT NULL,
  `title` text NOT NULL,
  `authors` text NOT NULL,
  `sent` tinyint(4) NOT NULL,
  PRIMARY KEY (`isbn`),
  UNIQUE KEY `isbn_UNIQUE` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Books`
--

LOCK TABLES `Books` WRITE;
/*!40000 ALTER TABLE `Books` DISABLE KEYS */;
/*!40000 ALTER TABLE `Books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Books_KnowledegeAreas`
--

DROP TABLE IF EXISTS `Books_KnowledegeAreas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Books_KnowledegeAreas` (
  `book_isbn` bigint(20) NOT NULL,
  `knowledgearea_id` bigint(20) NOT NULL,
  PRIMARY KEY (`book_isbn`,`knowledgearea_id`),
  UNIQUE KEY `book_isbn` (`book_isbn`,`knowledgearea_id`),
  KEY `knowledge_fk_idx` (`knowledgearea_id`),
  CONSTRAINT `book_fk` FOREIGN KEY (`book_isbn`) REFERENCES `Books` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `knowledge_fk` FOREIGN KEY (`knowledgearea_id`) REFERENCES `KnowledgeAreas` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Books_KnowledegeAreas`
--

LOCK TABLES `Books_KnowledegeAreas` WRITE;
/*!40000 ALTER TABLE `Books_KnowledegeAreas` DISABLE KEYS */;
/*!40000 ALTER TABLE `Books_KnowledegeAreas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Descriptions`
--

DROP TABLE IF EXISTS `Descriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Descriptions` (
  `id` bigint(20) NOT NULL,
  `text` text NOT NULL,
  `approved` tinyint(4) NOT NULL DEFAULT '0',
  `discarded` tinyint(4) DEFAULT '0',
  `user_nusp` bigint(20) NOT NULL,
  `image_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `description_user_idx` (`user_nusp`),
  KEY `image_id_fk_idx` (`image_id`),
  CONSTRAINT `description_user` FOREIGN KEY (`user_nusp`) REFERENCES `Users` (`nusp`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `image_id_fk` FOREIGN KEY (`image_id`) REFERENCES `Images` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Descriptions`
--

LOCK TABLES `Descriptions` WRITE;
/*!40000 ALTER TABLE `Descriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `Descriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Images`
--

DROP TABLE IF EXISTS `Images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Images` (
  `id` bigint(20) NOT NULL,
  `location` text,
  `page` int(11) NOT NULL,
  `book_isbn` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `book_isbn_fk_idx` (`book_isbn`),
  CONSTRAINT `book_isbn_fk` FOREIGN KEY (`book_isbn`) REFERENCES `Books` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Images`
--

LOCK TABLES `Images` WRITE;
/*!40000 ALTER TABLE `Images` DISABLE KEYS */;
/*!40000 ALTER TABLE `Images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KnowledgeAreas`
--

DROP TABLE IF EXISTS `KnowledgeAreas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KnowledgeAreas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KnowledgeAreas`
--

LOCK TABLES `KnowledgeAreas` WRITE;
/*!40000 ALTER TABLE `KnowledgeAreas` DISABLE KEYS */;
/*!40000 ALTER TABLE `KnowledgeAreas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ratings`
--

DROP TABLE IF EXISTS `Ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ratings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `positive` tinyint(4) NOT NULL DEFAULT '0',
  `rating_user_nusp` bigint(20) NOT NULL,
  `description_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `ratings_user_idx` (`rating_user_nusp`),
  KEY `description_id_fk_idx` (`description_id`),
  CONSTRAINT `description_id_fk` FOREIGN KEY (`description_id`) REFERENCES `Descriptions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ratings_user` FOREIGN KEY (`rating_user_nusp`) REFERENCES `Users` (`nusp`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ratings`
--

LOCK TABLES `Ratings` WRITE;
/*!40000 ALTER TABLE `Ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `Ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `nusp` bigint(7) NOT NULL,
  `name` text NOT NULL,
  `password` text NOT NULL,
  `usertype` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`nusp`),
  UNIQUE KEY `nusp_UNIQUE` (`nusp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (8041992,'Ricardo Boccoli Gallego','senha',1);
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users_KnowledegeAreas`
--

DROP TABLE IF EXISTS `Users_KnowledegeAreas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users_KnowledegeAreas` (
  `user_nusp` bigint(20) NOT NULL,
  `knowledgearea_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_nusp`,`knowledgearea_id`),
  UNIQUE KEY `user_id` (`user_nusp`,`knowledgearea_id`),
  KEY `knowledge_fk_idx` (`knowledgearea_id`),
  CONSTRAINT `user_fk` FOREIGN KEY (`user_nusp`) REFERENCES `Users` (`nusp`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users_KnowledegeAreas`
--

LOCK TABLES `Users_KnowledegeAreas` WRITE;
/*!40000 ALTER TABLE `Users_KnowledegeAreas` DISABLE KEYS */;
/*!40000 ALTER TABLE `Users_KnowledegeAreas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-27  8:08:03
