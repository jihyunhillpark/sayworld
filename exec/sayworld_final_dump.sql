CREATE DATABASE  IF NOT EXISTS `ssafy_web_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ssafy_web_db`;
-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 13.125.94.36    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	5.7.35

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
  `user_id` bigint(20) NOT NULL,
  `block_user` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`block_user`),
  KEY `FKqww1gtupef489ccab032ey6t5` (`block_user`),
  CONSTRAINT `FK5bflr4wamndlkhhcrp07mopyx` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKqww1gtupef489ccab032ey6t5` FOREIGN KEY (`block_user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
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
  `blog_content` varchar(255) DEFAULT NULL,
  `blog_created_at` datetime(6) DEFAULT NULL,
  `blog_lock` int(11) DEFAULT NULL,
  `blog_title` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`blog_id`),
  KEY `FKpxk2jtysqn41oop7lvxcp6dqq` (`user_id`),
  CONSTRAINT `FKpxk2jtysqn41oop7lvxcp6dqq` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,1,'7년의 밤 정말 재밌게 읽었다. 영화는 완전 엉망진창 못만들었지만 책은 정말 굿굿\n','2021-08-20 10:29:40.208000',0,'독서일기 1',1),(6,0,'','2021-08-20 14:41:20.195000',0,'모가디슈 영화를 보고나서..',15),(7,1,'20대의 성공 습관 책을 읽어보니 역시 자신감이 중요하군!\n','2021-08-20 13:52:10.371000',0,'성공습관 7가지->별거없네!',15),(8,1,'','2021-08-20 14:41:29.670000',0,'백종원 3대천왕 요리 책',15),(9,0,'','2021-08-20 15:07:24.863000',0,'해리포터',15),(10,0,'젊게살자~\n','2021-08-20 16:58:05.833000',0,'지츄야',23),(11,1,'조용종용\n','2021-08-20 16:58:20.471000',0,'조용조용',23);
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
  `book_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`book_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
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
-- Table structure for table `culture_power`
--

