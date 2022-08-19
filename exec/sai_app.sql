-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
--
-- Host: localhost    Database: sai_app
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `campus`
--

DROP TABLE IF EXISTS `campus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campus` (
  `campus_id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `class` int DEFAULT NULL,
  PRIMARY KEY (`campus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campus`
--

LOCK TABLES `campus` WRITE;
/*!40000 ALTER TABLE `campus` DISABLE KEYS */;
INSERT INTO `campus` VALUES (1,'서울',NULL),(2,'서울',1),(3,'서울',2),(4,'서울',3),(5,'서울',4),(6,'서울',5),(7,'서울',6),(8,'서울',7),(9,'서울',8),(10,'서울',9),(11,'서울',10),(12,'서울',11),(13,'서울',12),(14,'서울',13),(15,'서울',14),(16,'서울',15),(17,'서울',16),(18,'서울',17),(19,'서울',18),(20,'서울',19),(21,'서울',20),(22,'광주',NULL),(23,'광주',1),(24,'광주',2),(25,'광주',3),(26,'광주',4),(27,'광주',5),(28,'광주',6),(29,'부울경',NULL),(30,'부울경',1),(31,'부울경',2),(32,'부울경',3),(33,'부울경',4),(34,'부울경',5),(35,'대전',NULL),(36,'대전',1),(37,'대전',2),(38,'대전',3),(39,'대전',4),(40,'대전',5),(41,'대전',6),(42,'대전',7),(43,'대전',8),(44,'구미',NULL),(45,'구미',1),(46,'구미',2),(47,'구미',3),(48,'구미',4),(49,'구미',5),(50,'구미',6);
/*!40000 ALTER TABLE `campus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custom_interview_question`
--

DROP TABLE IF EXISTS `custom_interview_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom_interview_question` (
  `custom_interview_question_id` bigint NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`custom_interview_question_id`),
  KEY `FK61wtijxxbebjxmb0j5kvw22p` (`member_id`),
  CONSTRAINT `FK61wtijxxbebjxmb0j5kvw22p` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_interview_question`
--

LOCK TABLES `custom_interview_question` WRITE;
/*!40000 ALTER TABLE `custom_interview_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `custom_interview_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise`
--

DROP TABLE IF EXISTS `enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enterprise` (
  `enterprise_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`enterprise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise`
--

LOCK TABLES `enterprise` WRITE;
/*!40000 ALTER TABLE `enterprise` DISABLE KEYS */;
INSERT INTO `enterprise` VALUES (1,'네이버(주)'),(2,'카카오(주)'),(3,'(주)다우기술'),(4,'(주)빗썸홀딩스'),(5,'(주)케이티씨에스'),(6,'메가존(주)'),(7,'네이버클라우드(주)'),(8,'라인플러스(주)'),(9,'엔에이치엔한국사이버결제(주)'),(10,'(주)카카오모빌리티'),(11,'나이스평가정보(주)'),(12,'(주)야놀자'),(13,'(주)코스콤'),(14,'네이버웹툰(유)'),(15,'(주)케이지모빌리언스'),(16,'구글코리아(유)'),(17,'(주)연합뉴스'),(18,'이데일리(주)'),(19,'(주)여기어때컴퍼니'),(20,'카페24(주)'),(21,'(주)드림어스컴퍼니'),(22,'(주)헥토이노베이션'),(23,'(주)가비아'),(24,'한국인터넷진흥원'),(25,'(주)브랜드엑스코퍼레이션'),(26,'(주)이엔코퍼레이션'),(27,'(주)와이지플러스'),(28,'(주)이머니'),(29,'인포뱅크(주)'),(30,'(주)에스비에스에이앤티'),(31,'(주)사람인에이치알'),(32,'한일네트웍스(주)'),(33,'(주)키다리스튜디오'),(34,'한국평가데이터(주)'),(35,'(주)헥토파이낸셜'),(36,'(주)알스퀘어'),(37,'블룸버그코리아(유)'),(38,'(주)더플랜코퍼레이션'),(39,'(주)천재교육'),(40,'GS건설(주)'),(41,'유빈스(주)'),(42,'(주)예가람저축은행'),(43,'(주)코리아써키트'),(44,'(주)하나금융티아이'),(45,'현대건설'),(46,'(주)현대홈쇼핑'),(47,'롯데캐피탈(주)'),(48,'KB생명보험(주)'),(49,'제일약품'),(50,'현대오토에버(주)'),(51,'금호건설(주)'),(52,'한국투자증권(주)'),(53,'대상정보기술(주)'),(54,'GS네오텍(주)'),(55,'(주)플그림'),(56,'쿠팡 주식회사'),(57,'롯데멤버스(주)'),(58,'브이피(주)'),(59,'플레이디'),(60,'한화테크윈(주)'),(61,'삼성물산'),(62,'(주)LF'),(63,'(주)신세계아이앤씨'),(64,'(주)포스코'),(65,'교보핫트랙스(주)'),(66,'현대자동차(주)'),(67,'(주)한샘'),(68,'SM그룹'),(69,'엠코테크놀로지'),(70,'우리은행-우리에프아이에스'),(71,'기업은행'),(72,'KEB하나은행'),(73,'SC제일은행'),(74,'KB국민은행-KB데이터시스템'),(75,'신한은행-신한DS'),(76,'외환은행'),(77,'한국시티은행'),(78,'외환은행'),(79,'카카오엔터프라이즈'),(80,'LG CNS'),(81,'삼성SDS'),(82,'두산디지털이노베이션'),(83,'SK C&C'),(84,'CJ올리브네트웍스'),(85,'티맥스소프트'),(86,'현대IT&E'),(87,'현대HDS'),(88,'농협정보시스템'),(89,'미라콤아이앤씨'),(90,'IBK시스템'),(91,'코오롱베니트'),(92,'한화시스템/ICT'),(93,'교보정보통신'),(94,'KT DS'),(95,'LS아이티씨'),(96,'DB아이앤씨'),(97,'포스코ICT'),(98,'아시아나IDT'),(99,'한진정보통신'),(100,'티시스'),(101,'GS ITM'),(102,'메가존클라우드'),(103,'베스핀글로벌'),(104,'NDS'),(105,'비즈테크파트너스'),(106,'AKIS'),(107,'쌍용정보통신'),(108,'메타넷대우정보'),(109,'동국시스템즈'),(110,'동양시스템즈'),(111,'넷마블(주)'),(112,'(주)엔씨소프트'),(113,'(주)우아한형제들'),(114,'(주)넥슨코리아'),(115,'엔에이치엔(주)'),(116,'(주)크래프톤'),(117,'(주)카카오엔터테인먼트'),(118,'(주)지마켓'),(119,'(주)카카오게임즈'),(120,'(주)케이지이니시스'),(121,'한국오라클(유)'),(122,'(주)네오플'),(123,'(주)더블유게임즈'),(124,'(주)스마일게이트엔터테인먼트'),(125,'한국아이비엠(주)'),(126,'(주)컴투스'),(127,'(유)시높시스코리아'),(128,'(주)골프존'),(129,'(주)더블다운인터액티브'),(130,'(주)그라비티'),(131,'(주)펄어비스'),(132,'에스에이피코리아(주)'),(133,'(주)한글과컴퓨터'),(134,'라이엇게임즈코리아(유)'),(135,'(주)지어소프트'),(136,'데브시스터즈(주)'),(137,'(주)코리아센터'),(138,'(주)위메이드'),(139,'유비벨록스(주)'),(140,'(주)다날'),(141,'(주)웹젠'),(142,'(주)아프리카티비'),(143,'(주)네오위즈홀딩스'),(144,'(주)네오위즈'),(145,'(주)지니뮤직'),(146,'(주)하이퍼커넥트'),(147,'(주)위버스컴퍼니'),(148,'(주)라이온하트스튜디오'),(149,'제니스앤컴퍼니(주)'),(150,'동국시스템즈(주)'),(151,'에스케이엠앤서비스(주)'),(152,'팅크웨어(주)'),(153,'(주)스마일게이트메가포트'),(154,'(주)티머니'),(155,'(주)에프에스엔'),(156,'(주)비트망고'),(157,'(주)사삼구구코리아'),(158,'(주)안랩'),(159,'리디(주)'),(160,'(주)엔드림'),(161,'(주)조이시티'),(162,'젬텍(주)'),(163,'코나아이(주)'),(164,'(주)다나와'),(165,'(주)아이비케이시스템'),(166,'(주)드림시큐리티'),(167,'다쏘시스템코리아(주)'),(168,'(주)아이지에이웍스'),(169,'(주)카카오브이엑스'),(170,'네이버파이낸셜(주)'),(171,'네이버클라우드(주)'),(172,'라인플러스(주)'),(173,'아이피엑스(주)'),(174,'엔테크서비스(주)'),(175,'웍스모바일(주)'),(176,'라인비즈플러스(주)'),(177,'(주)그린웹서비스'),(178,'네이버제트(주)'),(179,'라인게임즈(주)'),(180,'라인파이낸셜플러스(주)'),(181,'스노우(주)'),(182,'네이버랩스(주)'),(183,'삼성생명보험(주)'),(184,'삼성물산(주)'),(185,'삼성디스플레이(주)'),(186,'삼성화재해상보험(주)'),(187,'삼성에스디에스(주)'),(188,'삼성SDI(주)'),(189,'삼성증권(주)'),(190,'삼성증권(주)'),(191,'삼성전기(주)'),(192,'삼성엔지니어링(주)'),(193,'삼성중공업(주)'),(194,'삼성전자판매(주)'),(195,'(주)호텔신라'),(196,'삼성카드(주)'),(197,'삼성전자로지텍(주)'),(198,'삼성전자서비스(주)'),(199,'삼성바이오로직스(주)'),(200,'삼성바이오에피스(주)'),(201,'에스티엠(주)'),(202,'에이치디씨신라면세점(주)'),(203,'삼성메디슨(주)'),(204,'(주)멀티캠퍼스'),(205,'삼성자산운용(주)'),(206,'(주)삼우종합건축사사무소'),(207,'(주)대한항공'),(208,'(주)한진'),(209,'(주)한진칼'),(210,'한국공항(주)'),(211,'(주)진에어'),(212,'한진부산컨테이너터미널(주)'),(213,'한진정보통신(주)'),(214,'(주)칼호텔네트워크'),(215,'(주)칼호텔네트워크'),(216,'한진인천컨테이너터미널(주)'),(217,'서울복합물류프로젝트금융투자(주)'),(218,'정석기업(주)'),(219,'(주)당근마켓'),(220,'네오사피엔스'),(221,'OK데이터시스템'),(222,'(주)머큐리'),(223,'(주)유라'),(224,'(주)와일리'),(225,'테크니컬러로키아 유한회사'),(226,'인바디'),(227,'주식회사 파수'),(228,'(주)엘젠'),(229,'마인즈랩'),(230,'딥브루닷'),(231,'(주)쥬비스다이어트'),(232,'동양시스템(주)'),(233,'아이와이씨엔씨(주)'),(234,'로지스팟'),(235,'코멘토'),(236,'와이즈와이어즈'),(237,'주식회사 쏘카'),(238,'(주)플랜아이'),(239,'일동이커머스'),(240,'에스에스엠케이(주)'),(241,'(주)이마트'),(242,'(주)신세계'),(243,'(주)신세계디에프'),(244,'(주)에스씨케이컴퍼니'),(245,'(주)이마트24'),(246,'(주)에스에스지닷컴'),(247,'(주)신세계인터내셔날'),(248,'(주)조선호텔앤리조트'),(249,'(주)신세계라이브쇼핑'),(250,'해피해킹'),(251,'삼성전자(주)');
/*!40000 ALTER TABLE `enterprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interested_enterprise`
--

DROP TABLE IF EXISTS `interested_enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interested_enterprise` (
  `interested_enterprise_id` bigint NOT NULL AUTO_INCREMENT,
  `enterprise_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`interested_enterprise_id`),
  KEY `FKhup2dxgpof3af9nybv9f0d6bc` (`enterprise_id`),
  KEY `FKcx74o0u0pjxrjo2f1j9mevkl9` (`member_id`),
  CONSTRAINT `FKcx74o0u0pjxrjo2f1j9mevkl9` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKhup2dxgpof3af9nybv9f0d6bc` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprise` (`enterprise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interested_enterprise`
--

LOCK TABLES `interested_enterprise` WRITE;
/*!40000 ALTER TABLE `interested_enterprise` DISABLE KEYS */;
INSERT INTO `interested_enterprise` VALUES (1,81,1),(2,1,1),(3,2,1),(6,251,2),(7,1,2);
/*!40000 ALTER TABLE `interested_enterprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interested_job`
--

DROP TABLE IF EXISTS `interested_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interested_job` (
  `interested_job_id` bigint NOT NULL AUTO_INCREMENT,
  `job_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`interested_job_id`),
  KEY `FKau67i9a202wn1690m5quaamfx` (`job_id`),
  KEY `FKbygu9pbky6r8pe4xmtemlf2ni` (`member_id`),
  CONSTRAINT `FKau67i9a202wn1690m5quaamfx` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`),
  CONSTRAINT `FKbygu9pbky6r8pe4xmtemlf2ni` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interested_job`
--

LOCK TABLES `interested_job` WRITE;
/*!40000 ALTER TABLE `interested_job` DISABLE KEYS */;
INSERT INTO `interested_job` VALUES (1,6,1),(2,29,1),(3,5,1),(7,6,2),(8,16,2),(9,10,2);
/*!40000 ALTER TABLE `interested_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interview_info`
--

DROP TABLE IF EXISTS `interview_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interview_info` (
  `interview_info_id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `feedback_complete` varchar(255) DEFAULT NULL,
  `feedback_request` varchar(255) DEFAULT NULL,
  `interview_date` date DEFAULT NULL,
  `consultant_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`interview_info_id`),
  KEY `FK790vegirrpnrrmhksrn9olmgx` (`consultant_id`),
  KEY `FKalmjd4df73cxpg8b3mhuqj2de` (`student_id`),
  CONSTRAINT `FK790vegirrpnrrmhksrn9olmgx` FOREIGN KEY (`consultant_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKalmjd4df73cxpg8b3mhuqj2de` FOREIGN KEY (`student_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview_info`
--

LOCK TABLES `interview_info` WRITE;
/*!40000 ALTER TABLE `interview_info` DISABLE KEYS */;
INSERT INTO `interview_info` VALUES (1,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','1:1 모의 면접','면접 준비 하고 싶습니다.','FALSE','TRUE','2022-08-17',2,1),(2,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','1:1 모의 면접','삼성전자 뿌시고 싶습니다!','FALSE','TRUE','2022-08-18',3,2),(3,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','1:1 모의 면접','면접 준비 하고 싶습니다.','TRUE','TRUE','2022-08-18',2,1),(4,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','1:1 모의 면접','삼성전자 뿌시고 싶습니다!','TRUE','TRUE','2022-08-18',3,2),(5,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','1:1 모의 면접','면접 준비 하고 싶습니다.','FALSE','FALSE','2022-08-18',2,1),(6,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','1:1 모의 면접','삼성전자 뿌시고 싶습니다!','FALSE','FALSE','2022-08-18',3,2);
/*!40000 ALTER TABLE `interview_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interview_question`
--

DROP TABLE IF EXISTS `interview_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interview_question` (
  `interview_question_id` bigint NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `question_detail_type` varchar(255) DEFAULT NULL,
  `question_type` varchar(255) DEFAULT NULL,
  `tts` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`interview_question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview_question`
--

LOCK TABLES `interview_question` WRITE;
/*!40000 ALTER TABLE `interview_question` DISABLE KEYS */;
INSERT INTO `interview_question` VALUES (1,'지원 동기가 무엇인가요?','common','personality','지원 동기가 무엇인가요?'),(2,'성격의 장점과 단점은 무엇이 있나요?','common','personality','성격의 장점과 단점은 무엇이 있나요?'),(3,'같이 일하고 싶은/싫은 스타일의 사람은 누구인가요?','common','personality','같이 일하고 싶은 또는 싫은 스타일의 사람은 누구인가요?'),(4,'인생에서 가장 열심히 했던 일이나 순간은 언제인가요?','common','personality','인생에서 가장 열심히 했던 일이나 순간은 언제인가요?'),(5,'간단한 자기소개 부탁드립니다.','common','personality','간단한 자기소개 부탁드립니다.'),(6,'저희 회사에 지원한 동기가 무엇인가요?','common','personality','저희 회사에 지원한 동기가 무엇인가요?'),(7,'자신의 능력 밖을 벗어난 업무가 주어진다면 어떻게 하겠습니까?','common','personality','자신의 능력 밖을 벗어난 업무가 주어진다면 어떻게 하겠습니까?'),(8,'입사 후 포부가 무엇입니까?','common','personality','입사 후 포부가 무엇입니까?'),(9,'당신에게 일이 왜 중요합니까?','common','personality','당신에게 일이 왜 중요합니까?'),(10,'직장은 어떤 면을 보고 선택합니까?','common','personality','직장은 어떤 면을 보고 선택합니까?'),(11,'인생에서 가장 필요한 사항은 무엇이라 생각하나요?','common','personality','인생에서 가장 필요한 사항은 무엇이라 생각하나요?'),(12,'본인이 리더로 추진했던 일이 있습니까? 있다면 어떤 성과가 나왔는지 말해보세요.','common','personality','본인이 리더로 추진했던 일이 있습니까? 있다면 어떤 성과가 나왔는지 말해보세요.'),(13,'회사 근무를 하면서 가장 중요하다고 생각하는 것은 무엇입니까?','common','personality','회사 근무를 하면서 가장 중요하다고 생각하는 것은 무엇입니까?'),(14,'본인은 따라가는 스타일입니까, 아니면 주도하는 스타일입니까?','common','personality','본인은 따라가는 스타일입니까? 아니면 주도하는 스타일입니까?'),(15,'자신의 능력 밖을 벗어난 업무가 주어진다면 어떻게 하겠습니까?','common','personality','자신의 능력 밖을 벗어난 업무가 주어진다면 어떻게 하겠습니까?'),(16,'어떤 일에 적극적으로 임한 순간은 언제였습니까?','common','personality','어떤 일에 적극적으로 임한 순간은 언제였습니까?'),(17,'어떤 경영 스타일일때 자신의 능력이 최대가 되나요?','common','personality','어떤 경영 스타일일때 자신의 능력이 최대가 되나요?'),(18,'상사의 말이 확실히 틀렸을 때는 어떻게 할 것인가','common','personality','상사의 말이 확실히 틀렸을 때는 어떻게 할 것인가'),(19,'입사 후 회사와 맞지 않는다면 어떻게 하시겠습니까?','common','personality','입사 후 회사와 맞지 않는다면 어떻게 하시겠습니까?'),(20,'어떤 상황에서 스트레스를 받나요?','common','personality','어떤 상황에서 스트레스를 받나요?'),(21,'본인의 업무스타일은 어떤 유형인가요?','common','personality','본인의 업무스타일은 어떤 유형인가요?'),(22,'상사와 의견이 다를 때는 어떻게 대처할 것입니까?','common','personality','상사와 의견이 다를 때는 어떻게 대처할 것입니까?'),(23,'상사가 부당한 업무지시를 시킨다면 어떻게 할 것입니까?','common','personality','상사가 부당한 업무지시를 시킨다면 어떻게 할 것입니까?'),(24,'노래방에서 몇 시간이나 놀 수 있습니까?','common','personality','노래방에서 몇 시간이나 놀 수 있습니까?'),(25,'단체활동에서 의견 충돌이 일어날 경우 어떻게 해결하겠습니까?','common','personality','단체활동에서 의견 충돌이 일어날 경우 어떻게 해결하겠습니까?'),(26,'대인관계에서 가장 중요하게 생각하는 것은 무엇입니까?','common','personality','대인관계에서 가장 중요하게 생각하는 것은 무엇입니까?'),(27,'하고싶지 않았던 부서에 배정되도 괜찮은가?','common','personality','하고싶지 않았던 부서에 배정되도 괜찮은가?'),(28,'인생에서 가장 행복했던 경험이 무엇인가요?','common','personality','인생에서 가장 행복했던 경험이 무엇인가요?'),(29,'존경하는 인물이 누구인가요? 그 이유는 무엇인가요?','common','personality','존경하는 인물이 누구인가요? 그 이유는 무엇인가요?'),(30,'남들이 해보지 않은 특별한 경험을 해 본적이 있나요?','common','personality','남들이 해보지 않은 특별한 경험을 해 본적이 있나요?'),(31,'최근에 읽은 기사가 있나요? 어떤 내용인가요?','common','personality','최근에 읽은 기사가 있나요? 어떤 내용인가요?'),(32,'트러블 슈팅 경험이 있으신가요?','common','job','트러블 슈팅 경험이 있으신가요?'),(33,'어떤 언어를 가장 많이 사용하시나요? 그 이유는 무엇인가요?','common','job','어떤 언어를 가장 많이 사용하시나요? 그 이유는 무엇인가요?'),(34,'좋은 소프트웨어란 무엇이라고 생각하나요?','common','job','좋은 소프트웨어란 무엇이라고 생각하나요?'),(35,'어떤 개발자가 되고 싶으신가요?','common','job','어떤 개발자가 되고 싶으신가요?'),(36,'지금까지 어떤 프로젝트를 해보셨나요?','common','job','지금까지 어떤 프로젝트를 해보셨나요?'),(37,'전에 진행했던 프로젝트의 아키텍처를 설명해 보세요.','common','job','전에 진행했던 프로젝트의 아키텍처를 설명해 보세요.'),(38,'자신이 제출한 포트폴리오를 설명해 보세요.','common','job','자신이 제출한 포트폴리오를 설명해 보세요.'),(39,'사용할 줄 아는 프로그래밍 언어는 어떤 것들이 있으며 얼마나 능숙하나요?','common','job','사용할 줄 아는 프로그래밍 언어는 어떤 것들이 있으며 얼마나 능숙하나요?'),(40,'queue에대해 설명해 보세요.','common','job','큐에대해 설명해 보세요.'),(41,'본인이 사용할 줄 아는 언어들의 차이점은 무엇인가요?','common','job','본인이 사용할 줄 아는 언어들의 차이점은 무엇인가요?'),(42,'프로세스와 스레드의 차이점은 무엇인가요?','common','job','프로세스와 스레드의 차이점은 무엇인가요?'),(43,'본인이 지원한 job가 무슨 일을 한다고 생각하나요?','common','job','본인이 지원한 job가 무슨 일을 한다고 생각하나요?'),(44,'업데이트한 내용에 큰 문제가 생겼다면 어떻게 할 것인가?','common','job','업데이트한 내용에 큰 문제가 생겼다면 어떻게 할 것인가?'),(45,'http와 https의 차이에 대해서 설명해보세요.','common','job','에이치티티피와 에이치티티피에스의 차이에 대해서 설명해보세요.'),(46,'GET방식과 POST방식의 차이에 대하여 설명하세요.','common','job','겟방식과 포스트방식의 차이에 대하여 설명하세요.'),(47,'컴포넌트와 모듈의 차이를 설명해보세요','common','job','컴포넌트와 모듈의 차이를 설명해보세요'),(48,'객체 지향과 절차지향의 차이점을 설명해 보세요.','common','job','객체 지향과 절차지향의 차이점을 설명해 보세요.'),(49,'객체지향 언어의 특징을 설명해보세요.','common','job','객체지향 언어의 특징을 설명해보세요.'),(50,'오버라이딩 오버로딩의 차이점과 특징에대해 설명해 보세요.','common','job','오버라이딩 오버로딩의 차이점과 특징에대해 설명해 보세요.'),(51,'교육 프로그램에 참가하게 된 계기가 무엇인가요?','common','job','교육 프로그램에 참가하게 된 계기가 무엇인가요?'),(52,'어떤 서비스를 개발하고 싶은가요?','common','job','어떤 서비스를 개발하고 싶은가요?'),(53,'회사 기술 스택에 맞추어 단기간 내에 언어와 프레임워크를 학습해야 할 때, 어떻게 공부하고 해결할 것인가?','common','job','회사 기술 스택에 맞추어 단기간 내에 언어와 프레임워크를 학습해야 할 때, 어떻게 공부하고 해결할 것인가?'),(54,'개발에 관심을 가지게 된 계기가 무엇인가요?','common','job','개발에 관심을 가지게 된 계기가 무엇인가요?'),(55,'비IT 동료와 효과적으로 의사소통하려면 어떻게 해야 합니까? ','common','job','비아이티 동료와 효과적으로 의사소통하려면 어떻게 해야 합니까? '),(56,'Stack 과 Queue에대해 설명해주세요.','common','job','스택과 큐에대해 설명해주세요.'),(57,'좋은 코드란 무엇이라 생각하시나요?','common','job','좋은 코드란 무엇이라 생각하시나요?'),(58,'개발 능력 향상을 위해 어떤 것을 하고 계신가요?','common','job','개발 능력 향상을 위해 어떤 것을 하고 계신가요?'),(59,'추상화에 대해 설명해주세요.','Backend','job','추상화에 대해 설명해주세요.'),(60,'MVC패턴에 대해 설명해주세요','Backend','job','엠브이씨패턴에 대해 설명해주세요'),(61,'세션과 쿠키의 차이점에대해 설명해주세요.','Backend','job','세션과 쿠키의 차이점에대해 설명해주세요.'),(62,'Spring DI에 대해 설명해주세요.','Backend','job','스프링 디아이에 대해 설명해주세요.'),(63,'ORM에 대해 설명해 보세요','Backend','job','오알엠에 대해 설명해 보세요'),(64,'데이터베이스 옵티마이저에 대해 설명해주세요','Backend','job','데이터베이스 옵티마이저에 대해 설명해주세요'),(65,'JPA를 사용하면 좋은점이 무엇인가요?','Backend','job','제이피에이를 사용하면 좋은점이 무엇인가요?'),(66,'JWT에 대해 설명해주세요, 사용하면 장점과 단점?','Backend','job','제이떠블유티에 대해 설명해주세요, 사용하면 장점과 단점?'),(67,'Enum 사용해보셨나요? Enum이란 무엇인가요?','Backend','job','이넘을 사용해보셨나요? 이넘이란 무엇인가요?'),(68,'REST API란? REST API 명령어(CRUD)에대해 아시는것 설명해주세요.','Backend','job','레스트 에이피아이란? 레스트 에이피아이 명령어에대해 아시는것 설명해주세요.'),(69,'HTTP METHOD에 대해 설명해보세요','Backend','job','에이치티티피 메서드에 대해 설명해보세요'),(70,'HTTP 상태코드에 대해 아는대로 말해보세요.','Backend','job','에이치티티피 상태코드에 대해 아는대로 말해보세요.'),(71,'DB에서 인덱스를 사용했을때 장점이 무엇이있나요?','Backend','job','디비에서 인덱스를 사용했을때 장점이 무엇이있나요?'),(72,'가비지 컬렉션에대해 설명해보세요.','Backend','job','가비지 컬렉션에대해 설명해보세요.'),(73,'브라우저 렌더링 과정을 설명해주세요.','Backend','job','브라우저 렌더링 과정을 설명해주세요.'),(74,'CORS는 무엇인지, 이를 처리해본 경험이 있다면 말씀해주세요.','Backend','job','씨오알에스는 무엇인지, 이를 처리해본 경험이 있다면 말씀해주세요.'),(75,'타입스크립트를 사용하는 이유는 무엇인가요?','Backend','job','타입스크립트를 사용하는 이유는 무엇인가요?'),(76,'브라우저는 어떻게 동작하는지 설명해주세요.','Backend','job','브라우저는 어떻게 동작하는지 설명해주세요.'),(77,'스프링프레임워크에대해 설명해보세요.','Backend','job','스프링프레임워크에대해 설명해보세요.'),(78,'RDBMS와 NOSQL의 차이에대해 설명해보세요.','Backend','job','알디비엠에스와 노에스큐엘의 차이에대해 설명해보세요.'),(79,'MyBatis의 장점에대해 설명해보세요.','Backend','job','마이바티스의 장점에대해 설명해보세요.'),(80,'싱글톤 패턴에 대해 설명해주세요','Backend','job','싱글톤 패턴에 대해 설명해주세요'),(81,'GET,POST를 어떻게 다르게 쓰는지 말씀해 주세요.','Frontend','job','겟 포스트를 어떻게 다르게 쓰는지 말씀해 주세요.'),(82,'호이스팅에 대해서 설명해보세요.','Frontend','job','호이스팅에 대해서 설명해보세요.'),(83,'this의 용법 아는대로 설명해주세요.','Frontend','job','디스의 용법 아는대로 설명해주세요.'),(84,'SPA에대해 설명해 주세요.','Frontend','job','에스피에이에대해 설명해 주세요.'),(85,'var let const 의 차이점에대해 설명해주세요.','Frontend','job','바 렛 콘스트 의 차이점에대해 설명해주세요.'),(86,'Promise와 Callback의 차이를 설명해주세요.','Frontend','job','프로미스와 콜백의 차이를 설명해주세요.'),(87,'async, await 사용방법을 설명해주세요.','Frontend','job','어싱크 어웨이트 사용방법을 설명해주세요.'),(88,'함수 선언형과 함수 표현식의 차이에 대해 설명해주세요.','Frontend','job','함수 선언형과 함수 표현식의 차이에 대해 설명해주세요.'),(89,'자바스크립트가 유동적인 언어인 이유는 무엇인가요?','Frontend','job','자바스크립트가 유동적인 언어인 이유는 무엇인가요?'),(90,'CSS에서 margin과 padding에 대해 설명해주세요.','Frontend','job','씨에스에스에서 마진과 패딩에 대해 설명해주세요.'),(91,'브라우저 저장소에는 무엇이 있고 그것들의 차이점을 설명해주세요.','Frontend','job','브라우저 저장소에는 무엇이 있고 그것들의 차이점을 설명해주세요.'),(92,'이벤트 버블링에 대해서 말씀해 주세요.','Frontend','job','이벤트 버블링에 대해서 말씀해 주세요.'),(93,'SASS, SCSS를 사용해본적이 있나요? 기존 CSS와 비교할 때 어떤면이 더 좋은가요?','Frontend','job','에스에이에스에스, 에스씨에스에스를 사용해본적이 있나요? 기존 씨에스에스와 비교할 때 어떤면이 더 좋은가요?'),(94,'Vue와 React의 차이는 무엇인가요?','Frontend','job','뷰와 리액트의 차이는 무엇인가요?'),(95,'Vue에서 양방향 데이터가 일어나는 원리에 대해서 설명해주세요.','Frontend','job','뷰에서 양방향 데이터가 일어나는 원리에 대해서 설명해주세요.'),(96,'MVVM모델에 대해서 설명해주세요.','Frontend','job','엠브이브이엠모델에 대해서 설명해주세요.'),(97,'ES6에서 Arrow 함수를 언제 쓰나요?','Frontend','job','이에스식스에서 에로우 함수를 언제 쓰나요?'),(98,'Javascript에서 this란 무엇인가요?','Frontend','job','자바스크립트에서 디스란 무엇인가요?'),(99,'Frond-End 성능 최적화란 무엇인가요? 성능 최적화 경험이 있다면 설명해 주세요.','Frontend','job','프론트엔드성능 최적화란 무엇인가요? 성능 최적화 경험이 있다면 설명해 주세요.'),(100,'CSR과 SSR의 차이점은 무엇인가요?','Frontend','job','씨에스알과 에스에스알의 차이점은 무엇인가요?'),(101,'주소창에 naver.com을 입력하면 어떤 일이 생기나요?','Frontend','job','주소창에 네이버 닷컴을 입력하면 어떤 일이 생기나요?'),(102,'AJAX에대해 설명해보세요.','Frontend','job','에이젝스에대해 설명해보세요.'),(103,'리플렉션이란 무엇인가요?(ios)','Android/iOS','job','리플렉션이란 무엇인가요?'),(104,'constraint layout에대해 설명해주세요.','Android/iOS','job','콘스트레인트 레이아웃에대해 설명해주세요.'),(105,'액티비티와 프래그먼트에 대해 설명해주세요.','Android/iOS','job','액티비티와 프래그먼트에 대해 설명해주세요.'),(106,'안드로이드 빌드 프로세스에대해 설명해주세요.','Android/iOS','job','안드로이드 빌드 프로세스에대해 설명해주세요.'),(107,'Bounds 와 Frame 의 차이점을 설명해주세요.','Android/iOS','job','바운즈와 프레임의 차이점을 설명해주세요.'),(108,'ARC란 무엇인지 설명해주세요.(ios)','Android/iOS','job','에이알씨란 무엇인지 설명해주세요.'),(109,'Copy On Write는 어떤 방식으로 동작하는지 설명해주세요.(ios)','Android/iOS','job','카피 온 라이트는 어떤 방식으로 동작하는지 설명해주세요.'),(110,'Optional 이란 무엇인지 설명해주세요.(ios)','Android/iOS','job','옵셔널이란 무엇인지 설명해주세요.'),(111,'Http Request Code 에 대해 아는대로 설명해 보세요.(ios)','Android/iOS','job','에이치티티피 리퀘스트 코드에 대해 아는대로 설명해 보세요.'),(112,'안드로이드 스튜디오의 Thread 에 대해 설명해보세요.','Android/iOS','job','안드로이드 스튜디오의 스레드에 대해 설명해보세요.'),(113,'Android Activity Life Cycle 에 대해 설명해보세요.','Android/iOS','job','안드로이드 액티비티 라이프 사이클에 대해 설명해보세요.'),(114,'안드로이드의 4대 컴포넌트에 대해서 설명해보세요.','Android/iOS','job','안드로이드의 사대 컴포넌트에 대해서 설명해보세요.'),(115,'안드로이드의 액티비티와 액티비티의 수명주기에 대해서 설명해보세요.','Android/iOS','job','안드로이드의 액티비티와 액티비티의 수명주기에 대해서 설명해보세요.'),(116,'Weak와 Strong에 대해 설명하세요.(ios)','Android/iOS','job','위크와 스트롱에 대해 설명하세요.'),(117,'Escaping Closure의 개념이 무엇인가요?(ios)','Android/iOS','job','이스케이핑 클로져의 개념이 무엇인가요?'),(118,'Swift에서 Class와 Struct의 차이는 무엇인가요?(ios)','Android/iOS','job','스위프트에서 클래스와 스트럭트의 차이는 무엇인가요?'),(119,'Autolayout Constraint의 Priority의 개념이 무엇이고, 어떤상황에 사용하나요?(ios)','Android/iOS','job','오토레이아웃 콘스트레인트의 프라이올리티의 개념이 무엇이고, 어떤상황에 사용하나요?'),(120,'안드로이드의 메니페이스 파일에 대해서 설명해보세요.','Android/iOS','job','안드로이드의 메니페이스 파일에 대해서 설명해보세요.'),(121,'코틀린의 장점에 대해 설명하세요.','Android/iOS','job','코틀린의 장점에 대해 설명하세요.'),(122,'안드로이드의 테스크란?','Android/iOS','job','안드로이드의 테스크란?'),(123,'고유값(eigen value)와 고유벡터(eigen vector)에 대해 설명해주세요. 그리고 왜 중요할까요?','Data Scientist','job','고유값과 고유벡터에 대해 설명해주세요. 그리고 왜 중요할까요?'),(124,'확률 모형과 확률 변수는 무엇일까요?','Data Scientist','job','확률 모형과 확률 변수는 무엇일까요?'),(125,'좋은 feature란 무엇인가요. 이 feature의 성능을 판단하기 위한 방법에는 어떤 것이 있나요?','Data Scientist','job','좋은 피쳐란 무엇인가요. 이 피쳐의 성능을 판단하기 위한 방법에는 어떤 것이 있나요?'),(126,'상관관계는 인과관계를 의미하지 않는다라는 말이 있습니다. 설명해주실 수 있나요?','Data Scientist','job','상관관계는 인과관계를 의미하지 않는다라는 말이 있습니다. 설명해주실 수 있나요?'),(127,'나만의 feature selection 방식을 설명해봅시다.','Data Scientist','job','나만의 피쳐 셀렉션방식을 설명해봅시다.'),(128,'아웃라이어의 판단하는 기준은 무엇인가요?','Data Scientist','job','아웃라이어의 판단하는 기준은 무엇인가요?'),(129,'평균(mean)과 중앙값(median)중에 어떤 케이스에서 뭐를 써야할까요?','Data Scientist','job','평균과 중앙값중에 어떤 케이스에서 뭐를 써야할까요?'),(130,'신뢰 구간의 정의는 무엇인가요?','Data Scientist','job','신뢰 구간의 정의는 무엇인가요?'),(131,'샘플링(Sampling)과 리샘플링(Resampling)에 대해 설명해주세요. 리샘플링은 무슨 장점이 있을까요?','Data Scientist','job','샘플링과 리샘플링에 대해 설명해주세요. 리샘플링은 무슨 장점이 있을까요?'),(132,'엔트로피(entropy)에 대해 설명해주세요.','Data Scientist','job','엔트로피에 대해 설명해주세요.'),(133,'회귀 / 분류시 알맞은 metric은 무엇일까요?','Data Scientist','job','회귀분류시 알맞은 메트릭은 무엇일까요?'),(134,'정규화를 왜 해야할까요? 정규화의 방법은 무엇이 있나요?','Data Scientist','job','정규화를 왜 해야할까요? 정규화의 방법은 무엇이 있나요?'),(135,'텍스트 더미에서 주제를 추출해야 합니다. 어떤 방식으로 접근해 나가시겠나요?','Data Scientist','job','텍스트 더미에서 주제를 추출해야 합니다. 어떤 방식으로 접근해 나가시겠나요?'),(136,'네트워크 관계를 시각화해야 할 경우 어떻게 해야할까요?','Data Scientist','job','네트워크 관계를 시각화해야 할 경우 어떻게 해야할까요?'),(137,'히스토그램의 가장 큰 문제는 무엇인가요?','Data Scientist','job','히스토그램의 가장 큰 문제는 무엇인가요?'),(138,'워드클라우드는 보기엔 예쁘지만 약점이 있습니다. 어떤 약점일까요?','Data Scientist','job','워드클라우드는 보기엔 예쁘지만 약점이 있습니다. 어떤 약점일까요?'),(139,'좋은 모델의 정의는 무엇일까요?','Data Scientist','job','좋은 모델의 정의는 무엇일까요?'),(140,'L1, L2 정규화에 대해 설명해주세요','Data Scientist','job','엘원 엘투 정규화에 대해 설명해주세요'),(141,'인덱스는 크게 Hash 인덱스와 B+Tree 인덱스가 있습니다. 이것은 무엇일까요?','Data Scientist','job','인덱스는 크게 해시인덱스와 비트리인덱스가 있습니다. 이것은 무엇일까요?'),(142,'딥러닝은 무엇인가요? 딥러닝과 머신러닝의 차이는?','Data Scientist','job','딥러닝은 무엇인가요? 딥러닝과 머신러닝의 차이는?'),(143,'DevOps를 설명해주세요.','DevOps','job','데브옵스를 설명해주세요.'),(144,'DevOps 구현을위한 모범 사례는 무엇인가요?','DevOps','job','데브옵스구현을위한 모범 사례는 무엇인가요?'),(145,'DevOps의 주요 구성 요소는 무엇입니까?','DevOps','job','데브옵스의 주요 구성 요소는 무엇입니까?'),(146,'지속적인 통합을 설명해주세요.','DevOps','job','지속적인 통합을 설명해주세요.'),(147,'프로젝트에서 DevOps를 구현해야 할 때 어떻게 접근 하시겠습니까?','DevOps','job','프로젝트에서 데브옵스를 구현해야 할 때 어떻게 접근 하시겠습니까?'),(148,'연속 테스트를 설명하세요.','DevOps','job','연속 테스트를 설명하세요.'),(149,'오늘날 업계에서 사용되는 10 대 DevOps 도구는 무엇입니까? 아는대로 말씀해주세요.','DevOps','job','오늘날 업계에서 사용되는 십대 데브옵스도구는 무엇입니까? 아는대로 말씀해주세요.'),(150,'DevOps에서 사용되는 스크립팅 도구는 무엇입니까?','DevOps','job','데브옵스에서 사용되는 스크립팅 도구는 무엇입니까?'),(151,'DevOps 성공을 위해 따랐던 몇 가지 지표를 설명하십시오.','DevOps','job','데브옵스성공을 위해 따랐던 몇 가지 지표를 설명하십시오.'),(152,'DevOps의 경력 관점에서 기대하는 것은 무엇입니까?','DevOps','job','데브옵스의 경력 관점에서 기대하는 것은 무엇입니까?');
/*!40000 ALTER TABLE `interview_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interview_video`
--

DROP TABLE IF EXISTS `interview_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interview_video` (
  `interview_video_id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `audio_name` varchar(255) DEFAULT NULL,
  `audio_url` varchar(255) DEFAULT NULL,
  `emotion_ratio` double DEFAULT NULL,
  `feedback` varchar(255) DEFAULT NULL,
  `stt` varchar(4000) DEFAULT NULL,
  `video_name` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `wrong_posture_count` bigint DEFAULT NULL,
  `interview_info_id` bigint DEFAULT NULL,
  `used_interview_question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`interview_video_id`),
  KEY `FKfalec3vfe4c9d8mjpyankbejk` (`interview_info_id`),
  KEY `FKq0buoux2ykni8frhrfwm4ygtu` (`used_interview_question_id`),
  CONSTRAINT `FKfalec3vfe4c9d8mjpyankbejk` FOREIGN KEY (`interview_info_id`) REFERENCES `interview_info` (`interview_info_id`),
  CONSTRAINT `FKq0buoux2ykni8frhrfwm4ygtu` FOREIGN KEY (`used_interview_question_id`) REFERENCES `used_interview_question` (`used_interview_question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview_video`
--

LOCK TABLES `interview_video` WRITE;
/*!40000 ALTER TABLE `interview_video` DISABLE KEYS */;
INSERT INTO `interview_video` VALUES (1,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','cee3137e-2939-46d9-8b4b-7724051abcaa.flac','https://storage.googleapis.com/sai-ssafy/cee3137e-2939-46d9-8b4b-7724051abcaa.flac',0.011755343998626029,'false','안녕하십니까 저는 이번 삼성전자에 지원한 지원자 신재서입니다 저는 삼성청년소프트웨어아카데미 다양한 프로젝트를 진행할 팀장으로써 팀원을 프로젝트를 진행하였으며 결과 프로젝트 감사합니다','cee3137e-2939-46d9-8b4b-7724051abcaa.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/cee3137e-2939-46d9-8b4b-7724051abcaa.mp4',2,1,1),(2,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','da93c637-6ebd-4aff-a754-8750553edcbc.flac','https://storage.googleapis.com/sai-ssafy/da93c637-6ebd-4aff-a754-8750553edcbc.flac',0.014164684955443395,'false','내 장점은 의사소통이다 팀원들과 의사소통을 원하나 하는 능력이 있습니다 단점은 너무 꼼꼼한 점입니다','da93c637-6ebd-4aff-a754-8750553edcbc.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/da93c637-6ebd-4aff-a754-8750553edcbc.mp4',2,1,2),(3,'2022-08-18 11:27:23.320555','2022-08-18 11:27:23.320555','cac4b40f-60d1-440c-a736-cb98c435ba7e.flac','https://storage.googleapis.com/sai-ssafy/cac4b40f-60d1-440c-a736-cb98c435ba7e.flac',0.03386581359192125,'false','구조를 모델 뷰 컨트롤러로 나누어 설계하는 방법을 말합니다 모든 데이터를 처리하는 영역이며 뷰는 사용자에게 보여질 화면을 처리하는 영역이고 컨트롤러는 사용자를 요청을 어떻게 처리할지를 결정하는 영역을 말합니다','cac4b40f-60d1-440c-a736-cb98c435ba7e.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/cac4b40f-60d1-440c-a736-cb98c435ba7e.mp4',2,2,3),(4,'2022-08-18 11:27:33.960537','2022-08-18 11:27:33.960537','4c93ea48-eb3d-417d-919d-e48fc713e9a1.flac','https://storage.googleapis.com/sai-ssafy/4c93ea48-eb3d-417d-919d-e48fc713e9a1.flac',0.028233498349533568,'false','삼성 청년소프트웨어아카데미에서 프로젝트를 진행하려 가장 힘들었던 경험은 프로젝트를 정해진 시간 내에 완료해야 하는 것이었습니다 단순히 공부만 할 때는 시간에 상관없이 목표한 기능을 만들어내지만 하면 됐지만 실제 프로젝트를 경험해보니 정해진 시간 내에 개발하는 것이 얼마나 어려운 일인지 깨닫게 되었습니다 처음에는 다양한 기능을 완벽하게 구현하는 것에만 집중하였습니다 그러다 보니 시간이 많이 부족했고 저희가 처음에 계획했던 기능들을 다 구현할 수 없었습니다 이점으로 프로젝트를 시간 내에 끝내는 것이 얼마나 어려운 일인지 깨닫게 되었습니다','4c93ea48-eb3d-417d-919d-e48fc713e9a1.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/4c93ea48-eb3d-417d-919d-e48fc713e9a1.mp4',2,2,4),(5,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','cee3137e-2939-46d9-8b4b-7724051abcaa.flac','https://storage.googleapis.com/sai-ssafy/cee3137e-2939-46d9-8b4b-7724051abcaa.flac',0.011755343998626029,'자기소개의 시작부터 중반까지는 굉장히 좋았습니다. 하지만 마무리 짓는 부분에서 너무 긴장한 탓인지 급하게 끝마치려는 느낌을 받았습니다. 이 부분만 보완하면 더 좋은 자기소개가 될 것 같습니다. ^^','안녕하십니까 저는 이번 삼성전자에 지원한 지원자 신재서입니다 저는 삼성청년소프트웨어아카데미 다양한 프로젝트를 진행할 팀장으로써 팀원을 프로젝트를 진행하였으며 결과 프로젝트 감사합니다','cee3137e-2939-46d9-8b4b-7724051abcaa.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/cee3137e-2939-46d9-8b4b-7724051abcaa.mp4',4,3,1),(6,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','da93c637-6ebd-4aff-a754-8750553edcbc.flac','https://storage.googleapis.com/sai-ssafy/da93c637-6ebd-4aff-a754-8750553edcbc.flac',0.014164684955443395,'본인의 프로젝트 경험에서 장점을 극대화한 점을 살릴 수 있다면 더 좋은 답변이 될 것 같습니다. ^^ 단점같은 경우도 단점을 극복해나가는 경험과 과정을 얘기할 수 있다면 더 좋은 답변이 되지 않을까 싶네요.','내 장점은 의사소통이다 팀원들과 의사소통을 원하나 하는 능력이 있습니다 단점은 너무 꼼꼼한 점입니다','da93c637-6ebd-4aff-a754-8750553edcbc.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/da93c637-6ebd-4aff-a754-8750553edcbc.mp4',4,3,2),(7,'2022-08-18 11:27:23.320555','2022-08-18 11:27:23.320555','cac4b40f-60d1-440c-a736-cb98c435ba7e.flac','https://storage.googleapis.com/sai-ssafy/cac4b40f-60d1-440c-a736-cb98c435ba7e.flac',0.03386581359192125,'MVC 패턴에 대해 각 구조별로 상세하게 잘 설명했습니다. 답변할 때의 시선처리와 대답하는 톤에만 신경을 잘 쓰면 더욱 우수한 답변이 될 것 같습니다. ^^','구조를 모델 뷰 컨트롤러로 나누어 설계하는 방법을 말합니다 모든 데이터를 처리하는 영역이며 뷰는 사용자에게 보여질 화면을 처리하는 영역이고 컨트롤러는 사용자를 요청을 어떻게 처리할지를 결정하는 영역을 말합니다','cac4b40f-60d1-440c-a736-cb98c435ba7e.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/cac4b40f-60d1-440c-a736-cb98c435ba7e.mp4',4,4,3),(8,'2022-08-18 11:27:33.960537','2022-08-18 11:27:33.960537','4c93ea48-eb3d-417d-919d-e48fc713e9a1.flac','https://storage.googleapis.com/sai-ssafy/4c93ea48-eb3d-417d-919d-e48fc713e9a1.flac',0.028233498349533568,'본인의 프로젝트 경험을 바탕으로 답변한 점은 우수했습니다. 프로젝트 진행 중 힘들었던 경험도 잘 녹여낸 것 같습니다. 하지만 해결해나가는 과정에 대한 답변이 조금 미흡했던 것 같습니다. 이 부분만 보완하면 충분히 좋은 답변이 될 것 같습니다. ^^','삼성 청년소프트웨어아카데미에서 프로젝트를 진행하려 가장 힘들었던 경험은 프로젝트를 정해진 시간 내에 완료해야 하는 것이었습니다 단순히 공부만 할 때는 시간에 상관없이 목표한 기능을 만들어내지만 하면 됐지만 실제 프로젝트를 경험해보니 정해진 시간 내에 개발하는 것이 얼마나 어려운 일인지 깨닫게 되었습니다 처음에는 다양한 기능을 완벽하게 구현하는 것에만 집중하였습니다 그러다 보니 시간이 많이 부족했고 저희가 처음에 계획했던 기능들을 다 구현할 수 없었습니다 이점으로 프로젝트를 시간 내에 끝내는 것이 얼마나 어려운 일인지 깨닫게 되었습니다','4c93ea48-eb3d-417d-919d-e48fc713e9a1.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/4c93ea48-eb3d-417d-919d-e48fc713e9a1.mp4',4,4,4),(9,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','cee3137e-2939-46d9-8b4b-7724051abcaa.flac','https://storage.googleapis.com/sai-ssafy/cee3137e-2939-46d9-8b4b-7724051abcaa.flac',0.011755343998626029,'false','안녕하십니까 저는 이번 삼성전자에 지원한 지원자 신재서입니다 저는 삼성청년소프트웨어아카데미 다양한 프로젝트를 진행할 팀장으로써 팀원을 프로젝트를 진행하였으며 결과 프로젝트 감사합니다','cee3137e-2939-46d9-8b4b-7724051abcaa.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/cee3137e-2939-46d9-8b4b-7724051abcaa.mp4',2,5,1),(10,'2022-08-18 11:27:10.279370','2022-08-18 11:27:10.279370','da93c637-6ebd-4aff-a754-8750553edcbc.flac','https://storage.googleapis.com/sai-ssafy/da93c637-6ebd-4aff-a754-8750553edcbc.flac',0.014164684955443395,'false','내 장점은 의사소통이다 팀원들과 의사소통을 원하나 하는 능력이 있습니다 단점은 너무 꼼꼼한 점입니다','da93c637-6ebd-4aff-a754-8750553edcbc.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/da93c637-6ebd-4aff-a754-8750553edcbc.mp4',2,5,2),(11,'2022-08-18 11:27:23.320555','2022-08-18 11:27:23.320555','cac4b40f-60d1-440c-a736-cb98c435ba7e.flac','https://storage.googleapis.com/sai-ssafy/cac4b40f-60d1-440c-a736-cb98c435ba7e.flac',0.03386581359192125,'false','구조를 모델 뷰 컨트롤러로 나누어 설계하는 방법을 말합니다 모든 데이터를 처리하는 영역이며 뷰는 사용자에게 보여질 화면을 처리하는 영역이고 컨트롤러는 사용자를 요청을 어떻게 처리할지를 결정하는 영역을 말합니다','cac4b40f-60d1-440c-a736-cb98c435ba7e.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/cac4b40f-60d1-440c-a736-cb98c435ba7e.mp4',2,6,3),(12,'2022-08-18 11:27:33.960537','2022-08-18 11:27:33.960537','4c93ea48-eb3d-417d-919d-e48fc713e9a1.flac','https://storage.googleapis.com/sai-ssafy/4c93ea48-eb3d-417d-919d-e48fc713e9a1.flac',0.028233498349533568,'false','삼성 청년소프트웨어아카데미에서 프로젝트를 진행하려 가장 힘들었던 경험은 프로젝트를 정해진 시간 내에 완료해야 하는 것이었습니다 단순히 공부만 할 때는 시간에 상관없이 목표한 기능을 만들어내지만 하면 됐지만 실제 프로젝트를 경험해보니 정해진 시간 내에 개발하는 것이 얼마나 어려운 일인지 깨닫게 되었습니다 처음에는 다양한 기능을 완벽하게 구현하는 것에만 집중하였습니다 그러다 보니 시간이 많이 부족했고 저희가 처음에 계획했던 기능들을 다 구현할 수 없었습니다 이점으로 프로젝트를 시간 내에 끝내는 것이 얼마나 어려운 일인지 깨닫게 되었습니다','4c93ea48-eb3d-417d-919d-e48fc713e9a1.mp4','https://sai-project.s3.ap-northeast-2.amazonaws.com/4c93ea48-eb3d-417d-919d-e48fc713e9a1.mp4',2,6,4);
/*!40000 ALTER TABLE `interview_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `job_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'IT교육'),(2,'게임개발'),(3,'기술지원'),(4,'데이터분석가'),(5,'데이터엔지니어'),(6,'백엔드/서버개발'),(7,'보안관제'),(8,'보안컨설팅'),(9,'앱개발'),(10,'웹개발'),(11,'웹마스터'),(12,'유지보수'),(13,'정보보안'),(14,'퍼블리셔'),(15,'검색엔진'),(16,'네트워크'),(17,'데이터라벨링'),(18,'데이터마이닝'),(19,'데이터시각화'),(20,'딥러닝'),(21,'머신러닝'),(22,'메타버스'),(23,'모델링'),(24,'모의해킹'),(25,'미들웨어'),(26,'반응형웹'),(27,'방화벽'),(28,'블록체인'),(29,'빅데이터'),(30,'프론트앤드'),(31,'웹기획-PM'),(32,'웹디자인'),(33,'웹퍼블리셔'),(34,'UI/UX 디자이너');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `member_status` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `year` int NOT NULL,
  `campus_id` bigint DEFAULT NULL,
  `profile_picture_id` bigint DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `UK_mbmcqelty0fbrvxp1q58dn57t` (`email`),
  KEY `FKfbgsnb791ubb18a2tnyep2m7g` (`campus_id`),
  KEY `FKhytgdv9wor19vc7xggy5wcb60` (`profile_picture_id`),
  CONSTRAINT `FKfbgsnb791ubb18a2tnyep2m7g` FOREIGN KEY (`campus_id`) REFERENCES `campus` (`campus_id`),
  CONSTRAINT `FKhytgdv9wor19vc7xggy5wcb60` FOREIGN KEY (`profile_picture_id`) REFERENCES `profile_picture` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2022-08-18 20:12:32.000000','2022-08-18 20:12:32.000000','1996-05-03','student1@sai.com','TRAINEE','심재서','$2a$10$u6s7WekPpq9Ih3yZ8MS.9uqmH4UqQDsVVtaARwD.LJ4nAx4qSK37i','01012341234',7,24,NULL),(2,'2022-08-18 20:12:32.000000','2022-08-18 11:14:14.573180','1995-05-30','student2@sai.com','TRAINEE','지근','$2a$10$u6s7WekPpq9Ih3yZ8MS.9uqmH4UqQDsVVtaARwD.LJ4nAx4qSK37i','01098765432',7,24,1),(3,'2022-08-18 20:12:32.000000','2022-08-18 20:12:32.000000','1988-02-16','consultant1@sai.com','CONSULTANT','김성재','$2a$10$u6s7WekPpq9Ih3yZ8MS.9uqmH4UqQDsVVtaARwD.LJ4nAx4qSK37i','01023452345',0,22,NULL),(4,'2022-08-18 20:12:32.000000','2022-08-18 20:12:32.000000','1987-03-06','consultant2@sai.com','CONSULTANT','류채윤','$2a$10$u6s7WekPpq9Ih3yZ8MS.9uqmH4UqQDsVVtaARwD.LJ4nAx4qSK37i','01034563456',0,22,NULL),(5,'2022-08-18 20:12:32.000000','2022-08-18 20:12:32.000000','1987-03-06','consultant3@sai.com','CONSULTANT','노승현','$2a$10$u6s7WekPpq9Ih3yZ8MS.9uqmH4UqQDsVVtaARwD.LJ4nAx4qSK37i','01056785678',0,22,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_roles`
--

DROP TABLE IF EXISTS `member_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_roles` (
  `member_member_id` bigint NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FKruptm2dtwl95mfks4bnhv828k` (`member_member_id`),
  CONSTRAINT `FKruptm2dtwl95mfks4bnhv828k` FOREIGN KEY (`member_member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_roles`
--

LOCK TABLES `member_roles` WRITE;
/*!40000 ALTER TABLE `member_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile_picture`
--

DROP TABLE IF EXISTS `profile_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile_picture` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `original_name` varchar(255) DEFAULT NULL,
  `size` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_picture`
--

LOCK TABLES `profile_picture` WRITE;
/*!40000 ALTER TABLE `profile_picture` DISABLE KEYS */;
INSERT INTO `profile_picture` VALUES (1,'2022-08-18 11:14:14.549436','2022-08-18 11:14:14.549436','image/jpeg','6fb6caae-b823-49f6-8b1e-15c89f88451c_profile.jpg','profile.jpg',619816);
/*!40000 ALTER TABLE `profile_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `schedule_id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `schedule_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `consultant_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `FKqjffgerda95vnpbkxcd8rraf3` (`consultant_id`),
  KEY `FKhs3sehek4rx2pgp85drtcii4n` (`student_id`),
  CONSTRAINT `FKhs3sehek4rx2pgp85drtcii4n` FOREIGN KEY (`student_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKqjffgerda95vnpbkxcd8rraf3` FOREIGN KEY (`consultant_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'practice','카카오 면접 대비',NULL,'2022-08-17','14:30:00',3,1),(2,'document','네이버 서류 마감',NULL,'2022-08-18','10:00:00',NULL,1),(3,'practice','쏘카 면접 대비',NULL,'2022-08-18','14:00:00',3,1),(4,'practice','토스 면접 대비',NULL,'2022-08-19','17:00:00',4,1),(5,'coding-test','쿠팡 코테',NULL,'2022-08-21','14:00:00',NULL,1),(6,'block','개인 일정',NULL,'2022-08-18','15:30:00',3,NULL),(7,'block','개인 일정',NULL,'2022-08-19','16:00:00',3,NULL),(8,'block','개인 일정',NULL,'2022-08-20','13:30:00',3,NULL),(9,'block','개인 일정',NULL,'2022-08-19','13:00:00',3,NULL),(10,'block','개인 일정',NULL,'2022-08-18','14:00:00',3,NULL),(11,'block','개인 일정',NULL,'2022-08-18','15:00:00',3,NULL),(12,'block','개인 일정',NULL,'2022-08-18','16:00:00',3,NULL),(13,'block','개인 일정',NULL,'2022-08-20','11:30:00',3,NULL),(14,'block','개인 일정',NULL,'2022-08-19','09:30:00',4,NULL),(15,'block','개인 일정',NULL,'2022-08-17','18:00:00',3,NULL),(16,'block','개인 일정',NULL,'2022-08-17','16:30:00',4,NULL),(17,'block','개인 일정',NULL,'2022-08-17','16:00:00',4,NULL),(18,'block','개인 일정',NULL,'2022-08-17','15:30:00',4,NULL),(19,'practice','삼성전자 면접 준비 관련',NULL,'2022-08-18','18:30:00',3,2),(20,'coding-test','네이버 코딩테스트',NULL,'2022-08-21','10:00:00',NULL,2),(21,'else','네이버 코딩테스트 준비 관련 상담 요청합니다.',NULL,'2022-08-19','14:00:00',3,2);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `used_interview_question`
--

DROP TABLE IF EXISTS `used_interview_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `used_interview_question` (
  `used_interview_question_id` bigint NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`used_interview_question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `used_interview_question`
--

LOCK TABLES `used_interview_question` WRITE;
/*!40000 ALTER TABLE `used_interview_question` DISABLE KEYS */;
INSERT INTO `used_interview_question` VALUES (1,'간단한 자기소개 부탁드립니다.'),(2,'성격의 장점과 단점은 무엇이 있나요?'),(3,'MVC패턴에 대해 설명해주세요'),(4,'프로젝트 진행 중 힘들었던 경험은 없었나요? 있었다면 어떻게 해결하셨나요?');
/*!40000 ALTER TABLE `used_interview_question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:17:19
