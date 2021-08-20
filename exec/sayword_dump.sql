-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	8.0.22

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

DROP TABLE IF EXISTS `black_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `black_list` (
  `user_id` bigint NOT NULL,
  `block_user` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`block_user`),
  KEY `FKqww1gtupef489ccab032ey6t5` (`block_user`),
  CONSTRAINT `FK5bflr4wamndlkhhcrp07mopyx` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKqww1gtupef489ccab032ey6t5` FOREIGN KEY (`block_user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `blog_id` bigint NOT NULL AUTO_INCREMENT,
  `blog_category` int DEFAULT NULL,
  `blog_content` varchar(255) DEFAULT NULL,
  `blog_created_at` datetime(6) DEFAULT NULL,
  `blog_lock` int DEFAULT NULL,
  `blog_title` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`blog_id`),
  KEY `FKpxk2jtysqn41oop7lvxcp6dqq` (`user_id`),
  CONSTRAINT `FKpxk2jtysqn41oop7lvxcp6dqq` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,0,'내용을 작성하시겠습니까 ~~~네!!\n','2021-08-19 23:42:15.836000',0,'안뇨옹',NULL);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_category` (
  `book_category_id` bigint NOT NULL AUTO_INCREMENT,
  `book_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`book_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
INSERT INTO `book_category` VALUES (1,'시'),(2,'철학'),(3,'로맨스 소설'),(4,'판타지 소설'),(5,'추리 소설'),(6,'고전문학'),(7,'에세이'),(8,'교육'),(9,'자기계발'),(10,'역사'),(11,'예술'),(12,'경제/경영');
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_friend`
--

DROP TABLE IF EXISTS `chat_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_friend` (
  `chat_friend_id` bigint NOT NULL AUTO_INCREMENT,
  `chat_friend_room_id` bigint NOT NULL,
  `chat_friend_message` varchar(100) DEFAULT NULL,
  `chat_friend_created_at` date DEFAULT NULL,
  `chat_friend_user_id` bigint NOT NULL,
  `chat_friend_file` text,
  PRIMARY KEY (`chat_friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `chat_friend_join_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `chat_friend_room_id` bigint NOT NULL,
  PRIMARY KEY (`chat_friend_join_id`),
  KEY `user_id` (`user_id`),
  KEY `chat_friend_room_id` (`chat_friend_room_id`),
  CONSTRAINT `chat_friend_join_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `chat_friend_join_ibfk_2` FOREIGN KEY (`chat_friend_room_id`) REFERENCES `chat_friend_room` (`chat_friend_room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `chat_friend_message_id` bigint NOT NULL AUTO_INCREMENT,
  `chat_friend_room_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `chat_friend_message` varchar(100) DEFAULT NULL,
  `chat_friend_created_at` date DEFAULT NULL,
  `chat_friend_img` text,
  PRIMARY KEY (`chat_friend_message_id`),
  KEY `user_id` (`user_id`),
  KEY `chat_friend_room_id` (`chat_friend_room_id`),
  CONSTRAINT `chat_friend_message_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `chat_friend_message_ibfk_2` FOREIGN KEY (`chat_friend_room_id`) REFERENCES `chat_friend_room` (`chat_friend_room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `chat_friend_room_id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`chat_friend_room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `culture_power_id` bigint NOT NULL AUTO_INCREMENT,
  `culture_category` bigint DEFAULT NULL,
  `culture_title` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`culture_power_id`),
  KEY `FKi5g90xwky65pnawv64v47an0` (`user_id`),
  CONSTRAINT `FKi5g90xwky65pnawv64v47an0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `culture_power`
--

LOCK TABLES `culture_power` WRITE;
/*!40000 ALTER TABLE `culture_power` DISABLE KEYS */;
INSERT INTO `culture_power` VALUES (3,1,'기초 실용음악 화성학 (입문자도 입시생도 독학하기 쉬운 음악이론, 개정판)',5);
/*!40000 ALTER TABLE `culture_power` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_list`
--

DROP TABLE IF EXISTS `friend_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend_list` (
  `user_id` bigint NOT NULL,
  `friend_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`friend_id`),
  KEY `FKbdww32705675skr0m8km94jj4` (`friend_id`),
  CONSTRAINT `FK838n1nm0a7wvt1a9pxs0ft8kk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKbdww32705675skr0m8km94jj4` FOREIGN KEY (`friend_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `keyword_id` bigint NOT NULL AUTO_INCREMENT,
  `keyword_name` varchar(255) NOT NULL,
  PRIMARY KEY (`keyword_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
INSERT INTO `keyword` VALUES (1,'호우호우'),(2,'키워드를 입력하세요');
/*!40000 ALTER TABLE `keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_category`
--

DROP TABLE IF EXISTS `movie_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_category` (
  `movie_category_id` bigint NOT NULL AUTO_INCREMENT,
  `movie_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movie_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_category`
--

LOCK TABLES `movie_category` WRITE;
/*!40000 ALTER TABLE `movie_category` DISABLE KEYS */;
INSERT INTO `movie_category` VALUES (1,'액션'),(2,'코미디'),(3,'모험'),(4,'애니메이션'),(5,'다큐멘터리'),(6,'판타지'),(7,'범죄/스릴러'),(8,'전쟁'),(9,'로맨스'),(10,'음악'),(11,'공포'),(12,'역사');
/*!40000 ALTER TABLE `movie_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_id` bigint NOT NULL AUTO_INCREMENT,
  `room_host_id` bigint NOT NULL,
  `room_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `room_invite_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `movie_category_id` bigint NOT NULL,
  `book_category_id` bigint NOT NULL,
  `room_password` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `room_img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `session_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`room_id`),
  KEY `FK4lx4bvvu9ja4rcl11rnbqv37k` (`room_host_id`),
  CONSTRAINT `FK4lx4bvvu9ja4rcl11rnbqv37k` FOREIGN KEY (`room_host_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (19,6,'리투아니아','request_invite_url',0,3,'','https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/314f119d-e18a-4a79-bb0d-d35e4863a732madong.jpeg','리투아니아리투아니아');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_keyword`
--

DROP TABLE IF EXISTS `room_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_keyword` (
  `room_id` bigint NOT NULL,
  `keyword_id` bigint NOT NULL,
  PRIMARY KEY (`room_id`,`keyword_id`),
  KEY `FKclq9twrry2ak9kb7cypmllitd` (`keyword_id`),
  CONSTRAINT `FK2vqa92r7px5kur3nr8twyw64j` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `FKclq9twrry2ak9kb7cypmllitd` FOREIGN KEY (`keyword_id`) REFERENCES `keyword` (`keyword_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_keyword`
--

LOCK TABLES `room_keyword` WRITE;
/*!40000 ALTER TABLE `room_keyword` DISABLE KEYS */;
INSERT INTO `room_keyword` VALUES (19,2);
/*!40000 ALTER TABLE `room_keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(100) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` char(2) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `default_page` int DEFAULT NULL,
  `tier_book` int DEFAULT NULL,
  `tier_movie` int DEFAULT NULL,
  `profile_img` text,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'$2a$10$fVDYct.MMo6AUO8gplIFjORbVSIsJdy7oetbfgb/p1WG6QmN5Hoqq','징징이','ssafy@ssafy.com','F',20,0,0,0,NULL),(6,'$2a$10$b2IVLzOh6cw5lsEvt42qJ.DjZEJ9/l1AlKjO.cP3PPZ/qelK/tAz.','홍홍이','abc123@naver.com','F',40,1,0,0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_book`
--

DROP TABLE IF EXISTS `user_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_book` (
  `user_id` bigint NOT NULL,
  `book_category_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`book_category_id`),
  KEY `FKhku9irp30x0e96bjn9qc5eeog` (`book_category_id`),
  CONSTRAINT `FKbc0bwdnndnxhct38sinbem0n0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKhku9irp30x0e96bjn9qc5eeog` FOREIGN KEY (`book_category_id`) REFERENCES `book_category` (`book_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_book`
--

LOCK TABLES `user_book` WRITE;
/*!40000 ALTER TABLE `user_book` DISABLE KEYS */;
INSERT INTO `user_book` VALUES (6,2),(6,7),(6,12);
/*!40000 ALTER TABLE `user_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_movie`
--

DROP TABLE IF EXISTS `user_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_movie` (
  `user_id` bigint NOT NULL,
  `movie_category_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`movie_category_id`),
  KEY `FKgofgmrfhc0wt0ltitfpmldmpk` (`movie_category_id`),
  CONSTRAINT `FKgofgmrfhc0wt0ltitfpmldmpk` FOREIGN KEY (`movie_category_id`) REFERENCES `movie_category` (`movie_category_id`),
  CONSTRAINT `FKkslwq0qn0h1waemnmedse5ncb` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_movie`
--

LOCK TABLES `user_movie` WRITE;
/*!40000 ALTER TABLE `user_movie` DISABLE KEYS */;
INSERT INTO `user_movie` VALUES (6,3),(6,6),(6,11);
/*!40000 ALTER TABLE `user_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_room`
--

DROP TABLE IF EXISTS `user_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_room` (
  `user_id` bigint NOT NULL,
  `room_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`room_id`),
  KEY `FK_room_TO_user_room_2` (`room_id`),
  CONSTRAINT `FK_room_TO_user_room_2` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `FK_user_TO_user_room_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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

-- Dump completed on 2021-08-19 23:59:56
