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
INSERT INTO `Books` VALUES (1,'Revolução dos Bichos','George Orwell',0),(73523321,'Database System Concepts',' Abraham Silberschatz',0),(8500330325,'COLEÇAO HISTORIA ILUSTRADA - GRECIA ANTIGA','Paul Cartledge',0),(8535216960,'Principios de Analise e Projeto de Sistemas Com Uml','Eduardo Bezerra ',0),(8576052377,'Sistemas Operacionais Modernos','Andrew S. Tanenbaum',0),(9780123838728,'Computer Architecture: A Quantitative Approach','David A. Patterson',0);
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
INSERT INTO `Books_KnowledegeAreas` VALUES (1,3),(8500330325,3),(9780123838728,4),(9780123838728,5),(73523321,6),(8535216960,6),(8576052377,6),(9780123838728,6),(1,7),(8500330325,7),(8576052377,8),(9780123838728,8),(1,12);
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
  CONSTRAINT `image_fk_id` FOREIGN KEY (`image_id`) REFERENCES `Images` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `location` text,
  `page` int(11) NOT NULL,
  `book_isbn` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `book_isbn_fk_idx` (`book_isbn`),
  CONSTRAINT `book_isbn_fk` FOREIGN KEY (`book_isbn`) REFERENCES `Books` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Images`
--

LOCK TABLES `Images` WRITE;
/*!40000 ALTER TABLE `Images` DISABLE KEYS */;
INSERT INTO `Images` VALUES (19,'img19.jpg',0,1),(20,'img2.jpg',10,1),(21,'img3.jpg',48,1),(22,'img4.jpg',68,1),(23,'img5.jpg',90,1),(24,'principio-de-analise-e-projetos-de-sistemas-com-uml-bezerra-eduardo-1154-1562584-G.jpg',0,8535216960),(25,'conceitual_exemplo_banco.gif',33,8535216960),(26,'image002.gif',90,8535216960),(27,'CO4.jpg',0,9780123838728),(28,'240a_pipeline.png',5,9780123838728),(29,'computer-architecture-a-quantitative-approach-cap4-section-8-7-728.jpg',20,9780123838728),(30,'Part6-CPU-Speed-improvements.png',80,9780123838728),(31,'download.jpeg',0,73523321),(32,'ds_conc3.gif',3,73523321),(33,'graphics1.png',10,73523321),(34,'image001.jpg',40,73523321),(35,'image002.gif',50,73523321),(36,'op1.jpg',0,8576052377),(37,'op2.png',10,8576052377),(38,'op3.gif',328,8576052377),(39,'hist1.jpg',0,8500330325),(40,'hist2.jpg',4,8500330325),(41,'hist3.jpg',8,8500330325),(42,'hist4.jpg',18,8500330325),(43,'hist5.png',36,8500330325);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KnowledgeAreas`
--

LOCK TABLES `KnowledgeAreas` WRITE;
/*!40000 ALTER TABLE `KnowledgeAreas` DISABLE KEYS */;
INSERT INTO `KnowledgeAreas` VALUES (1,'Biologia'),(2,'Geologia'),(3,'História'),(4,'Eletrônica'),(5,'Sistemas Digitais'),(6,'Programação'),(7,'Geografia'),(8,'Elétrica'),(9,'Mecânica'),(10,'Física'),(11,'Química'),(12,'Ficção');
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
INSERT INTO `Users` VALUES (3,'wilson','wilson',2),(1000,'historiador','historiador',2),(2000,'historiando','historiando',1),(8000,'luiz','luiz',1),(9000,'kevin','kevin',1),(8041992,'Ricardo','senha',1);
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
INSERT INTO `Users_KnowledegeAreas` VALUES (1000,3),(2000,3),(3,4),(8000,4),(9000,4),(8041992,4),(3,5),(8000,5),(9000,5),(8041992,5),(3,6),(8000,6),(9000,6),(8041992,6),(1000,7),(2000,7),(3,8),(8000,8),(9000,8),(8041992,8),(3,12),(1000,12),(2000,12),(8000,12),(9000,12),(8041992,12);
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

-- Dump completed on 2015-06-09 17:53:34
