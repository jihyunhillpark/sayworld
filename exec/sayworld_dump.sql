-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	5.7.34-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `black_list`
--
drop database if exists `ssafy_web_db`;
create database ssafy_web_db;
use ssafy_web_db;

DROP TABLE IF EXISTS `black_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `black_list` (
  `user_id` bigint(20) NOT NULL,
  `block_user` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`block_user`),
  KEY `FKqww1gtupef489ccab032ey6t5` (`block_user`),
  CONSTRAINT `FK5bflr4wamndlkhhcrp07mopyx` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKqww1gtupef489ccab032ey6t5` FOREIGN KEY (`block_user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `black_list`
--

LOCK TABLES `black_list` WRITE;
/*!40000 ALTER TABLE `black_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `black_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `blog_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_category` int(11) DEFAULT NULL,
  `blog_content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `blog_created_at` datetime(6) DEFAULT NULL,
  `blog_lock` int(11) DEFAULT NULL,
  `blog_title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`blog_id`),
  KEY `FKpxk2jtysqn41oop7lvxcp6dqq` (`user_id`),
  CONSTRAINT `FKpxk2jtysqn41oop7lvxcp6dqq` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_category` (
  `book_category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_category` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`book_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_friend`
--

DROP TABLE IF EXISTS `chat_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_friend` (
  `chat_friend_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chat_friend_room_id` bigint(20) NOT NULL,
  `chat_friend_message` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `chat_friend_created_at` date DEFAULT NULL,
  `chat_friend_user_id` bigint(20) NOT NULL,
  `chat_friend_file` text CHARACTER SET latin1,
  PRIMARY KEY (`chat_friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_friend`
--

LOCK TABLES `chat_friend` WRITE;
/*!40000 ALTER TABLE `chat_friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_friend_join`
--

DROP TABLE IF EXISTS `chat_friend_join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_friend_join` (
  `chat_friend_join_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `chat_friend_room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`chat_friend_join_id`),
  KEY `user_id` (`user_id`),
  KEY `chat_friend_room_id` (`chat_friend_room_id`),
  CONSTRAINT `chat_friend_join_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `chat_friend_join_ibfk_2` FOREIGN KEY (`chat_friend_room_id`) REFERENCES `chat_friend_room` (`chat_friend_room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_friend_join`
--

LOCK TABLES `chat_friend_join` WRITE;
/*!40000 ALTER TABLE `chat_friend_join` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_friend_join` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_friend_message`
--

DROP TABLE IF EXISTS `chat_friend_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_friend_message` (
  `chat_friend_message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chat_friend_room_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `chat_friend_message` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `chat_friend_created_at` date DEFAULT NULL,
  `chat_friend_img` text CHARACTER SET latin1,
  PRIMARY KEY (`chat_friend_message_id`),
  KEY `user_id` (`user_id`),
  KEY `chat_friend_room_id` (`chat_friend_room_id`),
  CONSTRAINT `chat_friend_message_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `chat_friend_message_ibfk_2` FOREIGN KEY (`chat_friend_room_id`) REFERENCES `chat_friend_room` (`chat_friend_room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_friend_message`
--

LOCK TABLES `chat_friend_message` WRITE;
/*!40000 ALTER TABLE `chat_friend_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_friend_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_friend_room`
--

DROP TABLE IF EXISTS `chat_friend_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_friend_room` (
  `chat_friend_room_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`chat_friend_room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_friend_room`
--

LOCK TABLES `chat_friend_room` WRITE;
/*!40000 ALTER TABLE `chat_friend_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_friend_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `culture_power`
--

DROP TABLE IF EXISTS `culture_power`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `culture_power` (
  `culture_power_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `culture_category` bigint(20) DEFAULT NULL,
  `culture_title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`culture_power_id`),
  KEY `FKi5g90xwky65pnawv64v47an0` (`user_id`),
  CONSTRAINT `FKi5g90xwky65pnawv64v47an0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `culture_power`
--

LOCK TABLES `culture_power` WRITE;
/*!40000 ALTER TABLE `culture_power` DISABLE KEYS */;
/*!40000 ALTER TABLE `culture_power` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_list`
--

DROP TABLE IF EXISTS `friend_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend_list` (
  `user_id` bigint(20) NOT NULL,
  `friend_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`friend_id`),
  KEY `FKbdww32705675skr0m8km94jj4` (`friend_id`),
  CONSTRAINT `FK838n1nm0a7wvt1a9pxs0ft8kk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKbdww32705675skr0m8km94jj4` FOREIGN KEY (`friend_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_list`
--

LOCK TABLES `friend_list` WRITE;
/*!40000 ALTER TABLE `friend_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyword`
--

DROP TABLE IF EXISTS `keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `keyword` (
  `keyword_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `keyword_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`keyword_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
/*!40000 ALTER TABLE `keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_category`
--

DROP TABLE IF EXISTS `movie_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_category` (
  `movie_category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `movie_category` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`movie_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_category`
--

LOCK TABLES `movie_category` WRITE;
/*!40000 ALTER TABLE `movie_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_host_id` bigint(20) NOT NULL,
  `room_title` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `room_invite_code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `movie_category_id` bigint(20) NOT NULL,
  `book_category_id` bigint(20) NOT NULL,
  `room_password` varchar(25) CHARACTER SET utf8mb4 DEFAULT NULL,
  `room_img` text CHARACTER SET utf8mb4,
  `session_id` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`room_id`),
  KEY `FK4lx4bvvu9ja4rcl11rnbqv37k` (`room_host_id`),
  CONSTRAINT `FK4lx4bvvu9ja4rcl11rnbqv37k` FOREIGN KEY (`room_host_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_keyword`
--

DROP TABLE IF EXISTS `room_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_keyword` (
  `room_id` bigint(20) NOT NULL,
  `keyword_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_id`,`keyword_id`),
  KEY `FKclq9twrry2ak9kb7cypmllitd` (`keyword_id`),
  CONSTRAINT `FK2vqa92r7px5kur3nr8twyw64j` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `FKclq9twrry2ak9kb7cypmllitd` FOREIGN KEY (`keyword_id`) REFERENCES `keyword` (`keyword_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_keyword`
--

LOCK TABLES `room_keyword` WRITE;
/*!40000 ALTER TABLE `room_keyword` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `nickname` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `email` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `gender` char(2) CHARACTER SET latin1 DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `default_page` int(11) DEFAULT NULL,
  `tier_book` int(11) DEFAULT NULL,
  `tier_movie` int(11) DEFAULT NULL,
  `profile_img` text CHARACTER SET latin1,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'$2a$10$g8h3bMlGwzBYshf523Cz/etErMCSorKLive6QZV5uIAoF01K5uwKi','wldwlddl','helloculsu@naver.com','M',18,0,0,0,'/url'),(2,'$2a$10$/e0TpE5ubZ1uIV/soFJ7QeAGMxOs/bVcoU.IXQXMwO8amV5RXsjzu','jihyun','ssafy@ssafy.com','F',20,0,0,0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_book`
--

DROP TABLE IF EXISTS `user_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_book` (
  `user_id` bigint(20) NOT NULL,
  `book_category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`book_category_id`),
  KEY `FKhku9irp30x0e96bjn9qc5eeog` (`book_category_id`),
  CONSTRAINT `FKbc0bwdnndnxhct38sinbem0n0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKhku9irp30x0e96bjn9qc5eeog` FOREIGN KEY (`book_category_id`) REFERENCES `book_category` (`book_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_book`
--

LOCK TABLES `user_book` WRITE;
/*!40000 ALTER TABLE `user_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_movie`
--

DROP TABLE IF EXISTS `user_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_movie` (
  `user_id` bigint(20) NOT NULL,
  `movie_category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`movie_category_id`),
  KEY `FKgofgmrfhc0wt0ltitfpmldmpk` (`movie_category_id`),
  CONSTRAINT `FKgofgmrfhc0wt0ltitfpmldmpk` FOREIGN KEY (`movie_category_id`) REFERENCES `movie_category` (`movie_category_id`),
  CONSTRAINT `FKkslwq0qn0h1waemnmedse5ncb` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_movie`
--

LOCK TABLES `user_movie` WRITE;
/*!40000 ALTER TABLE `user_movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_room`
--

DROP TABLE IF EXISTS `user_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_room` (
  `user_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`room_id`),
  KEY `FK_room_TO_user_room_2` (`room_id`),
  CONSTRAINT `FK_room_TO_user_room_2` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `FK_user_TO_user_room_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_room`
--

LOCK TABLES `user_room` WRITE;
/*!40000 ALTER TABLE `user_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-19 18:13:26