DROP TABLE IF EXISTS `culture_power`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `culture_power` (
  `culture_power_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `culture_category` bigint(20) DEFAULT NULL,
  `culture_title` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`culture_power_id`),
  KEY `FKi5g90xwky65pnawv64v47an0` (`user_id`),
  CONSTRAINT `FKi5g90xwky65pnawv64v47an0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `culture_power`
--

LOCK TABLES `culture_power` WRITE;
/*!40000 ALTER TABLE `culture_power` DISABLE KEYS */;
INSERT INTO `culture_power` VALUES (12,1,'7년의 밤',1),(23,1,'고구마구마 (반달 그림책)',10),(24,0,'극장판 반지의 비밀일기',10),(25,0,'신데렐라:마법 반지의 비밀',10),(42,1,'해커스공기업 단기 합격 NCS 통합 기본서 직업기초능력평가 + 직무수행능력평가 (상·하편 + 해설집) (2021 하반기,전3권,[모듈형+PSAT형(피셋형) | 실전모의고사 4회분(온라인 1회분 포함) | 영역별 출제예상문제 수록 | 코레일,한국전력공사,한수원 외 공사 공단 및 금융권 필기 전형 대비)',15),(43,1,'단기합격 해커스공기업 NCS 통합 기본서 직업기초능력평가 + 직무수행능력평가 (모듈형 + PSAT형 + 실전모의고사 3회분, 2021)',15),(44,1,'2021 신헌의 PSAT for NCS 수리·자료해석 핵심 기본서 (수리·자원관리·문제해결능력 이론+문제풀이)',15),(45,0,'크루엘라',15),(46,0,'인질',15),(47,1,'반지의 제왕 1~3 + 호빗 세트 - 전4권 (The Lord of the Rings + The Hobbit)',15),(48,1,'나나의 반지 (내 아이와 첫 바다 여행을 떠나요!)',15),(49,1,'해리 포터와 마법사의 돌 1 (해리포터 20주년)',23),(50,1,'해리 포터와 마법사의 돌 2 (해리포터 20주년)',23),(51,1,'해리 포터와 마법사의 돌 1 (해리포터 20주년)',27),(52,1,'해리 포터와 마법사의 돌 2 (해리포터 20주년)',27);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
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
  `keyword_name` varchar(255) NOT NULL,
  PRIMARY KEY (`keyword_id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
INSERT INTO `keyword` VALUES (1,'범죄도시'),(2,'마블리'),(3,'키워드를 입력하세요'),(4,'골룸'),(5,'무스비'),(6,'벚꽃필무렵'),(7,'손원평'),(8,'대만'),(9,'러브'),(10,'사랑'),(11,'세계로독서모임'),(12,'나미야잡화점'),(13,'첫 온라인 모임'),(14,'해리포터'),(15,'마법사'),(16,'삼성'),(17,'GSAT'),(18,'긴장감'),(19,'좀 슬퍼잉'),(20,'한강'),(21,'꿈백화점'),(22,'드루와드루와'),(23,'민식형님'),(24,'ssafy'),(25,'스릴러'),(26,'영화화'),(27,'인생작'),(28,'교훈'),(29,'눈물바다'),(30,'가쥬앙'),(31,'독서모임'),(32,'우리동네'),(33,'코코'),(34,'따뜻함'),(35,'가족'),(36,'로맨스'),(37,'대만띵작'),(38,'어벤져스'),(39,'3천만만큼사랑해'),(40,'매주_토요일'),(41,'부자가될래요'),(42,'독서'),(43,'독서법'),(44,'걸크러쉬'),(45,'툼레이더'),(46,'솔트'),(47,'한발 더 남았다'),(48,'너희 둘은 죽는다'),(49,'충치몇개냐'),(50,'시'),(51,'수요일'),(52,'블랙팬서'),(53,'소퓌아 아줌마'),(54,'극한직업'),(55,'나야말로'),(56,'완벽한타인'),(57,'당신은 안전하십니까'),(58,'다음작추천'),(59,'ㅇㅇ'),(60,'죽음의성물'),(61,'1권봤어요.'),(62,'화이팅'),(63,'올림픽보고'),(64,'다시봄'),(65,'달러구트'),(66,'별헤는밤'),(67,'감상'),(68,'하정우'),(69,'김먹방'),(70,'겨울왕국'),(71,'똑똑똑'),(72,'엘사?'),(73,'에세이'),(74,'작성'),(75,'1편이존잼'),(76,'동키귀욤'),(77,'서양철학'),(78,'니체'),(79,'미술작품'),(80,'해설'),(81,'좋은책'),(82,'추천'),(83,'엠마스톤'),(84,'뮤지컬영화'),(85,'삼성 지사트'),(86,'자기계발'),(87,'면접왕'),(88,'성공'),(89,'대만영화'),(90,'진짜개띵작'),(91,'첫사랑'),(92,'지츄야'),(93,'빨리와'),(94,'키워드를'),(95,'입력하세요'),(96,'Nginx test');
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
  `movie_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movie_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
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
  `room_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_category_id` bigint(20) DEFAULT '0',
  `room_host_id` bigint(20) DEFAULT NULL,
  `movie_category_id` bigint(20) DEFAULT '0',
  `room_img` varchar(255) DEFAULT NULL,
  `room_invite_code` varchar(255) DEFAULT NULL,
  `room_password` varchar(255) DEFAULT NULL,
  `room_title` varchar(255) DEFAULT NULL,
  `session_id` varchar(255) NOT NULL,
  PRIMARY KEY (`room_id`),
  KEY `FK4lx4bvvu9ja4rcl11rnbqv37k` (`room_host_id`),
  CONSTRAINT `FK4lx4bvvu9ja4rcl11rnbqv37k` FOREIGN KEY (`room_host_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,0,2,7,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/9431a60d-0e18-436a-9d07-31d8fa59137emavely.jpg','request_invite_url','','범죄도시아이오니','범죄도시아이오니범죄도시아이오니'),(3,0,2,4,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/44321d04-762d-4fd6-ba87-a1eb815db1fbcherryblossom.jpg','request_invite_url','','너의이름은_나의이름은','너의이름은_나의이름은너의이름은_나의이름은'),(6,5,2,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/6a606014-5de2-44cd-b3c3-870c62fc3d35namiya.jfif','request_invite_url','','세계로_독서모임','세계로_독서모임세계로_독서모임'),(9,5,2,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/75b4d290-c081-4bde-a465-743b886ecf04witness.jfif','request_invite_url','','용의자_x의_헌신_읽으신_분만_오세요','용의자_x의_헌신_읽으신_분만_오세요용의자_x의_헌신_읽으신_분만_오세요'),(13,0,2,7,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/e3a64898-437d-4ad1-8865-784c4b0ad861sinsegae.jpg','request_invite_url','','신세계_이야기할_사람_다_드루와','신세계_이야기할_사람_다_드루와신세계_이야기할_사람_다_드루와'),(15,5,1,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/24578ee5-71f6-4fd7-a775-d0d36af1d3b2%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C.jpg','request_invite_url','','7년의_밤','7년의_밤7년의_밤'),(16,0,2,4,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/924859ef-f0a4-48e9-9cda-ee18a7fe7d37chihiro.jpg','request_invite_url','','미야자키하야호_작품_정주행','미야자키하야호_작품_정주행미야자키하야호_작품_정주행'),(18,6,1,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/b1e39971-dfb5-4928-84f2-852b6a129d2f%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C.jpg','request_invite_url','abcd123','우리동네_독서모임','우리동네_독서모임우리동네_독서모임'),(19,0,2,4,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/c2f088a9-612a-4489-875b-608d5a90aff3coco.jpg','request_invite_url','','코코_인생에_대해_이야기해봐요','코코_인생에_대해_이야기해봐요코코_인생에_대해_이야기해봐요'),(22,0,2,9,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/070432fd-636f-48b2-9142-1a9108abe9e7sosi.jfif','request_invite_url','','매주_토요일마다_대만_로맨스_정주행갑니다','매주_토요일마다_대만_로맨스_정주행갑니다매주_토요일마다_대만_로맨스_정주행갑니다'),(26,0,2,1,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/8cb3fca7-de21-4103-831f-846bb6c45543angel.jfif','request_invite_url','','안젤리나졸리_액션물_시리즈','안젤리나졸리_액션물_시리즈안젤리나졸리_액션물_시리즈'),(29,0,2,1,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/35dd4909-9617-4053-9bb3-e8d022de9adesophia.jpg','request_invite_url','','히어로_덕후들은_여기로','히어로_덕후들은_여기로히어로_덕후들은_여기로'),(30,0,2,2,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/0cc2a54f-f5d3-4232-83c6-74f31ab5b856jobs.jfif','request_invite_url','','직장인의_삶_웃음으로_승화','직장인의_삶_웃음으로_승화직장인의_삶_웃음으로_승화'),(31,0,2,2,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/2119ad33-d92a-441b-89d8-a3f820e9831dwantan.png','request_invite_url','','유해진_코미디물_시작합니다','유해진_코미디물_시작합니다유해진_코미디물_시작합니다'),(34,5,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/3a2a6bcc-82f4-4b9a-b488-c238ce61a8fbXL%20%281%29.jfif','request_invite_url','cookie','아몬드','아몬드아몬드'),(35,4,11,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/87775f55-015f-41d2-b844-c3c4aef17e9e20210819_162737.png','request_invite_url','0731','해리포터_완독-hint생일0000','해리포터_완독-hint생일0000해리포터_완독-hint생일0000'),(36,1,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/6ca23ba4-da80-43f4-b5a2-6b82d5bd2e24images%20%282%29.jfif','request_invite_url','','관악구_시낭독','관악구_시낭독관악구_시낭독'),(37,12,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/a0f04f44-a239-4ea8-8fbc-cda5aff9ccb6XL%20%284%29.jfif','request_invite_url','','더_보스','더_보스더_보스'),(38,9,11,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/8dc90b8f-927d-4bfc-9925-af03bd577e2820210820_123839.png','request_invite_url','','지대넓얕','지대넓얕지대넓얕'),(40,8,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/f8321204-9c14-414e-95d0-18b312f0b010XL%20%283%29.jfif','request_invite_url','ssafy','메타버스','메타버스메타버스'),(41,9,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/fb7d9e26-f84d-4443-b36f-9847a1cc4ebdx9791158741242.jpg','request_invite_url','','서재의_마법','서재의_마법서재의_마법'),(42,0,11,1,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/c2b05f1b-645e-45a5-89ef-2aa4c412311d20210820_124210.png','request_invite_url','','국가대표감덩','국가대표감덩국가대표감덩'),(43,10,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/322b5fa9-366f-4f81-89c4-045993252399XL%20%282%29.jfif','request_invite_url','','소년이_온다','소년이_온다소년이_온다'),(44,4,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/79115578-1879-41c2-b167-9366343335a9x9791165341909.jpg','request_invite_url','','꿈_백화점','꿈_백화점꿈_백화점'),(45,1,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/f9df6167-00e4-414c-8b5c-64720f3ea92c8908060197_3.jpg','request_invite_url','','윤동주_시','윤동주_시윤동주_시'),(46,0,11,7,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/f5deaed9-b81f-4623-923d-917ef3f208d820210820_124322.png','request_invite_url','','황해','황해황해'),(47,0,11,4,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/e6d2d325-eb79-4c27-9ee5-c6ccb387586f20210820_124513.png','request_invite_url','','눈사람만들래','눈사람만들래눈사람만들래'),(48,7,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/c3cc59d3-d7e9-4a4a-b4b3-102760cd1edf9991923B5FDC073D0F.jfif','request_invite_url','','에세이_작성','에세이_작성에세이_작성'),(49,0,11,4,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/0e255c91-1b93-45ed-9feb-1751708cf29720210820_124517.png','request_invite_url','','슈렉_추억이다','슈렉_추억이다슈렉_추억이다'),(50,2,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/dbc933f1-5cae-41e0-b30b-c8cd2231e651l9791196165543.jpg','request_invite_url','phil','니체의_생각','니체의_생각니체의_생각'),(51,3,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/b9e03d42-75fe-4a61-9bc7-1ffb7a3973f8213816778g.jpg','request_invite_url','','미비포유','미비포유미비포유'),(52,11,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/0f195371-b63b-4eec-8d76-f5285fbd450fXL%20%285%29.jfif','request_invite_url','art','방구석_미술관','방구석_미술관방구석_미술관'),(53,9,13,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/8a0eacc8-b463-46f6-b21f-a91798c02600%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C%20%281%29.jfif','request_invite_url','','자기계발서_추천','자기계발서_추천자기계발서_추천'),(54,0,11,10,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/b5c083db-6273-4273-8296-ead1fdd627eb20210820_130253.png','request_invite_url','','라라랜드','라라랜드라라랜드'),(55,0,13,6,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/ed3239c6-77de-4214-9205-50a59b5b913c%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C.jfif','request_invite_url','','해리포터_시리즈','해리포터_시리즈해리포터_시리즈'),(57,0,15,1,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/ac1ebe6e-f689-4bd9-9d7e-417ae5865cb0%EC%96%B4%EB%B2%A4%EC%A0%B8%EC%8A%A4.jfif','request_invite_url','','어벤져스_어셈블','어벤져스_어셈블어벤져스_어셈블'),(58,9,15,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/4acbe4ba-ee3a-4ab1-ad51-60c773d5a663%EC%A7%80%EC%82%AC%ED%8A%B8.jfif','request_invite_url','','삼성지사트','삼성지사트삼성지사트'),(60,9,15,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/d847dc5f-5816-491f-8966-85d7ee71caa0%EC%84%B1%EA%B3%B5%EC%8A%B5%EA%B4%80.png','request_invite_url','','성공습관_같이길러요','성공습관_같이길러요성공습관_같이길러요'),(61,0,15,9,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/dee4b1e8-edf5-4a23-97c5-ccdb1eb0ead9%EA%B7%B8%EC%8B%9C%EC%A0%88.jfif','request_invite_url','','그시절_우좋소','그시절_우좋소그시절_우좋소'),(65,7,27,0,'https://sayworld-img.s3.ap-northeast-2.amazonaws.com/static/ffb8cf6a-0915-492c-ad6e-3007db592673%E3%85%8B%E3%84%B7%E3%85%8B%E3%84%B7.png','request_invite_url','','안녀어엉','안녀어엉안녀어엉'),(66,0,27,7,'https://ifh.cc/g/FieTKm.png','request_invite_url','','안뇽히히히','안뇽히히히안뇽히히히');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_keyword`
--

LOCK TABLES `room_keyword` WRITE;
/*!40000 ALTER TABLE `room_keyword` DISABLE KEYS */;
INSERT INTO `room_keyword` VALUES (1,1),(1,2),(3,5),(3,6),(34,7),(6,11),(43,11),(6,12),(6,13),(35,14),(55,14),(55,15),(9,18),(9,19),(43,20),(13,22),(13,23),(40,24),(15,25),(15,26),(51,26),(16,27),(16,28),(16,29),(19,29),(18,31),(18,32),(19,33),(19,34),(19,35),(22,36),(51,36),(61,36),(22,37),(57,39),(22,40),(37,41),(41,42),(41,43),(26,44),(26,45),(26,46),(36,50),(36,51),(29,52),(29,53),(30,54),(30,55),(31,56),(31,57),(31,58),(35,60),(38,61),(40,62),(42,63),(42,64),(44,65),(45,66),(45,67),(46,68),(46,69),(47,70),(47,71),(47,72),(48,73),(48,74),(49,75),(49,76),(50,77),(50,78),(52,79),(52,80),(53,81),(53,82),(54,83),(54,84),(58,85),(58,86),(60,86),(60,88),(61,89),(61,90),(61,91),(65,94),(65,95),(66,96);
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
  `age` int(11) DEFAULT NULL,
  `default_page` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_img` varchar(255) DEFAULT NULL,
  `tier_book` int(11) DEFAULT NULL,
  `tier_movie` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,10,0,'zerohee@naver.com','F','영의정영희','$2a$10$bwXObeqslm28ce0Kokw4Y.VcEXN9I0irTe84UxDwSswE/Tep7oEZy',NULL,0,0),(2,20,0,'daum@daum.net','F','징징몬','$2a$10$5G1XwKVGCE0g1GqOKjn8qe.I4pQKRYio7eQrXgw8LIts2BMogCpnu',NULL,0,0),(10,30,1,'qkrgudals10@naver.com','M','미니미니','$2a$10$Bg6524VRyZYcAkjJTHAZ5.E5Nm1EFVY0DDZxWZBCyPnhqRkAr8LnS',NULL,0,0),(11,30,0,'ccc@naver.com','F','하이루','$2a$10$judjaoAgNb8O4363Tp/uhe0rpQ3HPHTH21qjQfG9HvgNDgKzU0Du.',NULL,0,0),(13,20,1,'python@naver.com','F','독서중독','$2a$10$V3M2CYxbubYi.qOeINLUd.p5qVYX5.VxMF5uOsy4GTO1sRqr1fmi6',NULL,0,0),(15,20,0,'test12345@naver.com','M','7팀테스트계정','$2a$10$X3E76tmk5GZ1sig6P8kYKua9joFRHesgkL4iafsOA6x2h4HVLTu26',NULL,0,0),(16,20,0,'kce0531@naver.com','F','chenni','$2a$10$eHpeyCEZ9m.GgB9RCfcyM.wJivuc1MdPrTmBof/3rGpyFkzV9hZyW',NULL,0,0),(17,20,1,'abcdefg@naver.com','M','책읽으러왔어요','$2a$10$oSTzeLL9VOXgTcjaI7i9q.UMhIHBbj9Tx0FrClzuR9zhC41gguoE.',NULL,0,0),(18,20,0,'abcdefg@naver.com','M','CGV RVIP','$2a$10$Lgvgt6WlP9K0bFTYLjhIrOUZ5bjIqgXRqQ6YsWo3RdSJZwD01gfYu',NULL,0,0),(19,0,0,'hyeonuk27@gmail.com','','아하아하','$2a$10$kkcylodVusWzwYhjg2zLf.uhFDrMx40v5.xd/FTAFp.tMzATf3E.i',NULL,0,0),(20,20,0,'abcdefg@naver.com','M','CGV RVIP','$2a$10$ZaK1EKrAJwvBwliFQ9BAuOgwEbjv4b0MVhdrznfl6JAzuU85KOkwu',NULL,0,0),(21,20,0,'abcdefg@naver.com','M','CGV RVIP','$2a$10$5U1wMHyHDhrPDzmo82Sxq.GTr6xs5rURUpSBd5BXy9N.1Msk73WQ6',NULL,0,0),(22,60,0,'ddd@naver.com','M','존잘러','$2a$10$TFsOUoGYY0WGNEVQzjiHoOXeyJMgIOQ9DfuckU.QbnWyVOUnNEGi.',NULL,0,0),(23,20,0,'helloji@naver.com','F','지츄야','$2a$10$ESw9diwQ0RlYu/iL43pGdOXylYQDVYrgRqi9cc0eulLtuhCawwd9u',NULL,0,0),(24,30,1,'sooya@naver.com','M','sooyaa','$2a$10$YK5nZgtvJEVliqqeLKkVu.jD0pLYaXLV22jsXQ/6eA3ivv60LaQ4C',NULL,0,0),(25,40,0,'jihyunhillpark@naver.com','F','jihyun','$2a$10$l3H8OHdUab3C0s6GKCo6UeTlSZ.TKLt2UvMav2JVpt/en3hD1GAwi',NULL,0,0),(26,10,0,'test@gmail.com','F','교태부리지마','$2a$10$srNP.lH7fermNAv3Bpc6KuYYSn0btOurd877TJCpJmwwrwhKXjCka',NULL,0,0),(27,30,0,'hoohoo@naver.com','F','지지지지','$2a$10$WlvgQovExUWk43q78NjSY.LKT/EIHjPhlPIRjo2OJPeyKHn5Vaesq',NULL,0,0),(28,50,0,'jihyunee@gmail.com','F','종조이','$2a$10$ehkogRAOTVFcNXOOFeYg1eCN6MZQTvywmOrXESOheaRlcuA.Bb0F.',NULL,0,0);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_book`
--

LOCK TABLES `user_book` WRITE;
/*!40000 ALTER TABLE `user_book` DISABLE KEYS */;
INSERT INTO `user_book` VALUES (16,1),(19,1),(23,1),(24,1),(13,2),(15,2),(19,2),(24,2),(26,2),(1,3),(2,3),(11,3),(16,3),(22,3),(24,3),(25,3),(27,3),(22,4),(23,4),(25,4),(19,5),(13,6),(15,6),(27,6),(1,7),(11,7),(16,7),(22,7),(23,7),(2,8),(11,8),(25,9),(26,9),(2,10),(1,11),(15,11),(26,11),(27,11),(13,12);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_movie`
--

LOCK TABLES `user_movie` WRITE;
/*!40000 ALTER TABLE `user_movie` DISABLE KEYS */;
INSERT INTO `user_movie` VALUES (15,1),(19,1),(24,1),(1,2),(2,2),(13,2),(15,2),(19,2),(24,2),(22,3),(23,3),(25,3),(26,3),(11,4),(22,4),(23,4),(25,4),(26,4),(27,4),(16,5),(24,5),(1,6),(11,6),(28,6),(2,7),(13,7),(16,7),(22,7),(27,7),(28,7),(11,8),(15,9),(16,9),(26,9),(28,9),(1,10),(2,10),(23,10),(25,10),(16,11),(13,12),(19,12),(27,12);
/*!40000 ALTER TABLE `user_movie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-22 14:59:49
